package com.thea.guo.leftrightscrool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thea.guo.leftrightscrool.model.RightModel;
import com.thea.guo.leftrightscrool.tool.ObservableScrollView;
import com.thea.guo.leftrightscrool.tool.ScrollViewListener;
import com.thea.guo.leftrightscrool.tool.UtilTools;
import com.thea.guo.leftrightscrool.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends Activity implements
//        AbsListView.OnScrollListener,
        ScrollViewListener {
    private LinearLayout leftContainerView;
    private ListView leftListView;
    private List<String> leftlList;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private List<RightModel> models;
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    private ObservableScrollView ui_1;
    private int scrollHeight = 0;
    private int eachscreenHeight = 0;
    private RightAdapter mRightAdapter = null;
    private LeftAdapter mLeftAdapter = null;
    private int page = -1;
    private int newpage = 0;
    private int screenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab_view);
        leftContainerView = (LinearLayout) findViewById(R.id.left_container);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) findViewById(R.id.right_container);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);

        ui_1 = (ObservableScrollView) findViewById(R.id.ui_1);
        ui_1.setScrollViewListener(this);
        WindowManager wm = this.getWindowManager();
        screenHeight = wm.getDefaultDisplay().getHeight();

        leftContainerView.setBackgroundColor(Color.YELLOW);
        leftlList = new ArrayList<String>();
        initLeftData();
        mLeftAdapter = new LeftAdapter();
        leftListView.setAdapter(mLeftAdapter);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);


        rightContainerView.setBackgroundColor(Color.GRAY);
        models = new ArrayList<RightModel>();
        initRightData();
        mRightAdapter = new RightAdapter();
        rightListView.setAdapter(mRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);


        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position + "right");
            }
        });
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position + "left");
            }
        });

        scrollHeight();

    }

    private void initRightData() {

        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
        models.add(new RightModel(newpage + "111", "222", "333", "444", "555", "666"));
    }

    private void initLeftData() {
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");
        leftlList.add(newpage + "aaaa");

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y + screenHeight > eachscreenHeight * newpage) {
            if (newpage > page) {
//                Toast.makeText(TableActivity.this, "加载第"+newpage+"页" , Toast.LENGTH_SHORT).show();
                initRightData();
                initLeftData();

                mRightAdapter.notifyDataSetChanged();
                mLeftAdapter.notifyDataSetChanged();

                UtilTools.setListViewHeightBasedOnChildren(rightListView);
                UtilTools.setListViewHeightBasedOnChildren(leftListView);

                scrollHeight();
            }
        }
    }

    private void scrollHeight() {
        ui_1.measure(0, 0);
        int newscrollHeight = ui_1.getMeasuredHeight();
        if (newpage == 0) {
            eachscreenHeight = newscrollHeight;
        }

//        System.out.println(ui_1.getMeasuredHeight() + "   " + screenHeight);

        if (newscrollHeight > scrollHeight) {
            page++;
            newpage = page + 1;
            scrollHeight = newscrollHeight;
        }
    }

    class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (leftlList != null) {
                return leftlList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (leftlList != null) {
                return leftlList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LeftViewHold hold;
            if (convertView == null) {
                hold = new TableActivity.LeftViewHold();
                convertView = View.inflate(TableActivity.this, R.layout.layout_left_item, null);
                hold.textView = (TextView) convertView.findViewById(R.id.left_container_textview0);
                convertView.setTag(hold);
            } else {
                hold = (TableActivity.LeftViewHold) convertView.getTag();
            }
            hold.textView.setText(leftlList.get(position));
            return convertView;
        }
    }

    static class LeftViewHold {
        TextView textView;
    }

    class RightAdapter extends BaseAdapter {
//        private Context context;
//        List<RightModel> list;
//
//        public RightAdapter(Context context, List<RightModel> models) {
//            super();
//            this.context = context;
//            this.list = models;
//        }

        @Override
        public int getCount() {
            if (models != null) {
                return models.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (models != null) {
                return models.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TableActivity.RightViewHold viewHold;
            if (convertView == null) {
                viewHold = new TableActivity.RightViewHold();
                convertView = View.inflate(TableActivity.this, R.layout.layout_right_item, null);
                viewHold.textView0 = (TextView) convertView.findViewById(R.id.right_item_textview0);
                viewHold.textView1 = (TextView) convertView.findViewById(R.id.right_item_textview1);
                viewHold.textView2 = (TextView) convertView.findViewById(R.id.right_item_textview2);
                viewHold.textView3 = (TextView) convertView.findViewById(R.id.right_item_textview3);
                viewHold.textView4 = (TextView) convertView.findViewById(R.id.right_item_textview4);
                viewHold.textView5 = (TextView) convertView.findViewById(R.id.right_item_textview5);
                convertView.setTag(viewHold);
            } else {
                viewHold = (TableActivity.RightViewHold) convertView.getTag();
            }
            viewHold.textView0.setText(models.get(position).getText0());
            viewHold.textView1.setText(models.get(position).getText1());
            viewHold.textView2.setText(models.get(position).getText2());
            viewHold.textView3.setText(models.get(position).getText3());
            viewHold.textView4.setText(models.get(position).getText4());
            viewHold.textView5.setText(models.get(position).getText5());
            return convertView;
        }
    }

    static class RightViewHold {
        TextView textView0, textView1, textView2, textView3, textView4, textView5;
    }

}