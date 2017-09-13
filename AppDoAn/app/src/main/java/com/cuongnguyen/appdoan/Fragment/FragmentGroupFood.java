package com.cuongnguyen.appdoan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cuongnguyen.appdoan.Activity.InformationFood;
import com.cuongnguyen.appdoan.Adapter.AdapterFood;
import com.cuongnguyen.appdoan.Database.DBAdapter;
import com.cuongnguyen.appdoan.Model.MonAn;
import com.cuongnguyen.appdoan.R;

import java.util.List;

/**
 * Created by CUONG on 7/21/2016.
 */
public class FragmentGroupFood extends Fragment {
    private String maNhomMonAn;
    private List<MonAn> list;
    ListView listViewFood;
    AdapterFood adapterFood;
    DBAdapter db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DBAdapter(getContext());
    }

    public FragmentGroupFood(String maNhomMonAn) {
        this.maNhomMonAn=maNhomMonAn;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_food,container,false);
        listViewFood=(ListView)view.findViewById(R.id.listFood);
        list=db.getAllMonAn(maNhomMonAn);

        adapterFood=new AdapterFood(getContext(),R.layout.item_food,list);
        listViewFood.setAdapter(adapterFood);
        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), InformationFood.class);
                intent.putExtra("food",list.get(position));
                startActivity(intent);
            }
        });
        return view;
    }
}
