package com.admin.karma;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.admin.karma.adapters.ItemTouchHelperCallback;
import com.admin.karma.adapters.OnItemClickListener;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 30/09/2017.
 */

public class FirstSection extends Fragment  {


    private static final String TAG = "RecyclerViewExample";

    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;

    String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";

    //trial
    FeedItem item;

    private SwipeRefreshLayout swipeRefreshLayout;
    private int color = 0;
    private boolean loading;
    private int loadTimes;
    View view;

    private boolean isBookmarkClicked, isFavoriteClicked;
    private ImageView img_main_card_1, img_main_card_2 ;
    private ImageView img_main_card2_share, img_main_card2_bookmark, img_main_card2_favorite;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;
    private CardView card_main_1_2 ;
    protected RecyclerView.LayoutManager mLayoutManager;

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_first, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_recycler_view);


//
//        img_main_card2_share = (ImageView) mRecyclerView.findViewById(R.id.img_main_card2_share);
//        img_main_card2_bookmark = (ImageView) mRecyclerView.findViewById(R.id.img_main_card2_bookmark);
//        img_main_card2_favorite = (ImageView) mRecyclerView.findViewById(R.id.img_main_card2_favorite);
//
//        img_main_card_2 = (ImageView) mRecyclerView.findViewById(R.id.img_main_card_2);
//        card_main_1_2 = (CardView) mRecyclerView.findViewById(R.id.card_main_1_2);
//

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.pink, R.color.red, R.color.yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (color > 4) {
                            color = 0;
                        }
                        //adapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                        new DownloadTask().execute(url);

                    }
                }, 2000);

            }
        });

        mRecyclerView.addOnScrollListener(scrollListener);

        new DownloadTask().execute(url);

        return view;
    }


    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (!loading && linearLayoutManager.getItemCount() == (linearLayoutManager.findLastVisibleItemPosition() + 1)) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        new DownloadTask().execute(url);
                    }
                }, 1500);

                loading = true;
            }
        }
    };

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //setup materialviewpager
//
//        if (GRID_LAYOUT) {
//            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        } else {
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        }
//        mRecyclerView.setHasFixedSize(true);
//
//        //Use this now
//        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
//        //mRecyclerView.setAdapter(new MyRecyclerViewAdapter());
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView  = new RecyclerView(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new DownloadTask().execute(url);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // new DownloadTask().execute(url);

//        img_main_card2_bookmark.setOnClickListener(this);
//        img_main_card2_favorite.setOnClickListener(this);
//        img_main_card2_share.setOnClickListener(this);

//        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
//        alphaAnimation.setDuration(700);
//        img_main_card2_share.startAnimation(alphaAnimation);
//        img_main_card2_bookmark.startAnimation(alphaAnimation);
//        img_main_card2_favorite.startAnimation(alphaAnimation);
//
//        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
//        alphaAnimationShowIcon.setDuration(500);

    }



    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }



    public class DownloadTask extends AsyncTask<String, Void, Integer>  {


        @Override
        protected void onPreExecute() {
         //   Log.d(TAG,"On pre Execute");


        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful


                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result == 1) {
                adapter = new MyRecyclerViewAdapter(getContext(),feedsList);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//                adapter.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(FeedItem item) {
//                        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
//
//                    }
//                });

            } else {
                Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }



        private void parseResult(String result) {
            try {
                JSONObject response = new JSONObject(result);
                JSONArray posts = response.optJSONArray("posts");
                feedsList = new ArrayList<>();

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    FeedItem item = new FeedItem();
                    item.setTitle(post.optString("title"));
                    item.setThumbnail(post.optString("thumbnail"));
                    item.setIds(post.optInt("id"));
                    item.setContent(Html.fromHtml(post.getString("content")));
                    feedsList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
