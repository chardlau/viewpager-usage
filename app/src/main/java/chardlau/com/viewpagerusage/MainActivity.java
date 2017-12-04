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

    private ViewPager viewPager;

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

        viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);
        if (adapter.getCount() > 1) {
            viewPager.setCurrentItem(1, false);
        }
    }

    private class UpdatePagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener  {

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
            if (texts == null) {
                notifyDataSetChanged();
                return;
            }

            if (texts.size() == 1) {
                this.texts.addAll(texts);
            } else if (texts.size() > 1) {
                this.texts.add(texts.get(texts.size() - 1));
                this.texts.addAll(texts);
                this.texts.add(texts.get(0));
            }

            notifyDataSetChanged();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int realCount = getCount() - 2;
            // 多于1，才会循环跳转
            if ( getCount() > 1) {
                // 首位之前，跳转到末尾（N）
                if ( position < 1) {
                    position = realCount;
                    viewPager.setCurrentItem(position,false);
                }
                // 末位之后，跳转到首位（1）
                else if ( position > realCount) {
                    position = 1;
                    viewPager.setCurrentItem(position,false);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
