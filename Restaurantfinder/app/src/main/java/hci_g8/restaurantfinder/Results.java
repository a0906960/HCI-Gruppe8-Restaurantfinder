package hci_g8.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Results extends Activity implements View.OnClickListener {

    ImageButton button_image_one;
    ImageButton button_image_two;
    ImageButton button_image_three;
    ImageButton button_image_four;
    ImageButton button_image_five;
    ImageButton button_image_six;
    ImageButton button_image_seven;
    Button button_result_one;
    Button button_result_two;
    Button button_result_three;
    Button button_result_four;
    Button button_result_five;
    Button button_result_six;
    Button button_result_seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        button_image_one = (ImageButton) findViewById(R.id.button_image_one);
        button_image_one.setOnClickListener(this);

        button_image_two = (ImageButton) findViewById(R.id.button_image_two);
        button_image_two.setOnClickListener(this);

        button_image_three = (ImageButton) findViewById(R.id.button_image_three);
        button_image_three.setOnClickListener(this);

        button_image_four = (ImageButton) findViewById(R.id.button_image_four);
        button_image_four.setOnClickListener(this);

        button_image_five = (ImageButton) findViewById(R.id.button_image_five);
        button_image_five.setOnClickListener(this);

        button_image_six = (ImageButton) findViewById(R.id.button_image_six);
        button_image_six.setOnClickListener(this);

        button_image_seven = (ImageButton) findViewById(R.id.button_image_seven);
        button_image_seven.setOnClickListener(this);

        button_result_one = (Button) findViewById(R.id.button_result_one);
        button_result_one.setOnClickListener(this);

        button_result_two = (Button) findViewById(R.id.button_result_two);
        button_result_two.setOnClickListener(this);

        button_result_three = (Button) findViewById(R.id.button_result_three);
        button_result_three.setOnClickListener(this);

        button_result_four = (Button) findViewById(R.id.button_result_four);
        button_result_four.setOnClickListener(this);

        button_result_five = (Button) findViewById(R.id.button_result_five);
        button_result_five.setOnClickListener(this);

        button_result_six = (Button) findViewById(R.id.button_result_six);
        button_result_six.setOnClickListener(this);

        button_result_seven = (Button) findViewById(R.id.button_result_seven);
        button_result_seven.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == button_image_one || v == button_result_one || v == button_image_four || v == button_result_four) {
            Intent result_one = new Intent(this, Result1.class);
            startActivity(result_one);
        }
        if (v == button_image_two || v == button_result_two || v == button_image_five || v == button_result_five) {
            Intent result_two = new Intent(this, Result2.class);
            startActivity(result_two);
        }
        if (v == button_image_three || v == button_result_three || v == button_image_six || v == button_result_six) {
            Intent result_three = new Intent(this, Result3.class);
            startActivity(result_three);
        }


        if (v == button_image_seven || v == button_result_seven) {
            Intent result_four = new Intent(this, Result4.class);
            startActivity(result_four);
        }



    }

}
