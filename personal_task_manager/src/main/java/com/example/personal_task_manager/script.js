const API_URL = "http://localhost:8080/api/tasks";

async function fetchTasks() {
    const response = await fetch(API_URL);
    const tasks = await response.json();
    const taskList = document.getElementById("taskList");
    taskList.innerHTML = "";
    tasks.forEach(task => {
        const taskDiv = document.createElement("div");
        taskDiv.className = "task" + (task.completed ? " completed" : "");
        taskDiv.innerHTML = `
            <h3>${task.title}</h3>
            <p>${task.description}</p>
            <p>Due: ${task.dueDate} | Priority: ${task.priority}</p>
            <button onclick="deleteTask(${task.id})">Delete</button>
            <button onclick="toggleComplete(${task.id}, ${!task.completed})">
                ${task.completed ? "Mark as Pending" : "Mark as Completed"}
            </button>
        `;
        taskList.appendChild(taskDiv);
    });
}

async function deleteTask(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    fetchTasks();
}

async function toggleComplete(id, completed) {
    const response = await fetch(`${API_URL}/${id}`);
    const task = await response.json();
    task.completed = completed;
    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(task)
    });
    fetchTasks();
}

document.getElementById("taskForm").addEventListener("submit", async function (e) {
    e.preventDefault();
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const dueDate = document.getElementById("dueDate").value;
    const priority = document.getElementById("priority").value;

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description, dueDate, priority, completed: false })
    });

    this.reset();
    fetchTasks();
});

// Initial load
fetchTasks();
