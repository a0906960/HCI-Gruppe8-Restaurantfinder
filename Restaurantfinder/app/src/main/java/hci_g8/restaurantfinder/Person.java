package hci_g8.restaurantfinder;


import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String[] likes;
    private String[] dislikes;

    public Person(String name, String[] likes, String[] dislikes){
        setName(name);
        setLikes(likes);
        setDislikes(dislikes);
    }

    public String getName() { return this.name; }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLikes() {
        return this.likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String[] getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(String[] dislikes) {
        this.dislikes = dislikes;
    }
}
