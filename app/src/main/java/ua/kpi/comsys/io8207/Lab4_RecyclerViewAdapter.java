package ua.kpi.comsys.io8207;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Lab4_RecyclerViewAdapter extends RecyclerView.Adapter<Lab4_RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Lab4_Book> mData;

    public Lab4_RecyclerViewAdapter(Context mContext, List<Lab4_Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.lab4_book_item, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lab4_Book book = mData.get(position);

        holder.name.setText(mData.get(position).getBookName());
        holder.year.setText(mData.get(position).getSubtitle());
        holder.type.setText(mData.get(position).getAuthors());
        holder.image.setImageResource(mData.get(position).getImage());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Lab4_ActivityDetail.class);
                intent.putExtra("bookimage", book.getImage());
                intent.putExtra("booktitle", book.getBookName());
                intent.putExtra("booksubtitle", book.getSubtitle());
                intent.putExtra("bookauthors", book.getAuthors());
                intent.putExtra("bookpublisher", book.getPublisher());
                intent.putExtra("bookisbn13", book.getIsbn13());
                intent.putExtra("bookpages", book.getPages());
                intent.putExtra("bookyear", book.getYear());
                intent.putExtra("bookrating", book.getRating());
                intent.putExtra("bookdesc", book.getDesc());
                intent.putExtra("bookprice", book.getPrice());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView year;
        private TextView type;
        private ImageView image;
        LinearLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.title_book_item);
            year = itemView.findViewById(R.id.subtitle_book_item);
            type = itemView.findViewById(R.id.authors_book_item);
            image = itemView.findViewById(R.id.image_book_item);

            parentLayout = itemView.findViewById(R.id.item_parants);
        }
    }

    public void filterList(ArrayList<Lab4_Book> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }
}
