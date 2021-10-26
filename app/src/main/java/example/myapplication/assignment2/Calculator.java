package example.myapplication.assignment2;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<String> calcArray = new ArrayList<>();
    public ArrayList<Item> history =  new ArrayList<>();

    public Calculator() {
        items.add(new Item ("Pante","20.44",10, ""));
        items.add(new Item ("Shoes","10.44",100, "" ));
        items.add(new Item ("Hats","5.9",30, "" ));
    }

    //add element to calcArray
    void push(String value) {
        calcArray.add(value);
    }
    //push element to the history
    void pushHistory(Item value) {
        history.add(value);
    }

    //get the element of calcArray
    public String getCalcArray() {
        String result = "";
        for (int i = 0; i < calcArray.size(); i++) {
            result = result + calcArray.get(i);
        }
        return result;
    }

    //when buy the product, decrease the product
    public void changeQty(int index, int inputQty) {
        if (inputQty < 0){
            return;
        }
        items.get(index).qty = items.get(index).qty - inputQty;
    }
}
