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

public class SubJMS {

    private static String url = "failover://tcp://localhost:61616";
    public static ConnectionFactory connectFactory;
    public static Connection connect;
    public static Session receiveSession;
    public static Topic topic;
    public static MessageConsumer consumer;
    public static InitialContext context;

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

            connectFactory = (ConnectionFactory) context.lookup("ConnectionFactory");

            connect = connectFactory.createConnection();

            this.configSubscriber(topic);

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
        topic = (Topic) context.lookup("dynamicTopics/"+nameTopic);
        consumer = receiveSession.createConsumer(topic);
        connect.start();

        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    Client.tweets.add(new Tweet(((MapMessage) message).getString("user"), ((MapMessage) message).getString("tweet"),
                            ((MapMessage) message).getString("date"), ((MapMessage) message).getString("hashtag")));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };

        consumer.setMessageListener(listener);
    }

}
