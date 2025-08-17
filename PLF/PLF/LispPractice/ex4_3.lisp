
(defun G(F l)
  (funcall F L)
)
(print((lambda(L)(G #' CDR L)) '(1 2 3)))
(print(G #' (lambda(L)(G #' CDR L)) '(1 2 3)))