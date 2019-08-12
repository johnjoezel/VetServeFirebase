package com.example.vetservefirebase.Others;

import android.os.AsyncTask;
import android.util.Log;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.AddPet.AddPetView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchData extends AsyncTask<String, Void, ArrayList<String>>{

    private final String urlString;
    private AddPetActivity addPetActivity;
    String datas = "";


    public fetchData(String urlString,TaskListener taskListener) {
        this.urlString = urlString;
        this.taskListener = taskListener;
    }

    public interface TaskListener {
         void onFinished(ArrayList<String> result);
    }

    // This is the reference to the associated listener
    private final TaskListener taskListener;

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        ArrayList<String> data = new ArrayList<>();
        try {

            Log.d("mmmm", "doInBackground: " + urlString);
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                datas = datas + line;
            }
            JSONArray JA = new JSONArray(datas);
            for(int i = 0; i <JA.length(); i++) {
                JSONObject jsonObject = JA.getJSONObject(i);
                Breedname kigwa = new Breedname(jsonObject.getString("name"));
                data.add(kigwa.getBreedname());
            }
            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        if(this.taskListener != null) {
            // And if it is we call the callback function on it.
            this.taskListener.onFinished(strings);
        }
    }
}

//    @Override
//    protected ArrayList<String> doInBackground(Void... params) {
//        ArrayList<String> data = new ArrayList<>();
//        try {
//            URL url = new URL("https://api.thedogapi.com/v1/breeds");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while(line != null){
//                line = bufferedReader.readLine();
//                datas = datas + line;
//            }
//            JSONArray JA = new JSONArray(datas);
//            for(int i = 0; i <JA.length(); i++) {
//                JSONObject jsonObject = JA.getJSONObject(i);
//                Breedname kigwa = new Breedname(jsonObject.getString("name"));
//                data.add(kigwa.getBreedname());
//            }
//            Log.d("asdfasdf", data.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }


