package example.myapplication.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

//This class is for the item list
public class Item implements Parcelable {
    public  String productName;
    public  String price;
    public int qty;
    public String date;

    public Item(String productName, String price, int qty, String date) {
        this.productName = productName;
        this.price = price;
        this.qty = qty;
        this.date = date;
    }

    protected Item(Parcel in) {
        productName = in.readString();
        price = in.readString();
        qty = in.readInt();
        date = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(productName);
        parcel.writeString(price);
        parcel.writeInt(qty);
        parcel.writeString(date);
    }
}
