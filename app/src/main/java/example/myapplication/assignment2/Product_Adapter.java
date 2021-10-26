package example.myapplication.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_Adapter extends BaseAdapter {

    //Array Adapter
    ArrayList<Item> productList;
    Context context;

    public Product_Adapter(Context context, ArrayList<Item> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.product_list_row,null);
        }

        TextView itemName= (TextView) view.findViewById(R.id.itemName);
        TextView itemQty = (TextView) view.findViewById(R.id.itemQty);
        TextView itemPrice = (TextView) view.findViewById(R.id.itemPrice);

        //String doubleFormat = String.format("%.2f", productList.get(i).price);
        itemName.setText(productList.get(i).productName);
        itemQty.setText(String.valueOf(productList.get(i).qty));
        itemPrice.setText(String.valueOf(productList.get(i).price));

        return  view;
    }
}