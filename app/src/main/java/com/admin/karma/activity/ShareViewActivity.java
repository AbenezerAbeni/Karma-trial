package com.admin.karma.activity;

/**
 * Created by Admin on 04/11/2017.
 */

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.karma.Constant;
import com.admin.karma.R;
import com.bumptech.glide.Glide;

    public class ShareViewActivity extends AppCompatActivity {

        private ImageView image_scrolling_top;
        private TextView text_content;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scrolling);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scrolling);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                    intent.setType("text/plain");
                    startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                }
            });

            image_scrolling_top = (ImageView) findViewById(R.id.image_scrolling_top);
       //     Glide.with(this).load(R.drawable.material_design_2).fitCenter().into(image_scrolling_top);

            text_content = (TextView) findViewById(R.id.tv_scrolling);


            if(getIntent().hasExtra("Text")  ) {

              //  text_content.setText(getIntent().getCharSequenceExtra("Text"));

                if (Build.VERSION.SDK_INT >= 24)
                {
                    text_content.setText(Html.fromHtml(String.valueOf(getIntent().getCharSequenceExtra("Text")),Html.FROM_HTML_MODE_LEGACY));

                }
                else
                {
                    text_content.setText(Html.fromHtml(String.valueOf(getIntent().getCharSequenceExtra("Text"))));
                }

            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            Configuration configuration = getResources().getConfiguration();
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                CollapsingToolbarLayout collapsing_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
                collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
            } else {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }


    }
