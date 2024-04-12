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
}

function minConflicts(csp, maxSteps) {
  // Implement Min-Conflicts algorithm
}

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

  minConflicts(csp, maxSteps);
  //scheduleClasses(csp);
  csp.displaySchedule();
}

main();
