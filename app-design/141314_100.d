format 222

classcanvas 128100 class_ref 128386 // User
  classdiagramsettings member_max_width 0 end
  xyz 384 59 2000
end
classcanvas 128228 class_ref 128642 // Student
  classdiagramsettings member_max_width 0 end
  xyz 279 210 2000
end
classcanvas 128356 class_ref 128770 // Staff
  classdiagramsettings member_max_width 0 end
  xyz 504 216 2000
end
classcanvas 128484 class_ref 128898 // Request
  classdiagramsettings member_max_width 0 end
  xyz 377 433 2000
end
relationcanvas 128612 relation_ref 128100 // <generalisation>
  from ref 128228 z 2001 to ref 128100
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 128740 relation_ref 128228 // <generalisation>
  from ref 128356 z 2001 to ref 128100
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 128868 relation_ref 128356 // soumettre
  from ref 128228 z 2001 label "soumettre" italic max_width 255 xyz 300 352 2001 to ref 128484
  no_role_a no_role_b
  multiplicity_a_pos 377 412 3000 multiplicity_b_pos 301 301 3000
end
relationcanvas 128996 relation_ref 128484 // recevoir,traiter,approuver ou rejeter
  from ref 128356 z 2001 label "recevoir,traiter,approuver ou rejeter" italic max_width 255 xyz 505 342 2001 to ref 128484
  no_role_a no_role_b
  multiplicity_a_pos 451 412 3000 multiplicity_b_pos 511 289 3000
end
end
