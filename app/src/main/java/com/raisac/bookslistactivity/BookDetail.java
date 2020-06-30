package com.raisac.bookslistactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.raisac.bookslistactivity.databinding.ActivityBookDetailBinding;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Book book = getIntent().getParcelableExtra("Book");
        ActivityBookDetailBinding bookDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_book_detail);
        bookDetailBinding.setBook(book);

    }
}
