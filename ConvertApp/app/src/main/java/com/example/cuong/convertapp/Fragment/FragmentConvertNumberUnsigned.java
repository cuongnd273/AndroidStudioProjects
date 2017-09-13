package com.example.cuong.convertapp.Fragment;

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

import com.example.cuong.convertapp.R;

/**
 * Created by cuong on 9/28/2016.
 */
public class FragmentConvertNumberUnsigned extends Fragment {
    String arrFrom[]={"Thập phân","Nhị phân","Bát phân","Thập lục phân"};
    double posFrom,posTo;
    ArrayAdapter arrayAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, arrFrom);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_number, container, false);
        Spinner spinnerFrom = (Spinner) view.findViewById(R.id.spinnerFromNumber);
        Spinner spinnerTo = (Spinner) view.findViewById(R.id.spinnerToNumber);
        Button convert = (Button) view.findViewById(R.id.converNumber);
        final EditText from = (EditText) view.findViewById(R.id.fromNumber);
        final TextView to = (TextView) view.findViewById(R.id.toNumber);
        spinnerFrom.setAdapter(arrayAdapter);
        spinnerTo.setAdapter(arrayAdapter);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (from.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Nhập số cần chuyển", Toast.LENGTH_SHORT).show();
                } else {
                    String fromNumber=from.getText().toString();
                    String result=fromNumber;
                    if(posFrom==1)
                    {
                        result=String.valueOf(Integer.parseInt(fromNumber,2));
                    }else if(posFrom==2)
                    {
                        result=String.valueOf(Integer.parseInt(fromNumber,8));
                    }else if(posFrom==3)
                    {
                        result=String.valueOf(Integer.parseInt(fromNumber,16));
                    }
                    if(posTo==1)
                    {
                        result=Integer.toString(Integer.parseInt(result),2);
                    }else if(posTo==2)
                    {
                        result=Integer.toString(Integer.parseInt(result),8);
                    }else if(posTo==3)
                    {
                        result=Integer.toString(Integer.parseInt(result),16);
                    }
                    to.setText(result);
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posFrom = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posTo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}
