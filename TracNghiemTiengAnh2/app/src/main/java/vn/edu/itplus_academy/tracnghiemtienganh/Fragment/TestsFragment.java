package vn.edu.itplus_academy.tracnghiemtienganh.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Activitys.TestActivity;
import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.TestAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Model.Test;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.interfaces.RecyclerViewOnClickListener;


public class TestsFragment extends Fragment implements RecyclerViewOnClickListener{


    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    private List<Test> testList;
    private View view ;

    public TestsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycview, container, false);

        list();

        ListRecyclerView();

        return view;
    }

    /**
     *   LIST
     */
    private void list(){
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        int question_count = db.getCauhoi_KT_Count();
        db.close();


        int baithi = question_count/50;

        testList = new ArrayList<>();
        for (int i = 0; i < baithi ; i++) {
            testList.add(new Test(i+1,getContext().getResources().getString(R.string.exam).toString() ,60,50));
        }
    }

    /**
     *  LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
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
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListeneer(getActivity(), recyclerView, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        testAdapter = new TestAdapter(getActivity(),testList);
        recyclerView.setAdapter(testAdapter);
    }
    /**
     * ClICK
     */
    @Override
    public void onClickListener(View view, int position) {
        Intent intent = new Intent(getActivity(), TestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("test_index",position);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.animation_in, R.anim.animation_out);
    }
    /**
     *  LONGCLICK
     */
    @Override
    public void onLongClickListener(View view, int position) {

    }
    private static class RecyclerViewTouchListeneer implements RecyclerView.OnItemTouchListener{
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

        public RecyclerViewTouchListeneer(Context context,final RecyclerView mRecyclerView, final RecyclerViewOnClickListener mRecyclerViewOnClickListener) {
            this.mContext = context;
            this.mRecyclerViewOnClickListener = mRecyclerViewOnClickListener;

            mGestureDetector = new GestureDetector(mContext,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View view = mRecyclerView.findChildViewUnder(e.getX(),e.getY());

                    if (view != null && mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onLongClickListener(view, mRecyclerView.getChildPosition(view));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View view = mRecyclerView.findChildViewUnder(e.getX(),e.getY());

                    if (view != null && mRecyclerViewOnClickListener != null){
                        mRecyclerViewOnClickListener.onClickListener(view, mRecyclerView.getChildPosition(view));
                    }
                    return (true);
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;}
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }
}
