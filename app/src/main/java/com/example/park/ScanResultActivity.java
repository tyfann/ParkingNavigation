package com.example.park;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.park.fragment.Parking;
import com.example.park.util.DateUtil;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class ScanResultActivity extends AppCompatActivity {

    TextView tv_scanResult;

    public String scan_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanresult);
        Bmob.initialize(ScanResultActivity.this,"8803b2c419834daa0f57d8c26aea6c28");
        tv_scanResult = findViewById(R.id.tv_scanresult);
        ScanForResult();

    }

    private void ScanForResult(){
        scan_time= DateUtil.getNowTime();
        Time time = new Time();
        time.setIntime(scan_time);
        time.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(ScanResultActivity.this,"进入时间记录已上传！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ScanResultActivity.this,"上传失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        BmobQuery<Parking> query = new BmobQuery<Parking>();
        query.addWhereEqualTo("EmptyIF",Boolean.TRUE);
        query.order("createdAt");
        query.setCachePolicy(BmobQuery.CachePolicy.IGNORE_CACHE);
        query.findObjects(new FindListener<Parking>() {
            @Override
            public void done(List<Parking> object, BmobException e) {
                if(e==null){
                    Toast.makeText(ScanResultActivity.this,"缓存成功，共"+object.size()+"条数据",Toast.LENGTH_SHORT).show();
                    //List list=new ArrayList();
                    String s="";
                    for(int i=0;i<object.size();i++){
                        //text_print.setText("X坐标："+object.get(i).getCo_X()+"Y坐标："+object.get(i).getCo_Y());
                        //list.add("X坐标："+object.get(i).getCo_X()+"Y坐标："+object.get(i).getCo_Y());
                        // if(object.get(i).getEmptyIF()==Boolean.TRUE)
                        s=s.concat("X坐标："+object.get(i).getCo_X()+" Y坐标："+object.get(i).getCo_Y()+object.get(i).getEmptyIF()+"\n");
                    }
                    tv_scanResult.setText(s);

                }else{
                    Toast.makeText(ScanResultActivity.this,"缓存失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}