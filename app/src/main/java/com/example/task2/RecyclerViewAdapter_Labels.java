package com.example.task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Labels extends RecyclerView.Adapter<RecyclerViewAdapter_Labels.MyViewHolder> {

    List<Label> itemList;

    RecyclerViewAdapter_Labels(List<Label> items){
        this.itemList = new ArrayList<>();
        if(items == null)
            this.itemList = new ArrayList<>();
        else
            this.itemList.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_Labels.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_labels, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_Labels.MyViewHolder holder, int position) {
        holder.bind(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addLabel(Label newLabel){
        itemList.add(newLabel);
        notifyItemInserted(itemList.size());
    }

    public void deleteSelectedLabels(){
        List<Label> tempList = new ArrayList<>();
        tempList.addAll(itemList);
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).isSelected()) {
                itemList.remove(tempList.get(i));
            }
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CheckedTextView chktxt_label;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chktxt_label = itemView.findViewById(R.id.chktxt_label);
        }

        public void bind(Label label, int position){

//            this.itemView.setBackgroundColor(label.isSelected() ? Color.parseColor("") : Color.WHITE);
            this.itemView.setSelected(label.isSelected() ? true : false);
            this.chktxt_label.setText(label.getName());
            this.chktxt_label.setOnClickListener(view -> {
                label.setSelected(!label.isSelected());
                this.itemView.setSelected(!label.isSelected() ? true : false);
//                this.chktxt_label.setBackgroundColor(label.isSelected() ? Color.CYAN : Color.WHITE);
//                this.chktxt_label.setChecked(label.isSelected() ? true : false);
//                notifyItemChanged(this.getAdapterPosition());
                notifyItemChanged(position);

            });
        }
    }
}
