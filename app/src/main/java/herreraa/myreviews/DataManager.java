package herreraa.myreviews;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data manager to manage data.  Also hooks up to firebase
 *
 * Created by Al on 1/28/2017.
 */
public class DataManager extends BaseActivity{
    //fields
    private static DataManager dm;
    private List<Book> itemList;

    /**
     * basic constructor
     */
    private DataManager() {
        itemList = new ArrayList<>();
    }

    /**
     * Singleton to make sure "There can be only one" -Highlander
     *
     * @return
     */
    public static DataManager getDataManager() {
        if (dm == null) {
            dm = new DataManager();
        }
        return dm;
    }

    /**
     * getter for itemlist
     *
     * @return list of books
     */
    public List<Book> getItemList() {
        Collections.sort(itemList);
        return itemList;
    }

    /**
     * Getter for getting a book
     *
     * @param itemId id of the book
     * @return the book object
     */
    public Book getItem(int itemId) {
        Book item = null;
        for (int i = 0; i<itemList.size(); i++) {
            itemList.get(i);
            if (i == itemId) {
                item = itemList.get(i);
                break;
            }
        }
        return item;
    }

    /**
     * for deleting a book object
     *
     * @param book book object to delete
     */
    public void deleteItem(Book book) {
        itemList.remove(book);
        updateDatabase();
    }

    /**
     * Add a book to the list
     *
     * @param book book object to be added to the list
     */
    public void addItem(Book book) {
        itemList.add(book);
        updateDatabase();
    }

    /**
     * update database method
     */
    public void editItem() {
        updateDatabase();
    }

    /**
     * get the size of the list
     *
     * @return int of the list size
     */
    public int getSize() {
        return itemList.size();
    }

    /**
     * get the full list
     *
     * @return the list of books
     */
    public List<Book> getList() {
        Collections.sort(itemList);
        return itemList;
    }

    /**
     * setter fo the book list
     *
     * @param list the book list
     */
    public void setList(List<Book> list) {
        this.itemList = list;
    }

    /**
     * Get a book by book title
     *
     * @param name the title of the book
     * @return return the book object
     */
    public Book getBook(String name) {
        for (Book b: itemList) {
            if (b.getBookTitle().equals(name)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Method to update the database in firebase
     */
    private void updateDatabase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().
                getReference("book/" + userId);
        ref.push();
        ref.setValue(itemList);
    }

    /**
     * My gosh is this thing everywhere?
     */
    @Override
    public void setUpValueEventListeners() {

    }
}

















