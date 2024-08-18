package App;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginForm implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label statusfail;

    @FXML
    private Label statussuccess;

    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
    }

    public void SWtoReg() throws IOException{
        App.setRoot("regForm");
    }

    public void SWtoHome() throws IOException{
        App.setRoot("main");
    }
    
    public void loginButton() throws IOException{

        conn = EditdataBase.ConnectDb();
        try {
            String searchUser = txtUser.getText();
            String sql = "SELECT * FROM userdata WHERE username = \""+searchUser+ "\"";
            pst = conn.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                if (txtPass.getText().equalsIgnoreCase(rs.getString("password"))) {
                    App.setRoot("page1");
                }else{
                    statusfail.setText("Login Fail");
                }
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
}

