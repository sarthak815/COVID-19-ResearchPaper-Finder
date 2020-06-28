package com.example.paperscrapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class searchListViewAdapter extends ArrayAdapter<Researchpapers> {
    List<Researchpapers> paperlist;
    Context context;


    public searchListViewAdapter(List<Researchpapers> paperlist, Context context) {
        super(context, R.layout.nonmed_list, paperlist);
        this.paperlist = paperlist;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View listviewitem = inflater.inflate(R.layout.nonmed_list, null, true);
        TextView name = listviewitem.findViewById(R.id.name);
        TextView citations = listviewitem.findViewById(R.id.citation2);
        Researchpapers researchpapers = paperlist.get(position);
        name.setText(researchpapers.getTitle());
        String num = String.valueOf(researchpapers.getCitations());
        int f = (int) Float.parseFloat(num);
        num = String.valueOf(f);
        if (num.equals("0")) {
            num = "N/A";
        }
        citations.setText(num);

        return listviewitem;
    }
}
