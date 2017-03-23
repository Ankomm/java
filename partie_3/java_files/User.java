import java.util.*;

/**
 * User est l'utilisateur utilisant ce programme
 * @author alix.hemery
 * @version 1.0
 */
public class User extends People implements Relation {

    private String city;
    private String password;
    private String pseudo;
    private int age;
    private LinkedList<String> messages = new LinkedList<String>();
    private LinkedList<String> friends = new LinkedList<String>();
    private Scanner sc = new Scanner(System.in);


    /**
     * Premier constructeur
     * Aucun paramètres, aucun retour, juste donne des valeurs lambda aux attributs
     */
    public User(String newPassword) {

        name = "John";
        lastName = "Doe";
        city = "Nowhere";
        age = -1;
        password = newPassword;
    }

    /**
     * Constrcteur à deux paramètres
     * Aucun retour
     *
     * @param inName sera attibué à name
     * @param inLastName sera attribué à lastName
     *
     */
    public User(String inName, String inLastName, String newPassword) {

        name = inName;
        lastName = inLastName;
        city = "Nowhere";
        age = -1;
        password = newPassword;
    }

    /**
     * Constructeur à quatre paramètres
     * Aucun retour
     *
     * @param inName sera attribué à name
     * @param inLastName sera attribué à lastName
     * @param inCity sera attribué à city
     * @param inAge sera attribué à age
     */
    public User(String inName, String inLastName, String inCity, int inAge, String newPassword) {

        name = inName;
        lastName = inLastName;
        city = inCity;
        age = inAge;
        password = newPassword;
    }

    /**
     * Méthode sans paramètre ni retour, affichant les informations de l'utilisateur courant
     */
    public void screenInfos() {

        System.out.println("Your name is " + name + " " + lastName + ", you're " + age + " old and you're living in " + city);
    }

    public void screenAll() {

        LinkedList<String> getAllMessages = getMessages();
        LinkedList<String> getAllFriends = getFriends();

        int i = 0;
        int j = 0;

        screenInfos();

        System.out.println("\r\n\r\nMessages : \r\n");

        for (String message : getAllMessages) {

            System.out.println("------------------\r\n" + i + "\r\n" + message);
            i++;
        }

        System.out.println("\r\n\r\nFriends : \r\n");

        for (String friend : getAllFriends) {

            System.out.println("------------------\r\n" + j + "\r\n" + friend);
            j++;
        }

        System.out.println("\r\n\r\n\r\n");
    }

    /**
     * Getter sans paramètre retournant les infos de l'utilisateur courant
     *
     * @return un HashMap contenant les informations de l'utilisateur courant
     */
    public HashMap<String, String> getInfos() {

        HashMap<String, String> infosReturn = new HashMap<String, String>();

        infosReturn.put("name", name);
        infosReturn.put("lastName", lastName);
        infosReturn.put("city", city);
        infosReturn.put("age", Integer.toString(age));

        return infosReturn;
    }

    /**
     * Getter sans paramètre retournant les messages de l'utilisateur courant
     *
     * @return un LinkedList contenant les messages de l'utilisateur courant
     */
    public LinkedList<String> getMessages() { //renvoie les messages

        return messages;
    }

    public void getOneMessage() {

        LinkedList<String> getAllMessages = getMessages();
        int getIndexMessage;
        boolean messageError = false;

        if (getAllMessages.size() > 0) {

            do {

                messageError = false;

                System.out.println("Index of the message (0 to " + (getAllMessages.size() - 1) + ")");
                getIndexMessage = sc.nextInt();
                sc.nextLine();

                if (getIndexMessage >= 0 && getIndexMessage < getAllMessages.size()) {

                    System.out.println("------------------\r\n" + getAllMessages.get(getIndexMessage));
                } else {

                    System.out.println("ERROR ! Enter a number between 0 and " + (getAllMessages.size() - 1) + " !");
                    messageError = true;
                }
            } while (messageError);//continue de boucler si il y a une erreur
        } else {

            System.out.println("No message there");
        }
    }

    /**
     * Setter sans retour modifiant les informations de l'utilisateur
     *
     * @param setName sera attribué à name
     * @param setLastName sera attribué à lastName
     * @param setCity sera attribué à city
     * @param setAge sera attribué à age
     */
    public void setInfos(String setName, String setLastName, String setCity, int setAge) { //attribue les infos passées en paramètre

        name = setName;
        lastName = setLastName;
        city = setCity;
        age = setAge;
    }

