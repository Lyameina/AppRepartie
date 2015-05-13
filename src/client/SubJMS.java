package client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.NamingException;

/**
 * client.SubJMS - apprep :: twitter :: client
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class SubJMS implements MessageListener {

    public SubJMS() {
    }

    public void config(String topic) throws JMSException {
        //TODO
    }

    private void configSubscriber(String topic) throws JMSException, NamingException {
        //TODO
    }

    public void onMessage(Message message) {
        //TODO
    }
}
