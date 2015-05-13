# Mini Twitter

1. Les classes:
==============

1.1 Le Client:
--------------
* Main : scenario console
* SubJMS : thread pour la recuperation des messages des topics
* Client: 
    - Map[SubJMS][nom_topic]
    - connexion(login, mot_de_passe)
    - Sendmsg(#, String)
    - getList() : recupere sur le serveur l'ensemble des topic existant
    - getMyList() : recupere la liste sur le serveur des # auquels le client est abonné et mets à jour sa liste de SubJMS avec le resultat
    - suscribe (#) : ajoute un # à suivre et en profite pour mettre à jour les SUBJMS


