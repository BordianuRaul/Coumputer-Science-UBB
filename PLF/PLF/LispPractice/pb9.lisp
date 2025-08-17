(defun subarbst (l nr)
  (cond
    ((= nr 0) nil)
    ((atom l) l)
    ((numberp (car l)) (cons (car l) (subarbst (cdr l) nr)))
    ((and (= nr 1) (= (cadr l) 0)) (list (car l) (cadr l)))
    (t (cons (car l) (subarbst (cdr l) (+ (car (cdr l)) (- nr 1)))))))

(defun subarbdr (l nr)
  (cond
    ((= nr 1) (subarbst l nr))
    ((atom l) l)
    (t (subarbdr (cddr l) (+ (car (cdr l)) (- nr 1))))))

(defun transf (l)
  (cond
    ((null l) nil)
    ((= 0 (cadr l)) (list (car l)))
    ((= 1 (cadr l)) (list (car l) (transf (subarbst (cddr l) 1))))
    ((= 2 (cadr l)) (list (car l)
                          (transf (subarbst (cddr l) 1))
                          (transf (subarbdr (cddr l) 2))))))

(defun transform-flat-to-nested (flat-tree)
  (transf flat-tree))

;; Example usage
(setq flat-tree '(A 2 B 0 C 2 D 0 E 0))
(print (transform-flat-to-nested flat-tree))
