package herreraa.myreviews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Tests for DataManager class
 * @author Al Herrera
 * @version 2/19/2017
 *
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void addItem() throws Exception {
        Book book = new Book();
        book.setBookTitle("Ready Player One");
        DataManager dm = DataManager.getDataManager();

        assertTrue(dm.getItem(0).getBookTitle().equals(book.getBookTitle()));
    }

    @Test
    public void getItemList() throws Exception {
        Book book = new Book();
        book.setBookTitle("Ready Player One");
        DataManager dm = DataManager.getDataManager();
        dm.addItem(book);

        assertTrue(dm.getItemList().toString().equals("[Ready Player One, Ready Player One]"));
    }

    @Test
    public void getItem() throws Exception {
        Book book = new Book();
        book.setBookTitle("Ready Player One");
        DataManager dm = DataManager.getDataManager();
        dm.addItem(book);

        assertTrue(dm.getItem(0).getBookTitle().equals(book.getBookTitle()));
    }

    @Test
    public void deleteItem() throws Exception {
        Book book = new Book();
        book.setBookTitle("Snow Crash");
        DataManager dm = DataManager.getDataManager();
        dm.addItem(book);
        dm.deleteItem(3);

        assertTrue(dm.getItemList().toString().equals("[Ready Player One, Ready Player One, Ready Player One]"));
    }
}
