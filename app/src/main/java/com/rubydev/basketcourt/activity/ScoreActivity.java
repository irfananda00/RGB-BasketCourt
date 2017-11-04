package com.rubydev.basketcourt.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rubydev.basketcourt.R;
import com.rubydev.basketcourt.api.Service;
import com.rubydev.basketcourt.api.ServiceGenerator;
import com.rubydev.basketcourt.model.ResMessage;
import com.rubydev.basketcourt.model.Score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener{
    String teamA, teamB;
    TextView tvTeamA, tvTeamB, tvScoreA, tvScoreB;
    Button btnSave, btnReset;
    Button btnAdd3A, btnMin3A, btnAdd2A, btnMin2A, btnAdd1A, btnMin1A;
    Button btnAdd3B, btnMin3B, btnAdd2B, btnMin2B, btnAdd1B, btnMin1B;
    int scoreA, scoreB;
    ServiceGenerator generator = new ServiceGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvTeamA = findViewById(R.id.tvTeamA);
        tvTeamB = findViewById(R.id.tvTeamB);
        tvScoreA = (TextView) findViewById(R.id.tvScoreA);
        tvScoreB = (TextView) findViewById(R.id.tvScoreB);
        btnSave  = (Button) findViewById(R.id.btnSave);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnAdd3A = (Button) findViewById(R.id.btnAdd3A);
        btnMin3A = (Button) findViewById(R.id.btnMin3A);
        btnAdd2A = (Button) findViewById(R.id.btnAdd2A);
        btnMin2A = (Button) findViewById(R.id.btnMin2A);
        btnAdd1A = (Button) findViewById(R.id.btnAdd1A);
        btnMin1A = (Button) findViewById(R.id.btnMin1A);
        btnAdd3B = (Button) findViewById(R.id.btnAdd3B);
        btnMin3B = (Button) findViewById(R.id.btnMin3B);
        btnAdd2B = (Button) findViewById(R.id.btnAdd2B);
        btnMin2B = (Button) findViewById(R.id.btnMin2B);
        btnAdd1B = (Button) findViewById(R.id.btnAdd1B);
        btnMin1B = (Button) findViewById(R.id.btnMin1B);

        btnAdd3A.setOnClickListener(this);
        btnMin3A.setOnClickListener(this);
        btnAdd2A.setOnClickListener(this);
        btnMin2A.setOnClickListener(this);
        btnAdd1A.setOnClickListener(this);
        btnMin1A.setOnClickListener(this);
        btnAdd3B.setOnClickListener(this);
        btnMin3B.setOnClickListener(this);
        btnAdd2B.setOnClickListener(this);
        btnMin2B.setOnClickListener(this);
        btnAdd1B.setOnClickListener(this);
        btnMin1B.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        Intent intent = getIntent();
        teamA = intent.getStringExtra("teamA");
        teamB = intent.getStringExtra("teamB");

        tvTeamA.setText(teamA);
        tvTeamB.setText(teamB);

        tvScoreA.setText("0");
        tvScoreB.setText("0");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd1A:
                scoreA = scoreA + 1;
                break;
            case R.id.btnAdd2A:
                scoreA = scoreA + 2;
                break;
            case R.id.btnAdd3A:
                scoreA = scoreA + 3;
                break;
            case R.id.btnMin1A:
                if (scoreA > 0){
                    scoreA = scoreA - 1;
                }
                break;
            case R.id.btnMin2A:
                if (scoreA >= 2){
                    scoreA = scoreA - 2;
                }
                break;
            case R.id.btnMin3A:
                if (scoreA >= 3){
                    scoreA = scoreA - 3;
                }
                break;
            case R.id.btnAdd1B:
                scoreB = scoreB + 1;
                break;
            case R.id.btnAdd2B:
                scoreB = scoreB + 2;
                break;
            case R.id.btnAdd3B:
                scoreB = scoreB + 3;
                break;
            case R.id.btnMin1B:
                if (scoreB > 0){
                    scoreB = scoreB - 1;
                }
                break;
            case R.id.btnMin2B:
                if (scoreB >= 2){
                    scoreB = scoreB - 2;
                }
                break;
            case R.id.btnMin3B:
                if (scoreB >= 3){
                    scoreB = scoreB - 3;
                }
                break;
            case R.id.btnReset:
                scoreA =0;
                scoreB =0;
                break;
            case R.id.btnSave:
//                finish();

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
                Date now = Calendar.getInstance().getTime();
                String date = dateFormatter.format(now);

                Score score = new Score(teamA, teamB,
                        scoreA, scoreB,
                        date,
                        "1301142018");

                saveData(score);
                break;
        }
        tvScoreA.setText("" + scoreA);
        tvScoreB.setText("" + scoreB);
    }

    private void saveData(Score score) {

        // Create a very simple REST adapter which points the API endpoint.
        Service client =  generator.client;

        // Fetch a list
        Call<ResMessage> call = client.addScore(
                score.getTeam_a(),
                score.getTeam_b(),
                score.getScore_a()+"",
                score.getScore_b()+"",
                score.getDate(),
                score.getNim()
        );

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<ResMessage>() {
            @Override
            public void onResponse(Call<ResMessage> call, Response<ResMessage> response) {
                // The network call was a success and we got a response
                ResMessage res = response.body();
                if (res.getMessage().equals("success")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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
}
