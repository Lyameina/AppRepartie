package client;

import server.Server;
import server.ServerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Map;

/**
 * client.Client - apprep :: twitter :: client
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class Client {

    private Map<SubJMS, String> subscribers;


    public static void main(String[] args) {

        System.out.println(
                 "****************************************\n"
                +"****** BIENVENU DANS MINI TWITTER ******\n"
                +"****************************************\n\n");

        System.out.println("VEUILLEZ RENSEIGNER LES INFORMATIONS SUIVANTES: \n" +
                            "===============================================");

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Login : ");
            String login = bufferRead.readLine();

            System.out.print("Mot de Passe : ");
            String mdp = bufferRead.readLine();

            System.out.println("Connexion au serveur de MiniTwitter, veuillez patienter...\n");

            System.out.println("Recherche de l'objet serveur...\n");
            Server serveur = (Server) Naming.lookup("rmi://127.0.0.1:2048/Server");

            System.out.println("Connexion au serveur reussie !\n\n");
            System.out.print("Connexion au compte " + login + ", un instant... ");










        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
