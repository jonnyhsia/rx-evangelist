package com.citytogo.jonnyhsia.rxevangelist.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.citytogo.jonnyhsia.rxevangelist.R;

/**
 * Created by JonnyHsia on 17/10/21.
 * 自定义长宽比例的 ImageView
 */
public class AspectRatioImageView extends AppCompatImageView {

    private float mAspectRatio = 1f;

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
        mAspectRatio = array.getFloat(R.styleable.AspectRatioImageView_aspectRatio, 1f);
        array.recycle();
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width / mAspectRatio);

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
