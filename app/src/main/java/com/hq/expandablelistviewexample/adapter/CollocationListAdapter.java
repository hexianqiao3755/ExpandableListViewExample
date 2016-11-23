package com.hq.expandablelistviewexample.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hq.expandablelistviewexample.R;
import com.hq.expandablelistviewexample.bean.CollocationPackageBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class CollocationListAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ExpandableListView elv_collocation;
    private List<CollocationPackageBean> data;

    public CollocationListAdapter(Context context, ExpandableListView elv_collocation, List<CollocationPackageBean> data) {
        this.context = context;
        this.elv_collocation = elv_collocation;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getCollocationSkuDoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getCollocationSkuDoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//如果子条目需要响应click事件,必需返回true
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.collocation_list_item_parent, parent, false);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
            AutoUtils.auto(convertView);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        CollocationPackageBean collocationPackageBean = data.get(groupPosition);
        parentViewHolder.tv_collocation_name.setText(TextUtils.isEmpty(collocationPackageBean.getName()) ? "优惠套餐" : collocationPackageBean.getName());
        parentViewHolder.tv_save_text.setText("立省￥" + collocationPackageBean.getDiscountFee());
        parentViewHolder.iv_status.setImageResource(isExpanded ? R.mipmap.icon_top : R.mipmap.icon_bottom);
        parentViewHolder.v_space.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        parentViewHolder.hsv_goods_list.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        parentViewHolder.hsv_goods_list.setFocusable(false);//设置后解决套餐无法正常展开的bug
        if (!isExpanded) {
            //设置套餐折叠时显示套餐商品的图片
            initGoodsImage(collocationPackageBean, parentViewHolder, groupPosition);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.collocation_list_item_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
            AutoUtils.auto(convertView);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final CollocationPackageBean.CollocationSkuBean collocationSkuBean = data.get(groupPosition).getCollocationSkuDoList().get(childPosition);
        childViewHolder.sdv_goods_img.setImageURI(Uri.parse(collocationSkuBean.getImageMd5()));
        childViewHolder.tv_goods_title.setText(collocationSkuBean.getSkuTitle());
        childViewHolder.ll_root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入商品详情页操作
            }
        });
        if (childPosition == data.get(groupPosition).getCollocationSkuDoList().size() - 1) {
            //当前套餐的最后一个商品
            childViewHolder.ll_bottom.setVisibility(View.VISIBLE);
            childViewHolder.tv_collocation_price.setText(data.get(groupPosition).getTotalPrice().toString());
            childViewHolder.tv_add_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //把套餐商品加入购物车操作
                }
            });
        } else {
            childViewHolder.ll_bottom.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ParentViewHolder {
        private View v_space;
        private ImageView iv_status;
        private HorizontalScrollView hsv_goods_list;
        private TextView tv_collocation_name, tv_save_text;

        private ParentViewHolder (View view) {
            v_space = view.findViewById(R.id.v_space);
            iv_status = (ImageView) view.findViewById(R.id.iv_status);
            hsv_goods_list = (HorizontalScrollView) view.findViewById(R.id.hsv_goods_list);
            tv_collocation_name = (TextView) view.findViewById(R.id.tv_collocation_name);
            tv_save_text = (TextView) view.findViewById(R.id.tv_save_text);
        }
    }

    private class ChildViewHolder {
        private SimpleDraweeView sdv_goods_img;
        private LinearLayout ll_bottom, ll_root_view;
        private TextView tv_add_cart, tv_goods_title, tv_collocation_price;

        private ChildViewHolder (View view) {
            sdv_goods_img = (SimpleDraweeView) view.findViewById(R.id.sdv_goods_img);
            ll_bottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
            ll_root_view = (LinearLayout) view.findViewById(R.id.ll_root_view);
            tv_add_cart = (TextView) view.findViewById(R.id.tv_add_cart);
            tv_goods_title = (TextView) view.findViewById(R.id.tv_goods_title);
            tv_collocation_price = (TextView) view.findViewById(R.id.tv_collocation_price);
        }
    }

    /**
     * 初始化并设置套餐折叠时的所有商品图片
     * @param collocationPackageBean
     * @param parentViewHolder
     * @param groupPosition
     */
    private void initGoodsImage(CollocationPackageBean collocationPackageBean,
                                ParentViewHolder parentViewHolder, final int groupPosition) {
        View collocationView;
        SimpleDraweeView sdv_cart_image;
        RelativeLayout rl_middle;
        LinearLayout rootview = new LinearLayout(context);
        for (int i = 0, len = collocationPackageBean.getCollocationSkuDoList().size(); i < len; i++) {
            collocationView = inflater.inflate(R.layout.item_gift_img, null);
            sdv_cart_image = (SimpleDraweeView) collocationView.findViewById(R.id.sdv_cart_image);
            rl_middle = (RelativeLayout) collocationView.findViewById(R.id.rl_middle);
            sdv_cart_image.setImageURI(Uri.parse(collocationPackageBean.getCollocationSkuDoList().get(i).getImageMd5()));
            rl_middle.setVisibility(View.INVISIBLE);
            collocationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //手动实现展开操作
                    elv_collocation.expandGroup(groupPosition);
                }
            });
            AutoUtils.auto(collocationView);
            rootview.addView(collocationView);
        }
        parentViewHolder.hsv_goods_list.removeAllViews();
        parentViewHolder.hsv_goods_list.addView(rootview);
    }
}
