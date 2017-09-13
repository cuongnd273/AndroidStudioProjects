package com.example.app.barcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {
    TextView txtView;
    ImageView myImageView ;
    int REQUEST_CHUPANH=1;
    int REQUEST_CHONANH=2;
    Bitmap myBitmap=null;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btChonFile = (Button) findViewById(R.id.btChonFile);
        Button btChupAnh=(Button)findViewById(R.id.btChupAnh);
        Button btMaVach=(Button)findViewById(R.id.btDocMaVach);
        myImageView = (ImageView) findViewById(R.id.imgview);
        txtView=(TextView)findViewById(R.id.txtContent);
        btChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_CHUPANH);
                }

            }
        });
        btChonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CHONANH);
            }
        });
        btMaVach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBitmap!=null)
                {
                    myImageView.setImageBitmap(myBitmap);
                    BarcodeDetector detector =
                            new BarcodeDetector.Builder(getApplicationContext())
                                                .build();
                    if(!detector.isOperational()){
                        txtView.setText("Could not set up the detector!");
                        return;
                    }
                    Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                    SparseArray<Barcode> barcodes = detector.detect(frame);
                    Barcode thisCode = barcodes.valueAt(0);
                    txtView.setText(thisCode.rawValue);
                }else{
                    Toast.makeText(MainActivity.this,"Hãy chọn ảnh từ bộ nhớ hoặc chụp ảnh",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if(reqCode == REQUEST_CHONANH && data != null && resCode==RESULT_OK){
            String realPath = GetFilePathFromDevice.getPath(this, data.getData());
            myBitmap = BitmapFactory.decodeFile(realPath);
            myImageView.setImageBitmap(myBitmap);
        }else if(reqCode==REQUEST_CHUPANH && data!=null && resCode==RESULT_OK){
            Bundle extras = data.getExtras();
            myBitmap = (Bitmap) extras.get("data");
            myImageView.setImageBitmap(myBitmap);
        }
    }
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Nhấn lần nữa để thoát.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
}
