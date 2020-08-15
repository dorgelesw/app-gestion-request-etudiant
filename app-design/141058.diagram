format 222

classinstance 128002 class_ref 128386 // User
  name ""   xyz 49 14 2000 life_line_z 2000
classinstance 128130 class_ref 128002 // RequestController
  name ""   xyz 214 15 2000 life_line_z 2000
classinstance 128258 class_ref 128130 // RequestService
  name ""   xyz 382 18 2000 life_line_z 2000
classinstance 128386 class_ref 128258 // RequestRepository
  name ""   xyz 528 16 2000 life_line_z 2000
classinstance 129410 class_ref 128514 // MySQL
  name ""   xyz 678 17 2000 life_line_z 2000
durationcanvas 128514 classinstance_ref 128002 // :User
  xyzwh 68 90 2010 11 140
end
durationcanvas 128642 classinstance_ref 128130 // :RequestController
  xyzwh 266 90 2010 11 125
end
durationcanvas 128898 classinstance_ref 128258 // :RequestService
  xyzwh 427 107 2010 11 108
end
durationcanvas 129154 classinstance_ref 128386 // :RequestRepository
  xyzwh 583 122 2010 11 72
end
durationcanvas 129538 classinstance_ref 129410 // :MySQL
  xyzwh 699 133 2010 11 70
end
msg 128770 synchronous
  from durationcanvas_ref 128514
  to durationcanvas_ref 128642
  yz 91 2015 explicitmsg "http get liste des requetes et statuts"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 75 75
msg 129026 synchronous
  from durationcanvas_ref 128642
  to durationcanvas_ref 128898
  yz 108 2015 explicitmsg "call dedigated com.iuc.requests.api.service"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 293 93
msg 129282 synchronous
  from durationcanvas_ref 128898
  to durationcanvas_ref 129154
  yz 123 2015 explicitmsg "call method sql query"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 450 107
msg 129666 synchronous
  from durationcanvas_ref 129154
  to durationcanvas_ref 129538
  yz 137 2015 explicitmsg "sql request"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 616 122
msg 129794 return
  from durationcanvas_ref 129538
  to durationcanvas_ref 129154
  yz 179 2020 explicitmsg "liste des requetes"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 598 188
msg 129922 return
  from durationcanvas_ref 129154
  to durationcanvas_ref 128898
  yz 181 2015 unspecifiedmsg
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
msg 130050 return
  from durationcanvas_ref 128898
  to durationcanvas_ref 128642
  yz 195 2015 unspecifiedmsg
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
msg 130178 return
  from durationcanvas_ref 128642
  to durationcanvas_ref 128514
  yz 198 2015 explicitmsg "liste des requetes et statuts"
  show_full_operations_definition default show_class_of_operation default drawing_language default show_context_mode default
  label_xy 96 208
end
