package com.example.park.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.example.park.entity.Menu;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public DataUtil() {
    }

    public static List<ImageView> getHeaderAddInfo(Context context, int[] icons) {
        List<ImageView> datas = new ArrayList();

        for(int i = 0; i < icons.length; ++i) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i]);
            datas.add(icon);
        }

        return datas;
    }

    public static List<Menu> getMainMenus(int[] icons, String[] names) {
        List<Menu> menus = new ArrayList();

        for(int i = 0; i < icons.length; ++i) {
            Menu menu = new Menu(icons[i], names[i]);
            menus.add(menu);
        }

        return menus;
    }
}

