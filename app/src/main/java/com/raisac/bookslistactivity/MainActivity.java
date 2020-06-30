package com.raisac.bookslistactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressBar mProgressBar;
    private RecyclerView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressBar);
        response = findViewById(R.id.booklist_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        response.setLayoutManager(manager);
        Intent intent = getIntent();
        String query = intent.getStringExtra("Query");
        URL booksUrl;
        try {
            if (query==null|| query.isEmpty()) {
                booksUrl = ApiUtil.buildUrl("cooking");
            } else {
                booksUrl = new URL(query);
            }
            new BooksQueryTask().execute(booksUrl);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        try {
            URL boolUrl = ApiUtil.buildUrl(query);
            new BooksQueryTask().execute(boolUrl);

        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchUrl);
            } catch (IOException e) {
                Log.e("error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView error = findViewById(R.id.error);
            if (response == null) {
                response.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            } else {
                response.setVisibility(View.VISIBLE);
                error.setVisibility(View.INVISIBLE);

                ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
                String resultString = "";
                for (Book book : books) {
                    resultString = resultString + book.title + "\n" +
                            book.publishedDate + "\n\n";
                }
                BookAdapter adapter = new BookAdapter(books);
                response.setAdapter(adapter);
                mProgressBar.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_advanced_search:
                startActivity(new Intent(this, Search.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
