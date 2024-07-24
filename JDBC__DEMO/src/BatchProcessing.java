import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class BatchProcessing {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/mydb?user=root";
    public static final String username = "root";
    public static final String password = "Renuka@12";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            //Using Prepared statement
            String Query = "INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("Enter your name: ");
                String name = scanner.next();
                System.out.print("Enter your age: ");
                int age = scanner.nextInt();
                System.out.print("Enter your marks: ");
                double marks = scanner.nextDouble();
//                String queryToInsert = String.format("INSERT INTO students(name,age,marks) VALUES('%s' , %o , %f )" , name , age , marks);
//                statement.addBatch(queryToInsert);

                //Using prepared Statement
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.addBatch();

                System.out.print("Do you want to continue(Y/N)? ");
                String choice = scanner.next();
                if(choice.toUpperCase().equals("N"))    break;
            }

//            int[] rowsAffected = statement.executeBatch();
//            System.out.println(Arrays.toString(rowsAffected));

            //Using Prepared
            int[] rowsAffected = preparedStatement.executeBatch();
            System.out.println(Arrays.toString(rowsAffected));


            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while(resultSet.next()){
                System.out.println("Name : "+ resultSet.getString("name"));
                System.out.println("Age : "+ resultSet.getInt("age"));
                System.out.println("Marks : "+ resultSet.getDouble(3));
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
    }
}
