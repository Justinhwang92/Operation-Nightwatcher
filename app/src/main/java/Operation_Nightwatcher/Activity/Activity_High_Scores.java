package Operation_Nightwatcher.Activity;

import Operation_Nightwatcher.Activity.Database.User;
import Operation_Nightwatcher.Activity.Database.UserCursorAdapter;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;
import com.td.OperationNightwatcher.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Activity_High_Scores extends AppCompatActivity implements View.OnClickListener {
    private static final int USER_LOADER = 0;

    UserCursorAdapter mUserCursorAdapter;
    ListView HighScoresList;
    List<User> userList;
    //Audio_Activity_High_Scores myAudio;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        HighScoresList = findViewById(R.id.highScoreList);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //audio
    //    myAudio = new Audio_Activity_High_Scores(this);
       // Audio_Master_Control.checkMuteStatus(myAudio);
      //  myAudio.startMedia(Audio_Activity_High_Scores.MEDIA_PLAYERS.BGM_CREDITS_LOOP);

        //mute button
        /*if(Audio_Master_Control.myMuted)
        {
            muteAudio();
            ImageView audio = findViewById(R.id.gameaudio);
            audio.setImageResource(R.drawable.muted_audio);
        }*/

        /*findViewById(R.id.gameaudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView audio = findViewById(R.id.gameaudio);
                if(Audio_Master_Control.myMuted)
                {
                    audio.setImageResource(R.drawable.unmuted_audio);
                    unmuteAudio();
                }
                else
                {
                    audio.setImageResource(R.drawable.muted_audio);
                    muteAudio();
                }
            }
        });*/


        // go back to main menu
        //findViewById(R.id.backToMain).setOnClickListener(this);
     /*
             TODO: Commented Previous code, will remove it in the future it necessary


        // get database
        mUserCursorAdapter = new UserCursorAdapter(this, null);
        HighScoresList.setAdapter(mUserCursorAdapter);



        // loader
        getLoaderManager().initLoader(USER_LOADER, null, this);*/
    }

    @Override
    public void onClick(View v) {
        //myAudio.releasePlayers();
        Intent i;
        i = new Intent(this, Activity_Menu.class);
        finish();
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        finish();
    }

/*
        TODO: Commented Previous code, will remove it in the future it necessary

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // displaying sections
        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_HIGH_SCORE,
                UserEntry.COLUMN_LAST_GAME_SCORE
        };

        // sorting order
        String sortOrder = UserEntry.COLUMN_HIGH_SCORE + " DESC limit 50";

        // loader
        return new CursorLoader(this,
                UserEntry.CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        );

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mUserCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mUserCursorAdapter.swapCursor(null);
    }
*/

    //called when application stops
    @Override
    protected void onPause() {
        super.onPause();
       // myAudio.pauseLoopers();
    }
    //called when application starts/resumes
    @Override
    protected void onResume() {
        super.onResume();
        //myAudio.resumeLoopers();
    }

    //called when application stops
    @Override
    protected void onStop(){
        super.onStop();
    }

    //i think this is called when display turns back on.
    //if you press the power button and turn the emulator off and press again to start
    //it calls this method. not sure if power button on emulator just turns off screen or turns
    //off emulator
    @Override
    protected void onRestart(){
        super.onRestart();
        //myAudio.releasePlayers();
        //myAudio = new Audio_Activity_High_Scores(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        System.out.println(myRef);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                userList = new ArrayList<>();
                List<DataSnapshot> list = new ArrayList<>();
                HashMap<String, User> map = new HashMap<>();

                for (DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    list.add(userSnapShot);
                    map.put(userSnapShot.getKey(), userSnapShot.getValue(User.class));
                }

                Collections.sort(list, (str1, str2) -> (map.get(str2.getKey()).getHighscore() - map.get(str1.getKey()).getHighscore()));

                int index = 0;
                for (DataSnapshot snapshot : list) {
                    String id = snapshot.getKey();
                    if (index == 0) {
                        User user = map.get(id);
                        String newId = id+"gold";
                        user.setId(newId);
                        map.put(newId, user);
                        userList.add(map.get(newId));
                    } else if (index == 1) {
                        User user = map.get(id);
                        String newId = id+"silver";
                        user.setId(newId);
                        map.put(newId, user);
                        userList.add(map.get(newId));
                    } else if (index == 2) {
                        User user = map.get(id);
                        String newId = id+"bronze";
                        user.setId(newId);
                        map.put(newId, user);
                        userList.add(map.get(newId));
                    } else if (index >= 50) {
                        snapshot.getRef().removeValue();
                    } else
                        userList.add(map.get(id));
                    index++;
                }

                System.out.println("--------------------------userlist-------------------------------------");
                System.out.println(userList);

                mUserCursorAdapter = new UserCursorAdapter(Activity_High_Scores.this, userList);
                HighScoresList.setAdapter(mUserCursorAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });
    }

    /*public void muteAudio()
    {
        Audio_Master_Control.muteAllPlayers(myAudio);
    }

    public void unmuteAudio()
    {
        Audio_Master_Control.unmuteAllPlayers(myAudio);
    }*/
}