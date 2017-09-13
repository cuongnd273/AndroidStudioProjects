package free.app.com.moviebooking.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.model.User;

public class ThongTinActivity extends AppCompatActivity {
    TextView cmnd,taikhoan,hoten,ngaysinh,sdt,diachi;
    Toolbar toolbar;
    User user;
    Button dangxuat;
    int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        user=User.getUser(this);
        getControls();
    }
    public void getControls(){
        cmnd= (TextView) findViewById(R.id.cmnd);
        taikhoan= (TextView) findViewById(R.id.taikhoan);
        hoten= (TextView) findViewById(R.id.hoten);
        ngaysinh= (TextView) findViewById(R.id.ngaysinh);
        sdt= (TextView) findViewById(R.id.sdt);
        diachi= (TextView) findViewById(R.id.diachi);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        dangxuat= (Button) findViewById(R.id.dangxuat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cmnd.setText(user.getCmnd());
        taikhoan.setText(user.getTaikhoan());
        hoten.setText(user.getHoten());
        ngaysinh.setText(user.getNgaysinh());
        sdt.setText(user.getSdt());
        diachi.setText(user.getDiachi());
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinActivity.this);
                builder.setMessage("Bạn có muốn đăng xuất không");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                User.deleteUser(ThongTinActivity.this);
                                Intent intent=new Intent(ThongTinActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
}
