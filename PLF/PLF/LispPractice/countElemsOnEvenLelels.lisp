
(defun countEven(tree level)
  (cond
    ((and(atom tree) (= (mod level 2) 0)) 1)
    ((and(atom tree) (= (mod level 2) 1)) 0)
    (t (apply '+ (mapcar #'(lambda(ti)(countEven ti (+ 1 level) )) tree)))
  )
)

(defun wrapper(tree)
  (countEven tree 0)
)

(print(wrapper '(A (B) (C(F) (G)) (D) (E))))