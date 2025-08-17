(defun maxBetween (a b)
  (cond
    ((> a b) a)
    ((< a b) b)
    (t a)))

(defun findMaxElem (lst)
  (cond
    ((null lst) -1)
    ((and (atom (car lst)) (null (cdr lst))) (car lst))
    ((numberp (car lst)) (maxBetween (car lst) (findMaxElem (cdr lst))))
    ((listp (car lst)) (maxBetween (findMaxElem (car lst)) (findMaxElem (cdr lst))))
    (t (findMaxElem (cdr lst)))))



(defun removeMaxElemWithMax (lst maxValue)
  (cond
    ((null lst) nil)
    ((listp (car lst)) (cons (removeMaxElemWithMax (car lst) maxValue) (removeMaxElemWithMax (cdr lst) maxValue)))
    ((and (numberp (car lst)) (= (car lst) maxValue)) (removeMaxElemWithMax (cdr lst) maxValue))
    (t (cons (car lst) (removeMaxElemWithMax (cdr lst) maxValue)))))

(defun removeMaxElem (lst)
  (removeMaxElemWithMax lst (findMaxElem lst)))


(defun evenProduct(lst)
  (apply '* (mapcar (lambda(elem)
    (cond
    ((= (mod elem 2) 0) elem)
    (t 1)
    )
  ) lst))
)

(defun elim (l niv k)
  (cond
    ((and (atom l) (equal niv k)) (list l))
    ((listp l)
     (mapcan #'(lambda (x) (elim x (+ niv 1) k)) l))
    (t nil)))

(defun mainElim (l k)
  (elim l -1 k))

(print (mainElim '(A (B) (C (D) (E))) 1))