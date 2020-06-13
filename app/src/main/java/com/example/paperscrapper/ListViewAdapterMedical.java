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

public class ListViewAdapterMedical  extends ArrayAdapter<researchpapers> {
    List<researchpapers> paperlist1;
    Context context1;


    public ListViewAdapterMedical(List<researchpapers> paperlist, Context context) {
        super(context, R.layout.med_list, paperlist);
        this.paperlist1 = paperlist;
        this.context1 = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(context1);
        View listviewitem = inflater.inflate(R.layout.med_list, null, true);
        TextView name = listviewitem.findViewById(R.id.name_med);
        TextView citations = listviewitem.findViewById(R.id.citation2_med);
        researchpapers researchpapers = paperlist1.get(position);
        name.setText(researchpapers.getTitle());
        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");
        citations.setText(num);

        return listviewitem;
    }
}
