format 222

pseudostatecanvas 128002 pseudostate_ref 128002 // initial
   xyz 79 135 2000
end
statecanvas 128100 state_ref 128100 // Recevoir
  
  xyzwh 467 130 2000 71 35
end
statecanvas 128130 state_ref 141058 // Créer
  
  xyzwh 144 130 2000 53 35
end
statecanvas 128356 state_ref 128228 // En cours de traitement
  
  xyzwh 626 130 2000 149 35
end
statecanvas 128386 state_ref 141186 // Envoyer
  
  xyzwh 298 126 2000 65 35
end
statecanvas 128996 state_ref 128356 // à compléter
  
  xyzwh 293 205 2000 89 35
end
statecanvas 129252 state_ref 128484 // Approuver
  
  xyzwh 608 326 2000 79 35
end
statecanvas 129380 state_ref 128612 // Rejeter
  
  xyzwh 726 325 2000 61 35
end
pseudostatecanvas 132708 pseudostate_ref 128996 // join
   xyz 641 357 2005
end
pseudostatecanvas 132836 pseudostate_ref 129124 // join
   xyz 747 355 2005
end
pseudostatecanvas 133092 pseudostate_ref 129380 // final
   xyz 687 437 2000
end
stateactioncanvas 133476 stateaction_ref 128228
  xyzwh 608 378 2010 175 30
end
pseudostatecanvas 133604 pseudostate_ref 135908 // join
   xyzwh 636 300 2016 15 27
end
pseudostatecanvas 133732 pseudostate_ref 136036 // join
   xyzwh 747 299 2016 15 29
end
pseudostatecanvas 133860 pseudostate_ref 136164 // join
   xyzwh 691 407 2015 15 35
end
pseudostatecanvas 135012 pseudostate_ref 142692 // choice
   xyz 681 198 2000
end
stateactioncanvas 135652 stateaction_ref 134884
  xyzwh 615 270 2021 161 31
end
transitioncanvas 128228 transition_ref 128100 // <transition>
  decenter_end 428
  
  from ref 128386 z 2001 to ref 128100
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 129282 transition_ref 128258 // requête soumise
  decenter_end 541
  
  from ref 128130 z 2001 label "requête soumise" xyz 196 126 2001 to point 284 143
  line 134500 z 2001 to ref 128386
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 133220 transition_ref 135396 // <transition>
  
  from ref 128002 z 2001 to ref 128130
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 134884 transition_ref 141924 // <transition>
  
  from ref 128100 z 2001 to ref 128356
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 136292 transition_ref 142308 // <transition>
  decenter_begin 449
  
  from ref 128356 z 2001 to ref 135012
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 136676 transition_ref 142436 // <transition>
  
  from ref 135012 z 2022 to ref 135652
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 138340 transition_ref 149348 // <transition>
  decenter_end 569
  
  from ref 128996 z 2001 to ref 128386
  write_horizontally default show_definition default drawing_language default
end
transitioncanvas 138468 transition_ref 149476 // élément manquant
  decenter_end 314
  
  from ref 135012 z 2001 label "élément manquant" xyz 466 198 2001 to ref 128996
  write_horizontally default show_definition default drawing_language default
end
end
