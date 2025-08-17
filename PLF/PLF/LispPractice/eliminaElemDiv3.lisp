(defun myReplace (l )
  (cond
   ((and (atom l) (numberp l) (=(mod l 3) 0))
    nil)
   ((atom l)
    (list l))
   (t
    (list (mapcan #'(lambda (x)
                      (myReplace x))
                  l)))))

(defun main (l)
  (car (myReplace l)))

(print(main '(1 (2 A (4 A)) (6))))