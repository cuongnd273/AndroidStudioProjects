package com.example.cuong.convertapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
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
 * Created by cuong on 10/9/2016.
 */
public class FragmentConvertNumberSigned extends Fragment {
    String arrFrom[]={"Thập phân","Nhị phân bù 1","Nhị phân bù 2"};
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
        from.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                        result= String.valueOf(BinaryToDecimal1(fromNumber));
                    }else if(posFrom==2)
                    {
                        result= BinaryToDecimail2(fromNumber);
                    }
                    if(posTo==1)
                    {
                        result= String.valueOf(DecimalToBinary(result));
                    }else if(posTo==2)
                    {
                        result=Integer.toBinaryString(Integer.parseInt(result));
                    }
                    to.setText(result);
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posFrom=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posTo=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
    public char[] DecimalToBinary(String fromNumber)
    {
        String result="";
//        result=fromNumber.substring(1,fromNumber.length());
        result = Integer.toString(Integer.parseInt(fromNumber)*-1,2);
        char add[]=new char[result.length()];
        for (int i=0;i<result.length();i++)
        {
            if(result.charAt(i)=='0')
            {
                add[i]='1';
            }else if(result.charAt(i)=='1'){
                add[i]='0';
            }
        }
        result="1"+String.valueOf(add);
        char resultBinary[] = new char[32];
        for (int i=0; i < 32; i++)
            resultBinary[i] = '1';
        result = new StringBuffer(result).reverse().toString();
        for(int i = 0; i < result.length(); i++)
            resultBinary[resultBinary.length - i - 1] = result.charAt(i);
        return resultBinary;
    }
    public String BinaryToDecimal1(String fromNumber)
    {
        if(fromNumber.toCharArray()[0]=='1')
        {
            char add[]=new char[fromNumber.length()];
            for (int i=0;i<fromNumber.length();i++)
            {
                if(fromNumber.charAt(i)=='0')
                {
                    add[i]='1';
                }else if(fromNumber.charAt(i)=='1'){
                    add[i]='0';
                }
            }
            return String.valueOf(-1*Integer.parseInt(String.valueOf(add),2));
        }
        return String.valueOf(Integer.parseInt(fromNumber,2));
    }
    public String BinaryToDecimail2(String fromNumber) {
        if (fromNumber.toCharArray()[0] == '1') {
            char add[]=new char[fromNumber.length()];
            for (int i = 0; i < fromNumber.length(); i++) {
                if(fromNumber.charAt(i)=='0')
                {
                    add[i]='1';
                }else if(fromNumber.charAt(i)=='1'){
                    add[i]='0';
                }
            }
            for (int i = fromNumber.length() - 1; i >= 0; i--) {
                if (add[i] == '0') {
                    add[i] = '1';
                    break;
                }
                add[i] = '0';
            }
            return String.valueOf(-1*Integer.parseInt(String.valueOf(add),2));
        }
        return String.valueOf(Integer.parseInt(fromNumber,2));
    }
    private static String floatToBinaryString( double n ) {
        String val = "0.";    // Setting up string for result
        while ( n > 0 ) {     // While the fraction is greater than zero (not equal or less than zero)
            double r = n * 2;   // Multiply current fraction (n) by 2
            if( r >= 1 ) {      // If the ones-place digit >= 1
                val += "1";       // Concat a "1" to the end of the result string (val)
                n = r - 1;        // Remove the 1 from the current fraction (n)
            }else{              // If the ones-place digit == 0
                val += "0";       // Concat a "0" to the end of the result string (val)
                n = r;            // Set the current fraction (n) to the new fraction
            }
        }
        return val;          // return the string result with all appended binary values
    }
}
