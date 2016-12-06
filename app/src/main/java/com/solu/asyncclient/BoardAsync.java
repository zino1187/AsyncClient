/*
 Thread :  네트워크 요청..
 Handler : UI  에 결과 보여주기..
 */
package com.solu.asyncclient;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BoardAsync extends AsyncTask<String , Void, String>{
    String TAG;
    URL url;
    HttpURLConnection con;

    public BoardAsync() {
        TAG=this.getClass().getName();
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        BufferedReader buffr=null;
        StringBuffer sb = new StringBuffer();

        try {
            url = new URL(params[0]);
            con=(HttpURLConnection) url.openConnection();
            con.setDoInput(true); //입력 스트림 생성을 위한 속성 지정
            con.setRequestMethod("GET");
            con.getResponseCode(); //요청 발생 시점!!! HTTP표준 응답코드

            buffr = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            String data=null;

            while(true){
                data=buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(buffr!=null){
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String s) {
        Log.d(TAG, "넘겨받은 s 는 "+s);

        /*리스트뷰의 어댑터를 갱신 시키자!!*/


        super.onPostExecute(s);
    }


}
