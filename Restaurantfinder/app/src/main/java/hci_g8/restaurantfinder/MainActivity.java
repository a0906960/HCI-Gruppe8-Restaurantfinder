package hci_g8.restaurantfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    // create variables for objects
    Button button_new;
    Button button_saved;
    Button button_profile;

    // person array
    String[] likes = {"Italienisch", "Wienerisch", "Griechisch"};
    String[] dislikes = {"Vegan", "Wähle...", "Wähle..."};
    Person actualSession = new Person ("Jasmin", likes, dislikes);
    Person person1 = new Person ("Kevin", likes, dislikes);
    Person person2 = new Person ("Elias", likes, dislikes);
    Person person3 = new Person ("Piotr", likes, dislikes);
    Person person4 = new Person ("Elisabeth", likes, dislikes);
    ArrayList<Person> personDB = new ArrayList<Person>();

/*  ArrayList<ArrayList<Person>> groupDB = new ArrayList<ArrayList<Person>>(10);
    ArrayList<String> groupNames = new ArrayList<String>(10);

    int groupCount = 0;*/

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

        // getIntent of EditProfile
        Intent i = getIntent();
        if ( i.getStringExtra("Name") != null ) {
            String newName = i.getStringExtra("Name");
            String like1 = i.getStringExtra("Like1");
            String like2 = i.getStringExtra("Like2");
            String like3 = i.getStringExtra("Like3");
            String dislike1 = i.getStringExtra("Dislike1");
            String dislike2 = i.getStringExtra("Dislike2");
            String dislike3 = i.getStringExtra("Dislike3");
            String[] newLikes = {like1, like2, like3};
            String[] newDislikes = {dislike1, dislike2, dislike3};

            actualSession.setName(newName);
            actualSession.setLikes(newLikes);
            actualSession.setDislikes(newDislikes);
            Toast.makeText(getApplicationContext(), newName + " " + like1 + " " + like2 + " " + like3, Toast.LENGTH_LONG).show();
        }
      /*if ( i.getBundleExtra("groups") != null ){
            String groupName = getIntent().getStringExtra("groupName");
            Bundle extra = getIntent().getBundleExtra("groups");

            groupNames.set(groupCount, groupName);
            groupDB.set(groupCount, (ArrayList<Person>) extra.getSerializable("groupDB"));

            Toast.makeText(getApplicationContext(), groupDB.get(groupCount).get(1).getName(), Toast.LENGTH_LONG).show();

            groupCount++;
            Toast.makeText(getApplicationContext(), "GroupCount: " + groupCount, Toast.LENGTH_LONG).show();

        }*/

        // fill person array
        personDB.add(actualSession);
        personDB.add(person1);
        personDB.add(person2);
        personDB.add(person3);
        personDB.add(person4);


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
                Bundle extra_new = new Bundle();
                extra_new.putSerializable("personDB", personDB);

                Intent new_group_screen = new Intent(this, NewGroup.class);
                new_group_screen.putExtra("persons", extra_new);

                startActivity(new_group_screen);
                break;
            case R.id.button_saved:
                /*Bundle extra_saved = new Bundle();
                extra_saved.putSerializable("groupDB", groupDB);*/

                Intent saved_groups_screen = new Intent(this, SavedGroups.class);
                //saved_groups_screen.putExtra("groups", extra_saved);

                startActivity(saved_groups_screen);
                break;
            case R.id.button_profile:
                Intent edit_profile_screen = new Intent(this, EditProfile.class);



                startActivity(edit_profile_screen);
                break;
        }
    }

}
