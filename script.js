let addBtn = document.getElementById("addExpenseBtn");
let table = document.getElementById("expenseTable");

window.addEventListener("load", () => {
  let expenses = JSON.parse(localStorage.getItem("expenses")) || [];
  expenses.forEach((exp) => addExpenseToTable(exp));
});

addBtn.addEventListener("click", () => {
  let amount = document.getElementById("amount").value;
  let category = document.getElementById("category").value;
  let description = document.getElementById("description").value;
  let date = document.getElementById("date").value;

  if (amount && category && description && date) {
    let expense = { amount, category, description, date };
    addExpenseToTable(expense);

    let expenses = JSON.parse(localStorage.getItem("expenses")) || [];
    expenses.push(expense);
    localStorage.setItem("expenses", JSON.stringify(expenses));

    document.getElementById("amount").value = "";
    document.getElementById("category").value = "";
    document.getElementById("description").value = "";
    document.getElementById("date").value = "";
  } else {
    alert("Please fill all fields");
  }
});

function addExpenseToTable(expense) {
  let row = table.insertRow();
  row.insertCell(0).textContent = expense.amount;
  row.insertCell(1).textContent = expense.category;
  row.insertCell(2).textContent = expense.description;
  row.insertCell(3).textContent = expense.date;
}
