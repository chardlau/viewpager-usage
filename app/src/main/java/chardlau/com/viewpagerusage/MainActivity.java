package chardlau.com.viewpagerusage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DynamicDataSetAdapter adapter;

    private ViewPager viewPager;

    private List<String> randomData() {
        List<String> dataSource = new ArrayList<>();
        long tmp = new Date().getTime();
        long cnt = tmp % 10 + 1;
        for (int i = 0; i < cnt; i++) {
            dataSource.add("DataSource " + (tmp++));
        }
        return dataSource;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new DynamicDataSetAdapter();
        adapter.setTexts(randomData());

        viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);
        viewPager.setAdapter(adapter);

        findViewById(R.id.btn_random).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_random:
                adapter.setTexts(randomData());
                break;
        }
    }

    private static class DynamicDataSetAdapter extends PagerAdapter {

        private List<String> texts;

        public DynamicDataSetAdapter() {
            texts = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            String text = (String) view.getTag();
            return object.equals(text);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String text = texts.get(position);

            TextView textView = new TextView(container.getContext());
            textView.setTag(text);
            textView.setText(text);

            container.addView(textView);
            return text;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = container.findViewWithTag(object);
            if (view != null) {
                container.removeView(view);
            }
        }

        public synchronized void setTexts(List<String> texts) {
            this.texts.clear();
            if (texts != null && texts.size() > 0) {
                this.texts.addAll(texts);
            }
            notifyDataSetChanged();
        }
    }
}
