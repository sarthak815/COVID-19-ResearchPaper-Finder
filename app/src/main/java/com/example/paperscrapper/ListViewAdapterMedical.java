package com.example.paperscrapper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*public class ListViewAdapterMedical extends RecyclerView.Adapter<ListViewAdapterMedical.ViewHolder> {
    private List<Researchpapers> paperlist;
    private Context context;
    private ViewHolder holder;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView title_med;
        public TextView citations_med;

        public UserViewHolder(View view) {
            super(view);
            title_med = (TextView) view.findViewById(R.id.name_med);
            citations_med = (TextView) view.findViewById(R.id.citation2_med);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }



    public ListViewAdapterMedical(RecyclerView recyclerView, List<Researchpapers> paperlist, Context context) {
            this.paperlist = paperlist;
            this.context = context;

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }
    @Override
    public int getItemViewType(int position) {
        return paperlist.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @NonNull
    @Override
    public ListViewAdapterMedical.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.med_list, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapterMedical.ViewHolder holder, final int position) {
        Researchpapers researchpapers = paperlist.get(position);

        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");

        UserViewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TempListViewClickedValue = paperlist.get(position).getTitle().toString();
                String link1  = paperlist.get(position).getLink().toString();
                String authors1  = paperlist.get(position).getAuthors().toString();
                String journal1  = paperlist.get(position).getJournal().toString();
                String citations1  = paperlist.get(position).getCitations().toString();
                String abstract2 = paperlist.get(position).getAbstract1().toString();
                Intent intent = new Intent(context, paperview.class);
                intent.putExtra("ListViewClickedValue", TempListViewClickedValue);
                intent.putExtra("authors1", authors1 );
                intent.putExtra("journal1", journal1);
                intent.putExtra("citations1", citations1);
                intent.putExtra("link1", link1);
                intent.putExtra("abstract2", abstract2);
                context.startActivity(intent);
            }
        });

        if (holder instanceof UserViewHolder) {
            Researchpapers researchpapers = paperlist.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.title_med.setText(researchpapers.getTitle());
            userViewHolder.citations_med.setText(num);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }




    }

    @Override
    public int getItemCount() {
        return paperlist == null ? 0 : paperlist.size();
    }

    public void setLoaded() {
        isLoading = false;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_med;
        private TextView citations2_med;
        private View parentView;



    }
}*/
public class ListViewAdapterMedical extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Researchpapers> paperlist;
    private Activity activity;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
// "Loading item" ViewHolder
    public ListViewAdapterMedical(RecyclerView recyclerView, List<Researchpapers> paperlist, Activity activity) {
        this.paperlist = paperlist;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }
    @Override
    public int getItemViewType(int position) {
        return paperlist.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.med_list, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Researchpapers researchpapers = paperlist.get(position);

        String num = String.valueOf(researchpapers.getCitations());
        String str[] = num.split(".");
        if (holder instanceof UserViewHolder) {
            researchpapers = paperlist.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.title_med.setText(researchpapers.getTitle());
            userViewHolder.citations_med.setText(num);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }
    @Override
    public int getItemCount() {
        return paperlist == null ? 0 : paperlist.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    // "Normal item" ViewHolder
    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView title_med;
        public TextView citations_med;

        public UserViewHolder(View view) {
            super(view);
            title_med = (TextView) view.findViewById(R.id.name_med);
            citations_med = (TextView) view.findViewById(R.id.citation2_med);
        }
    }










}
