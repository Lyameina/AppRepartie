package client;

/**
 * client.Tweet - apprep :: twitter :: client
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 14/05/15
 */

public class Tweet {

    private String login;
    private String msg;
    private String date;
    private String hashtag;

    public Tweet(String login, String msg, String date, String hashtag){
        this.login = login;
        this.msg = msg;
        this.date = date;
        this.hashtag = hashtag;
    }

    @Override
    public String toString(){
        return "\n**********\n\nNew tweet from : "+login+" at "+date+"\n\n"+msg+" - #"+hashtag+"\n\n**********\n";
    }



}
