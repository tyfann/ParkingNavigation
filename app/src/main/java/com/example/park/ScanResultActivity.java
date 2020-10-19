package com.example.park;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.park.findroad.Map;
import com.example.park.findroad.New;
import com.example.park.fragment.InputMap;
import com.example.park.fragment.Parking;
import com.example.park.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class ScanResultActivity extends AppCompatActivity{

    TextView tv_scanResult;
    List<InputMap> inputMaps;
    public String scan_time;
    char selection;
    New anew;
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
        HashMap<String,Integer> IdMap = new HashMap<>();
        BmobQuery<Parking> query = new BmobQuery<Parking>();
        query.addWhereLessThan("Values",5);
        query.setLimit(500);
        query.order("createdAt");
        query.setCachePolicy(BmobQuery.CachePolicy.IGNORE_CACHE);
        query.findObjects(new FindListener<Parking>() {
            @Override
            public void done(List<Parking> object, BmobException e) {
                if(e==null){
                    Toast.makeText(ScanResultActivity.this,"缓存成功，共"+object.size()+"条数据",Toast.LENGTH_SHORT).show();
                    //List list=new ArrayList();
                    String s="";
                    inputMaps = new ArrayList<>();
                    InputMap inputMap1 = null;
                    for(int i=0;i<object.size();i++){
                        //text_print.setText("X坐标："+object.get(i).getCo_X()+"Y坐标："+object.get(i).getCo_Y());
                        //list.add("X坐标："+object.get(i).getCo_X()+"Y坐标："+object.get(i).getCo_Y());
                        // if(object.get(i).getEmptyIF()==Boolean.TRUE)
                        Integer x = object.get(i).getCo_X();
                        Integer y = object.get(i).getCo_Y();
                        inputMap1 = new InputMap(x,y,object.get(i).getValues());
                        String idcode = Integer.toString(x).concat("+").concat(Integer.toString(y));
                        inputMaps.add(inputMap1);
                        //s=s.concat("X坐标："+x+" Y坐标："+y+object.get(i).getValues()+"\n");
                    }
                    selection='A';
                    New.PrintParkingPath(inputMaps,selection);
                    //anew.PrintParkingPath(inputMaps,selection);
                    //tv_scanResult.setText(s);

                }else{
                    Toast.makeText(ScanResultActivity.this,"缓存失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}