    /**
     * Setter sans retour ajoutant un message à l'utilisateur courant
     *
     * @param message ajoute un élément au LinkedList 'messages'
     */
    public void setMessage(String message) { //ajoute un message

        messages.add(message);
    }

    /**
     * Supprime un message identifié par son index
     */
    public void delMessage() {

        LinkedList<String> getAllMessages = getMessages();
        int getIndexMessage;
        boolean messageError = false;

        if (getAllMessages.size() > 0) {

            do {

                messageError = false;

                System.out.println("Index of the message to delete (0 to " + (getAllMessages.size() - 1) + ")");
                getIndexMessage = sc.nextInt();
                sc.nextLine();

                if (getIndexMessage >= 0 && getIndexMessage < getAllMessages.size()) {

                    messages.remove(getIndexMessage);
                    System.out.println("Message deleted");
                } else {

                    System.out.println("ERROR ! Enter a number between 0 and " + (getAllMessages.size() - 1) + " !");
                    messageError = true;
                }
            } while (messageError);//continue de boucler si il y a une erreur
        } else {

            System.out.println("No message there");
        }
    }

    public void delMessageSoft(int index) {

        messages.remove(index);
    }

    /**
     * Donne la valeur 'null' à un message identifié par son index
     */
    public void nullMessage() {

        LinkedList<String> getAllMessages = getMessages();
        int getIndexMessage;
        boolean messageError = false;

        if (getAllMessages.size() > 0) {

            do {

                messageError = false;

                System.out.println("Index of the message which will be set to null (0 to " + (getAllMessages.size() - 1) + ")");
                getIndexMessage = sc.nextInt();
                sc.nextLine();

                if (getIndexMessage >= 0 && getIndexMessage < getAllMessages.size()) {

                    messages.set(getIndexMessage, null);
                    System.out.println("Message setted to null");
                } else {

                    System.out.println("ERROR ! Enter a number between 0 and " + (getAllMessages.size() - 1) + " !");
                    messageError = true;
                }
            } while (messageError);//continue de boucler si il y a une erreur
        } else {

            System.out.println("No message there");
        }


    }

    /**
     * Setter sans retour ajoutant un ami à l'utilisateur courant
     *
     * @param friendName ajoute un élément au LinkedList 'friends'
     */
    public void setFriend(String friendName) { //ajoute un ami

        friends.add(friendName);
    }

    /**
     * Getter sans paramètre retournant les amis de l'utilisateur courant
     *
     * @return un LinkedList contenant les amis de l'utilisateur courant
     */
    public LinkedList<String> getFriends() { //renvoie les amis

        return friends;
    }

    public void getOneFriend() {

        LinkedList<String> getAllFriends = getFriends();
        int getIndexFriend;
        boolean friendError = false;

        if (getAllFriends.size() > 0) {

            do {

                friendError = false;

                System.out.println("Index of the friend (0 to " + (getAllFriends.size() - 1) + ")");
                getIndexFriend = sc.nextInt();
                sc.nextLine();

                if (getIndexFriend >= 0 && getIndexFriend < getAllFriends.size()) {

                    System.out.println("------------------\r\n" + getAllFriends.get(getIndexFriend));
                } else {

                    System.out.println("ERROR ! Enter a number between 0 and " + (getAllFriends.size() - 1) + " !");
                    friendError = true;
                }
            } while (friendError);//continue de boucler si il y a une erreur
        } else {

            System.out.println("You have no friend");
        }
    }

    /**
     * Supprime un ami identifié par son index
     */
    public void delFriend() {

        LinkedList<String> getAllFriends = getFriends();
        int getIndexFriend;
        boolean friendError = false;

        if (getAllFriends.size() > 0) {

            do {

                friendError = false;
                System.out.println("Index of the friend to delete (0 to " + (getAllFriends.size() - 1) + ")");
                getIndexFriend = sc.nextInt();
                sc.nextLine();

                if (getIndexFriend >= 0 && getIndexFriend < getAllFriends.size()) {

                    friends.remove(getIndexFriend);
                    System.out.println("DEL OK");
                } else {

                    System.out.println("ERROR ! Enter a number between 0 and " + (getAllFriends.size() - 1) + " !");
                    friendError = true;
                }

            } while (friendError);
        } else {

            System.out.println("You have no friend");
        }


    }

    /**
     * Récupère le mot de passe de l'utilisateur courant
     *
     * @return mot de passe
     */
    public String getPassword() {

        return password;
    }

    public String getName() {

        return name;
    }

    public void connexion() {


    }

    public void deconnexion() {


    }

    public void addPeople() {


    }
}






