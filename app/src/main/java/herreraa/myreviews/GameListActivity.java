package herreraa.myreviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameListActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    //fields
    private ListView listView;
    public final static String ITEM_ID = "itemId";
    private GameDataManager dm = GameDataManager.getDataManager();
    private List<Game> games;
    private DatabaseReference ref;
    private boolean persistenceSet;
    private ArrayAdapter<Game> listAdapter;

    /**
     * On create for fab
     *
     * @param savedInstanceState save state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add back button to action bar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameListActivity.this, GameDetailsActivity.class);
                intent.putExtra(ITEM_ID, -2);
                startActivity(intent);
            }
        });

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
     * onResume fills the list
     */
    @Override
    protected void onResume() {
        super.onResume();
        //fill up the list view
        listView = (ListView) findViewById(R.id.lstGameList);
        games = dm.getItemList();

        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, games);
        if(games != null) {
            listView.setAdapter(listAdapter);
        }
        listView.setOnItemClickListener(this);

    }

    /**
     * Set up menu
     */
    @Override
    public void setUpValueEventListeners() {
        //initalize ref
        ref = FirebaseDatabase.getInstance().getReference("game/" + userId);

        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Game>> type =
                        new GenericTypeIndicator<List<Game>>(){};
                games = dataSnapshot.getValue(type);
                if (games == null) {
                    games = new ArrayList<>();
                }
                dm.setList(games);
                games = dm.getItemList();
                listAdapter.clear();
                listAdapter.addAll(games);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MYLOG", "Failed to read value.", error.toException());
            }
        });
    }

    /**
     * For selecting a game in the list
     *
     * @param adapterView a adapter view
     * @param view view
     * @param i location
     * @param l long location
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, GameDetailsActivity.class);
        intent.putExtra(ITEM_ID, i);
        startActivity(intent);
    }
}




















