package com.example.paperscrapper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListViewAdapterMedical extends RecyclerView.Adapter<ListViewAdapterMedical.ViewHolder> {
    private List<Researchpapers> paperlist;
    private Context context;
    private ViewHolder holder;


    public ListViewAdapterMedical(@NonNull List<Researchpapers> paperlist,@NonNull Context context) {

        this.paperlist = paperlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewAdapterMedical.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.med_list, parent, false);
        holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterMedical.ViewHolder holder, final int position) {
        final Researchpapers researchpapers = paperlist.get(position);
        holder.name_med.setText(researchpapers.getTitle());
        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");
        holder.citations2_med.setText(num);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TempListViewClickedValue = researchpapers.getTitle().toString();
                String link1  = researchpapers.getLink().toString();
                String authors1  = researchpapers.getAuthors().toString();
                String journal1  = researchpapers.getJournal().toString();
                String citations1  = researchpapers.getCitations().toString();
                String abstract2 = researchpapers.getAbstract1().toString();
                Intent intent1 = new Intent(context, paperview.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("ListViewClickedValue", TempListViewClickedValue);
                intent1.putExtra("authors1", authors1 );
                intent1.putExtra("journal1", journal1);
                intent1.putExtra("citations1", citations1);
                intent1.putExtra("link1", link1);
                intent1.putExtra("abstract2", abstract2);
                context.startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.paperlist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_med;
        private TextView citations2_med;
        private View parentView;


        public ViewHolder(@NonNull View view) {
            super(view);
            this.parentView = view;
            this.name_med = (TextView)view
                    .findViewById(R.id.name_med);
            this.citations2_med = (TextView)view
                    .findViewById(R.id.citation2_med);

        }
    }
}