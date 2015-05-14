package server;

import javax.jms.JMSException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
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
    private List<String> hashtags;
    private PubJMS publisher;

    public ServerImpl() throws RemoteException {
        users = new HashMap<String, Account>();
        hashtags = new ArrayList<String>();
        publisher = new PubJMS();
    }

    /**
     * Method used to create a new account
     * @param login    : String
     * @param password : String
     * @return boolean
     * @throws RemoteException
     */
    public boolean signup(String login, String password) throws RemoteException
    {
        Account account = new Account(login, password);
        if(account==null) return false;

        users.put(login, account);

        return users.containsValue(account);
    }

    /**
     * Method to connect user to its account
     *
     * @param login    : String
     * @param password : String
     * @return boolean
     */
    public boolean login(String login, String password) throws RemoteException
    {
        try {

            Account user = users.get(login);
            return user.login(login, password);

        } catch(NullPointerException e){

            System.err.println("[FAIL] Those login params do not match with any account already registered.");
            e.printStackTrace();

        }

        return false;
    }

    /**
     * Method used to disconnect a user
     * @param login : String
     * @return boolean
     */
    public boolean logout(String login) throws RemoteException
    {
        try {

            Account user = users.get(login);
            user.logout();
            return !user.isLoggedIn();

        } catch(NullPointerException e){

            System.err.println("[FAIL] Those login params do not match with any account already registered.");
            e.printStackTrace();

        }

        return false;
    }

    /**
     * Method used to send a message trough a JMS topic
     * @param hashtag : String
     * @param message : String
     * @param login : String
     * @return boolean
     */
    public boolean sendmsg(String hashtag, String message, String login) throws RemoteException
    {
        if(users.get(login).isLoggedIn()){

            if(!hashtags.contains(hashtag)) hashtags.add(hashtag);

            try {
                publisher.publish(hashtag, message, login);
                return true;
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Add a new hashtag in user's following list
     * @param login : String
     * @param hashtag : String
     * @return boolean
     */
    public boolean follow(String login, String hashtag) throws RemoteException
    {
        if(users.get(login).isLoggedIn()){
            users.get(login).addFollowing(hashtag);
            return true;
        }
        return false;
    }

    /**
     * Remove a hashtag in user's following list
     * @param login : String
     * @param hashtag : String
     * @return boolean
     */
    public boolean unfollow(String login, String hashtag) throws RemoteException
    {
        if(users.get(login).isLoggedIn()){
            users.get(login).removeFollowing(hashtag);
            return true;
        }
        return false;
    }

    /**
     * Returns all hashtags created by users
     * @return List<String>
     */
    public List<String> getAllHashtags() throws RemoteException
    {
        return hashtags;
    }


    /**
     * Returns all hashtags followed  by a user
     * @param login : String
     * @return List<String>
     * @throws RemoteException
     */
    public List<String> getUserHashtags(String login) throws RemoteException
    {
        if(users.get(login).isLoggedIn()){
            return users.get(login).getFollowing();
        }

        return null;
    }
}
