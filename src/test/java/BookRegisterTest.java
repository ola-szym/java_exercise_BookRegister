import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


public class BookRegisterTest {

    @Test
    public void shouldCheckIfBookWasGot() {
        //given
        Book book1 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);

        //when
        BookRegister register = new BookRegister();
        register.addBook(book1);
        Book book = register.getBook(book1.getTitle());

        //then
        assertEquals(book1,book);

    }

        @Test(expected = IllegalStateException.class)
        public void shouldNotAddTheSameBookAgain(){
            //given
            Book book1 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);
            Book book2 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);

            //when
            BookRegister register = new BookRegister();
            register.addBook(book1);
            register.addBook(book2);
        }

        @Test
        public void shouldCheckIfBookWithConcreteISBNumberExistsInBookRegister(){
            //given
            Book book1 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);

            //when
            BookRegister register = new BookRegister();
            register.addBook(book1);
            register.checkIfISBNAlreadyExists(book1.getISBN());

            //then
            assertEquals("1234567891234",book1.getISBN());
        }

        @Test
        public void shouldCheckIfAllBooksFromAuthorAreReturned(){
            //given

            Book book1 = new Book("Hobbit", "1234567891234", "1937", "J.R.R.Tolkien", 250);
            Book book2 = new Book("Lord of the Rings", "1234567891235", "1954", "J.R.R.Tolkien", 300);
            Book book3 = new Book("Silmarillion", "1234567891236", "1977", "J.R.R.Tolkien", 150);

            BookRegister bookRegister = new BookRegister();
            bookRegister.addBook(book1);
            bookRegister.addBook(book2);
            bookRegister.addBook(book3);

            //when
            List<Book> allBooksFromAuthor = bookRegister.getAllBooksFromAuthor(book3.getAuthor());

            List<Book> books = new ArrayList<>();
            books.add(book1);
            books.add(book2);
            books.add(book3);

            //then

            assertThat("List equality without order",
                    allBooksFromAuthor, containsInAnyOrder(books.toArray()));

        }
}


