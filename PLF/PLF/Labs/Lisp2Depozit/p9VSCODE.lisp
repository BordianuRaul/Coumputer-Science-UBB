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
(setq type1 '(A 2 B 1 D 1 E 1 F 2 G 0 H 2 J 0 K 0 C 2 L 2 N 0 Q 2 P 0 R 0 M 0))
(print (transform-tree type1))
