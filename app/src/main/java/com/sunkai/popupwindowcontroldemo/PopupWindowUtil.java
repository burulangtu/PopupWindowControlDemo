package com.sunkai.popupwindowcontroldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by sunkai on 2016/11/28.
 */

public class PopupWindowUtil {

    public static void showWifiMangerPopupWindow(final View view, final Context context, int x, int y, int gravity, int type) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.my_popup_window, null);
        final PopupWindow popupWindow = createPopupWindow(context, view, popupView);

        if (type == 1) {
            popupWindow.showAtLocation(view, gravity, x, y);
        } else if (type == 2) {
            popupWindow.showAsDropDown(view);
        } else if (type == 3) {
            popupWindow.showAsDropDown(view, x, y);
        } else if (type == 4) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                popupWindow.showAsDropDown(view, x, y, gravity);
            }
        } else if (type == 5) {
            int[] windowPos = calculatePopWindowPos(view, context);
            popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, windowPos[0], windowPos[1]);
        }
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView 呼出window的view
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, Context context) {
        final int windowPos[] = new int[2];

        final int anchorLoc[] = new int[2];// 获取锚点View在屏幕上的左上角坐标位置


        anchorView.getLocationOnScreen(anchorLoc);

        final int anchorHeight = anchorView.getHeight();
        final int anchorViewWidth = anchorView.getWidth();
        final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        windowPos[0] = anchorLoc[0] + anchorViewWidth;
        windowPos[1] = anchorLoc[1] + anchorHeight - 10;
        return windowPos;
    }

    @NonNull
    private static PopupWindow createPopupWindow(Context context, final View view, View popupView) {
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setTag(true);
            }
        });
        return popupWindow;
    }

}
