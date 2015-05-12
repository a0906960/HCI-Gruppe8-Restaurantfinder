package hci_g8.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class SavedGroups extends Activity implements View.OnClickListener {

    Button button_results;

    ArrayList<Person> groupDB = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_groups);

        button_results = (Button) findViewById(R.id.button_results);
        button_results.setOnClickListener(this);

     /*   Intent intent = getIntent();
        if ( intent != null ) {
            Bundle extra = getIntent().getBundleExtra("groups");
            groupDB = (ArrayList<Person>) extra.getSerializable("groupDB");
        }
        Toast.makeText(getApplicationContext(), groupDB.get(1).getName(), Toast.LENGTH_LONG).show();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if ( v == button_results){
            Intent result_screen = new Intent(this, Results.class);

            startActivity(result_screen);
        }

    }
}
