import java.sql.*;

public class ExpenseManager {
    private Connection conn;

    public ExpenseManager() {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/expense_tracker", "root", "your_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(double amount, String category, String description, String date) {
        try {
            String sql = "INSERT INTO expenses (amount, category, description, expense_date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, category);
            stmt.setString(3, description);
            stmt.setString(4, date);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewExpenses() {
        try {
            String sql = "SELECT * FROM expenses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getDouble("amount") + " | " +
                                   rs.getString("category") + " | " +
                                   rs.getString("description") + " | " +
                                   rs.getDate("expense_date"));
            }
            rs.close();
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExpenseManager em = new ExpenseManager();
        
        em.addExpense(500, "Food", "Lunch", "2025-11-14");
        em.viewExpenses();
    }
}
