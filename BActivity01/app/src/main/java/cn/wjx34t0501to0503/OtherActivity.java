package cn.wjx34t0501to0503;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author WuChangJian
 * @date 2020/3/27 9:54
 */
public class OtherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

    }

    public void goBack(View view) {
        EditText evContent = findViewById(R.id.evContent);
        String result = evContent.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("content", result);
        setResult(1, intent);
        this.finish();
    }
}
