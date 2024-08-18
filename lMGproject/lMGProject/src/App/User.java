package App;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdata")

public class User {
    @Id
    @Column ( name = "username" )
    private String username;

    @Column ( name = "password" )
    private String password;

    @Column ( name = "email" )
    private String email;

    private User(){
    }

    User(String Username, String Pass){
        this.username = Username;
        this.password = Pass;
    }

    User(String Username, String Pass, String Email){
        this.username = Username;
        this.password = Pass;
        this.email = Email;
      
    }
}
