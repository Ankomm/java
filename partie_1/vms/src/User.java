import java.util.*;

/**
 * User est l'utilisateur utilisant ce programme
 * @author alix.hemery
 * @version 1.0
 */
public class User {

    private String name;
    private String lastName;
    private String city;
    private int age;
    private LinkedList<String> messages = new LinkedList<String>();
    private LinkedList<String> friends = new LinkedList<String>();

    /**
     * Premier constructeur
     * Aucun paramètres, aucun retour, juste donne des valeurs lambda aux attributs
     */
    public User() {

        name = "John";
        lastName = "Doe";
        city = "Nowhere";
        age = -1;
    }

    /**
     * Constrcteur à deux paramètres
     * Aucun retour
     *
     * @param inName sera attibué à name
     * @param inLastName sera attribué à lastName
     *
     */
    public User(String inName, String inLastName) {

        name = inName;
        lastName = inLastName;
        city = "Nowhere";
        age = -1;
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
    public User(String inName, String inLastName, String inCity, int inAge) {

        name = inName;
        lastName = inLastName;
        city = inCity;
        age = inAge;
    }

    /**
     * Méthode sans paramètre ni retour, affichant les informations de l'utilisateur courant
     */
    public void screenInfos() {

        System.out.println("Your name is " + name + " " + lastName + ", you're " + age + " old and you're living in " + city);
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

    /**
     * Getter sans paramètre retournant les amis de l'utilisateur courant
     *
     * @return un LinkedList contenant les amis de l'utilisateur courant
     */
    public LinkedList<String> getFriends() { //renvoie les amis

        return friends;
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
     * Setter sans retour ajoutant un ami à l'utilisateur courant
     *
     * @param friendName ajoute un élément au LinkedList 'friends'
     */
    public void setFriend(String friendName) { //ajoute un ami

        friends.add(friendName);
    }

}
