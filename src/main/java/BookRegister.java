import java.util.*;

public class BookRegister {

    private Map<String, Book> bookList = new HashMap<>();

    public static void main(String[] args) {

        BookRegister bookRegister = new BookRegister();
        Book book1 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);
        bookRegister.checkIfISBNAlreadyExists(book1.getISBN());
        bookRegister.addBook(book1);
        Book book = bookRegister.getBook(book1.getTitle());
        System.out.println("The following book: " + book + " was added into Book Register");

        Book book2 = new Book("Lord of the Rings", "1234567891234", "1954", "J.R.R.Tolkien", 300);
        bookRegister.checkIfISBNAlreadyExists(book2.getISBN());

        Book book3 = new Book("ABC", "1234567891236", "1977", "J.R.R.Tolkien", 150);
        bookRegister.checkIfISBNAlreadyExists(book3.getISBN());
        bookRegister.addBook(book3);
        System.out.println("The following book: " + book3 + " was added into Book Register");

    }


    public void addBook(Book book) {

        String key = book.getTitle();

        if (bookList.containsKey(key)) {
            throw new IllegalStateException("This book already exists in Book Register");
        } else {
            bookList.put(key, book);
        }
    }

    public Book getBook(String title) {

        return bookList.get(title);
    }


    public boolean checkIfISBNAlreadyExists(String ISBN) {
        for (Map.Entry<String, Book> entry : bookList.entrySet()) {
            if (ISBN.equals(entry.getValue().getISBN())) {
                System.out.println("Book with the following ISBN number: " + ISBN + " already exists in Book Register");
                return false;
            }
        }
        return true;
    }

    public List<Book> getAllBooksFromAuthor(String author) {
        List<Book> allBooksFromAuthor = new ArrayList<>();
        for (Map.Entry<String, Book> entry : bookList.entrySet()) {
            if(author.equals(entry.getValue().getAuthor())) {
                allBooksFromAuthor.add(entry.getValue());
            }
        }
        return allBooksFromAuthor;
    }

    public Book getAllBooksByAuthorAndTitle(String author, String title) {
        for (Map.Entry<String, Book> entry : bookList.entrySet()) {
            if(author.equals(entry.getValue().getAuthor()) && title.equals(entry.getValue().getTitle())) {
                return entry.getValue();
            }
        }
        return null;
    }
}



