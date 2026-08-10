    package model;

    import java.io.Serializable;

    public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public Author() {
    }
    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Author(Author other) {
        this.id = other.id;
        this.name = other.name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    }