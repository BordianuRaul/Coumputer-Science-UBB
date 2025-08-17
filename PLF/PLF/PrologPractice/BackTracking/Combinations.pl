insert(E,L,[E|L]).

insert(E,[H|T],[E|R]):-
    insert(E,T,[H|R]).

comb([H|_], 1, [H]).
comb([_|T],K,R):-
    comb(T,K,R).
comb([H|T],K,[H|R]):-
    K>1,
    K1 is K - 1,
    comb(T,K1,R).
