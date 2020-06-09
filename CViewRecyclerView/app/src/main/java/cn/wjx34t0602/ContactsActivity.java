package cn.wjx34t0602;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0602.adapter.ContactsRecyclerAdatpter;
import cn.wjx34t0602.model.Contact;
import cn.wjx34t0602.util.Constant;

/**
 * @author WuChangJian
 * @date 2020/4/24 14:43
 */
public class ContactsActivity extends AppCompatActivity {

    public static final String TAG = "ContactsActivity";

    private List<Contact> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ContactsRecyclerAdatpter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new ContactsRecyclerAdatpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ContactsActivity.this, "你点击了第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < Constant.avator.length; i++) {
            Contact contact = new Contact(Constant.avator[i], Constant.names[i]);
            mDatas.add(contact);
        }
        mAdapter.setData(mDatas);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.contacts_recycler_view);
        mAdapter = new ContactsRecyclerAdatpter();
        mRecyclerView.setAdapter(mAdapter);
        showLinear(true);
    }

    private void showLinear(boolean isVertical) {
        if (mLinearLayoutManager == null)
            mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected:..." + item.getItemId());
        if (item.getItemId() == R.id.vertical) {
            Log.d(TAG, "vertical...");
            showLinear(true);
            return true;
        } else if (item.getItemId() == R.id.horizontal) {
            Log.d(TAG, "horizontal...");
            showLinear(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
