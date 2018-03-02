package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.main.MainPresenter;
import com.udacity.sandwichclub.main.MainView;
import com.udacity.sandwichclub.main.ResourceProvider;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(new ResourceProvider(getResources()));
        mainPresenter.attachView(this);
    }

    @Override
    public void bindSandwiches(final String[] sandwiches) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sandwiches);

        ListView listView = findViewById(R.id.sandwiches_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mainPresenter.sandwichSelected(sandwiches[position]);
            }
        });
    }

    @Override
    public void showSandwichDetails(String sandwichName) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_SANDWICH_NAME, sandwichName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
