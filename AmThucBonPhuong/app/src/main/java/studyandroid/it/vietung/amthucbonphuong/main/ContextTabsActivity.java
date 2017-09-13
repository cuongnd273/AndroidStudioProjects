package studyandroid.it.vietung.amthucbonphuong.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import studyandroid.it.vietung.amthucbonphuong.R;
import studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost.FoodCategoryActivity;
import studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost.MonanSQLActivity;
import studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost.MonanUathichActivity;
import studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost.KienThucAmthucActivity;
import studyandroid.it.vietung.amthucbonphuong.components.inside_tabhost.VideoAmthucActivity;

public class ContextTabsActivity extends TabActivity {

    private TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_tabs);

        tabhost = getTabHost();

        TabHost.TabSpec tabSpec1 = tabhost.newTabSpec("First Tab");
        tabSpec1.setIndicator("Món ăn");
        tabSpec1.setContent(new Intent(ContextTabsActivity.this, FoodCategoryActivity.class));
        TabHost.TabSpec tabSpec2 = tabhost.newTabSpec("Second Tab");
        tabSpec2.setIndicator("Ưa thích");
        tabSpec2.setContent(new Intent(ContextTabsActivity.this, MonanUathichActivity.class));
        TabHost.TabSpec tabSpec3 = tabhost.newTabSpec("Third Tab");
        tabSpec3.setIndicator("My foods");
        tabSpec3.setContent(new Intent(ContextTabsActivity.this, MonanSQLActivity.class));
        TabHost.TabSpec tabSpec4 = tabhost.newTabSpec("Four Tab");
        tabSpec4.setIndicator("Kiến thức Ẩm thực");
        tabSpec4.setContent(new Intent(ContextTabsActivity.this, KienThucAmthucActivity.class));
        TabHost.TabSpec tabSpec5 = tabhost.newTabSpec("Five Tab");
        tabSpec5.setIndicator("Video Ẩm thực");
        tabSpec5.setContent(new Intent(ContextTabsActivity.this, VideoAmthucActivity.class));

        tabhost.addTab(tabSpec1);
        tabhost.addTab(tabSpec2);
        tabhost.addTab(tabSpec3);
        tabhost.addTab(tabSpec4);
        tabhost.addTab(tabSpec5);
    }


}
