package com.iuc.requests.conf;

import com.iuc.requests.dao.Filiere;
import com.iuc.requests.dao.Staff;
import com.iuc.requests.repository.FiliereRepository;
import com.iuc.requests.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.iuc.requests.repository")
@EnableTransactionManagement
public class H2JpaConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:iuc-request-db-test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.iuc.requests.dao" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        return hibernateProperties;
    }

    @Bean
    Populator populator() {
        return new Populator();
    }

    public static class Populator {

        @Autowired
        StaffRepository staffRepository;

        @Autowired
        FiliereRepository filiereRepository;

        public void populateFiliere() {
            Filiere filiere1 = new Filiere();
            filiere1.setNom("INFORMATIQUE");
            filiere1.setDescription("Filiere informatique");

            filiereRepository.save(filiere1);
        }

        public void populateStaff() {

            Staff staff1 = new Staff();
            staff1.setNom("staff-nom-test-1");
            staff1.setPrenom("staff-prenom-test-1");
            staff1.setPosteOccupe("staff-director-test-1");
            staff1.setMatricule("1DIUC2020");
            staff1.setEmail("staff-test-1@iuc.com");
            staff1.setPassword("1234");

            Staff staff2 = new Staff();
            staff2.setNom("staff-nom-test-2");
            staff2.setPrenom("staff-prenom-test-2");
            staff2.setPosteOccupe("staff-chef-dep-test-2");
            staff2.setMatricule("1CIUC2020");
            staff2.setEmail("staff-test-2@iuc.com");
            staff2.setPassword("1234");
            staff2.setFiliere("INFORMATIQUE");

            staffRepository.save(staff1);
            staffRepository.save(staff2);
        }

        public void resetStaff() {
            staffRepository.deleteAll();
        }
    }
}
