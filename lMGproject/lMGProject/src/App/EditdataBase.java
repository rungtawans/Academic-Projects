package App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EditdataBase {
    public static void reg(String userName, String pass, String email){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
        addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            System.out.println("Creating new Student Object");
            User tempStudent = new User(userName,pass,email);
            session.beginTransaction();
            System.out.println("Saving the User");
            session.save(tempStudent);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
        }

    }

    public static Connection ConnectDb() {
//         String jdbcURL = "jdbc:mysql://localhost:3306/user?useSSL=false";
//         String user = "dbconnect";
//         String pass = "Bas@taks123";
        String jdbcURL = "jdbc:mysql://localhost:3306/userdata";
        String user = "immrung";
        String pass = "sun123";

         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = DriverManager.getConnection(jdbcURL,user,pass);
             return conn;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,e);
             return null;
         }
        
     }
}