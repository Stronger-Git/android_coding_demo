package cn.wjx34t0302;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author WuChangJian
 * @date 2020/3/10 20:15
 */
public class BMICheckBoxActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_bmi_checkbox);
        findViewById(R.id.bt_calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etHeight = findViewById(R.id.et_height);
                EditText etWeight = findViewById(R.id.et_weight);
                TextView tvResult = findViewById(R.id.tv_calc_result);
                double height = Double.parseDouble(etHeight.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());
                Double bmi = weight / (height * height);
                CheckBox cbWho = findViewById(R.id.cb_who);
                CheckBox cbAsia = findViewById(R.id.cb_asia);
                CheckBox cbChina = findViewById(R.id.cb_china);
                if (cbWho.isChecked()) {
                    if (bmi < 18.5) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过轻\n");
                    } else if (bmi <= 24.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：正常\n");
                    } else if (bmi <= 29.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过重\n");
                    } else if (bmi <= 34.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：肥胖\n");
                    } else {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：非常肥胖\n");
                    }
                }
                if (cbAsia.isChecked()) {
                    if (bmi < 18.5) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过轻\n");
                    } else if (bmi <= 22.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：正常\n");
                    } else if (bmi <= 24.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过重\n");
                    } else if (bmi <= 29.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：肥胖\n");
                    } else {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：非常肥胖\n");
                    }
                }
                if (cbChina.isChecked()) {
                    if (bmi < 18.5) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过轻\n");
                    } else if (bmi <= 23.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：正常\n");
                    } else if (bmi <= 27.9) {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：过重\n");
                    } else {
                        tvResult.append("BMI:" + bmi.toString() + " 体型：非常肥胖\n");
                    }
                } else {
                    tvResult.append("请选择BMI标准\n");
                }
            }
        });
    }
}
