package com.example.jonathangonzalezfragapdm;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class CardStackCallback extends DiffUtil.Callback {

    private List<ItemModel> viejo, nuevo;

    public CardStackCallback(List<ItemModel> viejo, List<ItemModel> nuevo) {
        this.viejo = viejo;
        this.nuevo = nuevo;
    }

    @Override
    public int getOldListSize() {
        return viejo.size();
    }

    @Override
    public int getNewListSize() {
        return nuevo.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return viejo.get(oldItemPosition).getImage() == nuevo.get(newItemPosition).getImage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return viejo.get(oldItemPosition) == nuevo.get(newItemPosition);
    }
}