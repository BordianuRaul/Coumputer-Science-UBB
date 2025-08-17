%multiply the elements of a list with k

%mullK(l-list, k-number, R- return list)
%flow model (i,i,o)

mulK([], _, []).

mulK([H|T, K, R):-
    mulK(T, K, Res2),
    NewH is H*K,
    R=[NewH|Res2].

