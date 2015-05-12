package hci_g8.restaurantfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Result1 extends Activity implements View.OnClickListener {

    Button button_res1_1;
    Button button_res1_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_end1);

        button_res1_1 = (Button) findViewById(R.id.button_res1_1);
        button_res1_1.setOnClickListener(this);

        button_res1_2 = (Button) findViewById(R.id.button_res1_2);
        button_res1_2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == button_res1_1 || v == button_res1_2)
            Toast.makeText(getApplicationContext(), "Dieses Feature ist noch nicht implementiert", Toast.LENGTH_SHORT).show();
    }
}