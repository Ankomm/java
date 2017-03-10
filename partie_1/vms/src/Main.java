import java.util.*;

/**
 * @author alix.hemery
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {

        boolean finish = false; //boucle principale, si true arrête le programme
        boolean infoError = false; //boucle d'erreur, si true alors il y a une erreur dans la saisie du nombre d'informations


        /**
         * Declaration de l'utilisateur courant
         * @see User
         */
        User me = null;

        int infoChoice; //nombre rentré par l'utilisateur pour le nombre d'infos qu'il souhaite donner
        int choice; //choix du menu

        HashMap<String, String> hashReturned = new HashMap<String, String>(); //collection retournée par la fonction scanInfos
        LinkedList<String> getAllMessages = new LinkedList<String>(); //collection de messages retournée par la méthode getMessages
        LinkedList<String> getAllFriends = new LinkedList<String>(); //collection d'amis retournée par la méthode getFriends

        Scanner sc = new Scanner(System.in);

        do {

            infoError = false;

            System.out.println("Which informations do you want to give us ? No one : 0, name/last name : 2, all(name, last name, city, age) : 4");
            infoChoice = sc.nextInt();
            sc.nextLine();
            if((infoChoice == 0) || (infoChoice == 2) || (infoChoice == 4)) {

                /**
                 * Demande les infos (en paramètre le nombre d'infos que souhaite donner l'utilisateur) à l'utilisateur et les stock
                 * @see scanInfos
                 */
                hashReturned = scanInfos(infoChoice);

                if (infoChoice == 0) {

                    me = new User();

                } else if (infoChoice == 2) {

                    me = new User(hashReturned.get("name"), hashReturned.get("lastName"));

                } else if (infoChoice == 4) {

                    me = new User(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("city"), Integer.parseInt(hashReturned.get("age")));

                }
            }else {

                System.out.println("ERROR ! Enter 0, 2 or 4 !");
                infoError = true;
            }
        }while(infoError); //continue de boucler si il y a une erreur

        do {

            System.out.println("==============\r\nMenu\r\n1. See my infos\r\n2. Cutsom my infos\r\n3. See a message\r\n4. Write a message\r\n5. See a friend\r\n6. Add a friend\r\n7. See all\r\n0. Exit\r\n==============");

            choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) {

                /**
                 * Affiche les informations de l'utilisateur
                 * @see screenInfos
                 */
                me.screenInfos();
            }
            else if(choice == 2) {

                /**
                 * Demande les infos à l'utilisateur afin de les mettre à jour, le 4 passé en paramètre signifie que toutes les infos seront demandées
                 * @see scanInfos
                 */
                hashReturned = scanInfos(4);
                me.setInfos(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("city"), Integer.parseInt(hashReturned.get("age")));
            }
            else if(choice == 3) {

                /**
                 * Récupère tout les messages de l'utilisateur
                 * @see getMessages
                 */
                getAllMessages = me.getMessages();
                int getIndexMessage;
                boolean messageError = false;

                if(getAllMessages.size() > 0) {

                    do {

                        messageError = false;

                        System.out.println("Index of the message (0 to " + (getAllMessages.size() - 1) + ")");
                        getIndexMessage = sc.nextInt();
                        sc.nextLine();

                        if(getIndexMessage >=0 && getIndexMessage < getAllMessages.size()) {

                            System.out.println("------------------\r\n" + getAllMessages.get(getIndexMessage));
                        }else {

                            System.out.println("ERROR ! Enter a number between 0 and " + (getAllMessages.size() - 1) + " !");
                            messageError = true;
                        }
                    }while(messageError);//continue de boucler si il y a une erreur
                }else {

                    System.out.println("No message there");
                }
            }
            else if(choice == 4) {

                System.out.println("Enter your message");

                /**
                 * Ajoute le message récupéré
                 *@see setMessage
                 */
                me.setMessage(sc.nextLine());
            }
            else if(choice == 5) {

                /**
                 * Récupère tout les amis
                 * @see getFriends
                 */
                getAllFriends = me.getFriends();
                int getIndexFriend;
                boolean friendError = false;

                if(getAllFriends.size() > 0) {

                    do {

                        friendError = false;

                        System.out.println("Index of the friend (0 to " + (getAllFriends.size() - 1) + ")");
                        getIndexFriend = sc.nextInt();
                        sc.nextLine();

                        if(getIndexFriend >=0 && getIndexFriend < getAllFriends.size()) {

                            System.out.println("------------------\r\n" + getAllFriends.get(getIndexFriend));
                        }else {

                            System.out.println("ERROR ! Enter a number between 0 and " + (getAllFriends.size() - 1) + " !");
                            friendError = true;
                        }

                    }while(friendError);//continue de boucler si il y a une erreur
                }else {

                    System.out.println("You have no friend");
                }
            }
            else if(choice == 6) {

                System.out.println("Enter a new friend");

                /**
                 * Ajoute l'amis récupéré
                 * @see setFriends
                 */
                me.setFriend(sc.nextLine());
            }
            else if(choice == 7) { //affiche tout

                getAllMessages = me.getMessages();
                getAllFriends = me.getFriends();

                int i = 0;
                int j = 0;

                me.screenInfos();

                System.out.println("\r\n\r\nMessages : \r\n");

                for(String message : getAllMessages) {

                    System.out.println("------------------\r\n" + i + "\r\n" + message);
                    i++;
                }

                System.out.println("\r\n\r\nFriends : \r\n");

                for(String friend : getAllFriends) {

                    System.out.println("------------------\r\n" + j + "\r\n" + friend);
                    j++;
                }

                System.out.println("\r\n\r\n\r\n");
            }
            else if(choice == 0) {

                System.out.println("Bye !");
                finish = true;
            }else {

                System.out.println("ERROR ! Enter 1, 2, 3, 4, 5, 6, 7, or 0 !");
            }
        }while(!finish);//continue de boucler tant que l'utilisateur ne quitte pas
    }

    /**
     * Demande les infos de l'utilisateur
     * @param nb
     *          Le nombre d'infos à demander
     * @return  Un HashMap contenant les infos récupérées
     *
     */

    private static HashMap<String, String> scanInfos(int nb) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, String> toReturn = new HashMap<String, String>();

        if(nb >= 2) {

            System.out.println("Enter your name");
            toReturn.put("name", sc.nextLine());
            System.out.println("Enter your last name");
            toReturn.put("lastName", sc.nextLine());
        }

        if(nb == 4) {

            System.out.println("Enter your city");
            toReturn.put("city", sc.nextLine());
            System.out.println("Enter your age");
            toReturn.put("age", Integer.toString(sc.nextInt()));
            sc.nextLine();
        }

        return toReturn;
    }
}
