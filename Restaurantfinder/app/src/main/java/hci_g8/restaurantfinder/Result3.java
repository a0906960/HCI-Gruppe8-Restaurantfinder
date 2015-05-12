package hci_g8.restaurantfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Result3 extends Activity implements View.OnClickListener {


    Button button_res3_1;
    Button button_res3_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_end3);

        button_res3_1 = (Button) findViewById(R.id.button_res3_1);
        button_res3_1.setOnClickListener(this);

        button_res3_2 = (Button) findViewById(R.id.button_res3_2);
        button_res3_2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == button_res3_1 || v == button_res3_2)
            Toast.makeText(getApplicationContext(), "Dieses Feature ist noch nicht implementiert", Toast.LENGTH_SHORT).show();
    }
}