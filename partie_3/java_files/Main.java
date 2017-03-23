import java.util.*;

/**
 * @author alix.hemery
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {

        boolean finish = false; //boucle principale, si true arrête le programme
        boolean infoError; //boucle d'erreur, si true alors il y a une erreur dans la saisie du nombre d'informations
        boolean userError;
        boolean noUser;
        boolean errorPassword;
        boolean errorModo;
        boolean modoUserError;

        String currentName = null;
        String getCreateModo;

        int newUser = 0;
        int modoLevel;
        int modoUser;

        HashMap<String, User> users = new HashMap<String, User>();
        User currentUser = null;

        /**
         * Declaration de l'utilisateur courant
         * @see User
         */
        User me = null;

        int userChoice;
        int infoChoice; //nombre rentré par l'utilisateur pour le nombre d'infos qu'il souhaite donner
        int choice; //choix du menu

        HashMap<String, String> hashReturned = new HashMap<String, String>(); //collection retournée par la fonction scanInfos

        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("Do you want to : \r\n1. Create a new user\r\n2. Select an user\r\n3. Generate an exception\r\n0. Exit");
            userChoice = sc.nextInt();
            sc.nextLine();

            do {

                userError = false;

                switch (userChoice) {

                    case 1:

                        do {

                            infoError = false;
                            System.out.println("Are you a\r\n1. User\r\n2. Moderator ?");
                            modoUser = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Which informations do you want to give us ? No one : 0, name/last name : 2, all(name, last name, city, age) : 4");
                            infoChoice = sc.nextInt();
                            sc.nextLine();

                            if ((infoChoice != 0) && (infoChoice != 2) && (infoChoice != 4)) {

                                System.out.println("ERROR ! Enter 0, 2 or 4 !");
                                infoError = true;
                            }


                        }while(infoError); //continue de boucler si il y a une erreur

                        do {

                            modoUserError = false;

                            switch (modoUser) {

                                case 1:


                                    /**
                                     * Demande les infos (en paramètre le nombre d'infos que souhaite donner l'utilisateur) à l'utilisateur et les stock
                                     * @see scanInfos
                                     */
                                    hashReturned = scanInfos(infoChoice);

                                    switch (infoChoice) {

                                        case 0:

                                            me = new User(hashReturned.get("password"));

                                            break;

                                        case 2:

                                            me = new User(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("password"));

                                            break;

                                        case 4:

                                            me = new User(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("city"), Integer.parseInt(hashReturned.get("age")), hashReturned.get("password"));

                                            break;
                                    }

                                    break;

                                case 2 :

                                    do {

                                        errorModo = false;

                                        System.out.println("Which level ? (1 : can custom or delete all users' messages, 2 : can delete users)");
                                        modoLevel = sc.nextInt();
                                        sc.nextLine();

                                    } while (errorModo);

                                    hashReturned = scanInfos(infoChoice);

                                    switch (infoChoice) {

                                        case 0:

                                            me = new Moderator(hashReturned.get("password"), modoLevel);

                                            break;

                                        case 2:

                                            me = new Moderator(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("password"), modoLevel);

                                            break;

                                        case 4:

                                            me = new Moderator(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("city"), Integer.parseInt(hashReturned.get("age")), hashReturned.get("password"), modoLevel);

                                            break;
                                    }

                                    break;

                                default :

                                    System.out.println("Enter 1, 2 or 3 !");
                                    modoUserError = true;

                            }
                        }while(modoUserError);

                        users.put(me.getName(), me);
                        currentName = me.getName();

                        break;

                    case 2 :

                        String searchName;
                        String searchPassword;

                        do {

                            noUser = false;

                            if (users.isEmpty()) {

                                System.out.println("There's no user");
                                userError = true;
                                userChoice = 1;
                            } else {

                                System.out.println("List of users : \r\n");

                                for (String key : users.keySet()) {

                                    System.out.println(key + "\r\n");
                                }

                                System.out.println("Enter your name");
                                searchName = sc.nextLine();

                                if (users.containsKey(searchName)) {

                                    currentUser = users.get(searchName);

                                    do {

                                        errorPassword = false;
                                        System.out.println("Enter your password");
                                        searchPassword = sc.nextLine();

                                        if (currentUser.getPassword().equals(searchPassword)) {

                                            currentName = searchName;

                                        } else {

                                            System.out.println("Wrong password !");
                                            errorPassword = true;
                                        }
                                    }while(errorPassword);

                                } else {

                                    System.out.println("There's no one called " + searchName);
                                    noUser = true;
                                }
                            }

                        }while(noUser);

                        break;

                    case 3 :

                        System.out.println("Enter a String");

                        try {

                            System.out.println(sc.nextInt());
                            sc.nextLine();
                        } catch (InputMismatchException e) {

                            System.out.println("You should have entered an int !");
                        }



                        break;

                    case 0 :

                        System.out.println("Bye !");
                        finish = true;

                        break;

                    default:

                        System.out.println("Enter0, 1, 2 or 3 !");
                        userError = true;

                }
            }while(userError);

            do {

                finish = false;

                System.out.println("==============\r\nMenu\r\n1. See my infos\r\n2. Cutsom my infos\r\n3. See a message\r\n4. Write a message\r\n5. Set a message to null\r\n6. Delete a message\r\n7. See a friend\r\n8. Add a friend\r\n9. Delete a friend\r\n10. See all");

                if ((users.get(currentName).getClass().getName().equals("Moderator"))) {

                    System.out.println("11. Delete a message from another user");

                    if (((Moderator)users.get(currentName)).getLevel() == 2) {

                        System.out.println("12. Delete an user");
                    }
                }

                System.out.println("0. Exit\r\n==============");
                choice = sc.nextInt();
                sc.nextLine();

                switch(choice) {

                    case 1 :

                        /**
                         * Affiche les informations de l'utilisateur
                         * @see screenInfos
                         */
                        users.get(currentName).screenInfos();

                        break;

                    case 2 :

                        /**
                         * Demande les infos à l'utilisateur afin de les mettre à jour, le 4 passé en paramètre signifie que toutes les infos seront demandées
                         * @see scanInfos
                         */
                        hashReturned = scanInfos(4);
                        users.get(currentName).setInfos(hashReturned.get("name"), hashReturned.get("lastName"), hashReturned.get("city"), Integer.parseInt(hashReturned.get("age")));

                        break;

                    case 3 :

                        /**
                         * Récupère tout les messages de l'utilisateur
                         * @see getMessages
                         */
                        users.get(currentName).getOneMessage();

                        break;

                    case 4 :

                        System.out.println("Enter your message");

                        /**
                         * Ajoute le message récupéré
                         *@see setMessage
                         */
                        users.get(currentName).setMessage(sc.nextLine());

                        break;

                    case 5 :

                        users.get(currentName).nullMessage();

                        break;

                    case 6 :

                        users.get(currentName).delMessage();

                        break;

                    case 7 :

                        /**
                         * Récupère tout les amis
                         * @see getFriends
                         */
                        users.get(currentName).getOneFriend();

                        break;

                    case 8 :

                        System.out.println("Enter a new friend");

                        /**
                         * Ajoute l'amis récupéré
                         * @see setFriends
                         */
                        users.get(currentName).setFriend(sc.nextLine());

                        break;

                    case 9 :

                        users.get(currentName).delFriend();

                        break;

                    case 10 :

                        users.get(currentName).screenAll();

                        break;

                    case 11 :

                        String getName;
                        int getIndex;

                        if((users.get(currentName).getClass().getName().equals("Moderator"))) {

                            int i;

                            for(String anUser : users.keySet()) {

                                i = 0;
                                LinkedList<String> getAllMessages = users.get(anUser).getMessages();

                                for(String message : getAllMessages) {

                                    System.out.println("------------------\r\n" + anUser + " " + i + "\r\n" + message);

                                    i ++;
                                }
                            }

                            System.out.println("Delete which message ?\r\nName of the user");
                            getName = sc.nextLine();

                            System.out.println("Index of the message");
                            getIndex = sc.nextInt();
                            sc.nextLine();

                            users.get(getName).delMessageSoft(getIndex);

                            System.out.println("Message deleted");

                        } else {

                            System.out.println("You're not a moderator !");
                        }

                        break;

                    case 12 :

                        if((users.get(currentName).getClass().getName().equals("Moderator"))) {

                            if (((Moderator)users.get(currentName)).getLevel() == 2) {

                                for (String key : users.keySet()) {

                                    System.out.println(key + "\r\n");
                                }

                                System.out.println("Delete which user ?");
                                users.remove(sc.nextLine());

                                System.out.println("User deleted");


                            } else {

                                System.out.println("You don't have the right level to do that !");
                            }


                        } else {

                            System.out.println("You're not a moderator !");
                        }

                        break;

                    case 0 :

                        System.out.println("Bye !");
                        finish = true;

                        break;

                    default :

                        System.out.println("ERROR ! Enter 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 or 0 !");

                }
            }while(!finish);//continue de boucler tant que l'utilisateur ne quitte pas

            System.out.println("Do you want to : \r\n0. Exit\r\n1. Create a new user or select another ?");
            newUser = sc.nextInt();
            sc.nextLine();

        }while(newUser == 1);
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

        System.out.println("Enter your password");
        toReturn.put("password", sc.nextLine());

        return toReturn;
    }
}
