(defun my-replace (l elem substituteElem)
  (cond
    ((and (atom l) (= l elem) substituteElem))
    ((listp l) (mapcar #'(lambda (a) (my-replace a elem substituteElem)) l))
    (t l)
  )
)

(print (my-replace '(1 2 (1 2 (3 1) 2)) 1 'a))
