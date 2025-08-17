(defun generate-subtree (tree nr)
  (cond
    ((= nr 0) nil)
    ((atom tree) tree)
    ((numberp (car tree)) (cons (car tree) (generate-subtree (cdr tree) nr)))
    ((and (= nr 1) (= (cadr tree) 0)) (list (car tree) (cadr tree)))
    (t (cons (car tree) (generate-subtree (cdr tree) (+ (cadr tree) (- nr 1)))))))

(defun generate-right-subtree (tree nr)
  (cond
    ((= nr 1) (generate-subtree tree nr))
    ((atom tree) tree)
    (t (generate-right-subtree (cddr tree) (+ (cadr tree) (- nr 1))))))

(defun transform-tree (tree)
  (cond
    ((null tree) nil)
    ((= 0 (cadr tree)) (list (car tree)))
    ((= 1 (cadr tree)) (list (car tree) (transform-tree (generate-subtree (cddr tree) 1))))
    ((= 2 (cadr tree)) (list (car tree)
                             (transform-tree (generate-subtree (cddr tree) 1))
                             (transform-tree (generate-right-subtree (cddr tree) 2))))))


;; Example usage
(setq flat-tree '(A 2 B 0 C 2 D 0 E 0))
(print (transform-tree flat-tree))
