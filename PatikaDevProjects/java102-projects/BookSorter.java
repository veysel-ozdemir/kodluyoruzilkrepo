import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class BookSorter {
    public static void main(String[] args) throws ParseException {
        Set<Book> set = new TreeSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        set.add(new Book("Zwang", "Paul", 170, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        set.add(new Book("AAlive", "Alexandre", 120, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        set.add(new Book("Brother", "Alexandre", 150, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        set.add(new Book("Crew", "Alexandre", 220, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        set.add(new Book("Aalive", "Alexandre", 120, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));

        for (Book book : set) {
            System.out.println(book.getBookName());
        }

        Set<Book> newSet = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPages() < o2.getPages() ? 1 : -1;
            }
        });

        newSet.addAll(set);

        for (Book book : newSet) {
            System.out.println(book.getBookName() + " : " + book.getPages());
        }
    }
}

class Book implements Comparable<Book> {
    private String bookName, authorName;
    private int pages;
    private Date publication;

    Book(String bookName, String authorName, int pages, Date publication) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.pages = pages;
        this.publication = publication;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPages() {
        return pages;
    }

    public Date getPublication() {
        return publication;
    }

    public String getBookName() {
        return bookName;
    }


    @Override
    public int compareTo(Book o) {
        return this.getBookName().compareTo(o.getBookName());
    }
}
