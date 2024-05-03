% Actions
act(
    go(X,Y), % Action name
    [at(shakey, X), on(shakey, floor), connected(X,Y), room(X), room(Y)],
    [at(shakey, X)], % Is now false
    [at(shakey, Y)]  % Is now true
).

act(
    push(B,X,Y), % Action name
    [at(shakey, X), on(shakey, floor), light_on(X, true), box_location(X, B), connected(X, Y), room(X), room(Y), box(B)],
    [box_location(X, B), at(shakey, X)], % Is now false
    [box_location(Y, B), at(shakey, Y)]  % Is now true
).

act(
    climbUp(B),
    [on(shakey, floor), box(B), at(shakey, X), box_location(X, B), room(X)],
    [on(shakey, floor)],
    [on(shakey, B)]
).

act(
    climbDown(B),
    [on(shakey, B), box(B), at(shakey, X), box_location(X, B), room(X)],
    [on(shakey, B)],
    [on(shakey, floor)]
).

act(
    turnOnLight(X),
    [on(shakey,B), box(B), at(shakey,X), box_location(X,B), room(X), light_on(X, false)],
    [light_on(X, false)],
    [light_on(X, true)]
).

act(
    turnOffLight(X),
    [on(shakey,B), box(B), at(shakey,X), box_location(X,B), room(X), light_on(X, true)],
    [light_on(X, true)],
    [light_on(X, false)]
).


%goal_state( [at(shakey, room1)]).
%goal_state( [light_on(room1, false)]).
goal_state( [box_location(room2, box2)]).
%goal_state( [on(shakey, box1)]).


initial_state([

% Shakey start position
at(shakey, room3), 
on(shakey, floor),

% Rooms
room(room1),
room(room2),
room(room3),
room(room4),
room(corridor),

% Connections between rooms (both directions)
connected(room1, corridor),
connected(room2, corridor),
connected(room3, corridor),
connected(room4, corridor),
connected(corridor, room1),
connected(corridor, room2),
connected(corridor, room3),
connected(corridor, room4),

% Boxes
box(box1),
box(box2),
box(box3),
box(box4),

% Switch
switch(switch1),
switch(switch2),
switch(switch3),
switch(switch4),

% Define locations of switches and boxes
switch_location(room1, switch1),
switch_location(room2, switch2),
switch_location(room3, switch3),
switch_location(room4, switch4),

box_location(room1, box1),
box_location(room1, box2),
box_location(room1, box3),
box_location(room1, box4),

light_on(room1, true),
light_on(room2, false),
light_on(room3, false),
light_on(room4, true),
light_on(corridor, true)

]).
