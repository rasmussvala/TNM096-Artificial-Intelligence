class CSP {
  constructor(times, classrooms, classes) {
    this.times = times;
    this.classrooms = classrooms;
    this.classes = classes;

    // Size of the schedule
    this.rows = this.times.length;
    this.cols = this.classrooms.length;

    this.schedule = this.createEmptySchedule(); // IMPORTANT: TO ACCESS A TIMESLOT USE schedule[col][row]
  }

  // Initialize a empty schedule
  createEmptySchedule() {
    const col = [];

    // Fills the schedule with blank strings
    for (let i = 0; i < this.cols; i++) {
      let row = [];
      for (let j = 0; j < this.rows; j++) {
        row.push("---");
      }
      col.push(row);
    }

    const schedule = col;
    return schedule;
  }

  displaySchedule() {
    console.log("\n" + "Classroom Schedule:");
    console.log("\t" + this.classrooms.join("\t| "));
    console.log("-------------------------------");
    for (let i = 0; i < this.rows; i++) {
      let scheduleRow = [];
      for (let j = 0; j < this.cols; j++) {
        scheduleRow.push(this.schedule[j][i]);
      }
      console.log(this.times[i] + "\t| " + scheduleRow.join("\t| "));
    }
    console.log("-------------------------------");
    console.log("\n");
  }

  shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }

  addClassesRandomly() {
    this.shuffleArray(this.classes);

    let counter = 0;

    for (let col = 0; col < this.cols; col++) {
      for (let row = 0; row < this.rows; row++) {
        if (counter < this.classes.length) {
          this.schedule[col][row] = this.classes[counter];
        } else {
          this.schedule[col][row] = "---";
        }
        counter++;
      }
    }
  }

  // Main algorithm to solve the CSP
  minConflicts(maxSteps) {
    for (let step = 0; step < maxSteps; step++) {
      let conflicts = [];

      // Check for conflicts
      for (let col = 0; col < this.cols; col++) {
        for (let row = 0; row < this.rows; row++) {
          if (this.hasConflict(row, col)) {
            conflicts.push({
              row: row,
              col: col,
              name: this.schedule[col][row],
            });
          }
        }
      }

      if (conflicts.length === 0) {
        console.log("Solved the CSP in " + step + " steps. ");
        return this.schedule;
      }

      const randomIndex = Math.floor(Math.random() * conflicts.length);
      const randomClass = conflicts[randomIndex];

      const { bestRow, bestCol } = this.findBestPlacement(randomClass);
      this.moveClassToBestPlacement(randomClass, bestRow, bestCol);
    }

    return null;
  }

  findBestPlacement(randomClass) {
    let bestRow = randomClass.row;
    let bestCol = randomClass.col;
    let minConflicts = this.countConflicts(randomClass.row, randomClass.col);

    for (let row = 0; row < this.rows; row++) {
      for (let col = 0; col < this.cols; col++) {
        if (this.schedule[col][row] === "---") {
          const conflicts = this.countConflicts(row, col);
          if (conflicts < minConflicts) {
            minConflicts = conflicts;
            bestRow = row;
            bestCol = col;
          }
        }
      }
    }

    return { bestRow, bestCol };
  }

  moveClassToBestPlacement(randomClass, bestRow, bestCol) {
    const currentClass = this.schedule[randomClass.col][randomClass.row];

    // Check if there's already a class scheduled at the best placement
    if (this.schedule[bestCol][bestRow] !== "---") {
      // Swap positions of randomClass and the class at the best placement
      this.schedule[randomClass.col][randomClass.row] =
        this.schedule[bestCol][bestRow];
      this.schedule[bestCol][bestRow] = currentClass;
    } else {
      // Move randomClass to the best placement
      this.schedule[randomClass.col][randomClass.row] = "---";
      this.schedule[bestCol][bestRow] = currentClass;
    }
  }

  countConflicts(row, col) {
    let conflicts = 0;
    const currentClass = this.schedule[col][row];

    // Check for conflicts in the same timeslot
    for (let i = 0; i < this.cols; i++) {
      if (i !== col && this.schedule[i][row][2] === currentClass[2]) {
        conflicts++;
      }
    }

    return conflicts;
  }

  // Functions that checks whether or not a class has an conflict in the current place
  hasConflict(row, col) {
    const currentClass = this.schedule[col][row];

    // Check for conflicts in the same timeslot
    for (let i = 0; i < this.cols; i++) {
      if (i !== col) {
        const otherClass = this.schedule[i][row];
        if (otherClass[2] === currentClass[2] && currentClass[2] !== "-") {
          return true;
        }
      }
    }

    return false;
  }
}

function main() {
  const times = [9, 10, 11, 12, 13, 14, 15, 16];
  const classrooms = ["TP51", "SP34", "K3"];
  const classes = [
    "MT101",
    "MT102",
    "MT103",
    "MT104",
    "MT105",
    "MT106",
    "MT107",
    "MT201",
    "MT202",
    "MT203",
    "MT204",
    "MT205",
    "MT206",
    "MT301",
    "MT302",
    "MT303",
    "MT304",
    "MT401",
    "MT402",
    "MT403",
    "MT501",
    "MT502",
  ];

  const csp = new CSP(times, classrooms, classes);
  const maxSteps = 100000;

  csp.addClassesRandomly();

  if (csp.minConflicts(maxSteps)) {
    csp.displaySchedule();
  } else {
    console.log("Didn't solve the CSP.");
  }
}

main();
