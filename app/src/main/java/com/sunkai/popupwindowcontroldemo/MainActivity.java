package com.sunkai.popupwindowcontroldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText x;
    private EditText y;
    private EditText gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (EditText) findViewById(R.id.x);
        y = (EditText) findViewById(R.id.y);
        gravity = (EditText) findViewById(R.id.gravity);
        findViewById(R.id.popup_window_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(1, v);
            }
        });

        findViewById(R.id.popup_window_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(2, v);
            }
        });
        findViewById(R.id.popup_window_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(3, v);
            }
        });
        findViewById(R.id.popup_window_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(4, v);
            }
        });
        findViewById(R.id.popup_window_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(5, v);
            }
        });


    }

    private void showPopupWindow(int type, View v) {

        if (null == v.getTag()) {
            v.setTag(true);
        }

        boolean tag = (boolean) v.getTag();
        if (tag) {
            int posx;
            int poxy;
            int posGravity;

            try {
                posx = Integer.parseInt(x.getText().toString());
                poxy = Integer.parseInt(y.getText().toString());
                posGravity = Integer.parseInt(gravity.getText().toString());
            } catch (Exception e) {
                posx = 0;
                poxy = 0;
                posGravity = 0;
            }

            switch (posGravity) {
                case 0:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.NO_GRAVITY, type);
                    break;
                case 1:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.LEFT, type);
                    break;
                case 2:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.TOP, type);
                    break;
                case 3:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.RIGHT, type);
                    break;
                case 4:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.BOTTOM, type);
                    break;
                case 5:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.CENTER, type);
                    break;
                case 6:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.LEFT | Gravity.TOP, type);
                    break;
                case 7:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.LEFT | Gravity.BOTTOM, type);
                    break;
                case 8:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.RIGHT | Gravity.TOP, type);
                    break;
                case 9:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.RIGHT | Gravity.BOTTOM, type);
                    break;
                case 10:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.RIGHT | Gravity.LEFT, type);
                    break;
                case 11:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.TOP | Gravity.BOTTOM, type);
                    break;
                default:
                    PopupWindowUtil.showWifiMangerPopupWindow(v, getApplicationContext(), posx, poxy, Gravity.NO_GRAVITY, type);
                    break;
            }

        }

        v.setTag(!tag);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
