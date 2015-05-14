package client;

/**
 * Created by camille on 14/05/15.
 */
public class Tweet {

    private String login;
    private String msg;
    private String date;

    public Tweet(String login, String msg, String date){
        this.login = login;
        this.msg = msg;
        this.date = date;
    }

    @Override
    public String toString(){
        return "\n**********\n\nNew tweet from : "+login+" at "+date+"\n\n"+msg+"\n\n**********\n";
    }



}
