package com.cs.test_picasso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.test_picasso.R;
import com.cs.test_picasso.entity.News;
import com.cs.test_picasso.utils.PicassoUtils;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/22.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<News.TngouBean> mlist;
    private ViewHolder holder;

    public MyAdapter(Context context, List<News.TngouBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    /**
     * 此适配器表示的数据集中有多少项。
     */
    @Override
    public int getCount() {
        return mlist.size();
    }

    /**
     * 获取与列表中指定位置相关联的行标识。
     *
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    /**
     * 获取与数据集中指定位置相关联的数据项。
     *
     * @param i
     * @return 这一层相当于for循环
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 注意： 获取在数据集中指定位置显示数据的视图。 您可以手动创建视图，也可以从XML布局文件中对其进行扩充。 当View充气的，除非你使用父视图（GridView控件，ListView中...）将应用默认的布局参数inflate(int, android.view.ViewGroup, boolean)指定根视图，并防止附着到根。
     *
     * @param i         该项目的适配器的数据集，我们希望他们的观点的项目中的位置。
     * @param view      旧视图重用，如果可能的话。 注意：您应该在使用之前检查此视图是否为非空值并且具有适当的类型。 如果无法将此视图转换为显示正确的数据，则此方法可以创建新视图。 异构列表可以指定自己的视图类型的数量，因此，这种观点是正确的类型始终（见getViewTypeCount()和getItemViewType(int)
     * @param viewGroup 此视图最终将被附连到父
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
            holder.image = (ImageView) view.findViewById(R.id.iv_item);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(holder);
        }
        view.getTag();
        PicassoUtils.loadImageWithSize(context,"http://tnfs.tngou.net/image"+mlist.get(i).getImg(),100,100,holder.image);
        holder.title.setText(mlist.get(i).getName());
        holder.content.setText(mlist.get(i).getDescription());

        return view;
    }

    class ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView content;

    }
}
