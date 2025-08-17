;generate a list of all arrengements of k elements from the list that have product P and sum S

;Math model
;
;flow(i,i,i)(i,i,o)
;arrengements(l1,....,ln,k) = 
;{
;   insert (l1, arrengements(l2,...,ln, k - 1)), k > 1
;   arrengements(l2,l3,...,ln,k) if l1 is skipped, k > 1
;   [l1], k = 1
;}
;
;insert(l1,...,ln,e) =
;{
;   [elem] U list, elem un 1st pos
;   l1 U insert(l2, ...,lm)
;
;
;
arragements(L,A).

arrengements([L|_,l, [L]).

arrengements(T,H,Out):-
    NewK is k - 1,
    arrengements(T, NewKm ResT),
    insert(RestT, H).
    
arrengements([_|T], K, Out):-
    arrengements(T,K,Out).

insert(L, Elem, [Elem|L]).
insert([H|T], Elem, [H|Out]):-
    insert(T, Elem, Out).

sum(l1,...,ln)

findall(A ,arragement(L,A), R):-
    
 