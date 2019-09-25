import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCTest {

    public static Connection createConnection(String databaseType, String host, String port, String dbName, String user, String password) {
        String url = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + dbName;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
//        Connection connection = JDBCTest.createConnection(
//                "postgresql",
//                "ec2-54-247-177-254.eu-west-1.compute.amazonaws.com",
//                "5432",
//                "db0h2n4m13vngt",
//                "asrkdhzdjrtych",
//                "b112664c9ade7f276f7a2a15cbff70fcf671af7e4bd6893cb3c53802ecc105d2"
//        );

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-254.eu-west-1.compute.amazonaws.com:5432/db0h2n4m13vngt?user=asrkdhzdjrtych&password=b112664c9ade7f276f7a2a15cbff70fcf671af7e4bd6893cb3c53802ecc105d2&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
            Statement statement = connection.createStatement();
            String tableSql = "CREATE TABLE person (\n" +
                    "person_id integer PRIMARY KEY,\n" +
                    "name varchar(30) NOT NULL,\n" +
                    "last_name varchar(30) NOT NULL,\n" +
                    "age integer NOT NULL,\n" +
                    "pesel varchar(11) NOT NULL\n" +
                    ");";
//            statement.execute(tableSql);

            String selectSql = "SELECT * FROM person";
            try {
                ResultSet resultSet = statement.executeQuery(selectSql);

                int lastId = 1;
                while (resultSet.next()) {
//                String person = resultSet.getString("name");
//                person += " " + resultSet.getString("pesel");
//                person += " "  + resultSet.getInt("age");
//                System.out.println(person);
                    lastId = resultSet.getInt("person_id");
                }
//
//            String insertSql = "INSERT INTO person(person_id, name, last_name, age, pesel)"
//                    + " VALUES('" + (lastId +1) + "','Jan','Kot', 67, '237652')";
//            statement.executeUpdate(insertSql);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

                Scanner scanner = new Scanner(System.in);

                int selectedOption = 0;

                do {
                    System.out.println("Select the option");
                    System.out.println("1 - Add the person");
                    System.out.println("2 - Search for person by name");
                    System.out.println("3 - Search for person by the first letter of last name");
                    System.out.println("4 - Exit");

                    selectedOption = scanner.nextInt();


                    switch (selectedOption) {
                        case 1:
                            System.out.println("Type the name");
                            String name = scanner.next();
                            System.out.println("Type the last name:");
                            String lastName = scanner.next();
                            System.out.println("Type the age:");
                            int age = scanner.nextInt();
                            System.out.println("Type pesel:");
                            String personalNumber = scanner.next();

                            String insertSql = "INSERT INTO person(person_id, name, last_name, age, pesel)"
                                    + "VALUES('" + (lastId + 1) + "'," + name + "'," + lastName + "'," + age + "'," + personalNumber + "')";

                            try {
                                statement.executeUpdate(insertSql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        case 2:
                            System.out.println("Type the name");
                            String nameFromUser = scanner.next();
                            String selectSqlByName = "SELECT * from person WHERE name ='" + nameFromUser + "'";
                            ResultSet selectedByName = statement.executeQuery(selectSqlByName); // prawidlowo wypisz rezultat (przyklad wyzej)
                            System.out.println(selectedByName.toString());

                        case 3:
                            System.out.println("Type the first letter of the last name");
                            String x = scanner.next();
                            String selectSqlByFirstLetterFromLastName = "SELECT * FROM person WHERE last_name LIKE '" + x + "%'" ;
                            ResultSet selectedByLastName = statement.executeQuery(selectSqlByFirstLetterFromLastName); // prawidlowo wypisz rezultat (przyklad wyzej)
                            System.out.println(selectedByLastName);

                        case 4:
                            break;
                        default:
                            System.out.println("Selected number is wrong");
                    }
                } while (selectedOption != 4);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}