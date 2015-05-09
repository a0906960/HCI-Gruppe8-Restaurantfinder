package hci_g8.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    // create variables for objects
    Button button_new;
    Button button_saved;
    Button button_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fill variables for objects
        button_new = (Button) findViewById(R.id.button_new);
        button_new.setOnClickListener(this);

        button_saved = (Button) findViewById(R.id.button_saved);
        button_saved.setOnClickListener(this);

        button_profile = (Button) findViewById(R.id.button_profile);
        button_profile.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.button_new:
                Intent new_group_screen = new Intent(this, NewGroup.class);

                startActivity(new_group_screen);
                break;
            case R.id.button_saved:
                Intent saved_groups_screen = new Intent(this, SavedGroups.class);

                startActivity(saved_groups_screen);
                break;
            case R.id.button_profile:
                Intent edit_profile_screen = new Intent(this, EditProfile.class);

                startActivity(edit_profile_screen);
                break;
        }
    }

}
