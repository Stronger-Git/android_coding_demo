package cn.wjx34t0302;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * @author WuChangJian
 * @date 2020/3/10 19:46
 */
public class BMIActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_bmi);
        findViewById(R.id.bt_calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etHeight = findViewById(R.id.et_height);
                EditText etWeight = findViewById(R.id.et_weight);
                TextView tvResult = findViewById(R.id.tv_calc_result);
                double height = Double.parseDouble(etHeight.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());
                Double bmi = weight / (height * height);
                RadioButton rbWho = findViewById(R.id.rb_who);
                RadioButton rbAsia = findViewById(R.id.rb_asia);
                RadioButton rbChina = findViewById(R.id.rb_china);
                RadioButton rbShowBmi = findViewById(R.id.rb_show_bmi);
                RadioButton rbConclusion = findViewById(R.id.rb_conclusion);
                RadioButton rbShowBoth = findViewById(R.id.rb_both);
                if (rbWho.isChecked()) {
                    if (bmi < 18.5) {
                        if (rbShowBmi.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString());
                        } else if (rbConclusion.isChecked()) {
                            tvResult.setText("体型：过轻");
                        } else if (rbShowBoth.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString() + " 体型：过轻");
                        }
                    } else if (bmi <= 24.9) {
                        if (rbShowBmi.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString());
                        } else if (rbConclusion.isChecked()) {
                            tvResult.setText(" 体型：正常");
                        } else if (rbShowBoth.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString() + " 体型：正常");
                        }
                    } else if (bmi <= 29.9) {
                        if (rbShowBmi.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString());
                        } else if (rbConclusion.isChecked()) {
                            tvResult.setText(" 体型：过重");
                        } else if (rbShowBoth.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString() + " 体型：过重");
                        }
                    } else if (bmi <= 34.9) {
                        if (rbShowBmi.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString());
                        } else if (rbConclusion.isChecked()) {
                            tvResult.setText(" 体型：肥胖");
                        } else if (rbShowBoth.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString() + " 体型：肥胖");
                        }
                    } else {
                        if (rbShowBmi.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString());
                        } else if (rbConclusion.isChecked()) {
                            tvResult.setText(" 体型：非常肥胖");
                        } else if (rbShowBoth.isChecked()) {
                            tvResult.setText("BMI:" + bmi.toString() + " 体型：非常肥胖");
                        }
                    }
                } else if (rbAsia.isChecked()) {
                    if (bmi < 18.5) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：过轻");
                    } else if (bmi <= 22.9) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：正常");
                    } else if (bmi <= 24.9) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：过重");
                    } else if (bmi <= 29.9) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：肥胖");
                    } else {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：非常肥胖");
                    }
                } else if (rbChina.isChecked()) {
                    if (bmi < 18.5) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：过轻");
                    } else if (bmi <= 23.9) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：正常");
                    } else if (bmi <= 27.9) {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：过重");
                    } else {
                        tvResult.setText("BMI:" + bmi.toString() + " 体型：非常肥胖");
                    }
                } else {
                    tvResult.setText("请选择BMI标准");
                }
            }
        });
    }
}
