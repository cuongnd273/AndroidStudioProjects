package vn.edu.itplus_academy.tracnghiemtienganh.AsyncTask;

import android.app.Activity;
import android.content.Intent;

import vn.edu.itplus_academy.tracnghiemtienganh.R;

/**
 * Created by tuananh on 03/04/2016.
 */
public class AsyncTaskSrceenSaver extends android.os.AsyncTask<Integer,Integer,Integer> {

    private Activity mContextOut;
    private Class mContextIn;

    public AsyncTaskSrceenSaver(Activity mContextOut, Class mContextIn) {
        this.mContextOut = mContextOut;
        this.mContextIn = mContextIn;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        try {
            Thread.sleep(params[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Intent intent = new Intent(mContextOut,mContextIn);
        mContextOut.startActivity(intent);
        mContextOut.overridePendingTransition(R.anim.animation_in,R.anim.animation_out);
        mContextOut.finish();
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
