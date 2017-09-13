package vn.edu.itplus_academy.thitracnghiem.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Adapter.MonThiAdapter;
import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.Service.TimeIntentService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThamGiaThiFragment extends Fragment {

    private RecyclerView recyclerView;
    private MonThiAdapter adapter;
    private List<MonThi> list;
    private View view ;
    private DBAdapter db;

    public ThamGiaThiFragment() {
    }

    private Intent serviceIntent;

    private ResponseReceiver receiver = new ResponseReceiver();




    // Broadcast component
    // Class mô phỏng một bộ thu sóng
    // (Thu tín hiệu gửi từ Service).
    public class ResponseReceiver extends BroadcastReceiver {

        // on broadcast received
        @Override
        public void onReceive(Context context, Intent intent) {

            // Kiểm tra nhiệm vụ của Intent gửi đến.
            if(intent.getAction().equals(TimeIntentService.ACTION_1)) {
                int value = intent.getIntExtra("time", -1);
                if(value == -1){
                    list();
                    adapter = new MonThiAdapter(getActivity(),list);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tham_gia_thi, container, false);
        db = new DBAdapter(getActivity());
        list();

        gridRecyclerView();

        return view;

    }

    private void list(){
        list = new ArrayList<>();
        list = db.getAll_MonThi();
    }

    private void gridRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MonThiAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Hủy đăng ký bộ thu sóng với Activity.
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Đăng ký bộ thu sóng với Activity.
        getActivity().registerReceiver(receiver, new IntentFilter(TimeIntentService.ACTION_1));
        list();
        adapter = new MonThiAdapter(getActivity(),list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        Log.d("ThamGia_","onResume");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d("ThamGia_","onStart");
    }
}
