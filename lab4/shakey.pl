% Actions
act(
    go(X,Y), % Action name
    [at(shakey,X), on(shakey,floor), connected(X,Y), room(X), room(Y)],
    [at(shakey,X)], % Is now false
    [at(shakey,Y)]  % Is now true
).

goal_state( [at(shakey,room1) ]).

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
light_on(room4, true)

]).
