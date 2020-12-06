package myCode;

public class artists {

    private String name;
    private String genre;
    private String description;
    private String discography;

    public artists(String name, String genre, String description, String discography) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.discography = discography;
    }

    public artists() {
        this.name = "";
        this.genre = "";
        this.description = "";
        this.discography = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscography() {
        return discography;
    }

    public void setDiscography(String discography) {
        this.discography = discography;
    }
}
