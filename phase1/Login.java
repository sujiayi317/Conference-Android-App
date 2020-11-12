import java.io.Serializable;
import java.util.*;

public class Login implements Serializable{

    private String username;
    private String password;

    public User Login()
    {
        //Presenter+Controller?
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter Your Username: ");
        this.username = in.next();

        System.out.println("Please Enter Your Password: ");
        this.password = in.next();


        //Check through list of Users for correct login
        FileReadWriter fileReadWriter = new FileReadWriter();
        for (User u: fileReadWriter.readFromUserFile("user.ser"))
        {
            if((u.getUserName()).equals(username) && (u.getPassword()).equals(password))
                return u;
        }
        //return something
    }

}
