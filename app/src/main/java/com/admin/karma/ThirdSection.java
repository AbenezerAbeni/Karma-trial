package com.admin.karma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.admin.karma.adapters.CursorRecyclerViewAdapter;
import com.admin.karma.adapters.MyListCursorAdapter;
import com.admin.karma.databases.DBHelper;
import com.admin.karma.databases.RequestProvider;
import com.admin.karma.databases.TableItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30/09/2017.
 */

public class ThirdSection extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorRecyclerViewAdapter adapter;
    private static final int LOADER_SEARCH_RESULTS = 1;

    //offset =30
    public final int offset = 20;
    private int page = 0;

    private RecyclerView mRecyclerView;
    private boolean loadingMore = false;
    private Toast shortToast;
    private static final String TAG = "Third_Section";


    List<SavedItem> saves;
    View view;
    DBHelper dbs;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     view = inflater.inflate(R.layout.activity_third ,container ,false);
     mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//     mRecyclerView.setAdapter(adapter);
   //  adapter = new MyListCursorAdapter(getContext(), cursor);



     //   mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        //this.getLoaderManager().restartLoader(LOADER_SEARCH_RESULTS, null, this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        final MyListCursorAdapter mAdapter = new MyListCursorAdapter(getContext(), null);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

//        int itemsCountLocal = getItemsCountLocal();
//        if (itemsCountLocal == 0) {
//           // fillTestElements();
//            //wannt to change this line to a more list to c all the  cursor elements
//            Log.d("Hey there", "ItemsCountLocal----Third Section---");
//        }

        shortToast = Toast.makeText(getContext(), TAG, Toast.LENGTH_SHORT);

        // start loader}

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(mRecyclerView, dx, dy);

                // recyclerView
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int maxPositions = layoutManager.getItemCount();

                if (lastVisibleItemPosition == maxPositions - 1) {
                    if (loadingMore)
                        return;

                    loadingMore = true;
                    page++;
                    getActivity().getSupportLoaderManager().restartLoader(0, null, ThirdSection.this);
                }
            }
        });
        getActivity().getSupportLoaderManager().restartLoader(0, null, ThirdSection.this);
    }

    private void fillTestElements() {
        int size = 1000;
        ContentValues[] cvArray = new ContentValues[size];
        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();
          //  cv.put(TableItems.KEY_TITLE, ("title " + i));
            cvArray[i] = cv;
        }

        getActivity().getContentResolver().bulkInsert(RequestProvider.urlForItems(0), cvArray);
    }

    private int getItemsCountLocal() {
        int itemsCount = 0;

        Cursor query = getActivity().getContentResolver().query(RequestProvider.urlForItems(0), null, null, null, null);
        if (query != null) {
            itemsCount = query.getCount();
            query.close();
        }
        return itemsCount;
    }



     /*loader*/

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new CursorLoader(getContext(), RequestProvider.urlForItems(offset * page), null, null, null, null);
            default:
                throw new IllegalArgumentException("no id handled!");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                Log.d(TAG, "onLoadFinished: loading MORE");
                shortToast.setText("loading MORE " + page);
                shortToast.show();

                Cursor cursor = ((MyListCursorAdapter) mRecyclerView.getAdapter()).getCursor();

                //fill all exisitng in adapter
                MatrixCursor mx = new MatrixCursor(TableItems.Columns);
                fillMx(cursor, mx);

                //fill with additional result
                fillMx(data, mx);

                ((MyListCursorAdapter) mRecyclerView.getAdapter()).swapCursor(mx);


                handlerToWait.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingMore = false;
                    }
                }, 2000);

                break;
            default:
                throw new IllegalArgumentException("no loader id handled!");
        }
    }

    private Handler handlerToWait = new Handler();

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(TableItems.KEY_TITLE)),
//                    data.getString(data.getColumnIndex(TableItems.KEY_ID)),
//                    data.getString(data.getColumnIndex(TableItems.KEY_BLOG))
                    //

            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // TODO: 2016-10-13
    }


}
