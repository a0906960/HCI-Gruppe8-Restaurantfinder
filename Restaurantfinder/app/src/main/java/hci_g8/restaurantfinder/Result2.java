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

/**
 * Created by Kev on 12.5.15.
 */
public class Result2 extends Activity implements View.OnClickListener {

    Button button_res2_1;
    Button button_res2_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_end2);

        button_res2_1 = (Button) findViewById(R.id.button_res2_1);
        button_res2_1.setOnClickListener(this);

        button_res2_2 = (Button) findViewById(R.id.button_res2_2);
        button_res2_2.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == button_res2_1 || v == button_res2_2)
            Toast.makeText(getApplicationContext(), "Dieses Feature ist noch nicht implementiert", Toast.LENGTH_SHORT).show();
    }
}