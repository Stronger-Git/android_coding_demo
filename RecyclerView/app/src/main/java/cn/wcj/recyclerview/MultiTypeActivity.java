package cn.wcj.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.wcj.recyclerview.adapter.MultiTypeAdapter;
import cn.wcj.recyclerview.bean.MultiTypeBean;
import cn.wcj.recyclerview.util.Datas;

/**
 * @author WuChangJian
 * @date 2020/3/14 21:44
 */
public class MultiTypeActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<MultiTypeBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);
        mRecyclerView = findViewById(R.id.rv_multi_type);
        // 1.初始化数据
        initData();
        // 2.初始化布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // 3.初始化适配器
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(mData);
        mRecyclerView.setAdapter(multiTypeAdapter);

    }

    /**
     * 因为涉及到多条目，每个条目类型的icon和title肯定不同
     * 所以要做出相应的判断 MultiTypeBean中的属性类型也应该设置为数组
     * 这里只改变了icon的类型
     */
    private void initData() {
        mData = new ArrayList<>();
        MultiTypeBean item = null;
        Random random = new Random();
        for (int i = 0; i < Datas.icons.length; i++) {
            // 多条目类型绑定数据有所不同，第一种条目类型和第二种类型差不多，都是一张图一个标题
            // 而第三种条目类型是多张图（以三张图片为例），所以设置有所不同
            int itemViewType = random.nextInt(3);
            item = new MultiTypeBean();
            if (itemViewType == 0) {
                item.setTitle("第一种条目类型");
                item.setItemType(itemViewType);
                int []icon = {Datas.icons[i]};
                item.setIcon(icon);
            } else if (itemViewType == 1) {
                item.setTitle("第二种条目类型");
                item.setItemType(itemViewType);
                int []icon = {Datas.icons[i]};
                item.setIcon(icon);
            } else {
                item.setTitle("第三种种条目类型");
                item.setItemType(itemViewType);
                int []icon = new int[3];
                for (int j = 0; j < 3; j++) {
                    icon[j] = Datas.icons[i];
                }
                item.setIcon(icon);
            }
            mData.add(item);
        }
    }
}
