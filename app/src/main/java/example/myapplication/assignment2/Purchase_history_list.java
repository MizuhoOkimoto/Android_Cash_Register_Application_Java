package example.myapplication.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Purchase_history_list extends AppCompatActivity {

    RecyclerView recyclerlist;
    ItemRecyclerAdapter adapter;
    Calculator myCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history_list);

        //initialize and make a instance
        myCalc = new Calculator();

        //check if the array list saved
        if (savedInstanceState != null)
            myCalc.history = savedInstanceState.getParcelableArrayList("items");

        recyclerlist = (RecyclerView) findViewById(R.id.purchasedList);

        if (getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            myCalc.history = bundleFromMainActivity.getParcelableArrayList("purchase_items");
        }

        //it did not work inside the onItemClicked, so put it outside
        Intent myIntent = new Intent(this, Purchase_detail.class);

        recyclerlist.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemRecyclerAdapter(myCalc.history, this, new ItemRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onItemClicked(Item item) {


                myIntent.putExtra("productName", item.productName);
                myIntent.putExtra("price", item.price);
                myIntent.putExtra("date", item.date);
                startActivity(myIntent);
            }
        });
        recyclerlist.setAdapter(adapter);
    }
    //for saving!
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", myCalc.history);
    }
}