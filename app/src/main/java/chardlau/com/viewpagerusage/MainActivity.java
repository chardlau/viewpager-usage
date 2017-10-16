package chardlau.com.viewpagerusage;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UpdatePagerAdapter adapter;
    private List<String> testDataSource1 = new ArrayList<>();
    private List<String> testDataSource2 = new ArrayList<>();
    private List<String> testDataSource3 = new ArrayList<>();

    private void initialData() {
        testDataSource1.add("testDataSource1 1");

        testDataSource2.add("testDataSource2 1");
        testDataSource2.add("testDataSource2 2");

        testDataSource3.add("testDataSource3 1");
        testDataSource3.add("testDataSource3 2");
        testDataSource3.add("testDataSource3 3");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialData();

        adapter = new UpdatePagerAdapter();
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);
        viewPager.setAdapter(adapter);

        findViewById(R.id.btn_viewpager_update_1).setOnClickListener(this);
        findViewById(R.id.btn_viewpager_update_2).setOnClickListener(this);
        findViewById(R.id.btn_viewpager_update_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_viewpager_update_1:
                adapter.setTexts(testDataSource1);
                break;
            case R.id.btn_viewpager_update_2:
                adapter.setTexts(testDataSource2);
                break;
            case R.id.btn_viewpager_update_3:
                adapter.setTexts(testDataSource3);
                break;
        }
    }


    private static class UpdatePagerAdapter extends PagerAdapter {

        private List<String> texts;

        public UpdatePagerAdapter() {
            texts = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String text = texts.get(position);

            TextView textView = new TextView(container.getContext());
            textView.setText(text);

            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void setTexts(List<String> texts) {
            this.texts.clear();
            if (texts != null && texts.size() > 0) {
                this.texts.addAll(texts);
            }
            notifyDataSetChanged();
        }
    }

}
