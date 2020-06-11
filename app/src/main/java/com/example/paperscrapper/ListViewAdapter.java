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

public class ListViewAdapter extends ArrayAdapter<researchpapers> {
    List<researchpapers> paperlist;
    Context context;


    public ListViewAdapter(List<researchpapers> paperlist, Context context) {
        super(context, R.layout.nonmed_list, paperlist);
        this.paperlist = paperlist;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(context);
        View listviewitem = inflater.inflate(R.layout.nonmed_list, null, true);
        TextView name = listviewitem.findViewById(R.id.name);
        TextView citations = listviewitem.findViewById(R.id.citation2);
        researchpapers researchpapers = paperlist.get(position);
        name.setText(researchpapers.getTitle());
        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");
        citations.setText(num);

        return listviewitem;
    }
}
