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

    Button button_results1;
    Button button_results2;
    Button button_results3;

    ArrayList<Person> groupDB = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_groups);

        button_results1 = (Button) findViewById(R.id.button_results1);
        button_results1.setOnClickListener(this);

        button_results2 = (Button) findViewById(R.id.button_results2);
        button_results2.setOnClickListener(this);

        button_results3 = (Button) findViewById(R.id.button_results3);
        button_results3.setOnClickListener(this);

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
        if ( v == button_results1){
            Intent result_screen1 = new Intent(this, Results.class);

            startActivity(result_screen1);
        }
        if ( v == button_results2){
            Intent result_screen2 = new Intent(this, Results.class);

            startActivity(result_screen2);
        }
        if ( v == button_results3){
            Intent result_screen3 = new Intent(this, Results.class);

            startActivity(result_screen3);
        }
    }
}
