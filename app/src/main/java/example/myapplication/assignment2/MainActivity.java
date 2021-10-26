package example.myapplication.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView product, total;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, buy, manager;
    private TextView qty;
    private ListView list;
    private Calculator myCalc;
    Product_Adapter adapter;
    int index;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCalc = new Calculator();

        //check if the array list saved
        if (savedInstanceState != null)
            myCalc.items = savedInstanceState.getParcelableArrayList("items");

        product = findViewById(R.id.productType);
        total = findViewById(R.id.totalPrice);
        btn1 = findViewById(R.id.btnNumber1);
        btn2 = findViewById(R.id.btnNumber2);
        btn3 = findViewById(R.id.btnNumber3);
        btn4 = findViewById(R.id.btnNumber4);
        btn5 = findViewById(R.id.btnNumber5);
        btn6 = findViewById(R.id.btnNumber6);
        btn7 = findViewById(R.id.btnNumber7);
        btn8 = findViewById(R.id.btnNumber8);
        btn9 = findViewById(R.id.btnNumber9);
        btn0 = findViewById(R.id.btnNumber0);
        buy = findViewById(R.id.buy);
        btnClear = findViewById(R.id.btnClear);
        qty = findViewById(R.id.qty);

        manager = findViewById(R.id.manager);

        builder = new AlertDialog.Builder(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);


        //clear button
        btnClear.setOnClickListener(view -> {
            qty.setText("");
            total.setText("");
            product.setText("");
            myCalc.calcArray.clear();
        });

        //manager button
        manager.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, Manager_panel.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("purchase_items", myCalc.history);
            myIntent.putExtra("bundle", bundle);
            startActivity(myIntent);
        });

        //Buy
        buy.setOnClickListener(view -> {
            //if product was not selected
            if (product.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Product must be selected", Toast.LENGTH_LONG).show();
                return;
            }
            // if qty was not selected
            if (qty.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Quantity must be selected", Toast.LENGTH_LONG).show();
                return;
            }
            //if qty is less than 0
            //input > qty restock
            if (Integer.parseInt(qty.getText().toString()) > myCalc.items.get(index).qty) {
                Toast.makeText(getApplicationContext(), "No enough quantity in the stock!", Toast.LENGTH_LONG).show();
                qty.setText("");
                myCalc.calcArray.clear();
                return;
            }

            double result = Double.parseDouble(qty.getText().toString()) * Double.parseDouble(myCalc.items.get(index).price);
            total.setText(String.valueOf(result));
            myCalc.changeQty(index, Integer.parseInt(qty.getText().toString()));
            adapter = new Product_Adapter(this, myCalc.items);
            list.setAdapter(adapter);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            String date = formatter.format(now);
            myCalc.pushHistory(new Item(myCalc.items.get(index).productName, String.format("%.2f", result), Integer.parseInt(qty.getText().toString()), date));
            System.out.println(String.format("%.2f", result));
            //success purchase //decimal format:Ok!
            builder.create();
            builder.setTitle("Thank you for your purchase!");
            builder.setMessage("Your purchase was " + myCalc.getCalcArray() + " " + myCalc.items.get(index).productName + " for $" + String.format("%.2f", result));
            builder.show();

            //make them clear when buy button is clicked
            qty.setText("");
            total.setText("");
            product.setText("");
            myCalc.calcArray.clear();

        });

        list = (ListView) findViewById(R.id.list);
        adapter = new Product_Adapter(this, myCalc.items);
        list.setAdapter(adapter);

        //When click the product name, display toast
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                product.setText(myCalc.items.get(i).productName);
                index = i;
            }
        });
    } //end of onCreate


    @Override
    public void onClick(View view) {
        prepareForCalc(view);
    }

    void prepareForCalc(View view) {
        //まずは全てStringに変換しておく
        String op = ((Button) view).getText().toString();
        myCalc.push(op);
        qty.setText(myCalc.getCalcArray());
    }


    //for saving!
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", myCalc.items);
    }
}
