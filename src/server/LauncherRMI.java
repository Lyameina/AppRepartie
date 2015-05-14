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
      try {

          int port = 2048;
          String host = "localhost";

          LocateRegistry.createRegistry(port);
          Server server = new ServerImpl();
          Naming.rebind("rmi://" + host + ":" + port + "/Server", server);

          System.out.println("Attente d'invocation - CTRL-C pour stopper");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
