package server;

import java.util.ArrayList;
import java.util.List;

/**
 * server.Account - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class Account {

    private String login; //username
    private String password; //password
    private boolean loggedIn; //this field is set to true if the user is logged in
    private List<String> following; //list of hashtags the user is following


    public Account(String login, String password) {
        this.login = login;
        this.password = password;
        this.loggedIn = true;
        this.following = new ArrayList<String>();
    }

    /**
     * Method to verify data connexion settings
     *
     * @param login    : String
     * @param password : String
     * @return boolean
     */
    public boolean login(String login, String password) {
        loggedIn = this.login.equals(login) && this.password.equals(password);

        return loggedIn;
    }

    /**
     * loggedIn field getter
     * @return boolean
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Method used for user log out
     */
    public void logout() {
        loggedIn = false;
    }

    /**
     * Method used to add a new following to user's list
     * @param hashtag : String
     */
    public void addFollowing(String hashtag) {
        this.following.add(hashtag);
    }

    /**
     * Method used to remove a following from user's list
     * @param hashtag : String
     */
    public void removeFollowing(String hashtag) {
        this.following.remove(hashtag);
    }

    /**
     * Following getter
     * @return List<String>
     */
    public List<String> getFollowing()
    {
        return following;
    }
}
