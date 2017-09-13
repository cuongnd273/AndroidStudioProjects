package vn.edu.itplus_academy.thitracnghiem.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import vn.edu.itplus_academy.thitracnghiem.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TraCuuDiemThiFragment extends Fragment {
    private View view;
    public TraCuuDiemThiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tra_cuu_diem_thi, container, false);
        WebView webView = (WebView)view.findViewById(R.id.webView);

        webView.setInitialScale(1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);

        webView.loadUrl("http://itplus-academy.edu.vn/tra-cuu.html");

        return view;
    }
}
