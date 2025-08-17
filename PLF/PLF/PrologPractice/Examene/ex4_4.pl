p(1).
q(1).
r(1).

p(2).
q(2).
r(2).

s():-
    p(X),
    !,
    q(Y),
    r(Z),
    write(X),
    write(Y),
    write(Z),
    nl.
