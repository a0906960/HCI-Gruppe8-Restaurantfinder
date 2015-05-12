package hci_g8.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class EditProfile extends Activity implements View.OnClickListener {

    Button back_button3;
    Button save_button;

    Spinner likeSpinner1;
    Spinner likeSpinner2;
    Spinner likeSpinner3;
    Spinner dislikeSpinner1;
    Spinner dislikeSpinner2;
    Spinner dislikeSpinner3;

    EditText editLoginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // EditText Name
        editLoginName = (EditText) findViewById(R.id.editLoginName);


        //Spinner array
        List<String> likesAndDislikes = new ArrayList<String>();
        likesAndDislikes.add("Wähle...");
        likesAndDislikes.add("Wienerisch");
        likesAndDislikes.add("Italienisch");
        likesAndDislikes.add("Griechisch");
        likesAndDislikes.add("Spanisch");
        likesAndDislikes.add("Japanisch");
        likesAndDislikes.add("Chinesisch");
        likesAndDislikes.add("Indisch");
        likesAndDislikes.add("Koreanisch");
        likesAndDislikes.add("Türkisch");
        likesAndDislikes.add("Vegan");
        likesAndDislikes.add("Vegetarisch");
        likesAndDislikes.add("Thailändisch");
        likesAndDislikes.add("Amerikanisch");
        likesAndDislikes.add("FastFood");
        likesAndDislikes.add("Französisch");
        likesAndDislikes.add("Mexikanisch");

        // define spinners
        likeSpinner1 = (Spinner) findViewById(R.id.likeSpinner1);
        likeSpinner2 = (Spinner) findViewById(R.id.likeSpinner2);
        likeSpinner3 = (Spinner) findViewById(R.id.likeSpinner3);
        dislikeSpinner1 = (Spinner) findViewById(R.id.dislikeSpinner1);
        dislikeSpinner2 = (Spinner) findViewById(R.id.dislikeSpinner2);
        dislikeSpinner3 = (Spinner) findViewById(R.id.dislikeSpinner3);

        // fill spinner with array
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, likesAndDislikes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        likeSpinner1.setAdapter(arrayAdapter);
        likeSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        likeSpinner2.setAdapter(arrayAdapter);
        likeSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        likeSpinner3.setAdapter(arrayAdapter);
        likeSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dislikeSpinner1.setAdapter(arrayAdapter);
        dislikeSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dislikeSpinner2.setAdapter(arrayAdapter);
        dislikeSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dislikeSpinner3.setAdapter(arrayAdapter);
        dislikeSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // define back button
        back_button3 = (Button) findViewById(R.id.back_button3);
        back_button3.setOnClickListener(this);

        // define save button
        save_button = (Button) findViewById(R.id.save_button);
        save_button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back_button3:
                finish();
                break;
            case R.id.save_button:

                Intent nextScreen = new Intent (this, MainActivity.class);

                nextScreen.putExtra("Name", editLoginName.getText().toString());
                nextScreen.putExtra("Like1", likeSpinner1.getSelectedItem().toString());
                nextScreen.putExtra("Like2", likeSpinner2.getSelectedItem().toString());
                nextScreen.putExtra("Like3", likeSpinner3.getSelectedItem().toString());
                nextScreen.putExtra("Dislike1", dislikeSpinner1.getSelectedItem().toString());
                nextScreen.putExtra("Dislike2", dislikeSpinner2.getSelectedItem().toString());
                nextScreen.putExtra("Dislike3", dislikeSpinner3.getSelectedItem().toString());

                startActivity(nextScreen);

                break;
        }
    }

}
