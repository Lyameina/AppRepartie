package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * server.ServerImpl - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class ServerImpl extends UnicastRemoteObject implements Server {

    private Map<String, Account> users;
    private Map<String, Integer> hashtags;

    public ServerImpl() throws RemoteException {
    }


    public boolean signup(String login, String password) throws RemoteException {
        //TODO
        return false;
    }

    public boolean login(String login, String password) throws RemoteException {
        Account user = users.get(login);
        return false;
    }

    public boolean logout(String login) throws RemoteException {
        //TODO
        return false;
    }

    public boolean sendmsg(String hashtag, String message, String login) throws RemoteException {
        //TODO
        return false;
    }

    public boolean follow(String login, String hashtag) throws RemoteException {
        //TODO
        return false;
    }

    public List<String> getAllHashtags() throws RemoteException {
        //TODO
        return null;
    }

    public List<String> getTopHashtags() throws RemoteException {
        //TODO
        return null;
    }
}
