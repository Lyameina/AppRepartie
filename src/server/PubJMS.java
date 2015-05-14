package server;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Calendar;
import java.util.Hashtable;

/**
 * server.PubJMS - apprep :: twitter :: server
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class PubJMS {

    private Connection connect = null;
    private Session sendSession = null;
    private MessageProducer sender = null;
    private InitialContext context = null;

    public PubJMS()
    {
        try {
            this.configuration();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to set properties used for JMS connection
     * @throws JMSException
     */
    private void configuration() throws JMSException {
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();
        } catch (javax.jms.JMSException jmse) {
            jmse.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to set publisher parameters like the topic name
     *
     * @param hashtag : String
     * @throws JMSException
     * @throws NamingException
     */
    private void publisherConfiguration(String hashtag) throws JMSException, NamingException {
        sendSession = connect.createSession(true, -1);
        Topic topic = (Topic) context.lookup(hashtag);
        sender = sendSession.createProducer(topic);
    }

    /**
     * Method used to publish a new message in the topic called  "hashtag"
     *
     * @param hashtag : String
     * @param msg     : String
     * @param login   : String
     * @throws JMSException
     */
    public void publish(String hashtag, String msg, String login) throws JMSException {

        Calendar c = Calendar.getInstance();

        try {
            publisherConfiguration(hashtag);

            MapMessage mess = sendSession.createMapMessage();
            mess.setString("tweet", msg);
            mess.setString("user", login);
            mess.setString("date", c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR)
                    + " " + (c.get(Calendar.AM_PM) == Calendar.AM ? c.get(Calendar.HOUR) : (c.get(Calendar.HOUR) + 12))
                    + ":" + c.get(Calendar.MINUTE));

            sender.send(mess);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
