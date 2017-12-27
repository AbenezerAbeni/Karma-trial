package com.admin.karma.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.karma.R;
import com.admin.karma.SavedItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 03/12/2017.
 */

public class MyListCursorAdapter extends CursorRecyclerViewAdapter {
//CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder>

    Context mContext;

    public MyListCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
    }

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_card_main_3_title)        public TextView mTextView;
//        public ImageView mImageView;
//
//
//        public ViewHolder(View view) {
//            super(view);
//            mTextView = view.findViewById(R.id.tv_card_main_3_title);
//            mImageView = view.findViewById(R.id.img_main_card_3);
//            ButterKnife.bind(this, view);
//
//        }
//
//        public void bindData(Cursor cursor) {
//            final String name = cursor.getString(cursor.getColumnIndex("title"));
//            this.mTextView.setText(name);
//        }
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_third, parent, false);
//
//
//        ViewHolder vh = new ViewHolder(itemView);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
//     //   SavedItem myListItem = SavedItem.fromCursor(cursor);
//     //   viewHolder.mTextView.setText(myListItem.getArticle_Title());
//
//        viewHolder.bindData(cursor);
//    }
//}

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView mTextView;
//        public ViewHolder(View view) {
//            super(view);
//            mTextView = view.findViewById(R.id.tv_card_main_3_title);
//        }
//
//        public void setData(Cursor c) {
//            mTextView.setText(c.getString(c.getColumnIndex("title")));
//        }
//    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        CustomViewHolder holder = (CustomViewHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View v = LayoutInflater.from(mContext).inflate(R.layout.card_third, parent, false);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_third, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.card_third, parent, false);
//        ViewHolder vh = new ViewHolder(itemView);
//        return vh;
//    }


}