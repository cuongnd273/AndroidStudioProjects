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
 * Created by cuong on 10/12/2016.
 */
public class FragmentConvertNumberIEEE extends Fragment {
    String arrFrom[]={"Thập phân","Nhị phân dấu chấm động"};
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
        final Spinner spinnerFrom = (Spinner) view.findViewById(R.id.spinnerFromNumber);
        final Spinner spinnerTo = (Spinner) view.findViewById(R.id.spinnerToNumber);
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
                    String fromNumber = from.getText().toString();
                    String result=fromNumber;
                    if (posFrom == 0) {
                        result = GetBinary32(Float.parseFloat(fromNumber));
                    } else if (posFrom == 1) {
                        result= String.valueOf(GetFloat32(fromNumber));
                    }
                    to.setText(result);
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posFrom=position;
                if(position==0)
                {
                    spinnerTo.setSelection(1);
                }else if(position==1){
                    spinnerTo.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    spinnerFrom.setSelection(1);
                }else if(position==1){
                    spinnerFrom.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
    private static float GetFloat32( String Binary )
    {
        int intBits = Integer.parseInt(Binary, 2);
        float myFloat = Float.intBitsToFloat(intBits);
        return myFloat;
    }

    // Get 32-bit IEEE 754 format of the decimal value
    private static String GetBinary32( float value )
    {
        int intBits = Float.floatToIntBits(value);
        String binary = Integer.toBinaryString(intBits);
        return binary;
    }

}
