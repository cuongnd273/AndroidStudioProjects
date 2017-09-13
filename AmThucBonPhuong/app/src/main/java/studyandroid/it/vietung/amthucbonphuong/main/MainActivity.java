package studyandroid.it.vietung.amthucbonphuong.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.databases.Variable_Global;

public class MainActivity extends Activity {

    private Button btn_monan;
    private Button btn_timnhahang;
    private File directory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application cw = getApplication();
        File dir = cw.getFilesDir().getParentFile();
        directory = new File(dir.getAbsolutePath() + "/FoodFiles");
        if (!directory.exists())
            directory.mkdirs();
        Variable_Global.setDirectory(directory);

        btn_monan = (Button) findViewById(R.id.btn_monan);
        btn_timnhahang = (Button) findViewById(R.id.btn_timnhahang);

        btn_monan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContextTabsActivity.class);
                startActivity(intent);
            }
        });

        btn_timnhahang.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                  startActivity(intent);
                }
              }
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog myAlertDialog = taoMotAlertDialog();
            myAlertDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private AlertDialog taoMotAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Question!");
        builder.setMessage("Bạn có muốn thoát ứng dụng?");
        builder.setCancelable(false);
        builder.setPositiveButton("Hủy",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        System.exit(0);
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }

}
