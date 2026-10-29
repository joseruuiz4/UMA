const addTask = document.getElementById("addTaskButton");
let inputElement = document.getElementById("taskInput");
let lista = document.getElementById("todoList");

inputElement.addEventListener("keydown", () => {
  if (event.key == "Enter") {
    addNewTask();
  }
});

function addNewTask() {
  if (inputElement.value.trim() != "") {
    let elem = document.createElement("li");
    elem.className = "list-group-item d-flex flex-row align-items-center";

    let span = document.createElement("span");
    span.className = "taskName flex-grow-1";
    span.innerHTML = inputElement.value;

    elem.appendChild(span);

    let taskUp = document.createElement("img");
    let taskDown = document.createElement("img");
    let taskRm = document.createElement("img");

    taskUp.src = "images/taskUp.png";
    taskDown.src = "images/taskDown.png";
    taskRm.src = "images/removeTask.png";

    taskUp.addEventListener("click", moveUpTask);
    taskDown.addEventListener("click", moveDownTask);
    taskRm.addEventListener("click", removeTask);

    elem.appendChild(taskUp);
    elem.appendChild(taskDown);
    elem.appendChild(taskRm);

    lista.appendChild(elem);
    console.log("hola");

    inputElement.value = "";
  }
}

function moveUpTask() {
  let tareaAMover = this.parentElement;
  let itemAnterior = tareaAMover.previousElementSibling;
  if (itemAnterior) {
    lista.insertBefore(tareaAMover, itemAnterior);
  }
}

function moveDownTask() {
  let tareaAMover = this.parentElement;
  let itemPosterior = tareaAMover.nextElementSibling;
  if (itemPosterior) {
    lista.insertBefore(itemPosterior, tareaAMover);
  }
}

function removeTask() {
  let tareaABorrar = this.parentElement;
  tareaABorrar.remove();
}

addTask.addEventListener("click", addNewTask);
