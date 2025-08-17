%insertM(L-list,E-elem, M-pos, R-resultList)
%flowModel(i,i,i,o) <=> (i,0)(i,i)

insertM([],E,1,[E]).
insertM([],_,M,[]):-M>1.
insertM([H|T], E, 1, [E, H|T]).
insertM([H|T],E,M,R):-
    M>1,
    NewM is M-1,
    insertM(T,E,NewM,RT),
    R=[H|T].

%sumC(L-list, C-collector, S-result sum)
%flow model (i,i,o)

sum([], C, C).

sum([H,T], C, S):-
    NewC is C+H,
    sumC(T, newC, S).

%deleteE(L-list, E-elem, R-result list)
%flow model(i,i,0)(i,i,i)

deleteE([], _, []).
deleteE([E|T], E, R):-
    delete(T,E,R).
deleteE([H|T],E,[H|RT]):-
    H=\=E,
    deleteE(T,E,RT).
