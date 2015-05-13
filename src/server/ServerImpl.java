package server;

import java.util.List;
import java.util.Map;

/**
 * server.ServerImpl - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class ServerImpl implements Server {

    private List<Account> users;
    private Map<String, Integer> hashtags;

    public ServerImpl() {
        //TODO
    }

    public static void main(String[] args) {
        //TODO
    }

    public boolean login(String login, String password) {
        //TODO
        return false;
    }

    public boolean logout(String login) {
        //TODO
        return false;
    }

    public boolean sendmsg(String hashtag, String message) {
        //TODO
        return false;
    }

    public boolean follow(String login, String hashtag) {
        //TODO
        return false;
    }

    public List<String> getAllHashtags() {
        //TODO
        return null;
    }

    public List<String> getTopHashtags() {
        //TODO
        return null;
    }
}
