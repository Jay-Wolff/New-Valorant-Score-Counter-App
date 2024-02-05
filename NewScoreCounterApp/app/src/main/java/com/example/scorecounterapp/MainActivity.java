package com.example.scorecounterapp;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


//HelloToastActivity was referenced for the count methods
public class MainActivity extends AppCompatActivity {
    //made int variables for the count method that is connected to showing the number of points each
    //team will be recieving
    private int CountOne = 0;
    private int CountTwo = 0;
    //each team score has it's own textview to be connected to the textview in the main activity
    private TextView ShowTeamOneScore;
    private TextView ShowTeamTwoScore;
    //used this string to reference who the winner is which is displayed in the winner activity
    private String winner;
    //used this int to do a calculation where on the winner activity it'd state who won and how many
    //points they won by, hence the name pointsWonBy
    private int pointsWonBy;
//mp7
    private String player;
    private TextView showNewTeamPlayer;
    private ImageView Image;
    private String teamName;
    private ImageView winnerImageL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //the activity is being created
        setContentView(R.layout.activity_main);
        //calling the textviews from the xml to outprint the numbers when the count methods happen
        //each textview has a seperate count method since they are connected to seperate buttons
        //Basically teamScoreOne = TeamOne's buttons and textviews
        ShowTeamOneScore = (TextView) findViewById(R.id.teamCountOne);
        ShowTeamTwoScore = (TextView) findViewById(R.id.teamCountTwo);

        //saving instance for the rotation
        if(savedInstanceState != null){
            CountOne = savedInstanceState.getInt("key1", 0);
            CountTwo = savedInstanceState.getInt("key2",0);
        }
        ShowTeamOneScore.setText(Integer.toString(CountOne));
        ShowTeamTwoScore.setText(Integer.toString(CountTwo));
//mp7
        Image = (ImageView) findViewById(R.id.Omen);
        showNewTeamPlayer = (TextView) findViewById(R.id.OmenName);


        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //restore the values from the preferences for the images for the player
        player = sharedPreferences.getString("reply", "Raze");
        String razeplayer = sharedPreferences.getString("reply","omenimage");
        int razeI = getResources().getIdentifier(razeplayer, "drawable", getPackageName());
        Image.setImageResource(razeI);

        //let the user name the team
        teamName = sharedPreferences.getString("signature", "Omen");
        String newName = sharedPreferences.getString("signature","Omen");
        showNewTeamPlayer.setText(newName);



    }

    //this saves the number the user has clicked for each of the teams after a rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //keys for each of the counts that the user has clicked so it's saved on rotation
        outState.putInt("key1", CountOne);
        outState.putInt("key2", CountTwo);
    }

    //count up methods that add up whenever the user clicks the buttons that are for the team
    //count one is for team one
    public void countUpOne(View view) {
        CountOne++;
        if (ShowTeamOneScore != null) {
            ShowTeamOneScore.setText(Integer.toString(CountOne));
        }
        //if statement below tells us who the winner is. so if countOne which is a reference of team1
        //gets their count equal to 5 then team one wins. team one is OMEN in my code.
        if(CountOne == 5){
            //winner is found so connect the winner string to the name of the winning team
            winner = "Omen";
            //calculate for the pointswonby
            pointsWonBy = CountOne - CountTwo;
            //launch the second activity, and in the second activity we'll display these things
            launchWinnerActivity();

        }
    }

    //count two is for team two
    //I tried to keep the numbers correspondent with the teams they referenced throughout the entire
    // code so team two is referenced in count two and the countuptwo method
    public void countUpTwo(View view) {
        CountTwo++;
        if (ShowTeamTwoScore != null) {
            ShowTeamTwoScore.setText(Integer.toString(CountTwo));
        }
        //if statement below tells us who the winner is. so if countTwo which is a reference of team2
        //gets their count equal to 5 then team two wins. team two is SAGE in my code.
        if(CountTwo == 5){
            //winner is found so connect the winner string to the name of the winning team
            winner = "Sage";
            //calculate for the pointswonby
            pointsWonBy = CountTwo - CountOne;
            //launch the second activity, and in the second activity we'll display these things
            launchWinnerActivity();
        }
    }

//mp7
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //launch winner activity which calls upon the second activity i made
    public void launchWinnerActivity(){
        //
        Intent intent = new Intent(this, WinnerActivity.class);
        //
        intent.putExtra("winnerName", winner);
        intent.putExtra("Points", pointsWonBy);
        //
        startActivity(intent);
    }
}