class Node {
  constructor(matrix, x, y, moves, parent) {
    this.parent = parent;

    this.matrix = matrix.map((row) => [...row]);

    this.x = x;
    this.y = y;

    // Stores the number of misplaced tiles
    this.cost = Infinity;

    this.moves = moves;
  }
}

class Tile {
  constructor(number, x, y) {
    this.number = number;
    this.x = x;
    this.y = y;
  }
}

export function solve(initial, final) {}

function findZeroCoordinate(matrix) {
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix[i].length; j++) {
      if (matrix[i][j] === 0) {
        return [i, j];
      }
    }
  }
  // If zero is not found, return null or any other appropriate value
  return null;
}

function printMatrix(matrix) {
  for (let i = 0; i < 3; i++) {
    console.log(matrix[i].join(" "));
  }
  console.log("\n");
}
