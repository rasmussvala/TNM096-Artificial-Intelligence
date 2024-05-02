% Facts
likes(john, pizza).
likes(mary, sushi).
likes(emil, pizza).

% Rules
friend(X, Y) :- likes(X, Z), likes(Y, Z).