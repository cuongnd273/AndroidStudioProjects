package com.example.manh.appconvert.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manh.appconvert.Database.DatabaseAdapter;
import com.example.manh.appconvert.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 9/28/2016.
 */
public class FragmentConvertCurrency extends Fragment {
    List<String> titles=new ArrayList<String>();
    List<Double> ratios=new ArrayList<Double>();
    DatabaseAdapter databaseAdapter=new DatabaseAdapter(getActivity());
    ArrayAdapter arrayAdapter;
    double ratioFrom,ratioTo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles=databaseAdapter.getTitleCurrency();
        ratios=databaseAdapter.getRatioCurrency();
        arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,titles);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_convert_currency,container,false);
        Spinner spinnerFrom=(Spinner)view.findViewById(R.id.spinnerFromCurrency);
        Spinner spinnerTo=(Spinner)view.findViewById(R.id.spinnerToCurrency);
        Button convert=(Button)view.findViewById(R.id.converCurrency);
        final EditText from=(EditText)view.findViewById(R.id.fromCurrency);
        final TextView to=(TextView)view.findViewById(R.id.toCurrency);
        spinnerFrom.setAdapter(arrayAdapter);
        spinnerTo.setAdapter(arrayAdapter);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(from.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Nhập số cần chuyển",Toast.LENGTH_SHORT).show();
                }else{
                    //Dựa vào tỉ lệ của tiền quy đổi ra tiền muốn chuyển
                    double result=(Double.parseDouble(from.getText().toString())*ratioTo)/ratioFrom;
                    to.setText(String.format("%.5f",result));
            }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ratioFrom=ratios.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ratioTo=ratios.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }
}
