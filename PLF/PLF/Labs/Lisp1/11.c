
;c) Remove all occurrences of a maximum numerical element from a nonlinear list.

(defun max_nr (a b)
    (if (> a b) a b)
)


(defun max_nr_in_list (l)
    (cond
        ((null l) -1)
        ((and (null (cdr l)) (numberp (car l))) (car l))
        ((numberp (car l)) (max_nr (car l) (max_nr_in_list (cdr l))))
        ((listp (car l)) (max_nr (max_nr_in_list (car l)) (max_nr_in_list (cdr l))))
        (t (max_nr_in_list (cdr l)))
     )
)

;(print (max_nr_in_list '(1 2 (3 (a) (1 3)) 4 5)))

(defun remove-element (lst element)
  (cond
    ((null lst) nil)
    ((listp (car lst))
     (cons (remove-element (car lst) element)
           (remove-element (cdr lst) element)))
    ((= (car lst) element)
     (remove-element (cdr lst) element)) 
    (T (cons (car lst) (remove-element (cdr lst) element)))
  )
) 

     
;; Example usage:
(setq my-list '(1 2 (3 (1 5)) 4 5))
(setq my-element (max_nr_in_list '(1 2 (3 (1 5)) 4 5)))
(setq my-list (remove-element my-list my-element))

(print my-list)
