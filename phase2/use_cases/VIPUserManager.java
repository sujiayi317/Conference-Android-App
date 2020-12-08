package use_cases;

import com.example.a207_demo.entities.VIPUser;
import entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VIPUserManager extends AttendeeManager implements Serializable {
    private List<VIPUser> vipUsers;

    /**
     * Creates an use_cases.VIPUserManager with lists of Events for a vipUser that is empty
     */
    public VIPUserManager() {
        vipUsers = new ArrayList<>();
    }


    /**
     * Reset the attendees: no user exists
     */
    public void reset() {
        vipUsers = new ArrayList<>();
    }

    /**
     * Getter method for all attendees
     *
     * @return list of attendees
     */
    public List<VIPUser> getVIPUsers() {
        return vipUsers;
    }

    /**
     * Creates A VIPUser and adds it to the map and lists
     */
    public void createVIPUser(String userName, String email, String password) {
        VIPUser vipuser = new VIPUser(userName, email, password);
        this.vipUsers.add(vipuser);
        super.addUser(vipuser);
    }

    /**
     * Creates a VIPUser and adds it to the map and list. But this is for loading the file.
     *
     * @param userName The user name of the VIPUser
     * @param email    The email of the VIPUser
     * @param password The password of the VIPUser
     * @param ID       The unique id of the VIPUser
     */
    public void loadVIPUser(String userName, String email, String password, String ID) {
        VIPUser vipuser = new VIPUser(userName, email, password, ID);
        this.vipUsers.add(vipuser);
        super.addUser(vipuser);
    }

    public ArrayList<StringBuilder> getAllVIPInfo(){
        ArrayList<StringBuilder> usersInfo = new ArrayList<>();
        for (int i = 0; i < vipUsers.size(); i++){
            StringBuilder singleInfo = new StringBuilder();
            User currentUser = vipUsers.get(i);
            singleInfo.append(i).append(") ").append(currentUser.getUserName()).append(" ").append(currentUser.getType());
            usersInfo.add(singleInfo);
        }
        StringBuilder SummaryInfo = new StringBuilder();
        SummaryInfo.append("Total Number: ").append(vipUsers.size());
        usersInfo.add(0, SummaryInfo);
        return usersInfo;
    }

}
