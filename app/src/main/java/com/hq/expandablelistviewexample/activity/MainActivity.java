package com.hq.expandablelistviewexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.hq.expandablelistviewexample.R;
import com.hq.expandablelistviewexample.adapter.CollocationListAdapter;
import com.hq.expandablelistviewexample.bean.CollocationPackageBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView elv_collocation;
    private List<CollocationPackageBean> collocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elv_collocation = (ExpandableListView) findViewById(R.id.elv_collocation);
        initData();
    }

    private void initData() {
        collocationList = new ArrayList<>();
        CollocationPackageBean collocation_1 = new CollocationPackageBean();
        CollocationPackageBean collocation_2 = new CollocationPackageBean();

        collocation_1.setTotalPrice(new BigDecimal(897));
        collocation_1.setDiscountFee(new BigDecimal(20));
        collocation_1.setName("818国货套餐3");
        List<CollocationPackageBean.CollocationSkuBean> goodsList_1 = new ArrayList<>();
        goodsList_1.add(new CollocationPackageBean.CollocationSkuBean("Meizu/魅族 魅蓝 note3 全网通 手机 银白 16GB", "http://img11.hqbcdn.com/product/07/0a/070ac7abd57c6d9251d89547f3d62501.jpg"));
        goodsList_1.add(new CollocationPackageBean.CollocationSkuBean("VR PLUS 智能眼镜vr虚拟现实头盔 3D沉浸式 暴风魔镜 vr plus 智能头盔 白色", "http://img15.hqbcdn.com/product/c6/10/c610075082199955a8d5dcf2aa765b17.jpg"));
        collocation_1.setCollocationSkuDoList(goodsList_1);

        collocation_2.setTotalPrice(new BigDecimal(1034));
        collocation_2.setDiscountFee(new BigDecimal(26));
        collocation_2.setName("超值套餐");
        List<CollocationPackageBean.CollocationSkuBean> goodsList_2 = new ArrayList<>();
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Meizu/魅族 魅蓝 note3 全网通 手机 银白 16GB", "http://img11.hqbcdn.com/product/07/0a/070ac7abd57c6d9251d89547f3d62501.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Uka/优加 Meizu/魅族 魅蓝 note3全覆盖全屏钢化玻璃膜 白色", "http://img8.hqbcdn.com/product/9c/15/9c15571aa92905ea1edafb0a288f1ebb.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("SanDisk/闪迪 至尊高速MicroSDHC-TF移动存储卡 Class10-48MB/S 升级版 16G", "http://img14.hqbcdn.com/product/29/cd/29cda69f5036b38454b6592f96fde774.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Huawei/华为 AM116 金属耳机 三键线控耳机 尊爵版", "http://img9.hqbcdn.com/product/0a/90/0a905d9988c91fb0625d9ee44377c8e0.jpg"));
        goodsList_2.add(new CollocationPackageBean.CollocationSkuBean("Lesimo/梵简 初见系列10000毫安充电宝 手机平板通用 移动电源 黑色", "http://img11.hqbcdn.com/product/67/3a/673ac0343758ce64e97c2d9986cbbef3.jpg"));
        collocation_2.setCollocationSkuDoList(goodsList_2);

        collocationList.add(collocation_1);
        collocationList.add(collocation_2);
        elv_collocation.setAdapter(new CollocationListAdapter(this, elv_collocation, collocationList));
        elv_collocation.expandGroup(0);//默认展开第一个
    }
}
