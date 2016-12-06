/*
 Thread :  네트워크 요청..
 Handler : UI  에 결과 보여주기..
 */
package com.solu.asyncclient;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardAsync extends AsyncTask<String , Void, String>{
    String TAG;
    URL url;
    HttpURLConnection con;
    ListFragment listFragment;

    public BoardAsync(ListFragment listFragment){
        TAG=this.getClass().getName();
        this.listFragment=listFragment;
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

        ArrayList<Board> list = new ArrayList<Board>();

        //넘겨받은 json 자바의 객체로 전환....
        try {
            JSONArray array=new JSONArray(s);
            for(int i=0;i<array.length();i++){
                JSONObject obj=array.getJSONObject(i);
                Board board = new Board();
                board.setBoard_id(obj.getInt("board_id"));
                board.setWriter(obj.getString("writer"));
                board.setTitle(obj.getString("title"));
                board.setContent(obj.getString("content"));
                board.setRegdate(obj.getString("regdate"));
                board.setHit(obj.getInt("hit"));

                list.add(board);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*리스트뷰의 어댑터를 갱신 시키자!!*/
        listFragment.listAdapter.list=list;
        listFragment.listAdapter.notifyDataSetChanged();

        super.onPostExecute(s);
    }


}
