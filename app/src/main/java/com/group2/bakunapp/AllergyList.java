package com.group2.bakunapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AllergyList extends AppCompatActivity {

    private static String JSON_URL= "https://run.mocky.io/v3/8ace7c0b-f2b3-4e23-9a9d-b8b15167ea10";
    private static String JSON_URL2= "https://run.mocky.io/v3/824b8c75-9fa0-4348-92e3-0b01d27ea4db";
    private static String JSON_URL3= "https://run.mocky.io/v3/fb652e82-e189-4ebf-9afd-fb2191de723f";



    List<AllergyModelClass> allergyList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_list);


        allergyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewallergy);
        Button next = (Button) findViewById(R.id.continuebtn);

        GetData getData = new GetData();
        getData.execute();




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AllergyList.this,Appointment.class));
            }
        });




    }


    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {


            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL3);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);


                    int data = isr.read();
                    while (data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return  current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {


            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("allergy");

                for (int i = 0;i< jsonArray.length() ; i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    AllergyModelClass allergymodel = new AllergyModelClass();
                    allergymodel.setId(jsonObject1.getString("id"));
                    allergymodel.setName(jsonObject1.getString("name"));
                    allergymodel.setDescription(jsonObject1.getString("description"));
                    allergymodel.setImage(jsonObject1.getString("image"));
                    allergymodel.setList1(jsonObject1.getString("list1"));
                    allergymodel.setList2(jsonObject1.getString("list2"));
                    allergymodel.setList3(jsonObject1.getString("list3"));
                    allergymodel.setList4(jsonObject1.getString("list4"));

                    allergyList.add(allergymodel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(allergyList);

        }
    }

    private void PutDataIntoRecyclerView(List<AllergyModelClass>allergyList){

        AllergyAdopter allergyAdopter = new AllergyAdopter(this, allergyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(allergyAdopter);

    }

}