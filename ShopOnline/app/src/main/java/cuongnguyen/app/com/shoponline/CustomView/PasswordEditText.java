package cuongnguyen.app.com.shoponline.CustomView;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cuongnguyen.app.com.shoponline.R;

/**
 * Created by CuongNguyen on 06/02/17.
 */
public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {
    Drawable eye,eyeStrike;
    Boolean visible=false;
    Boolean useStrike=false;
    Boolean useCheckValue=false;
    Drawable drawable;
    public PasswordEditText(Context context) {
        super(context);
        _constructor(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        _constructor(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _constructor(attrs);
    }
    public void _constructor(AttributeSet attributeSet){
        if(attributeSet!=null){
            TypedArray typedArray=getContext().getTheme().obtainStyledAttributes(attributeSet,R.styleable.PasswordsEditText,0,0);
            this.useStrike=typedArray.getBoolean(R.styleable.PasswordsEditText_useStrike,false);
            this.useCheckValue=typedArray.getBoolean(R.styleable.PasswordsEditText_useCheckValue,false);
        }
        eye=ContextCompat.getDrawable(getContext(),R.drawable.ic_visibility_black_24dp);
        eyeStrike=ContextCompat.getDrawable(getContext(),R.drawable.ic_visibility_off_black_24dp);
        setting();
        if(useCheckValue){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                   if(!hasFocus){
                       String txt=getText().toString();
                       TextInputLayout inputLayout= null;
                       if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                           inputLayout = (TextInputLayout) v.getParentForAccessibility();
                       }else{
                           inputLayout = (TextInputLayout) v.getParent();
                       }
                       if(txt.length()<6 || txt.length()>20){
                           inputLayout.setError("Mật khẩu phải có độ dài từ 6 đến 20 ký tự.");
                       }else{
                           inputLayout.setErrorEnabled(false);
                           inputLayout.setError("");
                       }
                   }
                }
            });
        }
    }
    public void setting(){
        setInputType(InputType.TYPE_CLASS_TEXT | (useStrike && visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables=getCompoundDrawables();
        drawable=useStrike && visible ? eye : eyeStrike;
        drawable.setAlpha(80);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(useStrike && event.getAction()==MotionEvent.ACTION_UP && event.getX()>=getRight()-drawable.getBounds().width()){
            visible=!visible;
            setting();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}

