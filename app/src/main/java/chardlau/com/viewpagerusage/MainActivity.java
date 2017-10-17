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

public class MainActivity extends AppCompatActivity {

    private List<String> testDataSource1 = new ArrayList<>();

    private void initialData() {
        testDataSource1.add("testDataSource1 1");
        testDataSource1.add("testDataSource1 2");
        testDataSource1.add("testDataSource1 3");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialData();

//        UpdatePagerAdapter adapter = new UpdatePagerAdapter();
        Update2PagerAdapter adapter = new Update2PagerAdapter();
        adapter.setTexts(testDataSource1);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_viewpager_update);
        viewPager.setAdapter(adapter);
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

private static class Update2PagerAdapter extends PagerAdapter {

    private List<String> texts;

    public Update2PagerAdapter() {
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

    public void setTexts(List<String> texts) {
        this.texts.clear();
        if (texts != null && texts.size() > 0) {
            this.texts.addAll(texts);
        }
        notifyDataSetChanged();
    }
}

}
