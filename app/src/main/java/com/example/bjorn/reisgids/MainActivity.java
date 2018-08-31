package com.example.bjorn.reisgids;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase reisgidsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //DB aanmaken of openen


        reisgidsDB = this.openOrCreateDatabase("reisgidsDB", MODE_PRIVATE, null);

        reisgidsDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "Amsterdam"
                + " (Activiteiten VARCHAR, Informatie VARCHAR ); ");

        reisgidsDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "Petersburg"
                + " (Activiteiten VARCHAR, Informatie VARCHAR ); ");

        //Testen of de database bestaat


        File dbFile = getDatabasePath("reisgidsDB");
        if (dbFile.exists()) {
            //Gegevens aanmaken

            reisgidsDB.execSQL("INSERT INTO "
                    + "Amsterdam"
                    + " (Activiteiten, Informatie)"
                    + " VALUES ('De dam bezoeken', 'Monument op de dam ter herdenking van de slachtoffers in de tweede wereldoorlog');");

            reisgidsDB.execSQL("INSERT INTO "
                    + "Petersburg"
                    + " (Activiteiten, Informatie)"
                    + " VALUES ('State Hermitage Museum bezoeken', 'Kunst en cultuur museum in Rusland');");


        } else {
            Log.i("reisgids DB", "bestaat niet");
        }



    }


    //Maak buttons die de layout veranderen onclick en haal de gegevens uit de database en show ze daar

    public void onClickAmsterdam(View v) {

        setContentView(R.layout.amsterdam);
        Cursor c = reisgidsDB.rawQuery("SELECT * FROM " + "Amsterdam", null);
        int Kolom1 = c.getColumnIndex("Activiteiten");
        int Kolom2 = c.getColumnIndex("Informatie");
        c.moveToFirst();
        String activiteiten = c.getString(Kolom1);
        String informatie = c.getString(Kolom2);

        TextView txtActiviteit = findViewById(R.id.txtActiviteit);
        txtActiviteit.setText(activiteiten);

        TextView txtInformatie = findViewById(R.id.txtInformatie);
        txtInformatie.setText(informatie);
    }


    public void onClickPetersburg(View v) {

        setContentView(R.layout.petersburg);
        Cursor c = reisgidsDB.rawQuery("SELECT * FROM " + "Petersburg", null);
        int Kolom1 = c.getColumnIndex("Activiteiten");
        int Kolom2 = c.getColumnIndex("Informatie");
        c.moveToFirst();
        String activiteiten = c.getString(Kolom1);
        String informatie = c.getString(Kolom2);

        TextView txtActiviteit = findViewById(R.id.txtActiviteit);
        txtActiviteit.setText(activiteiten);

        TextView txtInformatie = findViewById(R.id.txtInformatie);
        txtInformatie.setText(informatie);


    }

    public void onClickTerug(View v) {
        setContentView(R.layout.activity_main);
    }


}
