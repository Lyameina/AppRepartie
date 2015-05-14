package client;

import server.Server;
import server.ServerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static List<Tweet> tweets = new ArrayList<Tweet>();


    private static Server serveur;

    public static boolean creerCompte(String login, String mdp) throws RemoteException {

        System.out.println("Création du compte... " + login + ">" + mdp + "\n");
        if (serveur.signup(login, mdp)) {
            System.out.println("Création réussie. \n");
            return true;
        }
        else{
            System.out.println("Création échouée. Le login est surrement déjà pris \n");
            return false;
    }}

    public static void recupererHashtags(String login) throws RemoteException {
        System.out.println("Recupération des Hashtags aux quels vous êtes abonné...");

        List<String> list = serveur.getUserHashtags(login);
        subscribers = new HashMap<SubJMS, String>();
        System.out.println("* Mise en place des subcribers JMS *");
        for (String s : list) {
            SubJMS sub = new SubJMS(s);
            subscribers.put(sub, s);
        }
        System.out.println("* Suscribers configurés ! *");

    }

    public static void main(String[] args) {


        System.out.println(
                "****************************************\n"
                        + "****** BIENVENU DANS MINI TWITTER ******\n"
                        + "****************************************\n\n");


        try {
            BufferedReader bufferReadLine = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connexion au serveur de MiniTwitter, veuillez patienter...\n");
            System.out.println("Recherche de l'objet serveur...\n");

            serveur = (Server) Naming.lookup("rmi://127.0.0.1:2048/Server");

            System.out.println("Connexion au serveur reussie !\n\n");


            System.out.println("VEUILLEZ RENSEIGNER LES INFORMATIONS SUIVANTES: \n" +
                    "===============================================");
            boolean isConnected = false;

            String login = "";
            int choix;
            while (!isConnected) {
                System.out.println("Login : ");
                login = bufferReadLine.readLine();

                System.out.println("Mot de Passe : ");
                String mdp = bufferReadLine.readLine();

                System.out.println("Connexion au compte " + login + ", un instant...\n ");

                if (serveur.login(login, mdp)) {
                    System.out.println("Connexion au compte réussie \n");
                    isConnected = true;
                } else {

                    System.out.println("Le compte login: " + login + " mot de passe : " + mdp + " n'existe pas\n");
                    System.out.println("Voulez-vous le crée?(o/n) \n");
                    choix = bufferRead.read();
                    if (choix == 'o') {
                       isConnected = creerCompte(login, mdp);
                    }
                }
            }

            recupererHashtags(login);

            boolean quitter = false;

            while (!quitter) {
                System.out.println("\n\nCHOISIR UNE ACTION :\n" +
                        "===================\n");
                System.out.println("" +
                        "1 - Afficher la liste des Hashags\n" +
                        "2 - Souscrire à un Hashtag\n" +
                        "3 - Afficher la liste des Hashtags suivis\n" +
                        "4 - Se desabonner d'un Hashtag\n" +
                        "5 - Envoyer un tweet \n" +
                        "6 - Voir les tweets \n" +
                        "q - Deconnexion et quitter");

                choix = bufferRead.read();
                String hashtag;
                switch (choix) {
                    case '1':
                        afficherHashtags();
                        break;
                    case '2':
                        System.out.println("Quel Hashtag: ");
                        hashtag = bufferReadLine.readLine();
                        serveur.follow(login, hashtag);
                        recupererHashtags(login);
                        break;
                    case '3':
                        afficherMesHashtags(login);
                        break;
                    case '4':
                        System.out.println("Quel Hashtag: ");
                        hashtag = bufferReadLine.readLine();
                        serveur.unfollow(login, hashtag);
                        recupererHashtags(login);
                        break;
                    case '5':
                        System.out.println("Quel Hashtag: ");
                        hashtag = bufferReadLine.readLine();
                        System.out.println("Votre message: ");
                        serveur.sendmsg(hashtag, bufferReadLine.readLine(), login);
                        recupererHashtags(login);
                        System.out.println("Tweet envoyé\n");
                        break;
                    case '6':
                        voirTweets();
                        break;
                    case 'q':
                        serveur.logout(login);
                        quitter = true;
                        break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void voirTweets() {
        for (Tweet t : tweets){
            System.out.print(t);
        }
    }

    private static void afficherMesHashtags(String login) throws RemoteException {
        System.out.println("Recupération de la liste de mes Hashtags...\n");
        List<String> list = serveur.getUserHashtags(login);
        for (String s : list) {
            System.out.println(s);
        }
    }


    private static void afficherHashtags() throws RemoteException {
        System.out.println("Recupération de la liste des Hashtags...\n");
        List<String> list = serveur.getAllHashtags();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
