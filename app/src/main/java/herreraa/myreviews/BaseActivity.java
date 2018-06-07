package herreraa.myreviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * I want this to hold the login information
 *
 * Created by Al on 2/26/2017.
 */

abstract public class BaseActivity extends AppCompatActivity {

    //fields
    private DataManager dm;
    //firebase authentication
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private Menu actionBarMenu;
    protected String userId; //child classes may need access to this variable
    protected String email; //child classes may need access to this variable
    //firebase database
    private static boolean persistenceSet = false;

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        dm = DataManager.getDataManager();

        //set database to save offline
        if (!persistenceSet) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            persistenceSet = true;
        }

        //set up firebase authentication
        auth = FirebaseAuth.getInstance();
        authListener = getAuthListener();
    }

    /**
     * Handles authentication data sent back from the login page.
     *
     * @param requestCode sent code
     * @param resultCode returned code
     * @param data returned data in an intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 85) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /**
     * Removes authentication and database listeners on pause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    /**
     * Add authentication listener on resume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        auth.addAuthStateListener(authListener);
    }

    /**
     * Creates the firebase authentication state change listener.
     *
     * @return the listener
     */
    public FirebaseAuth.AuthStateListener getAuthListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //get user details
                    userId = user.getUid();
                    email = user.getEmail();
                    //customize action bar menu to show who's signed in
                    if (actionBarMenu != null) {
                        actionBarMenu.getItem(0).setTitle(email);
                    }
                    //set up event handlers for when data changes in the firebase cloud
                    setUpValueEventListeners();
                } else {
                    //user is signed out
                    userId = null;
                    email = null;
                    startActivityForResult(
                            //present email login
                            AuthUI.getInstance().createSignInIntentBuilder().build(),
                            85); //I wonder if Sensei will find this.  Me bad use magic number.
                }
            }
        };
    }

    /**
     * Go to the books section
     *
     * @param view for using button
     */
    public void btnBookOnClick(View view) {
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }

    /**
     * go to the games section
     *
     * @param view for using the button
     */
    public void btnGameOnClick(View view) {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }

    /**
     * Abstract method that must be implemented by all child classes to set up the
     * necessary firebase realtime database connection.
     */
    abstract public void setUpValueEventListeners();

}






































