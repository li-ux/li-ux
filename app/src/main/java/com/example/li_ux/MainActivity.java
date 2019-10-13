package com.example.li_ux;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText mYearET;
    private EditText mMonthET;
    private EditText mDayET;
    private TextView mQuestion1_TV;
    private TextView mQuestion2_TV;
    private EditText mQuestion3_ET;
    private TextView mQuestion3_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        initView();
        question2();
    }

    private void initView() {
        mYearET = (EditText) findViewById(R.id.year_ET);
        mMonthET = (EditText) findViewById(R.id.month_ET);
        mDayET = (EditText) findViewById(R.id.day_ET);
        mQuestion1_TV = (TextView) findViewById(R.id.question1_TV);
        mQuestion2_TV = (TextView) findViewById(R.id.question2_TV);
        mQuestion3_ET = (EditText) findViewById(R.id.question3_ET);
        mQuestion3_TV = (TextView) findViewById(R.id.question3_TV);


    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.question1_BTN:
                //获取文本框中输入的年月日
                String y = mYearET.getText().toString().trim();
                String m = mMonthET.getText().toString().trim();
                String d = mDayET.getText().toString().trim();
                //转换为int类型
                int year = Integer.parseInt(y);
                int month = Integer.parseInt(m);
                int day = Integer.parseInt(d);
                int day_of_year = question1(year, month, day);
                Toast.makeText(MainActivity.this, "该年的"+day_of_year+"天", Toast.LENGTH_SHORT).show();
                break;
            case R.id.question3_BTN:
                question3();
                break;
        }
    }
//未完成代码  不用参考
//    public int getDayOfYear(int year, int month, int day){
//        //闰年条件:  能被4整除，但不能被100整除。 或者能被400整除
//        //1、3、5、7、8、10、12月份，每个月31天。2月闰年有29天，非闰年28天其他月份，每月30天
//        int num_of_year = 0;
//        if (month>7){
//            num_of_year = (31<<2) + (30<<1) + ((year % 4 == 0 && year % 100 != 0)|| year%400 == 0 ? 29:28)
//            + ((((month - 8)/2)*31) + (((month - 8)/2)*30)+day) + ((month - 8)%2)*30;
//        }else{
//
//        }
//        return num_of_year;
//    }

    public int question1(int year, int month, int day){
        //闰年条件:  能被4整除，但不能被100整除。 或者能被400整除
        //1、3、5、7、8、10、12月份，每个月31天。2月闰年有29天，非闰年28天其他月份，每月30天

        //如果输入日期为1月   那么该天就是在该年的第 pi[0] + day 天了
        //如果输入日期为2月   那么该天就是在该年的第 pi[1] + day 天了....
        int[] pi={0,31,59,90,120,151,181,212,243,273,304,334,0};
        //pi数组中设定的2月天数是28
        //如果是闰年，二月份就为29天了,所以当查询日期的月份大于2时,所有日期都得+1
        if((year%4==0&&year%100!=0)||year%400==0){
            if(month>2)
                return pi[month-1]+day-1;
            else
                return pi[month-1]+day;
        }else{
            return pi[month-1]+day;
        }
    }

    public void question2(){
        StringBuilder strbuilder = new StringBuilder();
        for(int i = 1; i <= 9; i++){
            for(int j = 1; j <= i; j++){
                strbuilder.append(i+"*"+j+"="+(i*j)+"\t");
            }
            //每添加完一行乘法表    就添加一个换行字符
            strbuilder.append("\r\n");
        }
        mQuestion2_TV.setText(strbuilder.toString());
    }

    public void question3(){
        String num_str = mQuestion3_ET.getText().toString().trim();
        String[] num_char_arr = num_str.split("");
        int reustl_num = 0;
        for (int i = 1; i < num_char_arr.length; i++){
            //分别相加三个数乘以自己的三次方
            reustl_num += Math.pow(Integer.parseInt(num_char_arr[i]), 3);
        }
        if (reustl_num == Integer.parseInt(num_str))
            mQuestion3_TV.setText("是水仙花数");
        else
            mQuestion3_TV.setText("不是水仙花数");
    }
}
