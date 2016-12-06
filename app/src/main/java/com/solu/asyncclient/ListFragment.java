package com.solu.asyncclient;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/* 게시판 목록 보여주기*/
public class ListFragment extends Fragment{
    ListAdapter listAdapter; //ListFragment 를 제어하는 어댑터...
    ListView listView;
    BoardAsync boardAsync;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.fragment_list, null);

        //리스트뷰와 어댑터 연결!!
        listView=(ListView) view.findViewById(R.id.listView);
        listAdapter = new ListAdapter(this.getContext());
        listView.setAdapter(listAdapter);

        boardAsync = new BoardAsync(this);
        boardAsync.execute("http://192.168.0.15:9090/device/board");

        return view;
    }

}














