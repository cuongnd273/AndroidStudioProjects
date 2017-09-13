package cuongnguyen.app.com.shoponline.Model.ObjectClass;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CuongNguyen on 06/05/17.
 */

public class Account {
    public static final int LOGIN_EMAIL=1;
    public static final int LOGIN_FACEBOOK=2;
    public static final int LOGIN_GOOGLE_PLUS=3;
    public static int getLogged(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("logged",0);
    }
    public static void setLogged(Context context,int typeLogin){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("logged",typeLogin);
        editor.apply();
    }
    public static String getId(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        return sharedPreferences.getString("id","");
    }
    public static void setId(Context context,String id){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.apply();
    }
    public static String getName(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }
    public static void setName(Context context,String name){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info_account",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",name);
        editor.apply();
    }
}
