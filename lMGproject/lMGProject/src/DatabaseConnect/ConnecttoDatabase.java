package DatabaseConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class ConnecttoDatabase {
    public static void main(String[] args) {
//        String jdbcURL = "jdbc:mysql://localhost:3306/user?useSSL=false";
//        String user = "dbconnect";
//        String pass = "Bas@taks123";
        String jdbcURL = "jdbc:mysql://localhost:3306/usersdb";
        String user = "immrung";
        String pass = "sun123";


        try{
            System.out.println("Connect to Database "+ jdbcURL);
            Connection myConnection = DriverManager.getConnection(jdbcURL, user, pass);
            Statement status = myConnection.createStatement();
            System.out.println("Connection Succefull!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
