package client;

import server.Server;
import server.ServerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * client.Client - apprep :: twitter :: client
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */

public class Client {

    private static Map<SubJMS, String> subscribers;

    private static Server serveur;

    public static void creerCompte(String login, String mdp) throws RemoteException {

        System.out.println("Création du compte... "+login+">"+mdp+"\n");
        if (serveur.signup(login, mdp))
            System.out.println("Création réussie, connectez vous. \n");
        else
            System.out.println("Création échouée. Le login est surrement déjà pris \n");
    }

    public static void recupererHashtags(String login) throws RemoteException{
        System.out.println("Recupération des Hashtags aux quels vous êtes abonné...");

        List<String> list = serveur.getUserHashtags(login);
/**
        System.out.println("* Mise en place des subcribers JMS *");
        for (String s : list){
            SubJMS sub = new SubJMS();
            sub.config(s);
            subscribers.put(sub,s);
        }
        System.out.println("* Suscribers configurés ! *");
   **/
    }

    public static void main(String[] args) {

        System.out.println(
                 "****************************************\n"
                +"****** BIENVENU DANS MINI TWITTER ******\n"
                +"****************************************\n\n");



        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connexion au serveur de MiniTwitter, veuillez patienter...\n");
            System.out.println("Recherche de l'objet serveur...\n");

            serveur = (Server) Naming.lookup("rmi://127.0.0.1:2048/Server");

            System.out.println("Connexion au serveur reussie !\n\n");


            System.out.println("VEUILLEZ RENSEIGNER LES INFORMATIONS SUIVANTES: \n" +
                    "===============================================");
            boolean isConnected = false;

            String login="";
            while (! isConnected) {
                System.out.print("Login : ");
                login = bufferRead.readLine();

                System.out.print("Mot de Passe : ");
                String mdp = bufferRead.readLine();

                System.out.print("Connexion au compte " + login + ", un instant... ");

                if (serveur.login(login, mdp)) {
                    System.out.println("Connexion au compte réussie");
                    isConnected = true;
                } else {

                    System.out.println("Le compte login: " + login + " mot de passe : " + mdp + " n'existe pas");
                    System.out.println("Voulez-vous le crée?(o/n) ");
                    String choix = bufferRead.readLine();
                    if (choix.equals("o")) {
                        creerCompte(login, mdp);
                    }
                }
            }

            recupererHashtags(login);

            boolean quitter = false;

            while (! quitter){
                System.out.println("CHOISIR UNE ACTION :\n" +
                        "===================\n");
                System.out.println("" +
                        "1 - Afficher la liste des Hashags\n" +
                        "2 - Souscrire à un Hashtag\n" +
                        "3 - Creer un Hashtags \n ");
                quitter = true;
                //todo
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
