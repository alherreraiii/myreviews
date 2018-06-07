package herreraa.myreviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import static herreraa.myreviews.R.id.fragment;

/**
 * Main Method which controls the title screen
 *
 * @author Al Herrera
 * @version 2-26-2017 1.0
 *
 */
public class TitleScreenActivity extends BaseActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private ValueEventListener dataListener;
    private DataManager dm;
    Fragment fragment;

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
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
        getMenuInflater().inflate(R.menu.menu_no_icon, menu);
        return true;
    }

        /**
     * Handles selections from the actin bar option menu.
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
            builder.setMessage("My Reviews created by Al Herrera")
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
        if (id == R.id.action_sign_out) {
            AuthUI.getInstance().signOut(this);
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * I hope this is the last one.
     */
    @Override
    public void setUpValueEventListeners() {
        //no listeners required
    }

}














