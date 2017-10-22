package com.citytogo.jonnyhsia.rxevangelist.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.citytogo.jonnyhsia.rxevangelist.R

/**
 * Created by JonnyHsia on 17/6/28.
 * 醒目标题的自定义 Toolbar (Kotlin)
 */
class HighlightToolbar : FrameLayout {

    var title: String? = null
        set(value) {
            field = value
            mTvTitle?.text = value
        }
    var subTitle: String? = null
        set(value) {
            field = value
            mTvSubTitle?.text = value
        }
    private var resource: Int = -1
        set(value) {
            if (value != -1) {
                field = value
                Glide.with(this)
                        .load(value)
                        .apply(RequestOptions().circleCrop())
                        .into(mImgButton)
            }
        }

    private var mTvTitle: TextView? = null
    private var mTvSubTitle: TextView? = null
    private var mImgButton: ImageView? = null

    var mButtonClick: ((View) -> Unit)? = null

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_hightlight_toolbar, this, true)
        mTvTitle = findViewById(R.id.tvTitle)
        mTvSubTitle = findViewById(R.id.tvSubTitle)
        mImgButton = findViewById(R.id.imgButton)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HighlightToolbar)
        // 获取自定义控件的属性
        title = typedArray.getString(R.styleable.HighlightToolbar_highlightTitle)
        subTitle = typedArray.getString(R.styleable.HighlightToolbar_highlightSubTitle)
        resource = typedArray.getResourceId(R.styleable.HighlightToolbar_icon, -1)
        typedArray.recycle()

        if (TextUtils.isEmpty(subTitle)) {
            mTvSubTitle?.visibility = View.GONE
        }
        mImgButton?.setOnClickListener {
            mButtonClick?.invoke(it)
        }
    }

}