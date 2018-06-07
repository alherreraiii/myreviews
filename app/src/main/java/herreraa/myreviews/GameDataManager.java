package herreraa.myreviews;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Al on 2/12/2017.
 */

public class GameDataManager extends BaseActivity{
    //fields
    private static GameDataManager dm;
    private List<Game> itemList;

    private GameDataManager() {
        itemList = new ArrayList<>();
    }

    public static GameDataManager getDataManager() {
        if (dm == null) {
            dm = new GameDataManager();
        }
        return dm;
    }

    public List<Game> getItemList() {
        Collections.sort(itemList);
        return itemList;
    }

    public Game getItem(int itemId) {
        Game item = null;
        for (int i = 0; i<itemList.size(); i++) {
            itemList.get(i);
            if (i == itemId) {
                item = itemList.get(i);
                break;
            }
        }
        return item;
    }

    public void deleteItem(Game game) {
        itemList.remove(game);
        updateDatabase();
    }

    public void addItem(Game game) {
        itemList.add(game);
        updateDatabase();
    }

    public int getSize() {
        return itemList.size();
    }

    public void setList(List<Game> list) {
        this.itemList = list;
    }

    private void updateDatabase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().
                getReference("game/" + userId);
        ref.push();
        ref.setValue(itemList);
    }

    @Override
    public void setUpValueEventListeners() {

    }
}






















