%suma(L-list, S-suma)
%flow model(i,o)

sum([],0).

sum([H|T], S):-
    suma(T,TS),
    S is H + TS.
