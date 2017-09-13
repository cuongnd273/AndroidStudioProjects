package free.app.com.moviebooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.activity.ThongTinPhimActivity;
import free.app.com.moviebooking.adapter.AdapterFilm;
import free.app.com.moviebooking.api.MovieAPI;
import free.app.com.moviebooking.constant.Constants;
import free.app.com.moviebooking.model.Phim;
import free.app.com.moviebooking.model.StartSnapHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CuongNguyen on 08/17/17.
 */

public class FragmentPhimSapChieu extends Fragment implements Callback<List<Phim>>{
    RecyclerView recyclerView;
    ArrayList<Phim> list;
    AdapterFilm adapterFilm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<>();
        adapterFilm=new AdapterFilm(list, getContext(), new AdapterFilm.ClickListener() {
            @Override
            public void imageClick(View v, int position) {
                Intent intent=new Intent(getActivity(), ThongTinPhimActivity.class);
                intent.putExtra("maphim",list.get(position).getMaphim());
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_phim_sap_chieu,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.phim_sap_chieu);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getData();
        SnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(recyclerView);
        return view;
    }
    public void getData(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieAPI gerritAPI = retrofit.create(MovieAPI.class);

        Call<List<Phim>> call = gerritAPI.phimsapchieu();
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
        for(Phim item : response.body()){
            list.add(item);
        }
        adapterFilm.notifyDataSetChanged();
        recyclerView.setAdapter(adapterFilm);
    }

    @Override
    public void onFailure(Call<List<Phim>> call, Throwable t) {

    }
}
