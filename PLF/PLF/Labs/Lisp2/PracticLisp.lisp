
(defun smallerThen(a b)
  (cond
  ( (< a b) a)
  ((< b a ) b)
  (t a)
  )
)

(defun minNrInList (l)
    (cond
        ((null l) -1)
        ((and (null (cdr l)) (numberp (car l))) (car l))
        ((numberp (car l)) (smallerThen(car l) (minNrInList (cdr l))))
        ((listp (car l)) (smallerThen (minNrInList (car l)) (minNrInList (cdr l))))
        (t (minNrInList (cdr l)))
))

(defun createList (lst minE elem)
  (cond
    ((null lst) nil)
    ((and (numberp (car lst)) (= (car lst) minE))
    (cons (car lst) (cons elem (createList (cdr lst) minE elem))))
    (t (cons (car lst) (createList (cdr lst) minE elem)))))

(defun main(lst elem)
  createList(lst minNrInList(lst) elem)
)

(print(min '(1 2 3 4 5)))