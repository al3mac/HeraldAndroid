
package com.szwagry.heroldandroid.views;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

        import com.szwagry.heroldandroid.R;

        import org.androidannotations.annotations.AfterInject;
        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by ragnar on 10/3/15.
 */

@EBean
public class SpinnerItemAdapter extends BaseAdapter {

    List<SpinnerItem> spinnerItems;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        spinnerItems = new ArrayList<SpinnerItem>();
        spinnerItems.add(new SpinnerItem("Bicycle", R.drawable.bicycle));
        spinnerItems.add(new SpinnerItem("Car", R.drawable.car));
        spinnerItems.add(new SpinnerItem("Mobile", R.drawable.mobile));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SpinnerItemView spinnerItemView;
        if (convertView == null) {
            spinnerItemView = SpinnerItemView_.build(context);
        } else {
            spinnerItemView = (SpinnerItemView) convertView;
        }

        spinnerItemView.bind(getItem(position));

        return spinnerItemView;
    }

    @Override
    public int getCount() {
        return spinnerItems.size();
    }

    @Override
    public SpinnerItem getItem(int position) {
        return spinnerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}