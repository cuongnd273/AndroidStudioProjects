package cuongnguyen.app.com.shoponline.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import cuongnguyen.app.com.shoponline.R;

/**
 * Created by CuongNguyen on 06/02/17.
 */

public class ClearEditText extends android.support.v7.widget.AppCompatEditText {
    Drawable crossX,noncrossX;
    Boolean visible=false;
    Drawable drawable;
    public ClearEditText(Context context) {
        super(context);
        _constructor();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        _constructor();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _constructor();
    }
    public void _constructor(){
        crossX= ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        noncrossX=ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent);
    }
    public void setting(){
        setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        Drawable[] drawables=getCompoundDrawables();
        if(visible)drawable=crossX;
        else drawable=noncrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(lengthAfter!=0 || (lengthBefore!=0 && start!=0))visible=true;
        else visible=false;
        setting();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(visible && event.getAction()==MotionEvent.ACTION_UP && event.getX()>=(getRight()-drawable.getBounds().width())){
            setText("");
            visible=false;
            setting();
        }
        return super.onTouchEvent(event);
    }
}
