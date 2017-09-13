package com.example.app.goofood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.goofood.API.MyServices;
import com.example.app.goofood.Adapter.AdapterComment;
import com.example.app.goofood.GetData.GetListComment;
import com.example.app.goofood.GetData.GetPlace;
import com.example.app.goofood.GetData.Result;
import com.example.app.goofood.Model.Comment;
import com.example.app.goofood.Model.ExpandableHeightListView;
import com.example.app.goofood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaceActivity extends AppCompatActivity {

    RestAdapter restAdapter;
    MyServices git;
    TextView vitri,noibat,title;
    ImageView image;
    ProgressDialog dialog;
    EditText comment;
    Button btComment,loadmore;
    SharedPreferences pre;
    ExpandableHeightListView listComment;
    AdapterComment adapterComment;
    ArrayList<Comment> list;
    int idAcc;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        GetControl();
        pre=getSharedPreferences("login", MODE_PRIVATE);
        idAcc=pre.getInt("idAcc",0);
        final Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(MainActivity.URL).build();
        git = restAdapter.create(MyServices.class);
        git.InfoPlace(id, new Callback<GetPlace>() {
            @Override
            public void success(GetPlace getPlace, Response response) {
                title.setText(getPlace.getPlace().getTendiadiem());
                vitri.setText(getPlace.getPlace().getVitridialy());
                noibat.setText(getPlace.getPlace().getDacdiemnoibat());
                Picasso.with(PlaceActivity.this).load(getPlace.getPlace().getAnh()).into(image);
                dialog.dismiss();
                git.ListComment(id, page, new Callback<GetListComment>() {
                    @Override
                    public void success(GetListComment getListComment, Response response) {
                        if(getListComment.getListComment().size()>0)
                        {
                            list= (ArrayList<Comment>) getListComment.getListComment();
                            adapterComment=new AdapterComment(PlaceActivity.this,R.layout.item_comment,list);
                            listComment.setAdapter(adapterComment);
                            listComment.setExpanded(true);
                            if(Integer.parseInt(getListComment.getTotal())<=list.size())
                            {
                                loadmore.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {


            }
        });
        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idAcc==0)
                {
                    Toast.makeText(PlaceActivity.this,"Bạn cần đăng nhập để bình luận",Toast.LENGTH_SHORT).show();
                }else{
                    if(comment.getText().length()==0)
                    {
                        Toast.makeText(PlaceActivity.this,"Hãy nhập bình luận",Toast.LENGTH_SHORT).show();
                    }else{
                        dialog=new ProgressDialog(PlaceActivity.this);
                        dialog.setMessage("Loading...");
                        dialog.show();
                        git.Comment(String.valueOf(idAcc), id, comment.getText().toString(), new Callback<Result>() {
                            @Override
                            public void success(Result result, Response response) {
                                if(result.getSuccess()==1)
                                {
                                    Intent intent = getIntent();
                                    intent.putExtra("id",id);
                                    finish();
                                    startActivity(intent);
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                dialog.dismiss();
                            }
                        });
                    }

                }
            }
        });
        loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=page+1;
                git.ListComment(id, page, new Callback<GetListComment>() {
                    @Override
                    public void success(GetListComment getListComment, Response response) {
                        for(Comment item : getListComment.getListComment())
                        {
                            list.add(item);
                            adapterComment.notifyDataSetChanged();
                        }
                        if(Integer.parseInt(getListComment.getTotal())<=list.size())
                        {
                            loadmore.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
    }
    public void GetControl()
    {
        vitri=(TextView)findViewById(R.id.locationPlace);
        noibat=(TextView)findViewById(R.id.infoPlace);
        title=(TextView)findViewById(R.id.titlePlace);
        image=(ImageView)findViewById(R.id.imagePlace);
        listComment=(ExpandableHeightListView)findViewById(R.id.listComment);
        comment=(EditText)findViewById(R.id.comment);
        btComment=(Button)findViewById(R.id.btComment);
        loadmore=(Button)findViewById(R.id.btLoadMore);
        list=new ArrayList<Comment>();
    }
}
