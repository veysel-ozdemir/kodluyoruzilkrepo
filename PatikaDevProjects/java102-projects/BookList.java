import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookList {
    public static void main(String[] args) throws ParseException {
        ArrayList<MyBook> booksList = new ArrayList<>();
        booksList.add(new MyBook("WW1", "Alex Andre", 420, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        booksList.add(new MyBook("WW2", "Alex Andre", 203, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2013")));
        booksList.add(new MyBook("In Love", "Paulina Paul", 100, new SimpleDateFormat("dd-MM-yyyy").parse("11-01-2001")));
        booksList.add(new MyBook("Brotherhood", "Mark Markovic", 120, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));
        booksList.add(new MyBook("The Debut", "Cristian Thun", 90, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012")));

        HashMap<String, String> hashMap = new HashMap<>();
        booksList.forEach(book -> hashMap.put(book.getBookName(), book.getAuthorName()));
        hashMap.forEach((book, author) -> System.out.println(book + " : " + author));

        List<MyBook> newList = booksList.stream().filter(book -> book.getPageNumber() > 100).toList();
        System.out.println("\n-- Books with 100+ pages --");
        newList.forEach(book -> System.out.println(book.getBookName() + " : " + book.getPageNumber()));
    }
}

class MyBook {
    private String bookName, authorName;
    private int pageNumber;
    private Date publicationDate;

    public MyBook(String bookName, String authorName, int pageNumber, Date publicationDate) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.pageNumber = pageNumber;
        this.publicationDate = publicationDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
