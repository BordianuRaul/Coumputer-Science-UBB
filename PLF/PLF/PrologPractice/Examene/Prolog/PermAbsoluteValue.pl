insert(E,L,[E|L]).
insert(E,[H|T],[H|R]):-
    insert(E,T,R).

perm([],[]).
perm([H|T],R):-
    perm(T,RT),
    insert(H,RT,R).

absCond([_],true).

absCond([First,Second|T],R):-
    X is First - Second,
    AbsX is abs(X),
    AbsX >= 2,
    absCond([Second|T],R).

absCond([First,Second|_],false):-
    X is First - Second,
    AbsX is abs(X),
    AbsX < 2.

filter([],[]).

filter([H|T],[H|R]):-
    absCond(H,Rabs),
    Rabs = true,
    !,
    filter(T,R).

filter([H|T],R):-
    absCond(H,Rabs),
    Rabs = false,
    !,
    filter(T,R).

buildL(0,[]).
buildL(N,[N|R]):-
    N > 0,
    N1 is N - 1,
    buildL(N1,R).

wrapper(N,R):-
    buildL(N,L),
    findall(R,perm(L,R),RL),
    filter(RL,R).
