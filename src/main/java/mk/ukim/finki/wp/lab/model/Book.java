package mk.ukim.finki.wp.lab.model;

public class Book {
    private String title;
    private String genre;
    private double averageRating;

    public Book(){}

    public Book (String title,String genre, double averageRating){
        this.title=title;
        this.genre=genre;
        this.averageRating=averageRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
