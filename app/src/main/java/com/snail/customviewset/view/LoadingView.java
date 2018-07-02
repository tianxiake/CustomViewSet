package com.snail.customviewset.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.snail.customviewset.R;
import com.snail.customviewset.util.DensityUtils;

/**
 * @author yongjie on 2018/6/27.
 */
public class LoadingView extends LinearLayout {

    private ShapeView shapeView;
    private ImageView shadowView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.activity_load_view, null);
        shadowView = inflate.findViewById(R.id.sv_shape);
        shadowView = inflate.findViewById(R.id.iv_shadow);

//        startPullDownAnimation();
    }

    private void startPullDownAnimation() {
        //下落动画
        ObjectAnimator translationY = ObjectAnimator.ofFloat(shapeView, "TranslationY", DensityUtils.dp2px(getContext(), 50), 0);
        translationY.start();
    }

}
