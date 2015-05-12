package hci_g8.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NewGroup extends Activity implements View.OnClickListener {

    EditText groupName;

    ArrayList<Person> personDB = new ArrayList<Person>();
    AutoCompleteTextView autoCompletePersons;
    List<String> personNames = new ArrayList<String>();

    Button addPerson;
    ListView newGroupListView;
    ArrayAdapter groupArrayAdapter;
    ArrayList newGroupMember = new ArrayList();

    ArrayList<Person> group = new ArrayList<Person>();

    Button saveGroupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);


        groupName = (EditText) findViewById(R.id.groupNameEditText);

        Intent intent = getIntent();
        if ( intent != null ) {
            Bundle extra = getIntent().getBundleExtra("persons");
            personDB = (ArrayList<Person>) extra.getSerializable("personDB");
        }
        //Toast.makeText(getApplicationContext(), personDB.get(1).getName(), Toast.LENGTH_LONG).show();

        for ( int i = 0; i < personDB.size(); i++) {
            personNames.add(i, personDB.get(i).getName());
        }

        //Toast.makeText(getApplicationContext(), String.valueOf(personNames.size()), Toast.LENGTH_LONG).show();

        String[] NAMES = new String[ personNames.size() ];

        for ( int j = 0; j < personNames.size(); j++ ) {
            NAMES[j] = String.valueOf(personNames.get(j));
        }

        //Toast.makeText(getApplicationContext(), NAMES[2], Toast.LENGTH_LONG).show();

        autoCompletePersons = (AutoCompleteTextView) findViewById(R.id.autoCompletePersons);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, NAMES);
        autoCompletePersons.setAdapter(adapter);


        addPerson = (Button) findViewById(R.id.addPerson);
        addPerson.setOnClickListener(this);


        newGroupListView = (ListView) findViewById(R.id.newGroupListView);
        groupArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newGroupMember);
        newGroupListView.setAdapter(groupArrayAdapter);

        saveGroupButton = (Button) findViewById(R.id.save_group_button);
        saveGroupButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if ( v == saveGroupButton ) {
            Bundle extra = new Bundle();
            extra.putSerializable("groupDB", group);

            Intent main_screen = new Intent (this, MainActivity.class);
            main_screen.putExtra("groupName", groupName.getText().toString());
            main_screen.putExtra("groups", extra);

            startActivity(main_screen);
        }

        if ( v == addPerson) {

            int size = group.size();
            if ( size == 0 ) {
                for ( int i = 0; i < personDB.size(); i++ ) {
                    if ( autoCompletePersons.getText().toString().equals(personDB.get(i).getName())) {
                        group.add(0, personDB.get(i));
                        newGroupMember.add(autoCompletePersons.getText().toString());
                    }
                }
            }else {
                for ( int i = 0; i < personDB.size(); i++ ) {
                    if ( autoCompletePersons.getText().toString().equals(personDB.get(i).getName())) {
                        group.add(size, personDB.get(i));
                        newGroupMember.add(autoCompletePersons.getText().toString());
                    }
                }
            }

            //Toast.makeText(getApplicationContext(), group.toString(), Toast.LENGTH_LONG).show();

            groupArrayAdapter.notifyDataSetChanged();
            autoCompletePersons.setText(null);
        }
    }
}
