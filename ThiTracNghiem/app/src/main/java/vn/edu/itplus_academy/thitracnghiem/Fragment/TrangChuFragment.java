package vn.edu.itplus_academy.thitracnghiem.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Activity.LogInActivity;
import vn.edu.itplus_academy.thitracnghiem.Activity.MainActivity;
import vn.edu.itplus_academy.thitracnghiem.Activity.SignUpActivity;
import vn.edu.itplus_academy.thitracnghiem.Adapter.ChuongTrinhHocAdapter;
import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Model.ChildItem;
import vn.edu.itplus_academy.thitracnghiem.Model.GroupItem;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.Service.TimeIntentService;
import vn.edu.itplus_academy.thitracnghiem.widgets.AnimatedExpandableListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuFragment extends Fragment implements View.OnClickListener{
    private AnimatedExpandableListView lvLapTrinhUngDung,lvThietKeDoHoa;
    private ChuongTrinhHocAdapter adapter;
    private View view;
    private Button btnDangKy,btnThamGiaThi;
    private ViewPager viewPager;
    private int i=0;
    private Handler handler;
    private boolean stopThread = false;
    private Thread thread;
    private DBAdapter db;
    private ArrayList<ThiSinh> thiSinhs;
    public TrangChuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        // Inflate the layout for this fragment
        button();
        listViewLTUD();
        listViewTKDH();
        viewPager();
        return view;
    }
    public void button(){
        btnDangKy = (Button) view.findViewById(R.id.btn_dangky);
        btnThamGiaThi = (Button) view.findViewById(R.id.btn_thangiathi);
        btnDangKy.setOnClickListener(this);
        btnThamGiaThi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dangky:
                thiSinhs=new ArrayList<>();
                db=new DBAdapter(getActivity());
                thiSinhs= (ArrayList<ThiSinh>) db.getAll_ThiSinh();
                if(thiSinhs.size() >0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setTitle("Thi Trắc Nghiệm ");
                        alert.setMessage("Bạn phải đăng xuất thì mới có thể đăng ký?");
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent serviceIntent = new Intent(getActivity(), TimeIntentService.class);
                                getActivity().stopService(serviceIntent);
                                db.deleteAll_ThiSinh();
                                db.deleteAll_MonThi();
                                db.deleteAll_CauHoi();
                                Intent intentSignUp = new Intent(getActivity(), SignUpActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("start", MainActivity.MAINACTIVITY);
                                intentSignUp.putExtras(bundle);
                                startActivity(intentSignUp);
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alert.show();
                    }
                else {
                    Intent intentSignUp = new Intent(getActivity(), SignUpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("start", MainActivity.MAINACTIVITY);
                    intentSignUp.putExtras(bundle);
                    startActivity(intentSignUp);
                }
                break;
            case R.id.btn_thangiathi:
                thiSinhs=new ArrayList<>();
                db=new DBAdapter(getActivity());
                thiSinhs= (ArrayList<ThiSinh>) db.getAll_ThiSinh();
                if(thiSinhs.size() >0)
                {
                Fragment frag = new ThamGiaThiFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_main, frag, "mainFrag");
                ft.commit();
                getActivity().setTitle(R.string._thamgiathi);
                }else{
                    Intent intentLogin = new Intent(getActivity(),LogInActivity.class);
                    startActivity(intentLogin);
                }
                break;
        }
    }
    public void listViewLTUD(){
        List<GroupItem> items = new ArrayList<>();
        String[] groupLTUD = getResources().getStringArray(R.array.group_LTUD);
        String[] arrayLTWEB = getResources().getStringArray(R.array.array_LTWEB);
        String[] arrayNETFRAMEWORD = getResources().getStringArray(R.array.array_NETFRAMEWORD);
        String[] arrayJAVAEE = getResources().getStringArray(R.array.array_JAVAEE);
        // Populate our list with groups and it's children
        for(int i = 0; i < groupLTUD.length; i++) {
            GroupItem item = new GroupItem();

            item.title = groupLTUD[i];
            if(i == 0){
                for(int j = 0; j < arrayLTWEB.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = arrayLTWEB[j];
                    item.items.add(child);
                }
            }else if(i == 1){
                for(int j = 0; j < arrayNETFRAMEWORD.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = arrayNETFRAMEWORD[j];
                    item.items.add(child);
                }
            }else if(i == 2){
                for(int j = 0; j < arrayJAVAEE.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = arrayJAVAEE[j];
                    item.items.add(child);
                }
            }
            items.add(item);
        }

        adapter = new ChuongTrinhHocAdapter(getActivity());
        adapter.setData(items);

        lvLapTrinhUngDung = (AnimatedExpandableListView) view.findViewById(R.id.lv_laptrinhungdung);
        lvLapTrinhUngDung.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        lvLapTrinhUngDung.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (lvLapTrinhUngDung.isGroupExpanded(groupPosition)) {
                    lvLapTrinhUngDung.collapseGroupWithAnimation(groupPosition);
                } else {
                    lvLapTrinhUngDung.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

    }
    public void listViewTKDH(){
        List<GroupItem> items = new ArrayList<>();
        String[] groupTKDH = getResources().getStringArray(R.array.group_TKDH);
        String[] arrayTKDHCN = getResources().getStringArray(R.array.array_TKDHCN);
        String[] array3D = getResources().getStringArray(R.array.array_3D);
        String[] arrayNT = getResources().getStringArray(R.array.array_NT);
        // Populate our list with groups and it's children
        for(int i = 0; i < groupTKDH.length; i++) {
            GroupItem item = new GroupItem();

            item.title = groupTKDH[i];
            if(i == 0){
                for(int j = 0; j < arrayTKDHCN.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = arrayTKDHCN[j];
                    item.items.add(child);
                }
            }else if(i == 1){
                for(int j = 0; j < array3D.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = array3D[j];
                    item.items.add(child);
                }
            }else if(i == 2){
                for(int j = 0; j < arrayNT.length; j++) {
                    ChildItem child = new ChildItem();
                    child.title = arrayNT[j];
                    item.items.add(child);
                }
            }
            items.add(item);
        }

        adapter = new ChuongTrinhHocAdapter(getActivity());
        adapter.setData(items);

        lvThietKeDoHoa= (AnimatedExpandableListView) view.findViewById(R.id.lv_thietkedohoa);
        lvThietKeDoHoa.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        lvThietKeDoHoa.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (lvThietKeDoHoa.isGroupExpanded(groupPosition)) {
                    lvThietKeDoHoa.collapseGroupWithAnimation(groupPosition);
                } else {
                    lvThietKeDoHoa.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

    }

    private void viewPager(){
        viewPager = (ViewPager) view.findViewById(R.id.vp_images);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what ==0) {
                    if(i<100) {
                        i++;
                        if((i/20)<5){
                            viewPager.setCurrentItem(i/20);
                        }
                    }else {
                        i=0;
                        viewPager.setCurrentItem(i);
                    }

                }
            }
        };
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stopThread){
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopThread = true;
        Log.d("trangchu","onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.interrupt();
        Log.d("trangchu","onDestroy");
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        int[] list = {R.mipmap.imagei,R.mipmap.imageii,R.mipmap.imageiii,R.mipmap.imageiv,R.mipmap.imagev};
        public MyViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.image_item, container, false);
            ViewHolder holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.img);
            view.setTag(holder);
            holder.img.setBackgroundResource(list[position]);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        public class ViewHolder{
            ImageView img;
        }

    }

}
