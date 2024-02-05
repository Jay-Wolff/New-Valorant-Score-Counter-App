package com.example.scorecounterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class WinnerActivity extends AppCompatActivity {
    //making variables that show the winning team at the top of the second activity and
    private String winnerName;
    //textview which connects to the top of the winnerActivity which displays the winning team
    private TextView displayWinnerName;
    //textview which connects to the bottom of the winner activity which displays the number of
    //points the winning team beat the other team by
    private TextView pointsWonBy;
    private String pointSentence;
    //making variable to show the points won by
    private int points;

//mp7
    private String winnerI;
    private ImageView winnerImageL;
    private String number;
    private TextView numberPer;


//MP3
    //variables methods for the mp3 stuff
    private EditText demDigits;
    private EditText messageType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //the activity is being created
        setContentView(R.layout.activity_winner);
        //
        Bundle result = getIntent().getExtras();
        //Intent intent = getIntent();
        //
        if(result != null){
            winnerName = result.getString("winnerName");
            points = result.getInt("Points");
        }
        //connecting the textView in the winning team based whichever team the user clicked to the
        //value of 5 first
        displayWinnerName = findViewById(R.id.WinningTeam);
        //setting the text for the display winner name to the winnername string which is called in
        //the main activity
        displayWinnerName.setText(winnerName);
        //used to show hoq much the winner team beat the other by
        pointsWonBy = findViewById(R.id.howMuchWonBy);
        //pointSentence = displayWinnerName + " won by " + (points);
        //how you display int to text view
        pointsWonBy.setText(winnerName + " won by " +String.valueOf(points));


//MP3
// finding the views for the call and message buttons
        demDigits = findViewById(R.id.phoneText);
        messageType = findViewById(R.id.messageText);
        //setting a hint(preloads message) this hint is for bringing down pointsWonBy

        //the hint is set to the messageType which is message in the method
        //preloads the winner name and the amount of points won by
        //the user can change the text or leave it as is to send
        messageType.setText(winnerName + " won by " +String.valueOf(points));

//mp7
        winnerImageL = (ImageView) findViewById(R.id.WinnerImage);
        numberPer = (TextView) findViewById(R.id.phoneText);

        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //changing the image for the winner image
        winnerI = sharedPreferences.getString("replyImage", "WinnerImage");
        String Wimage = sharedPreferences.getString("replyImage","WinnerImage");
        int razeI = getResources().getIdentifier(Wimage, "drawable", getPackageName());
        winnerImageL.setImageResource(razeI);

        //allowing the user to enter number
        number = sharedPreferences.getString("number", "phoneText");
        String numbah = sharedPreferences.getString("number","phoneText");
        numberPer.setText(numbah);

//phone text connect , key is number

    }

    //this is the method to return back to the main activity using the back button we created on the
    //bottom right of the screen
    public void backToMain(View view) {
        //whith the intent we make sure that the package conext is the winner activity and the main
        //activity class is the one being referenced to be called
        Intent intent = new Intent(this, MainActivity.class);
        //start activity ensures that when the button is clicked the activity will start and return
        //the user back to the main activity where they can once again pick which team wins
        startActivity(intent);
    }


//MP 3 BELOW
    //intent data is always a uri object
    //this is the method to call
    public void callMethod(View view) {
        String number = demDigits.getText().toString();
        //preset for getting the android telephone number entry
        Uri call = Uri.parse("tel:" + number);
        //intent action dial is preset for number the user enters to dial in for the phone app
        Intent intent = new Intent(Intent.ACTION_DIAL, call);
        //starts everything above after user clicks the call button
        startActivity(intent);
    }

    //this is the method to message
    public void messageMethod(View view) {
        //recalling the string of number since we need to type the number of who is going to recieve
        //the message
        String number = demDigits.getText().toString();
        //message will be the one that was made above where it states the winning team and how much
        //they won by. it's primarily preloaded into the message bar and the user just needs to click
        // send
        String message = messageType.getText().toString();
        //uri android preset for sending the message to the number. the number is the one the user
        //will input
        Uri call = Uri.parse("smsto:" + number);
        //intent for action set to is a preset for messages
        Intent intent = new Intent(Intent.ACTION_SENDTO, call);
        //the put extra is the body of the message which is the winning team and how much they won by
        intent.putExtra("sms_body", message);
        //starts everything in the method above when the user clicks the message button
        startActivity(intent);

    }
    //this is the method to map
    public void mapMethod(View view) {
        String location = "Valorant near me";
        Uri map = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, map);
        startActivity(intent);
    }



}