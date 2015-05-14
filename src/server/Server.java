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
     * @param login    : String
     * @param password : String
     * @return boolean
     * @throws RemoteException
     */
    boolean signup(String login, String password) throws RemoteException;

    /**
     * Method to log user in
     *
     * @param login    : String
     * @param password : String
     * @return boolean
     */
    boolean login(String login, String password) throws RemoteException;

    /**
     * Method
     *
     * @param login
     * @return
     */
    boolean logout(String login) throws RemoteException;

    /**
     * @param hashtag
     * @param message
     * @param login
     * @return
     */
    boolean sendmsg(String hashtag, String message, String login) throws RemoteException;

    /**
     * @param login
     * @param hashtag
     * @return
     */
    boolean follow(String login, String hashtag) throws RemoteException;

    /**
     * @return
     */
    List<String> getAllHashtags() throws RemoteException;

    /**
     * @return
     */
    List<String> getTopHashtags() throws RemoteException;

}
