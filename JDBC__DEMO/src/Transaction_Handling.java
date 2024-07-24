import java.sql.*;
import java.util.Scanner;

public class Transaction_Handling {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/lenden?user=root";
    public static final String username = "root";
    public static final String password = "Renuka@12";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);

            String debit_query = "UPDATE accounts set balance = balance - ? WHERE account_number=?";
            String credit_query = "UPDATE accounts set balance = balance + ? WHERE account_number=?";

            PreparedStatement preparedStatementForDebit = connection.prepareStatement(debit_query);
            PreparedStatement preparedStatementForCredit = connection.prepareStatement(credit_query);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ankit, How much money send to Ankita? ");
            double amount = scanner.nextDouble();



            preparedStatementForDebit.setDouble(1,amount);
            preparedStatementForDebit.setInt(2,101);

            preparedStatementForCredit.setDouble(1,amount);
            preparedStatementForCredit.setInt(2,102);

            int affectedRowsDebit = preparedStatementForDebit.executeUpdate();

            int affectedRowsCredit = preparedStatementForCredit.executeUpdate();

            if(isSafeToDebit(connection,amount,101)){
                connection.commit();
                System.out.println("Transaction Successful!");
            } else {
                connection.rollback();
                System.out.println("Insufficient balance to debit!!!");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean isSafeToDebit(Connection connection , Double amount , int account_number){
        String query_to_fetch_user_data = "SELECT balance FROM accounts WHERE account_number=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_to_fetch_user_data);
            preparedStatement.setInt(1,account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getDouble("balance") < amount){
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
