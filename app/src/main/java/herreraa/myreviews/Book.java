package herreraa.myreviews;

import android.support.annotation.NonNull;

/**
 * This class is one book
 *
 * @author Al Herrera
 * @version 1/15/2017
 */

class Book implements Comparable<Book> {

    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private float bookStars;
    private String bookReview;
    private String bookDateStarted;
    private String bookDateFinished;

    /**
     * Default constructor.
     */
    Book() {

    }

    /**
     * Access the book title
     *
     * @return the book title
     */
    String getBookTitle() {
        return bookTitle;
    }

    /**
     * Allows the title to be changed
     *
     * @param bookTitle the title of the book
     */
    void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * Access the book author
     *
     * @return a String of the book author
     */
    String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * Allows the author to be changed
     *
     * @param bookAuthor the book author's name
     */
    void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * Access the book genre
     *
     * @return a String of the book genre
     */
    String getBookGenre() {
        return bookGenre;
    }

    /**
     * Allows the book genre to change
     *
     * @param bookGenre the genre of the book
     */
    void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    /**
     * Access to the number of stars
     *
     * @return a int of the number of stars
     */
    float getBookStars() {
        return bookStars;
    }

    /**
     * Allows the number of stars to change
     *
     * @param bookStars the number of stars for a book
     */
    void setBookStars(float bookStars) {
        this.bookStars = bookStars;
    }

    /**
     * Access the review of the book
     *
     * @return a String of the book review
     */
    String getBookReview() {
        return bookReview;
    }

    /**
     * Allows the book review to change
     *
     * @param bookReview the review of the book
     */
    void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    /**
     * Access the date started reading the book
     *
     * @return a String of the book started reading date
     */
    String getBookDateStarted() {
        return bookDateStarted;
    }

    /**
     * Allows the date started reading the book to change
     *
     * @param bookDateStarted the date started reading the book
     */
    void setBookDateStarted(String bookDateStarted) {
        this.bookDateStarted = bookDateStarted;
    }

    /**
     * Access the date finished reading the book
     *
     * @return a String of the date finished reading the book
     */
    String getBookDateFinished() {
        return bookDateFinished;
    }

    /**
     * Allows the date finished reading the book to change
     *
     * @param bookDateFinished the date finished reading the book
     */
    void setBookDateFinished(String bookDateFinished) {
        this.bookDateFinished = bookDateFinished;
    }

    /**
     * to string for book
     *
     * @return return the book title
     */
    @Override
    public String toString() {
        return bookTitle;
    }

    /**
     * compare to for book
     *
     * @param book book object which holds a book
     * @return int for comparison purposes
     */
    @Override
    public int compareTo(@NonNull Book book) {
        return this.getBookTitle().compareTo(book.bookTitle);
    }
}

