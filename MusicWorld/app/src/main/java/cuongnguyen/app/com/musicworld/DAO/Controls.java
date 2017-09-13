package cuongnguyen.app.com.musicworld.DAO;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CuongNguyen on 4/11/2017.
 */

public class Controls {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public Controls(Context context){
        preferences=context.getSharedPreferences("controls",Context.MODE_PRIVATE);
    }
    public boolean getControlRandom(){
        return preferences.getBoolean("random",false);
    }
    public void setControlRandom(){
        editor=preferences.edit();
        if(preferences.getBoolean("random",false)){
            editor.putBoolean("random",false);
            editor.apply();
        }else{
            editor.putBoolean("random",true);
            editor.apply();
        }
    }
    public boolean getControlRepeat(){
        return preferences.getBoolean("repeat",false);
    }
    public void setControlRepeat(){
        editor=preferences.edit();
        if(preferences.getBoolean("repeat",false)){
            editor.putBoolean("repeat",false);
            editor.apply();
        }else{
            editor.putBoolean("repeat",true);
            editor.apply();
        }
    }
}
