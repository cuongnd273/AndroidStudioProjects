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

import com.example.manh.appconvert.R;


/**
 * Created by cuong on 9/28/2016.
 */
public class FragmentConvertTemperature extends Fragment {
    String arr[]={"C","F","Kelvin","Rankine"};
    ArrayAdapter arrayAdapter;
    int tempFrom,tempTo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,arr);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_convert_temperature,container,false);
        Spinner spinnerFrom=(Spinner)view.findViewById(R.id.spinnerFromTemperature);
        Spinner spinnerTo=(Spinner)view.findViewById(R.id.spinnerToTemperature);
        Button convert=(Button)view.findViewById(R.id.converTemperature);
        final EditText from=(EditText)view.findViewById(R.id.fromTemperature);
        final TextView to=(TextView)view.findViewById(R.id.toTemperature);
        spinnerFrom.setAdapter(arrayAdapter);
        spinnerTo.setAdapter(arrayAdapter);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(from.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Nhập số cần chuyển",Toast.LENGTH_SHORT).show();
                }else{
                    double temp=Double.valueOf(from.getText().toString());
                    //Chuyển nhiệt độ đầu vào về độ C
                    if(tempFrom==1)
                    {
                        temp=(temp-32)/1.8;
                    }else if(tempFrom==2)
                    {
                        temp=temp-273.15;
                    }else if(tempFrom==3)
                    {
                        temp=(temp-491.76)/1.8;
                    }

                    //Sau đó chuyển độ C về dạng đã chọn
                    if(tempTo==1)
                    {
                        temp=temp*1.8+32;
                    }else if(tempTo==2)
                    {
                        temp=temp+273.15;
                    }else if(tempTo==3)
                    {
                        temp=temp*1.8+491.67;
                    }
                    to.setText(String.format("%.2f",temp));
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempFrom=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempTo=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}
