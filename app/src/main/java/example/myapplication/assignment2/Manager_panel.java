package example.myapplication.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Manager_panel extends AppCompatActivity {

    Button history;
    Calculator myCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        history = findViewById(R.id.history);

        //initialize and make a instance
        myCalc = new Calculator();

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCalc.history = bundleFromMainActivity.getParcelableArrayList("purchase_items");
        }

        //when history button is clicked
        history.setOnClickListener(view -> {
            if(myCalc.history.isEmpty()) {
                Toast.makeText(getApplicationContext(),"There is no purchases",Toast.LENGTH_LONG).show();
                return;
            }
                Intent myIntent = new Intent(this, Purchase_history_list.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("purchase_items", myCalc.history);
                myIntent.putExtra("bundle", bundle);
                startActivity(myIntent);
        });

    }
}