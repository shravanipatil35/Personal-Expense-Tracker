import java.io.*;
import java.util.*;

public class ExpenseManager {

    static class Expense {
        double amount;
        String category;
        String description;
        String date;

        Expense(double amount, String category, String description, String date) {
            this.amount = amount;
            this.category = category;
            this.description = description;
            this.date = date;
        }

        @Override
        public String toString() {
            return amount + " | " + category + " | " + description + " | " + date;
        }
    }

    private static final String FILE_NAME = "expenses.txt";
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(double amount, String category, String description, String date) {
        Expense e = new Expense(amount, category, description, date);
        expenses.add(e);
        saveExpenses();
        System.out.println("Expense added!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        System.out.println("Amount | Category | Description | Date");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                pw.println(e.amount + "," + e.category + "," + e.description + "," + e.date);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadExpenses() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    double amount = Double.parseDouble(parts[0]);
                    String category = parts[1];
                    String description = parts[2];
                    String date = parts[3];
                    expenses.add(new Expense(amount, category, description, date));
                }
            }
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager em = new ExpenseManager();
        while (true) {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Date (yyyy-MM-dd): ");
                    String date = sc.nextLine();
                    em.addExpense(amt, cat, desc, date);
                    break;
                case 2:
                    em.viewExpenses();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
