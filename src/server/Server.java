package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * server.Server - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public interface Server extends Remote {


    /**
     * Method used to create a new account
     * @param login    : String
     * @param password : String
     * @return boolean
     * @throws RemoteException
     */
    boolean signup(String login, String password) throws RemoteException;

    /**
     * Method to connect user to its account
     *
     * @param login    : String
     * @param password : String
     * @return boolean
     */
    boolean login(String login, String password) throws RemoteException;

    /**
     * Method used to disconnect a user
     * @param login : String
     * @return boolean
     */
    boolean logout(String login) throws RemoteException;

    /**
     * Method used to send a message trough a JMS topic
     * @param hashtag : String
     * @param message : String
     * @param login : String
     * @return boolean
     */
    boolean sendmsg(String hashtag, String message, String login) throws RemoteException;

    /**
     * Add a new hashtag in user's following list
     * @param login : String
     * @param hashtag : String
     * @return boolean
     */
    boolean follow(String login, String hashtag) throws RemoteException;

    /**
     * Remove a hashtag in user's following list
     * @param login : String
     * @param hashtag : String
     * @return boolean
     */
    boolean unfollow(String login, String hashtag) throws RemoteException;

    /**
     * Returns all hashtags created by users
     * @return List<String>
     */
    List<String> getAllHashtags() throws RemoteException;

    /**
     * Returns all hashtags followed  by a user
     * @param login : String
     * @return List<String>
     * @throws RemoteException
     */
    List<String> getUserHashtags(String login) throws RemoteException;

}
