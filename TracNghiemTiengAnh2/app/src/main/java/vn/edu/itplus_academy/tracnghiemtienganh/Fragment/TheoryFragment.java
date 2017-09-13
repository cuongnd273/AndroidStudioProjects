package vn.edu.itplus_academy.tracnghiemtienganh.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import vn.edu.itplus_academy.tracnghiemtienganh.R;

public class TheoryFragment extends Fragment {

    private WebView webView;
    private View view;
    private String temp;

    public TheoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theory, container, false);

        getData();

        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("file://"+temp);
        return view;

    }

    /**
     * GETDATA
     */
    public void getData(){
        Bundle bundle = getArguments();
        temp = bundle.getString("grammar");
    }

}
