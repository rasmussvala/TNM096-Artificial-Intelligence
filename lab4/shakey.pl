% Shakey
location(shakey, room3). % Initial state
on(shakey, floor).

% Rooms
room(room1).
room(room2).
room(room3).
room(room4).
room(corridor).

% Connections between rooms (both directions)
connected(room1, corridor).
connected(room2, corridor).
connected(room3, corridor).
connected(room4, corridor).
connected(corridor, room1).
connected(corridor, room2).
connected(corridor, room3).
connected(corridor, room4).

% Boxes
box(box1).
box(box2).
box(box3).
box(box4).

% Define locations of switches and boxes
switch_location(room1, switch1).
switch_location(room2, switch2).
switch_location(room3, switch3).
switch_location(room4, switch4).
box_location(room1, box1).
box_location(room1, box2).
box_location(room1, box3).
box_location(room1, box4).

light_on(switch1, true).
light_on(switch2, false).
light_on(switch3, false).
light_on(switch4, true).

% Predicate to check if Shakey is at a location
at(shakey, Location) :-
    location(shakey, Location).

% Predicate to check if Shakey can go to a location
go(A, C) :-
    at(shakey,A),
    connected(A, B),
    connected(B, C).

push(b, X, Y) :- 
    at(b,X),
    connected(X, Y),
    location(b, Y).



