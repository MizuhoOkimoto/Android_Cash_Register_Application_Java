package example.myapplication.assignment2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.viewHolder> {
    ArrayList<Item> itemList;
    Context mContext;

    //when item is clicked
    public interface OnItemClickListner{
        void onItemClicked(Item item);
    }
    private final OnItemClickListner listner;

    public ItemRecyclerAdapter(ArrayList<Item> itemList, Context context,OnItemClickListner listnerFromActivity) {
        this.itemList = itemList;
        this.mContext = context;
        listner = listnerFromActivity;
    }

    //view holder (get 1 row)
    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView productName;
        private final TextView total;
        private final TextView qty;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.purchasedProductName);
            total = itemView.findViewById(R.id.purchasedPrice);
            qty = itemView.findViewById(R.id.purchasedQty);
        }

        public TextView getProductNameText() {
            return productName;
        }
        public TextView getTotalText() {

            return total;

        }
        public TextView getQtyText() {
            return qty;
        }
    }

    @NonNull
    @Override
    public ItemRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater =  LayoutInflater.from(mContext);
        View view =  myInflater.inflate(R.layout.recycler_list_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getProductNameText().setText(itemList.get(position).productName);
        holder.getTotalText().setText(String.valueOf(itemList.get(position).price));
        holder.getQtyText().setText(String.valueOf(itemList.get(position).qty));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClicked(itemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}


