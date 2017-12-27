package com.admin.karma;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.karma.activity.ShareViewActivity;
import com.admin.karma.adapters.OnItemClickListener;
import com.admin.karma.adapters.onMoveAndSwipedListener;
import com.admin.karma.databases.DBHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin 10/25/16.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private List<SavedItem> savess;
    private Context mContext;
   // private AdapterView.OnItemClickListener onItemClickListener;
    private int color = 0;
    private LayoutInflater inflater;
    private boolean isBookmarkClicked, isFavoriteClicked;
    private ImageView img_main_card2_share, img_main_card2_bookmark, img_main_card2_favorite;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;
    private CardView card_main_1_2 ;
    SavedItem saves;
    DBHelper db;


    // private final View.OnClickListener mOnClickListener = new OnItemClickListener();



    public MyRecyclerViewAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;

    }

    private OnItemClickListener onItemClickListener;

//    public MyRecyclerViewAdapter(List<SavedItem> cn) {
//        this.savess = cn;
//
//    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      //  View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_main_2, null);
       // view.setOnClickListener(OnClickListener);

        db = new DBHelper(mContext);
        img_main_card2_share = (ImageView) view.findViewById(R.id.img_main_card2_share);
        img_main_card2_bookmark = (ImageView) view.findViewById(R.id.img_main_card2_bookmark);
        img_main_card2_favorite = (ImageView) view.findViewById(R.id.img_main_card2_favorite);

     //   view.setonItem(onItemClickListener);

        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        img_main_card2_share.startAnimation(alphaAnimation);
        img_main_card2_bookmark.startAnimation(alphaAnimation);
        img_main_card2_favorite.startAnimation(alphaAnimation);

        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);



        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {
        final FeedItem feedItem = feedItemList.get(i);
        final CustomViewHolder recyclerViewHolder = (CustomViewHolder) customViewHolder;
        String titles = feedItem.getTitle();
        CharSequence contents  = feedItem.getContent();
        int id;
        Image af;



        //Download image using picasso library
        if (!TextUtils.isEmpty(feedItem.getThumbnail())) {
            Picasso.with(mContext).load(feedItem.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));

//        customViewHolder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ShareViewActivity.class);
//                intent.putExtra("color", color);
//                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
//                        ((Activity) mContext, customViewHolder.rela_round, "shareView").toBundle());
//            }
//        });
        
      //  customViewHolder.mView.setOnClickListener((View.OnClickListener)(view));

       // final BasemapItem item = items.get(position);

       // customViewHolder.img_main_card2_favorite.setImageBitmap(feedItem.getThumbnail(i).getImage());


//
//        customViewHolder.img_main_card2_favorite.setOnClickListener (new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Do something!
//                if (!isFavoriteClicked) {
//                  //  img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
//                 customViewHolder.img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
//                 customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                 customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//
//                    //   img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                   // img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                    isFavoriteClicked = true;
//                  //  Toast.makeText(mContext, "FAV Click" + i , Toast.LENGTH_SHORT).show();
//
//
//                } else {
//                    customViewHolder.img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//                    customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                    customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
//                    isFavoriteClicked = false;
//                 //   Toast.makeText(mContext, "OOP Click" + i , Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               onItemClickListener.onItemClick(feedItem);

//                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                b.compress(Bitmap.CompressFormat.PNG, 50, bs);

//                Intent i = new Intent(this, SecondActivity.class)
//                i.putExtra("Image", bs.toByteArray());
//                i.putExtra("Text", yourTextView.gettext().toString());
//                startActivity(i);

            switch (v.getId()) {
                case R.id.img_main_card2_bookmark :
                    if (!isBookmarkClicked){
                       // alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
                       // alphaAnimationShowIcon.setDuration(500);
                        customViewHolder.img_main_card2_bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
                        customViewHolder.img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                        customViewHolder.img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                        isBookmarkClicked = true;
                        String tit = feedItem.getTitle();
                     //   CharSequence con = feedItem.getContent();
                       // byte [] ime = feedItem.getThumbnail();
                        int id = feedItem.getids();
                        //-------------------------------
//                        saves.setArticle_Title(tit);

                        // Inserting Contacts
                     //   Log.d("Insert: ", "Inserting ..");
                        //db = new DBHelper(id,tit);
                     //   db.addSAVES(new SavedItem(id ,tit   ));
                        db.addSaves(new SavedItem(id , tit));
                    //    Toast.makeText(mContext, tit + i , Toast.LENGTH_SHORT).show();


                        //  Toast.makeText(mContext, "Hellow ", Toast.LENGTH_LONG).show();
                    } else {
                        customViewHolder.img_main_card2_bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                        customViewHolder.img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                        customViewHolder.img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon);
                        isBookmarkClicked = false;
                        //Toast.makeText(mContext, "book Click" + i , Toast.LENGTH_SHORT).show();

                    }
                    break;
                case R.id.img_main_card2_favorite:
                    if (!isFavoriteClicked) {
                        customViewHolder.img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                        customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                        customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                        isFavoriteClicked = true;
                      //  Toast.makeText(mContext, "FAV Click" + i , Toast.LENGTH_SHORT).show();

                    } else {
                        customViewHolder.img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                        customViewHolder.img_main_card2_favorite.startAnimation(alphaAnimationShowIcon);
                        isFavoriteClicked = false;
                        //Toast.makeText(mContext, "FAV Click" + i , Toast.LENGTH_SHORT).show();

                    }
                    break;
                case R.id.img_main_card_2 :
                {

                    Intent intent = new Intent(mContext, ShareViewActivity.class);
                    intent.putExtra("color", color);
                    intent.putExtra("Text",  Html.fromHtml(String.valueOf((CharSequence) feedItem.getContent())));
                    // Html.fromHtml(jObject.getString("content"))
                    // Spanned contents= Html.fromHtml(feedItem.getTitle());

                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                            ((Activity) mContext, recyclerViewHolder.rela_round, feedItem.getTitle()).toBundle());
                }
                break;
                case R.id.tv_card_main_2_title:
                    {
                //    onItemClickListener.onItemClick(feedItem);

                    Intent intent = new Intent(mContext, ShareViewActivity.class);
                    intent.putExtra("color", color);
                    intent.putExtra("Text",  Html.fromHtml(String.valueOf((CharSequence) feedItem.getContent())));
                    // Html.fromHtml(jObject.getString("content"))
                    // Spanned contents= Html.fromHtml(feedItem.getTitle());

                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                            ((Activity) mContext, recyclerViewHolder.rela_round, feedItem.getTitle()).toBundle());

                }
                break;

            }




            }

        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.textView.setOnClickListener(listener);

        customViewHolder.img_main_card2_bookmark.setOnClickListener(listener);
        customViewHolder.img_main_card2_share.setOnClickListener(listener);
        customViewHolder.img_main_card2_favorite.setOnClickListener(listener);




    }

    @Override
    public int getItemCount() {

        return (null != feedItemList ? feedItemList.size() : 0);
    }




    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        private RelativeLayout rela_round;
        private ImageView img_main_card2_share, img_main_card2_bookmark, img_main_card2_favorite;
        private CardView card_main_1_2 ;


        public CustomViewHolder(View view) {
            super(view);
          //  this.imageView = view.findViewById(R.id.thumbnail);
          //  this.textView = view.findViewById(R.id.title);

            db = new DBHelper(mContext);
            this.imageView = view.findViewById(R.id.img_main_card_2);
            this.textView = view.findViewById(R.id.tv_card_main_2_title);
            rela_round = (RelativeLayout) view.findViewById(R.id.rela_round);

            img_main_card2_share = (ImageView) view.findViewById(R.id.img_main_card2_share);
            img_main_card2_bookmark = (ImageView) view.findViewById(R.id.img_main_card2_bookmark);
            img_main_card2_favorite = (ImageView) view.findViewById(R.id.img_main_card2_favorite);

            card_main_1_2 = (CardView) view.findViewById(R.id.card_main_1_2);






        }





    }


}