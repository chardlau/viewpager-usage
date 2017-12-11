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

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);

        UpdatePagerAdapter adapter = new UpdatePagerAdapter(viewPager);
        adapter.setTexts(testDataSource1);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);
        viewPager.setCurrentItem(1);
    }

    private static class UpdatePagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
        private ViewPager viewPager;
        private int count;
        private int currentItem;
        private List<String> texts;


        public UpdatePagerAdapter(ViewPager viewPager) {
            this.viewPager = viewPager;
            this.count = 0;
            this.currentItem = 0;
            this.texts = new ArrayList<>();
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
            this.count = 0;
            this.texts.clear();
            if (texts != null && texts.size() > 0) {
                this.count = texts.size();
                for (int i = 0; i <= count + 1; i++) {
                    if (i == 0) {
                        this.texts.add(texts.get(count - 1));
                    } else if (i == count + 1) {
                        this.texts.add(texts.get(0));
                    } else {
                        this.texts.add(texts.get(i - 1));
                    }
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_IDLE://No operation
                    if (currentItem == 0) {
                        viewPager.setCurrentItem(count, false);
                    } else if (currentItem == count + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    break;
                case ViewPager.SCROLL_STATE_DRAGGING: //start Sliding
                    if (currentItem == 0) {
                        viewPager.setCurrentItem(count, false);
                    } else if (currentItem == count + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    break;
                case ViewPager.SCROLL_STATE_SETTLING://end Sliding
                    break;
            }
        }
    }
}
