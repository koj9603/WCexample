package com.example.wcexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> array;
    TextView text;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new ArrayList<String>();
        text = findViewById(R.id.text);
        list = findViewById(R.id.list);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {

                        try {
                            Document document = Jsoup.connect("http://naver.com/").get();
                            Elements elements = document.select("span.ah_k");
                            int i = 1;
                            for (Element e : elements) {
                                Log.d("검색어", e.text());
                                array.add(e.text());
                                if (i == 10) {
                                    break;
                                }
                                i++;
                            }
                        } catch (IOException e) {
                            Log.d("error", e.toString());
                        }
                        return null;
                    }
                }.execute();
            }
        });

    }
}
