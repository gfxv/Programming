package shared.enteties;


import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private final String name;
    private final String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
