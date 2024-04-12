class CSP {
  constructor(times, classrooms, classes) {
    this.times = times;
    this.classrooms = classrooms;
    this.classes = classes;
    this.schedule = this.createEmptySchedule(); // Call the method to initialize schedule
  }

  createEmptySchedule() {
    const schedule = [];

    // Fills the schedule with blank strings
    for (let i = 0; i < this.classrooms.length; i++) {
      let row = [];
      for (let j = 0; j < this.times.length; j++) {
        row.push(" - ");
      }
      schedule.push(row);
    }

    return schedule;
  }

  displaySchedule() {
    console.log("\n" + "Classroom Schedule:");
    console.log("\t" + this.classrooms.join("\t| "));
    console.log("-------------------------------");
    for (let i = 0; i < this.times.length; i++) {
      let scheduleRow = [];
      for (let j = 0; j < this.classrooms.length; j++) {
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

  // Function to add classes randomly to the schedule
  addClassesRandomly() {
    const rows = this.schedule.length;
    const cols = this.schedule[0].length;

    // Shuffle the classes array
    this.shuffleArray(this.classes);

    let counter = 0;

    for (let i = 0; i < rows; i++) {
      for (let j = 0; j < cols; j++) {
        this.schedule[i][j] = this.classes[counter];
        counter++;
      }
    }
  }
}

function minConflicts(csp, maxSteps) {}

function scheduleClasses(csp) {
  // Implement class scheduling logic to satisfy constraints
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
  const maxSteps = 100;

  csp.addClassesRandomly();

  // minConflicts(csp, maxSteps);
  //scheduleClasses(csp);
  csp.displaySchedule();
}

main();
