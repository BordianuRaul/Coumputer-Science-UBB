insert(E,L,[E|L]).
insert(E,[H|T],[H|R]):-
    insert(E,T,R).

perm([],[]).
perm([H|T],R):-
    perm(T,RP),
    insert(H,RP,R).

arr([H|_],1,[H]).
arr([_|T],K, R):-
    arr(T,K,R).
arr([H|T], K, RA):-
    K > 1,
    K1 is K - 1,
    arr(T, K1,R),
    insert(H,R,RA).

nrElem([],0).
nrElem([_|T],R):-
    nrElem(T,Rnr),
    R is Rnr + 1.

sumElem([],0).
sumElem([H|T],R):-
    sumElem(T,Rs),
    R is Rs + H.

filterArr([],[]).

filterArr([H|T],[H|R]):-
    nrElem(H,Rnr),
    0 is Rnr mod 2,
    sumElem(H,Rs),
    1 is Rs mod 2,
    !,
    filterArr(T,R).

filterArr([_|T],R):-
    filterArr(T,R).


wrapper(L, R) :-
    findall(Rarr, (nrElem(L, Lnr), arr(L, Lnr, Rarr)), Rall),
    findall(F, (member(Arr, Rall), filterArr([Arr], F)), R).
