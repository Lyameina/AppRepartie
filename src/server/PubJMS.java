package server;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * server.PubJMS - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class PubJMS {

    private Connection connect;
    private Session sendSession;
    private MessageProducer sender;
    private Queue queue;
    private InitialContext context;

    public PubJMS() {
        //TODO
    }

    public void configurer(String hashtag) throws JMSException {
        //TODO
    }

    private void configurerPublisher(String hashtag) throws JMSException, NamingException {
        //TODO
    }

    public void publier(String hashtag, String msg) throws JMSException {
        //TODO
    }


}
