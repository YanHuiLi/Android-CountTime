package android.archer.com.counttime;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @function 实现倒计时的小程序
 * @author Archer
 *
 */

public class MainActivity extends Activity implements View.OnClickListener{



    private EditText inPut;
    private TextView time;
    private  int i=0;

    private Timer mTimer=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                   initView();



    }

    //初始化各个组件的方法

    private void initView(){
        inPut= (EditText) findViewById(R.id.InputTime);
        Button getTime = (Button) findViewById(R.id.GetTime);
        Button startTime = (Button) findViewById(R.id.StartTIme);
        Button stopTime = (Button) findViewById(R.id.StopTime);
        time= (TextView) findViewById(R.id.time);

        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.GetTime:
                 time.setText(inPut.getText().toString());
                 i=Integer.parseInt(inPut.getText().toString());
                 break;

             case R.id.StartTIme:
                  startTime();
                 break;

             case R.id.StopTime:
                 stopTime();
                 break;
             default:
                 break;
         }


    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           //只能承载string类型的数据
            time.setText(msg.arg1+"");
            startTime();
        }
    };

    public void  startTime(){
        mTimer=new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               if (i>=1){
                   i--;
               }

                Message msg = mHandler.obtainMessage();
                msg.arg1 = i;
                mHandler.sendMessage(msg);
            }
        };
           //schedule方法要求传入两个参数，第一个是timetask对象和延时执行的时间
        mTimer.schedule(timerTask,1000);
    }

    public  void stopTime(){


        mTimer.cancel();
    }

}
