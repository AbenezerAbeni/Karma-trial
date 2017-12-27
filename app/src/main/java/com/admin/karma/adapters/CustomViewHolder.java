package com.admin.karma.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.admin.karma.R;
import com.admin.karma.databases.TableItems;

/**
 * Created by Admin on 17/12/2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView textView1;

    public CustomViewHolder(View itemView) {
        super(itemView);
        textView1 = (TextView) itemView.findViewById(R.id.tv_card_main_3_title);
    }

    public void setData(Cursor c) {
        textView1.setText(c.getString(c.getColumnIndex(TableItems.KEY_TITLE)));
    }
}