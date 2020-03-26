package cn.wjx34t0402;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * @author WuChangJian
 * @date 2020/3/11 15:28
 * @Description 自定义对话框，实现用户协议弹出框的实现
 */
public class CustomDialog extends Dialog {

    private CustomDialogListener mCustomDialogListener;
    public CustomDialog(Context context, CustomDialogListener customDialogListener) {
        super(context);
        mCustomDialogListener = customDialogListener;
    }

    /**
     * (1) 重写onCreate方法，加载对话框布局，onCreate方法的通性 get
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        // 自定义对话框的宽度适配屏幕
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        this.getWindow().setAttributes(lp);
        Button btConfirm = findViewById(R.id.btConfirmLicence);
        final CheckBox cbConfirm = findViewById(R.id.cbConfirmLicence);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = cbConfirm.isChecked();
                mCustomDialogListener.customDialogClick(checked);
            }
        });

    }
    /**
     * 自定义事件接口类 实现响应事件的回调以及自定义
     */
    public interface CustomDialogListener {
        void customDialogClick(boolean isConfirm);
    }
}
