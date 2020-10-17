package com.example.park.fragment;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.park.CaptureAct;
import com.example.park.R;
import com.example.park.ScanResultActivity;
import com.example.park.adapter.MainHeaderAdAdpter;
import com.example.park.adapter.MainMenuAdapter;
import com.example.park.util.DataUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


//主界面视图
public class Mainfragment extends Fragment{
    protected int [] icons={R.mipmap.ad,R.mipmap.firstpage};
    protected int [] menuIons={R.mipmap.parking,R.mipmap.car,R.mipmap.mappin,
            R.mipmap.manager,R.mipmap.moneybag,R.mipmap.search2,
            R.mipmap.message2,R.mipmap.comments};
    protected ViewPager mVPagerHeaderAd;        //广告头
    protected RecyclerView mRecycleViewMenu;    //主菜单
    protected LinearLayout Ll_scanner;
    //protected Button btn_Scanner;
    //public static String Park1 = "ParkingMap1";
    String [] menus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menus=this.getActivity().getResources().getStringArray(R.array.main_menu);
        mVPagerHeaderAd=(ViewPager) getView().findViewById(R.id.vpager_main_head_ad);
        mRecycleViewMenu=(RecyclerView) getView().findViewById(R.id.recycleview_menu);
        //btn_Scanner=getActivity().findViewById(R.id.btn_qrocde);
        Ll_scanner=getActivity().findViewById(R.id.Ll_scanner);
        /*
        tn_Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });

         */
        Ll_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
        MainHeaderAdAdpter adapter=new MainHeaderAdAdpter(getActivity(), DataUtil.getHeaderAddInfo(getActivity(),icons));
        mVPagerHeaderAd.setAdapter(adapter);

        //菜单
        //布局样式
        mRecycleViewMenu.setLayoutManager(new GridLayoutManager(getActivity(),4));
        MainMenuAdapter mainMenuAdapter=new MainMenuAdapter(getActivity(),DataUtil.getMainMenus(menuIons,menus));
        mRecycleViewMenu.setAdapter(mainMenuAdapter);
    }
    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Code");
        intentIntegrator.initiateScan();
    }
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null) {
            if (result.getContents()!=null) {
                if(result.getContents().equals(Park1)){

                    Intent intent = new Intent(getActivity().getApplicationContext(), ScanResultActivity.class);
                    startActivity(intent);


                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(result.getContents());
                    builder.setTitle("Scanning Result");
                    builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            scanCode();
                        }
                    }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }else {
                Toast.makeText(getContext(),"No Result!",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(),"No content",Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

     */


}

