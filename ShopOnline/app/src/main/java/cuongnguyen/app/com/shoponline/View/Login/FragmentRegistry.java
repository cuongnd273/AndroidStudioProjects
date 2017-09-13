package cuongnguyen.app.com.shoponline.View.Login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cuongnguyen.app.com.shoponline.CustomView.ClearEditText;
import cuongnguyen.app.com.shoponline.CustomView.PasswordEditText;
import cuongnguyen.app.com.shoponline.R;

public class FragmentRegistry extends Fragment implements View.OnClickListener,View.OnFocusChangeListener{
    ClearEditText fullName,email;
    PasswordEditText password,repeat_password;
    Button registry,registry_facebook,registry_google;
    SwitchCompat turn_receive_sale;
    TextView rule,commitment;
    public FragmentRegistry() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registry, container, false);
        getControls(view);
        return view;
    }
    void getControls(View v){
        fullName= (ClearEditText) v.findViewById(R.id.registry_fullName);
        email= (ClearEditText) v.findViewById(R.id.registry_email);
        password= (PasswordEditText) v.findViewById(R.id.registry_password);
        repeat_password= (PasswordEditText) v.findViewById(R.id.registry_repeat_password);
        registry= (Button) v.findViewById(R.id.button_registry);
        registry_facebook= (Button) v.findViewById(R.id.registry_facebook);
        registry_google= (Button) v.findViewById(R.id.registry_google);
        turn_receive_sale= (SwitchCompat) v.findViewById(R.id.turn_rececive_sale);
        rule= (TextView) v.findViewById(R.id.rule);
        commitment= (TextView) v.findViewById(R.id.commitment);

        fullName.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);
        repeat_password.setOnFocusChangeListener(this);
        registry.setOnClickListener(this);
        registry_facebook.setOnClickListener(this);
        registry_google.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_registry:
                break;
            case R.id.registry_facebook:
                break;
            case R.id.registry_google:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            TextInputLayout inputLayout;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                inputLayout = (TextInputLayout) v.getParentForAccessibility();
            }else{
                inputLayout = (TextInputLayout) v.getParent();
            }
            switch (v.getId()){
                case R.id.registry_fullName:
                    if(fullName.getText().length()==0) {
                        inputLayout.setErrorEnabled(true);
                        inputLayout.setError(getString(R.string.error_registry_fullname));
                    }else{
                        inputLayout.setErrorEnabled(false);
                    }
                    break;
                case R.id.registry_email:
                    if(!isValidEmail(email.getText().toString())){
                        inputLayout.setErrorEnabled(true);
                        inputLayout.setError(getString(R.string.error_registry_email));
                    }else {
                        inputLayout.setErrorEnabled(false);
                    }
                    break;
                case R.id.registry_repeat_password:
                    if(!repeat_password.getText().equals(password.getText()) || password.getText().equals("")){
                        inputLayout.setErrorEnabled(true);
                        inputLayout.setError(getString(R.string.error_registry_repeat_password));
                    }else{
                        inputLayout.setErrorEnabled(false);
                    }
                    break;
            }
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
