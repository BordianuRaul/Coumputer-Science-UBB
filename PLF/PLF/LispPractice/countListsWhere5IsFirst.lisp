(defun transform(l)
  (cond
  ((numberp l) (list l))
  ((atom l) nil)
  (t (apply #'append(mapcar #'transform l)))
  )
)

(defun checkFive(list)
  (cond
  ((null (transform l)) nil)
  ((equal(car (transform l)) 5) t)
  (t nil)
  )
)


(defun countFive(list)

  ((atom list) 0)
  ((checkFive list) (+ 1 (apply '+ (mapcar 'countFive list))))
  (t (apply '+ (mapcar #'(countFive list))))
  
)
  

(print(countFive '(A(5(3 5) (5 A B)) (B 5) (5 B))))