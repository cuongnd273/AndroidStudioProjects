package cuongnguyen.app.com.shoponline.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cuongnguyen.app.com.shoponline.Model.ObjectClass.GroupProduct;
import cuongnguyen.app.com.shoponline.R;

/**
 * Created by CuongNguyen on 05/31/17.
 */

public class ExpandableMenuAdapter extends BaseExpandableListAdapter {
    Context context;
    List<GroupProduct> listGroup;
    List<GroupProduct> data;
    int levelMenu;
    public ExpandableMenuAdapter(Context context, List<GroupProduct> data, List<GroupProduct> listGroup, int levelMenu) {
        this.context = context;
        this.data = data;
        this.listGroup=new ArrayList<>();
        this.levelMenu=levelMenu;
        if(this.levelMenu==1) {
            for(GroupProduct itemFirst:this.data){
                if(itemFirst.getIdGroup()==0){
                    this.listGroup.add(itemFirst);
                }
            }
            for(GroupProduct itemGroup:this.listGroup){
                itemGroup.setListChild(new ArrayList<GroupProduct>());
                for(GroupProduct item:this.data){
                    if(itemGroup.getId()!=item.getId() && itemGroup.getId()==item.getIdGroup() && item.getIdGroup()!=0){
                        itemGroup.addChild(item);
                    }
                }
            }
        }else{
            this.listGroup=listGroup;
            for(GroupProduct itemGroup:this.listGroup){
                itemGroup.setListChild(new ArrayList<GroupProduct>());
                for(GroupProduct item:this.data){
                    if(itemGroup.getId()!=item.getId() && itemGroup.getId()==item.getIdGroup() && item.getIdGroup()!=0){
                        itemGroup.addChild(item);
                    }
                }
            }
        }
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listGroup.get(groupPosition).getListChild().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listGroup.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return listGroup.get(groupPosition).getListChild().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewMenuFirst=inflater.inflate(R.layout.item_menu,parent,false);
        TextView name= (TextView) viewMenuFirst.findViewById(R.id.name);
        name.setPadding(15*this.levelMenu,10,10,10);
        ImageView image= (ImageView) viewMenuFirst.findViewById(R.id.status);
        name.setText(listGroup.get(groupPosition).getName());
        if(listGroup.get(groupPosition).getListChild().size()>0){
            if(isExpanded)image.setImageResource(R.drawable.ic_remove_black_24dp);
            else image.setImageResource(R.drawable.ic_add_black_24dp);
        }
        return viewMenuFirst;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SecondExpandable secondExpandable=new SecondExpandable(context);
        secondExpandable.setGroupIndicator(null);
        ExpandableMenuAdapter adapter=new ExpandableMenuAdapter(context,this.data,this.listGroup.get(groupPosition).getListChild(),this.levelMenu+1);
        secondExpandable.setAdapter(adapter);
        return secondExpandable;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class SecondExpandable extends ExpandableListView{
        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            int height = Resources.getSystem().getDisplayMetrics().heightPixels;
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
