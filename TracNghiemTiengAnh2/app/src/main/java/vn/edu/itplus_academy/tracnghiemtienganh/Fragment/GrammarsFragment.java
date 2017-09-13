package vn.edu.itplus_academy.tracnghiemtienganh.Fragment;


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

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.GrammarAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.models.ChuDe;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrammarsFragment extends Fragment {


    private RecyclerView recyclerView;
    private GrammarAdapter grammarAdapter;
    private List<ChuDe> lst_chude;
    private View view ;

    public GrammarsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycview, container, false);

        list();

        gridRecyclerView();

        return view;
    }
    /**
     * LIST
     */
    private void list(){
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        lst_chude = new ArrayList<ChuDe>();
        lst_chude = db.getAll_ChuDe();
        Log.d("Test", "list: "+lst_chude.size());
        db.close();
    }

    /**
     *  GRID RECYCLERVIEW
     */
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
        grammarAdapter = new GrammarAdapter(getActivity(),lst_chude);
        recyclerView.setAdapter(grammarAdapter);
    }
}
