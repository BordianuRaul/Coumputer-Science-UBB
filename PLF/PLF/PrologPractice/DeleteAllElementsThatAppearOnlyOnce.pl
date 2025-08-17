%NrApp(list-list,E-elem,R-result)
%flow(i,i,i),(i,i,o)


nrApp([],_,0).

nrApp([H|T], E, R):-
    H=E,
    nrApp(T,E,R1),
    R is R1+1.
nrApp([H|T],E,R):-
    H\=E,
    nrApp(T,E,R).

deleteApp([],_,[]).

deleteApp([H|T],CopyL,[H|R]):-
    nrApp(CopyL, H,ResultOcc),
    ResultOcc > 1,
    deleteApp(T,CopyL,R).

deleteApp([H|T],CopyL, R):-
    nrApp(CopyL, H, ResultOcc),
    ResultOcc < 2,
    deleteApp(T,CopyL,R).

