:- use_module(library(clpfd)).

% Define objects
block(X) :- X in 2..4 \/ 6. % Objects 2[B],3[C],4[D],6[F] are blocks
pyramid(X) :- X in 1 \/ 5. % Objects 1[A],5[E] are pyramids
object(X) :- X in 1..6. % All are objects

% Define colors
orange(X) :- X in 1 \/ 4. % Objects 1[A] and 4[D] orange
green(X) :- X in 2 \/ 5. % Objects 2[B] are 5[E] green
blue(X) :- X in 3 \/ 6. % Objects 3[C] and 6[F] are blue

% Pickup from table
act(pick_from_table(X),
    [block(X), handempty, clear(X), on(X, table)],
    [handempty, on(X, table)],
    [holding(X)]
).

act(pick_from_table(X),
    [pyramid(X), handempty, clear(X), on(X, table)],
    [handempty, on(X, table)],
    [holding(X)]
).

% Pickup from block
act(pickup_from_block(X, Y),
    [block(X), handempty, clear(X), on(X, Y), block(Y), diff(X, Y)],
    [handempty, on(X, Y)],
    [holding(X), clear(Y)]
).

act(pickup_from_block(X, Y),
    [pyramid(X), handempty, clear(X), on(X, Y), block(Y), diff(X, Y)],
    [handempty, on(X, Y)],
    [holding(X), clear(Y)]
).

% Put down on table
act(putdown_on_table(X),
    [block(X), holding(X)],
    [holding(X)],
    [handempty, on(X, table)]
).

act(putdown_on_table(X),
    [pyramid(X), holding(X)],
    [holding(X)],
    [handempty, on(X, table)]
).

% Put down on block
act(putdown_on_block(X, Y),
    [block(X), holding(X), block(Y), clear(Y), diff(X, Y)],
    [holding(X), clear(Y)],
    [handempty, on(X, Y)]
).

act(putdown_on_block(X, Y),
    [pyramid(X), holding(X), block(Y), clear(Y), diff(X, Y)],
    [holding(X), clear(Y)],
    [handempty, on(X, Y)]
).

% Goal state
goal_state([
    on(X, Y), % Object X is on top of object Y
    on(Y, Z), % Object Y is on top of object Z
    green(Y), % Object Y is green
    blue(Z)   % Object Z is blue
]).

% Initial state
initial_state([
    clear(1), clear(2), clear(3), clear(5), % A,B,C,E are clear
    on(3,4), on(5,6), % C is on D and E is on F
    on(1, table), on(2, table), on(4, table), on(6, table), % A,B,D,F are on the table
    handempty, % Robot hand is empty

    block(2), block(3), block(4), block(6), % Objects B,C,D,F are blocks
    pyramid(1), pyramid(5), % Objects A,E are pyramids

    orange(1), orange(4), % Objects A,D are orange
    green(2), green(5), % Objects B,E are green
    blue(3), blue(6), % Objects C,F and 8 are blue

    diff(1, 2), diff(1, 3), diff(1, 4), diff(1, 5), diff(1, 6), % Ensure objects are different
    diff(2, 1), diff(2, 3), diff(2, 4), diff(2, 5), diff(2, 6),
    diff(3, 1), diff(3, 2), diff(3, 4), diff(3, 5), diff(3, 6),
    diff(4, 1), diff(4, 2), diff(4, 3), diff(4, 5), diff(4, 6),
    diff(5, 1), diff(5, 2), diff(5, 3), diff(5, 4), diff(5, 6),
    diff(6, 1), diff(6, 2), diff(6, 3), diff(6, 4), diff(6, 5)
]).
