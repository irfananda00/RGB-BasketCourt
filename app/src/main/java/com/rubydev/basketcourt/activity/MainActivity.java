package com.rubydev.basketcourt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rubydev.basketcourt.R;
import com.rubydev.basketcourt.adapter.HistoryAdapter;
import com.rubydev.basketcourt.api.Service;
import com.rubydev.basketcourt.api.ServiceGenerator;
import com.rubydev.basketcourt.model.ResMessage;
import com.rubydev.basketcourt.model.Score;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Score> listHistory = new ArrayList<>();
    HistoryAdapter adapter;
    RecyclerView rvHistory;
    ServiceGenerator generator = new ServiceGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(MainActivity.this, listHistory);
        rvHistory.setAdapter(adapter);

        loadHistory("1301142018");

//        listHistory.add(new Score("RPL","Basdat",12,13,"22-10-2016"));
//        listHistory.add(new Score("RPL","Basdat",12,13,"22-10-2016"));
//        listHistory.add(new Score("RPL","Basdat",12,13,"22-10-2016"));
//        adapter.notifyItemInserted(listHistory.size());
    }

    private void loadHistory(String nim) {
        Service client =  generator.client;

        Call<ResMessage> call = client.listScore(nim);

        call.enqueue(new Callback<ResMessage>() {
            @Override
            public void onResponse(Call<ResMessage> call, Response<ResMessage> response) {
                // The network call was a success and we got a response
                ResMessage res = response.body();
                if (res.getMessage().equals("success")) {
                    listHistory.addAll(res.getContent());
                    adapter.notifyItemInserted(listHistory.size());
                }else {
                    onFailure(call, new Throwable(res.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<ResMessage> call, Throwable t) {
                // the network call was a failure
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.i("testingIrfan", "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
