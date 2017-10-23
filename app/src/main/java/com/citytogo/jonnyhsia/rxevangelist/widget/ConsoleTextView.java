package com.citytogo.jonnyhsia.rxevangelist.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import com.citytogo.jonnyhsia.rxevangelist.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JonnyHsia on 17/10/22.
 * 使用 RxJava 实现的简单的带打字机效果的 Text View
 */
public class ConsoleTextView extends AppCompatTextView {

    private static final long MIN_PERIOD = 16;

    /**
     * 打字动画的时长
     */
    private long mDuration;

    /**
     * 是否使用"动画"
     */
    private boolean isAnimate;

    /**
     * 是否输出其他日志
     */
    private boolean isDebug = false;

    /**
     * Disposable
     */
    private Disposable mDisposable;

    /**
     * 是否保持显示最底部
     */
    private boolean isStayBottom = true;

    public ConsoleTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ConsoleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ConsoleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ConsoleTextView);
        mDuration = typedArray.getInt(R.styleable.ConsoleTextView_duration, 500);
        isAnimate = typedArray.getBoolean(R.styleable.ConsoleTextView_animate, true);
        isStayBottom = typedArray.getBoolean(R.styleable.ConsoleTextView_stayBottom, true);

        typedArray.recycle();
    }

    /**
     * 往 ConsoleTextView 添加 Log
     * 根据 isAnimate 的值来判断默认是否使用动画
     */
    public void appendLog(CharSequence log) {
        appendLog(log, isAnimate);
    }

    /**
     * 往 ConsoleTextView 添加 Log
     *
     * @param log       日志字符序列
     * @param isAnimate 是否开启动画
     */
    public void appendLog(CharSequence log, boolean isAnimate) {
        if (isAnimate) {
            appendLogWithAnimation(log, isDebug);
        } else {
            append("\n" + log);
        }
    }

    public void clearLog() {
        clearLog("");
    }

    public void clearLog(@NonNull String initialLog) {
        setText(initialLog);
    }

    /**
     * 使用 RxJava 实现打字机效果
     */
    private void appendLogWithAnimation(final CharSequence log, final boolean isDebug) {
        final int length = log.length();
        long period = Math.max(mDuration / length, MIN_PERIOD);
        final int[] pos = {0};

        Observable.interval(period, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        if (isDebug) {
                            append("\nsubscribe.");
                        }
                    }

                    @Override
                    public void onNext(Long aLong) {
                        // FIXME: 17/10/22 线程不安全
                        CharSequence charSequence;
                        // 一开始换行
                        if (pos[0] == 0) {
                            charSequence = "\n" + log.charAt(0);
                        } else {
                            charSequence = "" + log.charAt(pos[0]);
                        }
                        append(charSequence);
                        pos[0]++;
                        // 输出完毕后调用 onComplete()
                        if (pos[0] == length) {
                            onComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (isDebug) {
                            append(String.format("\n%s", e.getMessage()));
                        }
                    }

                    @Override
                    public void onComplete() {
                        mDisposable.dispose();
                        if (isDebug) {
                            append("\ncompleted!");
                        }
                    }
                });
//        Observable.create(new ObservableOnSubscribe<CharSequence>() {
//            @Override
//            public void subscribe(ObservableEmitter<CharSequence> emitter) throws Exception {
//                for (int i = 0; i < length; i++) {
//                    emitter.onNext(log.subSequence(i, i + 1));
//                }
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CharSequence>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        d.dispose();
//                    }
//
//                    @Override
//                    public void onNext(CharSequence charSequence) {
//                        append(charSequence);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        append("\n");
//                    }
//                });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public boolean isAnimate() {
        return isAnimate;
    }

    public void setAnimate(boolean animate) {
        isAnimate = animate;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public boolean isStayBottom() {
        return isStayBottom;
    }

    public void setStayBottom(boolean stayBottom) {
        isStayBottom = stayBottom;
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        super.append(text, start, end);

        if (!isStayBottom) {
            return;
        }

        if (getParent() instanceof ScrollView) {
            final ScrollView scrollView = (ScrollView) getParent();
            // 不能直接调用方法 scroll 到底部
            // 使用 View.post 保证任务在视图操作中按序执行
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }
}
