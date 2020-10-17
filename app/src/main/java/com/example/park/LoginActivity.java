package com.example.park;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.park.fragment.Mainfragment;
import com.example.park.fragment.Myfragment;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_name,edit_password;
    private Button btn_insert,btn_login;

    public LoginActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        Bmob.initialize(LoginActivity.this,"8803b2c419834daa0f57d8c26aea6c28");
        //绑定控件
        initView();
        //控件方法
        btnRegister();
        btnLogin();
    }

    private void initView() {
        edit_name=findViewById(R.id.edt_username);
        edit_password=findViewById(R.id.edt_pwd);
        btn_insert=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login1);
    }

    //Register控件方法
    private void btnRegister() {
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String person_name=edit_name.getText().toString();
                String person_password=edit_password.getText().toString();
                if(person_name.length()==0){
                    Toast.makeText(LoginActivity.this,"用户名不得为空",Toast.LENGTH_LONG).show();
                }
                else if(person_password.length()<6) {
                    Toast.makeText(LoginActivity.this,"密码不得少于5个字符",Toast.LENGTH_LONG).show();
                }
                else{
                    Person person=new Person();
                    person.setName(person_name);
                    person.setPassword(person_password);
                    person.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    edit_name.setText("");
                    edit_password.setText("");
                }

            }
        });
    }
    private void btnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String person_name=edit_name.getText().toString();
                String person_password=edit_password.getText().toString();
                if(person_name.length()==0){
                    Toast.makeText(LoginActivity.this,"用户名不得为空",Toast.LENGTH_LONG).show();
                }
                else{
                    BmobQuery<Person> personBmobQuery=new BmobQuery<>();
                    personBmobQuery.addWhereEqualTo("name",person_name);
                    personBmobQuery.findObjects(new FindListener<Person>() {
                        @Override
                        public void done(List<Person> object, BmobException e) {
                            if(e==null) {
                                int flag = 0;
                                for (int i = 0; i < object.size(); i++) {
                                    if (object.get(i).getPassword().equals(person_password)) {
                                        Log.d("LoginActivity", object.get(i).getPassword() + person_password);
                                        flag = 1;
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                        Intent intentlogin = new Intent(LoginActivity.this, MainActivity.class);
                                        //sharedpreference存储用户名等信息，为覆盖模式
                                        SharedPreferences.Editor editor = getSharedPreferences("user", 0).edit();
                                        editor.putString("name", person_name);
                                        editor.commit();
                                        startActivity(intentlogin);
                                        //LoginActivity.this.finish();
                                        break;
                                    }
                                }
                                if (flag == 0) {
                                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                                Log.d("LoginActivity", "原因："+e);
                            }
                        }

                    });
                }

            }
        });
    }
    public void back0(View v3) {
        Intent login1 = new Intent(this, Myfragment.class);
        this.startActivity(login1);
    }
}
