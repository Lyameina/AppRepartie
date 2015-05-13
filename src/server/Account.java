package server;

import java.util.List;

/**
 * server.Account - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class Account {

    private String login;
    private String password;
    private boolean loggedIn;
    private List<String> following;

    public Account(String login, String password) {
        //TODO
    }

    public boolean connection(String login, String password) {
        //TODO
        return false;
    }

    public boolean isLoggedIn() {
        //TODO
        return false;
    }

    public void deconnection() {
        //TODO
    }

    public void addFollowing(String hashtag) {
        //TODO
    }

    public void removeFollowing(String hashtag) {
        //TODO
    }
}
