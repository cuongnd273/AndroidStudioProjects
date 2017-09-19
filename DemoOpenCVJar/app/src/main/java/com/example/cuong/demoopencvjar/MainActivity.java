package com.example.cuong.demoopencvjar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener{
    private static final String TAG="OPENCV";
    JavaCameraView cameraView;
    static {
        if(OpenCVLoader.initDebug())
            Log.i(TAG, "Success");
        else
            Log.i(TAG, "Error");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView= (JavaCameraView) findViewById(R.id.camera);
        cameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraView!=null)
            cameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int i, int i1) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(Mat mat) {
        return null;
    }
}
