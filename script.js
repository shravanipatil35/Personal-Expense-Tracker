let addBtn = document.getElementById("addExpenseBtn");
let table = document.getElementById("expenseTable");

addBtn.addEventListener("click", () => {
    let amount = document.getElementById("amount").value;
    let category = document.getElementById("category").value;
    let description = document.getElementById("description").value;
    let date = document.getElementById("date").value;

    if(amount && category && description && date) {
        let row = table.insertRow();
        row.insertCell(0).textContent = amount;
        row.insertCell(1).textContent = category;
        row.insertCell(2).textContent = description;
        row.insertCell(3).textContent = date;

        document.getElementById("amount").value = "";
        document.getElementById("category").value = "";
        document.getElementById("description").value = "";
        document.getElementById("date").value = "";
    } else {
        alert("Please fill all fields");
    }
});
