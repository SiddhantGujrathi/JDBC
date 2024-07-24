import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String userename = "root";
    private static final String password = "Renuka@12";

    public static void main(String[] args) {
        // 1. Add mysql connector jar file in Java Libraries
        // 2. Import necessary packages from java.sql.___
        // 3. Load Drivers
        // 4. url of sql , username of mysql database , password of mysql database
        // 5. copy jdbc connection link from workbench
        // 6. paste in sql and change "?user=root" from this "jdbc:mysql://127.0.0.1:3306/?user=root"; to database name
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        // 5. Connection with database
        try {
            //DriverManager Class getConnection() returns object of connection interface
            Connection connection = DriverManager.getConnection(url,userename,password);
//            //Connection interface instance method createStatement() is used initialize statement instance
//            Statement statement = connection.createStatement();
//            String query = "select * from students";
//            //when fetch data from db use executeQuery() which returns whole table
//            ResultSet resultSet = statement.executeQuery(query);
//            //when inserting,updating or deleting data from db use executeUpdate()
//
//            //Printing data from ResultSet
//            while(resultSet.next()){                      //resultSet.next() it tells that is there next row exist or not in table
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("Marks");
//                System.out.println("ID: "+id);
//                System.out.println("name: "+name);
//                System.out.println("age: "+age);
//                System.out.println("marks: "+marks);
//            }

//            //Insertion in database
//            String queryToInsert = String.format("INSERT INTO students(name,age,marks) VALUES('%s',%o,%f)", "Sandip" , 21 , 96.6 );
//            //OR use String queryToInsert = "INSERT INTO students(name,age,marks) values(\"Siddhant\",22,76)";
//            int rowsAffected = statement.executeUpdate(queryToInsert);    //executeUpadte() returns how much rows are affected
//            System.out.println(rowsAffected);


//            //Update in Database
//            String queryToUpdate = String.format("UPDATE students SET marks= %f WHERE id = %d",  98.2 , 3 );
//            int rowsAffected = statement.executeUpdate(queryToUpdate);    //executeUpadte() returns how much rows are affected
//            System.out.println(rowsAffected);

//            //Delete From DATABASE
//            String queryToDelete = String.format("DELETE FROM students WHERE id = %d", 1 );
//            int rowsAffected = statement.executeUpdate(queryToDelete);    //executeUpdate() returns how much rows are affected
//            System.out.println(rowsAffected);

            //preparedStatement  :
            // Normal statement compiles every time while prepared statement compile once and thereafter we only change the parameters
            // Like if we need to insert multiple records then normal statement gets compiled every time and then execute while
            // prepared statement compile once and thereafter insert each records

//            //Insertion using preparedStatement
//            String generalQuerytoInsert = "INSERT INTO students(name,age,marks) VALUES(?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(generalQuerytoInsert);
//            preparedStatement.setString(1,"Ankita");
//            preparedStatement.setInt(2,45);
//            preparedStatement.setDouble(3,70.0);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println(rowsAffected);

//            //Fetch data using preparedStatement
//            String generalQuerytoFetch = "SELECT marks FROM students WHERE name=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(generalQuerytoFetch);
//            preparedStatement.setString(1,"Ankita");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){               // As we are fetching only one row
//                System.out.println("Marks : "+resultSet.getDouble("marks"));
//            } else {
//                System.out.println("No record found!!");
//            }

//            //Update using prepared statement
//            String generalQuerytoUpdate = "UPDATE students SET age=? WHERE name=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(generalQuerytoUpdate);
//            preparedStatement.setInt(1,21);
//            preparedStatement.setString(2,"Ankita");
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println(rowsAffected);

            //Delete using prepared statement
            String generalQuerytoDelete = "DELETE FROM students WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(generalQuerytoDelete);
            preparedStatement.setString(1,"Siddhant");
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}