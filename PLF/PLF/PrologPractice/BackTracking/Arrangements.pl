insert1(E,L,[E|L]).

insert1(E,[H|T],[H|R]):-
    insert1(E,T,R).


perm1([],[]).
perm1([H|T],R):-
    perm1(T, TR),
    insert1(H, TR, R).

comb1([H|_], 1, [H]).
comb1([_|T],K,R):-
    comb1(T,K,R).
comb1([H|T],K,[H|R]):-
    K>1,
    K1 is K - 1,
    comb1(T,K1,R).

lazyArr(L,K,R):-
    comb1(L,K,C),
    perm1(C,R).


arr1([H|_],1,[H]).

arr1([_|T], K, R):-
    arr1(T,K,R).

arr1([H|T],K,RA):-
    K > 1,
    K1 is K - 1,
    arr1(T,K1,R),
    insert1(H,R,RA).



