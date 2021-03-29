package Operation_Nightwatcher.Activity.Database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.td.OperationNightwatcher.R;

import java.util.List;

public class UserCursorAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;
    public UserCursorAdapter(Activity context, List<User> userList) {
        super(context, R.layout.score_list, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View listItemView = LayoutInflater.from(context).inflate(R.layout.score_list, parent, false);

        // Field of the listItemView
        TextView nameText = (TextView) listItemView.findViewById(R.id.name_text);
        TextView scoreText = (TextView) listItemView.findViewById(R.id.score_text);

        User user = userList.get(position);

       /*
               TODO: Commented Previous code, will remove it in the future it necessary

        // Get pet properties from the database
        String playerName = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_USER_NAME));
        int highScore = cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_HIGH_SCORE));
       // int lastScore = cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_LAST_GAME_SCORE));

        // Fill the listItemView with the values*/
        if (user.getId().contains("gold")) {
            ImageView goldimage = (ImageView) listItemView.findViewById(R.id.crown);
            //goldimage.setImageResource(R.drawable.gold);

            //Displaying emojis only if user is one of top 3
            if (user.getEmoji() != null && user.getEmoji().length() > 0)
                nameText.setText(user.getUsername()+" "+user.getEmoji());
            else
                nameText.setText(user.getUsername());
        }
        else if (user.getId().contains("silver")) {
            ImageView silverimage = (ImageView) listItemView.findViewById(R.id.crown);
            //silverimage.setImageResource(R.drawable.silver);

            //Displaying emojis only if user is one of top 3
            if (user.getEmoji() != null && user.getEmoji().length() > 0)
                nameText.setText(user.getUsername()+" "+user.getEmoji());
            else
                nameText.setText(user.getUsername());
        }
        else if (user.getId().contains("bronze")) {
            ImageView bronzeimage = (ImageView) listItemView.findViewById(R.id.crown);
           // bronzeimage.setImageResource(R.drawable.bronze);

            //Displaying emojis only if user is one of top 3
            if (user.getEmoji() != null && user.getEmoji().length() > 0)
                nameText.setText(user.getUsername()+" "+user.getEmoji());
            else
                nameText.setText(user.getUsername());
        } else
            nameText.setText(user.getUsername());
        scoreText.setText(String.valueOf(user.getHighscore()));
        return listItemView;
    }
}
