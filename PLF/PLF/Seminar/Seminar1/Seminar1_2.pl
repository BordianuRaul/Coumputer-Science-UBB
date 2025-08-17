%insertE(L-list,E-elem,R-result)
%flow model (i,i,o)

insertE([], E, [E]).

insertE([H,T], E, [E]):-
    insertE(T,E,TR).
