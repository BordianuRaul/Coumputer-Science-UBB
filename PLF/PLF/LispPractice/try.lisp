
(defun G(L)
  (mapcon #' list L)
)

(print(G '(1 2)))

(print (mapcon #' G '(1 2)))

(print(apply #' append(mapcon #' G '(1 2))))