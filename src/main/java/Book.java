public class Book {

    private String title;
    private String ISBN;
    private String year;
    private String author;
    private int pages;


    public Book(String title, String ISBN, String year, String author, int pages) {
        this.title = title;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }


    public String toString(){

        return "Title: " + title + ", ISBN number: " + ISBN + ", year of publication : " + year + ", author: " + author + ", number of pages: " + pages;
    }

}
