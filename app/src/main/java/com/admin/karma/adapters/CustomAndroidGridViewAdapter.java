package com.admin.karma.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.admin.karma.R;

/**
 * Created by Admin on 26/10/2017.
 */
public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] string;
    private final int[] Imageid;

    public CustomAndroidGridViewAdapter(Context c,String[] string,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.string = string;
    }

    @Override
    public int getCount() {
        return string.length;
    }

    @Override
    public Object getItem(int p) {
        return null;
    }

    @Override
    public long getItemId(int p) {
        return 0;
    }


    @Override
    public View getView(int p, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridview_custom_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);
            textView.setText(string[p]);
            imageView.setImageResource(Imageid[p]);
        } else {
            grid = (View) convertView;
        }


//               onItemClickListener.onItemClick(feedItem);

//                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                b.compress(Bitmap.CompressFormat.PNG, 50, bs);

//                Intent i = new Intent(this, SecondActivity.class)
//                i.putExtra("Image", bs.toByteArray());
//                i.putExtra("Text", yourTextView.gettext().toString());
//                startActivity(i);




        return grid;
    }
}