p(100).
p(N):-
    N1 is N - 1,
    write(N),
    p(N1).

p(0):-!.
