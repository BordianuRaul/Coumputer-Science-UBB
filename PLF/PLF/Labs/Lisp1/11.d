; d) Write a function which returns the product of numerical even atoms from a list, to any level.
(defun productList (l)
    (cond
        ((and (numberp l) (not (evenp l))) 1)
        ((numberp l) l )
        ((atom l) 1)
        (T (apply '* (mapcar 'productList l)))
    )
)

(print (productList '(1 2 3 (4 5 (6)) (7))))