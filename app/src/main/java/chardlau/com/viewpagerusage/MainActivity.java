package chardlau.com.viewpagerusage;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> testDataSource1 = new ArrayList<>();

    private void initialData() {
        testDataSource1.add("Data 1");
        testDataSource1.add("Data 2");
        testDataSource1.add("Data 3");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialData();

        UpdatePagerAdapter adapter = new UpdatePagerAdapter();
        adapter.setTexts(testDataSource1);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2, false);
    }


    private static class UpdatePagerAdapter extends PagerAdapter {

        private List<String> texts;

        public UpdatePagerAdapter() {
            texts = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e("Adapter", "position: " + position);
            int index = position % 3;
            String text = texts.get(index);

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
