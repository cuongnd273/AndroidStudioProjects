package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.itplus_academy.tracnghiemtienganh.R;

public class GuiPhanHoiActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private EditText edtMessage;
    private Button btnGui;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_phan_hoi);
        edtMessage = (EditText) findViewById(R.id.edt_message);
        btnGui = (Button) findViewById(R.id.btn_gui);

        btnGui.setOnClickListener(this);
        toolBar();
    }
    @Override
    public void onClick(View v) {
        message = edtMessage.getText().toString();
        String TO = "bannt@itplus-academy.edu.vn";
//        String TO = "vietung.it@gmail.com";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + TO));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản Hồi Trắc Nghiệm Tiếng Anh");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Không có ứng dụng email được cài đặt.", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.phanhoi));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }

        return super.onOptionsItemSelected(item);
    }


}
