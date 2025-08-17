; b) Write a function to test if a linear list of numbers has a "mountain" aspect (a list has a "mountain"
; aspect if the items increase to a certain point and then decreases.
;  Eg. (10 18 29 17 11 10). The list must have at least 3 atoms to fullfil this criteria.

(defun _mountain (l increasing)
	(cond
		((= (list-length l) 1) (if increasing nil T))
		((and (< (car l) (cadr l)) (not increasing)) nil)
		((and (> (car l) (cadr l)) increasing) (_mountain (cdr l) nil))
		(T (_mountain (cdr l) increasing))
	)
)

(defun main (l)
	(if (or(< (list-length l) 3) (>(car l) (cadr l)))
		nil
		(_mountain l T)
	)
)

(print (main '(29 17 11 10)))

(print (main '(10 18 29 17 11 29 10)))