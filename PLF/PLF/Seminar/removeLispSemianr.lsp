;Remove all occ from a list

;rem_el(l1,l2,...,ln,elem)=
;{
;   l1 U rem_el(l2,...,ln, elem), atom(l1) amd l1 != elem
;   rem_el(l2,...,ln,elem), atom(l1) amd l1 = elem
;   rem_el(l1, e) U rem_el(l2,...,ln,elem), if !atom(l1)
;   [], n = 0
;}
;

(defun rem_el(my_lst, elem)

    (cond
        ((null my_lst)nil)
        (and (atom(car my_lst)) (= (car my_lst) elem))
        (cons(car my_lst) rem_el(cdr my_lst)elem)
        ((atom(car my_lst)) rem_el(cdr my_lst)e)
        (T (rem_el(car my_lst) elem)(rem_el(cdr my_lst)elem))
        
    )
    
)