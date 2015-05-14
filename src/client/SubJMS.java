package client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * client.SubJMS - apprep :: twitter :: client
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class SubJMS implements MessageListener {

    private Connection connect = null;
    private Session receiveSession = null;
    private InitialContext context = null;

    public SubJMS(String topic)
    {
        try {
            this.config(topic);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to set properties used for JMS connection
     * @param topic : String
     * @throws JMSException
     */
    private void config(String topic) throws JMSException {
        try {

            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();

            this.configSubscriber(topic);
            connect.start();

        } catch (JMSException e){
            e.printStackTrace();
        } catch (NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Method used to set subscribers parameters like the topic name
     * @param nameTopic : String
     * @throws JMSException
     * @throws NamingException
     */
    private void configSubscriber(String nameTopic) throws JMSException, NamingException {
        receiveSession = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup(nameTopic);
        MessageConsumer topicReceiver = receiveSession.createConsumer(topic);
        connect.start();

        while (true){
            Message m= topicReceiver.receive();
            onMessage(m);
        }
    }

    /**
     * Catch message reception events and print the message received.
     * @param message : String
     */
    public void onMessage(Message message) {
        try {
            Client.tweets.add(new Tweet(((MapMessage)message).getString("user"), ((MapMessage)message).getString("tweet"),
                    ((MapMessage) message).getString("msg")));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
