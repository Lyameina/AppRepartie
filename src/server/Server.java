package server;

import java.util.List;

/**
 * server.Server - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public interface Server {

    boolean login(String login, String password);

    boolean logout(String login);

    boolean sendmsg(String hashtag, String message, String login);

    boolean follow(String login, String hashtag);

    List<String> getAllHashtags();

    List<String> getTopHashtags();

}
