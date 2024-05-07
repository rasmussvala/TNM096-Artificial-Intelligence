:- use_module(library(clpfd)).

% Define objects
block(X) :- X in 2..4 \/ 6. % Objects 2[B],3[C],4[D],6[F] are blocks
pyramid(X) :- X in 1 \/ 5. % Objects 1[A],5[E] are pyramids
object(X) :- X in 1..6. % All are objects
table(X) :- X in 0. % Table

% Define colors
orange(X) :- X in 1 \/ 4. % Objects 1[A] and 4[D] orange
green(X) :- X in 2 \/ 5. % Objects 2[B] are 5[E] green
blue(X) :- X in 3 \/ 6. % Objects 3[C] and 6[F] are blue

% Pickup from table
act(pick_from_table(X),
    [handempty, clear(X), on(X, 0)],
    [handempty, on(X, 0)],
    [holding(X)]
):- object(X).

% Pickup from block
act(pickup_from_block(X, Y),
    [handempty, clear(X), on(X, Y)],
    [handempty, on(X, Y)],
    [holding(X), clear(Y)]
):- object(X).

% Put down on table
act(putdown_on_table(X),
    [holding(X)],
    [holding(X)],
    [handempty, on(X, 0)]
):- object(X).

% Put down on block
act(putdown_on_block(X, Y),
    [holding(X), clear(Y)],
    [holding(X), clear(Y)],
    [handempty, on(X, Y)]
):- object(X), block(Y).

% Goal state
goal_state([
    on(X, Y), 
    on(Y, Z)  
]):- green(Y),blue(Z).

% Initial state
initial_state([

    clear(1), 
    clear(2), 
    clear(3), 
    clear(5), % A,B,C,E are clear

    on(3,4), 
    on(5,6), % C is on D and E is on F

    on(1, 0), 
    on(2, 0), 
    on(4, 0), 
    on(6, 0), % A,B,D,F are on the table

    handempty % Robot hand is empty
]).
