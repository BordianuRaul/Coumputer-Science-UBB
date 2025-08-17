(defun evenProduct(lst)
  (apply '* (mapcar (lambda(elem)
    (cond
    ((= (mod elem 2) 0) elem)
    (t 1)
    )
  ) lst))
)

(print (evenProduct '(1 2 3 4)))