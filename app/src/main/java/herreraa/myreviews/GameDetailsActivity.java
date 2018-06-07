package herreraa.myreviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class GameDetailsActivity extends BaseActivity {

    //fields
    private Game thisGame;
    private TextView txtGameTitle;
    private TextView txtGameCompany;
    private TextView txtGameGenre;
    private RatingBar rtgStars;
    private TextView txtGameReviewDate;
    private EditText txtGameReview;
    private Button btnEdit;
    private Button btnSave;
    private Button btnDelete;
    private GameDataManager dm = GameDataManager.getDataManager();
    private boolean isNewGame;
    List<Game> list;
    DatabaseReference ref;

    /**
     * The on create method
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
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
        getMenuInflater().inflate(R.menu.menu_game_details, menu);
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
            builder.setMessage("Video Games")
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
            thisGame = new Game();
            isNewGame = true;
        } else {
            thisGame = dm.getItem((id));
            isNewGame = false;
        }

        //title
        txtGameTitle = (TextView)findViewById(R.id.txtGameTitle);
        txtGameTitle.setText(thisGame.getGameTitle());
        //company
        txtGameCompany = (TextView)findViewById(R.id.txtGameCompany);
        txtGameCompany.setText(thisGame.getCompany());
        //genre
        txtGameGenre = (TextView)findViewById(R.id.txtGameGenre);
        txtGameGenre.setText(thisGame.getGameGenre());
        //stars
        rtgStars = (RatingBar)findViewById(R.id.rtgStars);
        rtgStars.setRating(thisGame.getGameStars());
        //book date started
        txtGameReviewDate = (TextView)findViewById(R.id.txtGameReviewDate);
        txtGameReviewDate.setText(thisGame.getGameDateReview());
        //the review
        txtGameReview = (EditText)findViewById(R.id.txtGameReview);
        txtGameReview.setText(thisGame.getGameReview());

        //Set up buttons
        btnEdit = (Button) findViewById(R.id.btnGameEdit);
        btnSave = (Button) findViewById(R.id.btnGameSave);
        rtgStars = (RatingBar) findViewById(R.id.rtgStars);
        btnDelete = (Button) findViewById(R.id.btnGameDelete);
        btnDelete.setEnabled(true);

        //Enable detail screen if new book
        if(id == -2) {
            //Activate all the textviews
            txtGameTitle.setEnabled(true);
            txtGameCompany.setEnabled(true);
            txtGameGenre.setEnabled(true);
            txtGameReviewDate.setEnabled(true);
            txtGameReview.setEnabled(true);
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
     * Again, really?
     */
    @Override
    public void setUpValueEventListeners() {

    }

    /**
     * Handles the edit button click event
     *
     * @param view the edit label
     */
    public void btnEditOnClick(View view) {
        //Activate all the textviews
        txtGameTitle.setEnabled(true);
        txtGameCompany.setEnabled(true);
        txtGameGenre.setEnabled(true);
        txtGameReviewDate.setEnabled(true);
        txtGameReview.setEnabled(true);
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
        thisGame.setGameTitle(txtGameTitle.getText().toString());
        thisGame.setCompany(txtGameCompany.getText().toString());
        thisGame.setGameGenre(txtGameGenre.getText().toString());
        thisGame.setGameDateReview(txtGameReviewDate.getText().toString());
        thisGame.setGameStars(rtgStars.getRating());
        thisGame.setGameReview(txtGameReview.getText().toString());

        //if new add new item to list
        if(isNewGame == true) {
            dm.addItem(thisGame);
            isNewGame = false;
        }

        // save to firebase
        ref = FirebaseDatabase.getInstance().getReference("game/" + userId);
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
        dm.deleteItem(thisGame);

        // save to firebase
        ref = FirebaseDatabase.getInstance().getReference("game/" + userId);
        ref.push();
        ref.setValue(dm.getItemList());

        //Go back to list page
        finish();
    }
}

















