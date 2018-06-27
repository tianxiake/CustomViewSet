package com.snail.customviewset;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;

import java.lang.reflect.Method;

public class TouchEventTestActivity extends AppCompatActivity {

    private static final String LYJ_TAG = "LYJ_TouchEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        int hasVirtualKey = getHasVirtualKey();
        Log.d(LYJ_TAG, "virtual Key:" + hasVirtualKey);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        Log.d(LYJ_TAG, "heightPixels:" + heightPixels);

//        PackageManager packageManager = getPackageManager();
//        List<ApplicationInfo> listAppcations = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
//        for (ApplicationInfo listAppcation : listAppcations) {
//            String name = listAppcation.name;
//            String packageName = listAppcation.packageName;
//            Log.d(LYJ_TAG, "name:" + name + ",packageName:" + packageName);
//        }

//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(LYJ_TAG, "post delay");
////                Intent intent = new Intent(Intent.ACTION_MAIN,null);
////                intent.addCategory(Intent.CATEGORY_HOME);
////                startActivity(intent);
//                Thread.dumpStack();
//
//            }
//        }, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LYJ_TAG, "onTouchEvent Action=" + event.getAction());
        return super.onTouchEvent(event);
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    private int getHasVirtualKey() {
        int dpi = 0;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }
}
