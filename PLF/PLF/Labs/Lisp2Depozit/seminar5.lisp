; minPos(l1,...,ln, minC, posC, index)
;{
;   posC, if n = 0  
;   minPos(l1,...,ln-1, minC, posC, index+1), if l1 not a number
;   minPos(l2,...,ln, l1, [index], index+1), if l1 < minC
;   minPos(l2,...,ln, l1, (index), index+1), if l1 number and minC not a number
;   minPos(l2,...,ln, minC, posC, index+1), if l1 > numberC
;   minPos(l2,...,ln, minC, posC U index, index+1), if l1 = minC
;   
;}
;main(l1,...,ln, [], 1)
;
;
;
;
;
;
;

(defun minPos(l, minC, posC, index)
    (cond
        ((null l) null)
        ((And (numberp minC)) (minPos (cdr l) minC posC (+ index 1)))
        ((And(numberp(minC))(< (car l) minC) (minPos (cdr l) minC (append POSC(list index)) (+ index 1))))
        ((or (not (numberp minC)) (< (car l) minC)) (minPos (cdr l) (car l) (list index) (+ index 1)))
        (T(minPos(cdr l)(car l)(list index)(+ index 1)))
    )
)

(defun mainMinPos(l)
    (minPos l null null 1)
)

;product(x)
;{
;   1, if x not a numerical atom
;   x, if x is a numerical atom
;   PI(1->n)(xi), if x is a list   
;
;
;
;}

(defun product(x)
    (cond
        (numberp(x) x)
        (atom(x) 1)
        (T(apply '* (mapcar 'product x)))
    )
)
