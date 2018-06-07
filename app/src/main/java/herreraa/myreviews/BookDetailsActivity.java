package herreraa.myreviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity class for book details screen
 *
 * @author Al Herrera
 * @version 1/15/2017
 */
public class BookDetailsActivity extends BaseActivity {

    //fields
    private Book thisBook;
    private TextView txtBookTitle;
    private TextView txtBookAuthor;
    private TextView txtBookGenre;
    private RatingBar rtgStars;
    private TextView txtBookDateStarted;
    private TextView txtBookDateFinished;
    private EditText txtBookReview;
    private Button btnEdit;
    private Button btnSave;
    private Button btnDelete;
    private DataManager dm = DataManager.getDataManager();
    private boolean isNewBook;
    List<Book> list;
    DatabaseReference ref;

    /**
     * Builds the activity when first being created.
     *
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add back button to action bar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Builds the action bar option menu
     *
     * @param menu the action bar menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_details, menu);
        return true;
    }

    /**
     * Handles selections from the action bar option menu.
     *
     * @param item the menu item selected
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Books")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * OnResume loads up the book and sets the ui up
     */
    @Override
    protected void onResume() {
        super.onResume();

        //get current book
        //need to grab book sent from listview or clear and make new book
        //get data
        Intent intent = getIntent();
        int id = intent.getIntExtra(BookListActivity.ITEM_ID, -1);
        if (id==-1){
            finish();
        }

        // Determine if new or edit
        if(id==-2){
            thisBook = new Book();
            isNewBook = true;
        } else {
            thisBook = dm.getItem((id));
            isNewBook = false;
        }

        //title
        txtBookTitle = (TextView)findViewById(R.id.txtBookTitle);
        txtBookTitle.setText(thisBook.getBookTitle());
        //author
        txtBookAuthor = (TextView)findViewById(R.id.txtBookAuthor);
        txtBookAuthor.setText(thisBook.getBookAuthor());
        //genre
        txtBookGenre = (TextView)findViewById(R.id.txtBookGenre);
        txtBookGenre.setText(thisBook.getBookGenre());
        //stars
        rtgStars = (RatingBar)findViewById(R.id.rtgStars);
        rtgStars.setRating(thisBook.getBookStars());
        //book date started
        txtBookDateStarted = (TextView)findViewById(R.id.txtBookDateStarted);
        txtBookDateStarted.setText(thisBook.getBookDateStarted());
        //book date finished
        txtBookDateFinished = (TextView)findViewById(R.id.txtBookDateFinished);
        txtBookDateFinished.setText(thisBook.getBookDateFinished());
        //the review
        txtBookReview = (EditText)findViewById(R.id.txtGameReview);
        txtBookReview.setText(thisBook.getBookReview());

        //Set up buttons
        btnEdit = (Button) findViewById(R.id.btnGameEdit);
        btnSave = (Button) findViewById(R.id.btnGameSave);
        rtgStars = (RatingBar) findViewById(R.id.rtgStars);
        btnDelete = (Button) findViewById(R.id.btnGameDelete);
        btnDelete.setEnabled(true);

        //Enable detail screen if new book
        if(id == -2) {
            //Activate all the textviews
            txtBookTitle.setEnabled(true);
            txtBookAuthor.setEnabled(true);
            txtBookGenre.setEnabled(true);
            txtBookDateStarted.setEnabled(true);
            txtBookDateFinished.setEnabled(true);
            txtBookReview.setEnabled(true);
            btnDelete.setEnabled(false);
            //Disable edit button
            btnEdit.setEnabled(false);
            //Enable save button and rating bar
            btnSave.setEnabled(true);
            rtgStars.setIsIndicator(false);
            list = dm.getItemList();
        }
    }

    /**
     * Handles the edit button click event
     *
     * @param view the edit label
     */
    public void btnEditOnClick(View view) {
        //Activate all the textviews
        txtBookTitle.setEnabled(true);
        txtBookAuthor.setEnabled(true);
        txtBookGenre.setEnabled(true);
        txtBookDateStarted.setEnabled(true);
        txtBookDateFinished.setEnabled(true);
        txtBookReview.setEnabled(true);
        //Disable edit button
        btnEdit.setEnabled(false);
        //Enable save button and rating bar
        btnSave.setEnabled(true);
        rtgStars.setIsIndicator(false);
        list = dm.getItemList();

    }

    /**
     * handles the save button click event
     *
     * @param view  the save button label
     */
    public void btnSaveOnClick(View view) {
        //Git R Dun
        //Gotta save the info to the object
        thisBook.setBookTitle(txtBookTitle.getText().toString());
        thisBook.setBookAuthor(txtBookAuthor.getText().toString());
        thisBook.setBookGenre(txtBookGenre.getText().toString());
        thisBook.setBookDateStarted(txtBookDateStarted.getText().toString());
        thisBook.setBookDateFinished(txtBookDateFinished.getText().toString());
        thisBook.setBookStars(rtgStars.getRating());
        thisBook.setBookReview(txtBookReview.getText().toString());

        //if new add new item to list
        if(isNewBook == true) {
            list.add(thisBook);
            isNewBook = false;
        }

        // save to firebase
        ref = FirebaseDatabase.getInstance().getReference("book/" + userId);
        ref.push();
        ref.setValue(dm.getItemList());

        //Go back to list page
        finish();

    }

    /**
     * handles the delete button
     *
     * @param view the delete button label
     */
    public void btnDeleteOnClick(View view) {
        dm.deleteItem(thisBook);

        // save to firebase
        ref = FirebaseDatabase.getInstance().getReference("book/" + userId);
        ref.push();
        ref.setValue(dm.getItemList());

        //Go back to list page
        finish();
    }

    /**
     * handles the Goodreads button click event
     *
     * @param view the Goodreads button label
     */
    public void imgGoodreadsOnClick(View view) {
        Toast.makeText(getBaseContext(),"Goodreads Button Clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * handles the Facebook button click event
     *
     * @param view the Facebook button label
     */
    public void imgFacebookOnClick(View view) {
        Toast.makeText(getBaseContext(),"Facebook Button Clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * handles the Twitter button click event
     *
     * @param view the Twitter button label
     */
    public void imgTwitterOnClick(View view) {
        Toast.makeText(getBaseContext(),"Twitter Button Clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * abstract method that seems to be everywhere
     *
     */
    @Override
    public void setUpValueEventListeners() {

    }
}



























