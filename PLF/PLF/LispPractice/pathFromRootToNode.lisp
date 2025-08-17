(defun mySearch(l e)
  (cond
    
    ((null l) NIL)
    ((and(atom (car l)) (equal (car l) e)) t)
    ((listp(car l)) (or (mySearch (car l) e) (mySearch (cdr l) e)))
    (t (mySearch (cdr l) e))
  )
)


(defun path (tree e)
  (cond
    ((and (atom tree) (not (= tree e))) nil)
    ((and (atom tree) (= tree e)) (list e))
    ((and (listp tree) (mySearch tree e))
     (append (list (car tree))
             (mapcan #'(lambda (x) (path x e)) (cdr tree))))
  )
)

(print (path '(a (b (g)) (c (d (e)) (f))) 'e))
