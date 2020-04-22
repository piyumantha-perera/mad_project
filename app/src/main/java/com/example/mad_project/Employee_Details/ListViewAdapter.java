package com.example.mad_project.Employee_Details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mad_project.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Salary> salaryList;

    public ListViewAdapter(Context mContext, List<Salary> salaryList) {
        this.mContext = mContext;
        this.salaryList = salaryList;
    }

    @Override
    public int getCount() {
        return salaryList.size();
    }

    @Override
    public Object getItem(int position) {
        return salaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.listviewedit, null);

        TextView basic = (TextView)v.findViewById(R.id.basSal);
        TextView travel = (TextView)v.findViewById(R.id.travAll);
        TextView otHours = (TextView)v.findViewById(R.id.ot);
        TextView salaryAdv = (TextView)v.findViewById(R.id.salAdv);
        TextView netSalary = (TextView)v.findViewById(R.id.netSal);
        TextView date = (TextView)v.findViewById(R.id.date);

        //Set text for TextView

        basic.setText("Basic Salary: "+salaryList.get(position).getBasSal());
        travel.setText("Travel Allowance: "+salaryList.get(position).getTravAll());
        otHours.setText("OT Hours: "+salaryList.get(position).getOt());
        salaryAdv.setText("Salary Advance: "+salaryList.get(position).getSalAdva());
        netSalary.setText("Net Salary: "+salaryList.get(position).getNetSal());
        date.setText("Date: "+salaryList.get(position).getDate());

        //Save product name to tag
        v.setTag(salaryList.get(position).getName());

        return v;
    }
}
