package ua.kpi.comsys.io8207;

public class Lab4_Book {
    private String title;
    private String subtitle;
    private String authors;
    private String publisher;
    private String isbn13;
    private String pages;
    private String year;
    private String rating;
    private String desc;
    private String price;
    private int image;

    public Lab4_Book(String title, String subtitle, String authors, String publisher, String isbn13,
                     String pages, String year, String rating, String description,
                     String price, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn13 = isbn13;
        this.pages = pages;
        this.year = year;
        this.rating = rating;
        this.desc = description;
        this.price = price;
        this.image = image;
    }

    public String getBookName() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getPages() {
        return pages;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

}
