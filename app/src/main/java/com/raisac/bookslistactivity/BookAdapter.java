package com.raisac.bookslistactivity;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    Context mContext;

    ArrayList<Book> mBooks;

    public BookAdapter(ArrayList<Book> books) {
        mBooks = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.Bind(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView authros;
        TextView publishers;
        TextView publishDate;
        TextView title;
        TextView description;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            authros = itemView.findViewById(R.id.author);
            publishDate = itemView.findViewById(R.id.date);
            publishers = itemView.findViewById(R.id.publishers);
            itemView.setOnClickListener(this);

        }

        public void Bind(Book book) {
            title.setText(book.title);
            authros.setText(book.authors);
            publishDate.setText(book.publishedDate);
            publishers.setText(book.publisher);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book selectedBook = mBooks.get(position);
            Intent intent = new Intent(v.getContext(), BookDetail.class);
            intent.putExtra("Book", selectedBook);
            v.getContext().startActivity(intent);

        }
    }
}