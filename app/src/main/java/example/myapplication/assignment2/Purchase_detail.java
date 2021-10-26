package example.myapplication.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Purchase_detail extends AppCompatActivity {

    private TextView productName, total, dateText;
    Calculator myCalc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        productName = findViewById(R.id.productName);
        total = findViewById(R.id.productPrice);
        dateText = findViewById(R.id.date);

        Intent myIntent = getIntent();
        String name = "Product: " + myIntent.getStringExtra("productName");
        productName.setText(name);


        String price = "Price: $" + myIntent.getStringExtra("price");
        total.setText(price);

        String date = "Purchase Date: " + myIntent.getStringExtra("date");
        dateText.setText(date);

    }
}