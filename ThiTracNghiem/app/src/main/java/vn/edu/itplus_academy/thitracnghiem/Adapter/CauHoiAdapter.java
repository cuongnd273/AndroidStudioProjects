package vn.edu.itplus_academy.thitracnghiem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.Model.CauHoi;
import vn.edu.itplus_academy.thitracnghiem.R;

public class CauHoiAdapter extends BaseAdapter {

    private Context mContext;
    private List<CauHoi> mlist;
    private LayoutInflater mLayoutInflater;

    public CauHoiAdapter(Context context, List<CauHoi> list) {
        this.mContext = context;
        this.mlist = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_cauhoi,parent,false);
            holder = new ViewHolder();
            holder.txtCauHoi = (TextView) convertView.findViewById(R.id.txt_cauHoi);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        CauHoi cauHoi = mlist.get(position);

        holder.txtCauHoi.setText(cauHoi.getTxtID());
        return convertView;
    }


    public class ViewHolder{
        TextView txtCauHoi;
    }


}
