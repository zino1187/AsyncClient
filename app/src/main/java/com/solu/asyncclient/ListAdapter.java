package com.solu.asyncclient;
/*
 * Created by zino on 2016-12-06.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    /*웹서버로부터 데이터 가져오기..*/
    ArrayList<Board> list=new ArrayList<Board>();

    public ListAdapter(Context context) {
        this.context = context;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getBoard_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view=null;
        Board dto=list.get(i);

        if(convertView==null){ //새롭게 인플레이션 시켜야 할 경우라면...
            view=inflater.inflate(R.layout.fragment_item, null);
        }else{//기존의 뷰로 대체할 경우라면..
            view=convertView;
        }

        TextView txt_title=(TextView)view.findViewById(R.id.txt_title);
        TextView txt_writer=(TextView)view.findViewById(R.id.txt_writer);
        TextView txt_regdagte=(TextView)view.findViewById(R.id.txt_regdate);

        txt_title.setText(dto.getTitle());
        txt_writer.setText(dto.getWriter());
        txt_regdagte.setText(dto.getRegdate());

        return view;
    }
}
