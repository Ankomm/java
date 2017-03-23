import java.util.*;

/**
 *
 */
public class Moderator extends User implements Waged {

    private int level;

    public Moderator(String password, int setLevel) {

        super(password);
        level = setLevel;
    }

    public Moderator(String inName, String inLastName, String password, int setLevel) {

        super(inName, inLastName, password);
        level = setLevel;
    }

    public Moderator(String inName, String inLastName, String inCity, int inAge, String newPassword, int setLevel) {

        super(inName, inLastName, inCity, inAge, newPassword);
        level = setLevel;
    }

    public void setLevel(int inLevel) {

        level = inLevel;
    }

    public int getLevel() {

        return level;
    }

    public void delOtherMessage() {


    }

    public void getPaid() {


    }
}
