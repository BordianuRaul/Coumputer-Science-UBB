myAppend([],[],[]).

myAppend([],List,List).

myAppend([H|T],List,[H|R]):-
    myAppend(T,List,R).

removeSublists([],[]).

removeSublists([H|T],[H|R]):-
    atomic(H),
    removeSublists(T,R).

removeSublists([H|T], R):-
    is_list(H),
    removeSublists(H, HeadRemove),
    removeSublists(T, TailRemove),
    myAppend(HeadRemove,TailRemove,R).



