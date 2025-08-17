(defun inlocuire (l)
  (cond
    ((and (atom l) (numberp l) (zerop (mod l 2)))
     (+ l 1))
    ((atom l)
     l)
    (t
     (mapcar #'inlocuire l))))

(print(inlocuire '(1 s 4 (2 f (7)))))

