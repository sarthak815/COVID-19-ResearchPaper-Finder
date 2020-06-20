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

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    List<Researchpapers> paperlist;
    Context context;
    private ViewHolder holder;


    public ListViewAdapter(List<Researchpapers> paperlist, Context context) {
        this.paperlist = paperlist;
        this.context = context;
    }


    @NonNull
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nonmed_list, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ViewHolder holder, final int position) {
        Researchpapers researchpapers = paperlist.get(position);
        holder.name.setText(researchpapers.getTitle());
        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");
        holder.citations2.setText(num);
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TempListViewClickedValue = paperlist.get(position).getTitle().toString();
                String link1  = paperlist.get(position).getLink().toString();
                String authors1  = paperlist.get(position).getAuthors().toString();
                String journal1  = paperlist.get(position).getJournal().toString();
                String citations1  = paperlist.get(position).getCitations().toString();
                String abstract2 = paperlist.get(position).getAbstract1().toString();
                Intent intent = new Intent(context, paperview.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ListViewClickedValue", TempListViewClickedValue);
                intent.putExtra("authors1", authors1 );
                intent.putExtra("journal1", journal1);
                intent.putExtra("citations1", citations1);
                intent.putExtra("link1", link1);
                intent.putExtra("abstract2", abstract2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.paperlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView citations2;
        private View parentView;


        public ViewHolder(@NonNull View view) {
            super(view);
            this.parentView = view;
            this.name = (TextView)view
                    .findViewById(R.id.name);
            this.citations2 = (TextView)view
                    .findViewById(R.id.citation2);

        }
    }
}
