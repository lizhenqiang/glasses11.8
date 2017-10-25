package com.remote.glasses.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.remote.glasses.R;

/**
 * Created by Feics on 2016/7/20.
 */
public class NumberPickerDialog  extends Dialog {

//    private static NumberPickerDialog dialog;    //声明静态的单例对象的变量
//    public static NumberPickerDialog getSingle(Context context) {    //外部通过此方法可以获取对象
//        if (dialog == null) {
//            synchronized (VisionNumUtil.class) {   //保证了同一时间只能只能有一个对象访问此同步块
//                if (dialog == null) {
//                    dialog = new NumberPickerDialog(context);
//                }
//            }
//        }
//        return dialog;   //返回创建好的对象
//    }

    protected NumberPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public NumberPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public NumberPickerDialog(Context context) {
        super(context, R.style.list_dialog);
        this.setContentView(R.layout.numberpicker);
        setSizeAndPosition();//固定到默认位置  如果需要 重写此方法

    }


    /**
     * 默认设置dialog大小与位置
     */
    public void setSizeAndPosition() {
        setSizeAndPosition(0, 0, 0, 0, 10f, Gravity.CENTER);
    }

    public void setSizeAndPosition(int width, int height, int x, int y, float aplha, int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams layout = window.getAttributes();
        window.setGravity(gravity);
        if (width == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = (int) ((dm.widthPixels) * 0.9);// 宽度height =
        }
        layout.width = width;// 宽度
        if (height > 0) {
            layout.height = height;// 高度
        }
        if (x > 0) {
            layout.x = x;// x坐标
        }
        if (y > 0) {
            layout.y = y;// y坐标
        }
        if (aplha > 0) {
            layout.alpha = 10f; // 透明度
        }
        window.setAttributes(layout);
    }

}
