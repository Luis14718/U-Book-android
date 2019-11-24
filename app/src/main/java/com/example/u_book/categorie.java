package com.example.u_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



import java.util.ArrayList;


import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class categorie extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);



        settingDummyData();
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());






    }
    public void onClick(View view) {
        setContentView(R.layout.activity_categorie);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        switch (view.getId()){
            case R.id.btn1:
                settingDummyData();

                break;
            case R.id.btn2:
                settingPsychology();
                break;
            case R.id.btn3:
                settingFinance();
                break;
            case R.id.btn4:
                settingMacroeconomics();
                break;
            case R.id.btn5:
                settingBusiness();
                break;
        }
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

    }







    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }



    private  void setdata(String name, String description, String image){


    }
    private void settingDummyData() {


        games = new ArrayList<>();
        games.add(new Game(R.drawable.essentialsofnegotiation, "Essentials of Negotiation","77862465"));
        games.add(new Game(R.drawable.strategicmanagement, "Strategic Management","1260092372"));
        games.add(new Game(R.drawable.sellingbulidingpartherships, "Selling building Partherships","N/A"));
        games.add(new Game(R.drawable.theworldofchildren, "The World of Children","205940145"));
        games.add(new Game(R.drawable.abnormalpsychology, "Abnormal Psychology","1506333354"));
        games.add(new Game(R.drawable.corporatefinance, "Corporate Finance","N/A"));
        games.add(new Game(R.drawable.essentialsofcorporatefinance, "Essentials of Corporate Finance","1259277216"));
        games.add(new Game(R.drawable.publicfinance, "Public Finance","78021685"));
        games.add(new Game(R.drawable.entrepen, "Personal Finance","1133595839"));
        games.add(new Game(R.drawable.macroeconomicsessentials, "Macroecnomics Essentials","N/A"));
        games.add(new Game(R.drawable.macro, "Macroeconomics","262533340"));



    }

    private void settingBusiness() {


        games = new ArrayList<>();
        games.add(new Game(R.drawable.essentialsofnegotiation, "Essentials of Negotiation","77862465"));
        games.add(new Game(R.drawable.strategicmanagement, "Strategic Management","1260092372"));
        games.add(new Game(R.drawable.sellingbulidingpartherships, "Selling building Partherships","N/A"));


    }
    private void settingPsychology() {


        games = new ArrayList<>();
        games.add(new Game(R.drawable.theworldofchildren, "The World of Children","205940145"));
        games.add(new Game(R.drawable.abnormalpsychology, "Abnormal Psychology","1506333354"));

    }
    private void settingFinance() {


        games = new ArrayList<>();
        games.add(new Game(R.drawable.corporatefinance, "Corporate Finance","N/A"));
        games.add(new Game(R.drawable.essentialsofcorporatefinance, "Essentials of Corporate Finance","1259277216"));
        games.add(new Game(R.drawable.publicfinance, "Public Finance","78021685"));
        games.add(new Game(R.drawable.entrepen, "Personal Finance","1133595839"));

    }
    private void settingMacroeconomics() {


        games = new ArrayList<>();
        games.add(new Game(R.drawable.macroeconomicsessentials, "Macroecnomics Essentials","N/A"));
        games.add(new Game(R.drawable.macro, "Macroeconomics","262533340"));

    }

}
