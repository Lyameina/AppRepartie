package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * server.LauncherRMI - apprep :: twitter :: server
 *
 * Classe qui lance le serveur RMI.
 * Penser à ouvrir le rmi registry sur le port 2048 avant
 * de lancer le serveur.
 *
 * @author Camille Boinaud [boinaud@polytech.unice.fr]
 * @author Maylanie Mesnier [mesnier@polytech.unice.fr]
 * @version 13/05/15
 */
public class LauncherRMI {

    public static void main(String[]args) {
      /**  try {
            System.out.println("création de l'objet serveur ...");
            Server server = new ServerImpl();
            System.out.println("Référencement dans le RMIRegistry...");

            //faire le registry sur le port 2048:
            LocateRegistry.createRegistry(2048);

            Naming.rebind("Server", server);
            System.out.println("Attente d'invocation - CTRL-C pour stopper");
        } catch (Exception e){
            e.printStackTrace();
        }**/
    }
}
