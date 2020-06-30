package com.raisac.bookslistactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText title = findViewById(R.id.edTitle);
        Button search = findViewById(R.id.searchBtn);
        final EditText authors = findViewById(R.id.edAuthor);
        final EditText publisher = findViewById(R.id.edPublisher);
        final EditText isnb = findViewById(R.id.edISBN);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle = title.getText().toString().trim();
                String sAuthor = authors.getText().toString().trim();
                String sPublishers = publisher.getText().toString().trim();
                String sIsbn = isnb.getText().toString().trim();


                if (sAuthor.isEmpty() && sTitle.isEmpty() && sPublishers.isEmpty() && sIsbn.isEmpty()) {
                    String noData = getString(R.string.no_searchData);
                    Toast.makeText(Search.this, noData, Toast.LENGTH_SHORT).show();
                } else {
                    URL QueryUrl = ApiUtil.buildUrl(sTitle, sAuthor, sPublishers, sIsbn);

                    Context context = getApplicationContext();
                    int position = SpUtil.getPrefenceInt(context, SpUtil.POSTION);
                    if (position == 0 || position == 5) {
                        position = 1;
                    } else {
                        position++;
                    }
                    String key = SpUtil.QUERY + position;
                    String value = sTitle + "," + sAuthor + "," + sPublishers + "," + sIsbn;
                    SpUtil.setPreferenceString(context, key, value);
                    SpUtil.setPreferenceInt(context, SpUtil.POSTION, position);

                    Intent intent = new Intent(Search.this, MainActivity.class);
                    intent.putExtra("Query", QueryUrl);
                    startActivity(intent);
                }

            }
        });


    }

}
