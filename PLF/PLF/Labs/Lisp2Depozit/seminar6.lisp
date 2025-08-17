
; depth(root)
; {
;   0, root is null 
;   1, root is atom
;   1  + max(depth(rooti))i=2->n
;}

(defun depth(tree)
  (cond
    ((null tree) 0)
    ((atom tree) 1)
    (t (+ 1 (max(apply #'mapcar #'depth(cdr tree)))))
  )
)

(defun cauta (l e)
  (cond
    ((null l) nil)
    ((and (atom (car l)) (equal (car l) e)) t)
    ((listp (car l)) (or (cauta (car l) e) (cauta (cdr l) e)))
    (t (cauta (cdr l) e))
  )
)

(defun drum (l e)
  (cond
    ((and (atom l) (not (equal l e))) nil)
    ((and (atom l) (equal l e)) (list e))
    ((and (listp l) (cauta l e))
     (let ((subpaths (mapcan #'(lambda (x) (drum x e)) l)))
       (if subpaths
           (cons (car l) subpaths)  ; Use 'subpaths' directly
           nil)))
    (t nil)
  )
)

(defun count-elements (list)
  (if (null list)
      0
      (+ 1 (count-elements (cdr list)))))

(defun levelOfNode (lst node)
  (count-elements(drum lst node))
)

print(levelofnode '(A (B) (C (D) (E))) 'E)
