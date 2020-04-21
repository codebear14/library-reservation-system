package models;

public class Book {

    int bookId;
    String bookName;
    String topicName;
    String authorName;
    int bookAvailability;

    public Book(int bookId, String bookName, String topicName, String authorName, int bookAvailability) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.topicName = topicName;
        this.authorName = authorName;
        this.bookAvailability = bookAvailability;
    }

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getBookAvailability() {
        return bookAvailability;
    }
}
