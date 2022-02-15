package com.iuc.requests.conf;

import com.iuc.requests.dao.Filiere;
import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.repository.FiliereRepository;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    em.setPackagesToScan(new String[] {"com.iuc.requests.dao"});
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

    @Autowired StaffRepository staffRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired FiliereRepository filiereRepository;

    public void populateFiliere() {
      Filiere filiereInformatique = new Filiere();
      filiereInformatique.setNom("INFORMATIQUE");
      filiereInformatique.setDescription("Filiere informatique");

      filiereRepository.save(filiereInformatique);
    }

    public void populateStaff() {

      Staff staff1 = new Staff();
      staff1.setNom("Dounkeu");
      staff1.setPrenom("Patrice");
      staff1.setPosteOccupe("Director");
      staff1.setMatricule("1DIUC2020");
      staff1.setEmail("dounkeu@iuc.com");
      staff1.setPassword("1234");
      staff1.setFiliere("INFORMATIQUE");

      Staff staff2 = new Staff();
      staff2.setNom("Tchiencheu");
      staff2.setPrenom("Dominique");
      staff2.setPosteOccupe("Chef Departement");
      staff2.setMatricule("1CIUC2024");
      staff2.setEmail("tchiencheu@iuc.com");
      staff2.setPassword("1234");
      staff2.setFiliere("INFORMATIQUE");

      staffRepository.save(staff1);
      staffRepository.save(staff2);
    }

    public void populateStudent() {

      Student student1 = new Student();
      student1.setNom("Oum Oum");
      student1.setPrenom("Loic");
      student1.setNiveau("1");
      student1.setMatricule("1DIUC2021");
      student1.setEmail("vimaltest1@gmail.com");

      student1.setPassword("1234");
      student1.setFiliere("INFORMATIQUE");

      Student student2 = new Student();
      student2.setNom("Ambassa");
      student2.setPrenom("George");
      student2.setNiveau("1");
      student2.setMatricule("1CIUC2021");
      student2.setEmail("ambassa@iuc.com");
      student2.setPassword("1234");
      student2.setFiliere("INFORMATIQUE");

      studentRepository.save(student1);
      studentRepository.save(student2);
    }

    public Student populateOneStudent() {

      Student student1 = new Student();
      student1.setNom("student-nom-test-1");
      student1.setPrenom("student-prenom-test-1");
      student1.setNiveau("1");
      student1.setMatricule("1DIUC2021");
      student1.setEmail("vimaltest1@gmail.com");
      student1.setPassword("1234");
      student1.setFiliere("INFORMATIQUE");
      return  studentRepository.save(student1);

    }

    public void resetStaff() {
      staffRepository.deleteAll();
    }

    public void resetStudent() {
      studentRepository.deleteAll();
    }
  }
}
