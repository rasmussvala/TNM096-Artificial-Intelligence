const { solve } = require("./a-star.js");

const initial = [
  [7, 2, 4],
  [5, 0, 6],
  [8, 3, 1],
];

const temp = [
  [1, 2, 5],
  [4, 0, 8],
  [3, 6, 7],
];

const final = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
];

solve(temp, final);
