package com.aizen.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aizen.common.R;
import com.aizen.utils.JudgeNotchUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;

import androidx.annotation.Nullable;

/**
 * Created by ld on 2018/11/23.
 *
 * @author ld
 * @date 2018/11/23
 * 描    述：
 */
public class CloseToolBar extends LinearLayout {

    private int DEFAULT_HEIGHT = 64;
    private int inHeigt;
    private int DEFAULT_BOTTOM_HEIGHT = 44;

    /**
     *     上下分离layout
     */
    private RelativeLayout mBottomLayout;
    /**
     * Title
     */
    private int barTextColor = 0x333333;
    private int barTextSize = 18;
    private TextView mBarText;
    /**
     * 关闭Icon
     */
    private int closeIcon = R.mipmap.close;
    private View mCloseView;
    private FinishBackListener mListener;

    private int defaultColor = 0xFFFFFF;

    private View defaultEleView = null;
    private float defaultEleSize = 1.0f;
    private int defaultEleColor = 0xffffff;


    public CloseToolBar(Context context) {
        super(context);
    }

    public CloseToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CloseToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setGravity(Gravity.BOTTOM);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CloseToolBar);
        barTextColor = array.getColor(R.styleable.CloseToolBar_barTextColor,barTextColor);
        barTextSize = array.getDimensionPixelSize(R.styleable.CloseToolBar_barTextSize,barTextSize);
        closeIcon = array.getResourceId(R.styleable.CloseToolBar_closeImg,closeIcon);
        inHeigt = array.getDimensionPixelSize(R.styleable.CloseToolBar_barHeight,inHeigt);
        defaultColor = array.getColor(R.styleable.CloseToolBar_barColor,defaultColor);
        defaultEleSize = array.getDimension(R.styleable.CloseToolBar_eleSize,defaultEleSize);
        defaultEleColor = array.getColor(R.styleable.CloseToolBar_eleColor,defaultEleColor);
        array.recycle();
        if(JudgeNotchUtils.hasNotchScreen((Activity) context)){
            if(inHeigt < DEFAULT_HEIGHT){
                DEFAULT_HEIGHT = 70;
            }else {
                DEFAULT_HEIGHT = inHeigt;
            }
        }else {
            DEFAULT_HEIGHT = inHeigt;
        }
        LayoutParams rootP = new LayoutParams(LayoutParams.MATCH_PARENT,SizeUtils.dp2px(DEFAULT_HEIGHT));
        setLayoutParams(rootP);
        setBackgroundColor(defaultColor);
        initToolBar(context);
        initBack();
    }

    /**
     * 初始化Click
     */
    private void initBack() {
        if(null != mCloseView){
            mCloseView.setOnClickListener(v -> {
                if(null != mListener){
                    mListener.back();
                }
            });
        }
    }

    /**
     * 绘制Toolbar
     * @param context
     */
    private void initToolBar(Context context) {
        mBottomLayout = new RelativeLayout(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,SizeUtils.dp2px(DEFAULT_BOTTOM_HEIGHT));
        mBottomLayout.setLayoutParams(params);

        mBarText = new TextView(context);
        RelativeLayout.LayoutParams barParams = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        barParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mBarText.setLayoutParams(barParams);
        mBarText.setTextSize(SizeUtils.px2sp(barTextSize));
        mBarText.setTextColor(barTextColor);
        mBottomLayout.addView(mBarText);

        mCloseView = inflate(context,R.layout.widget_close_view,null);
        mCloseView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView close = mCloseView.findViewById(R.id.close);
        close.setImageResource(closeIcon);
        LayoutParams closeP = new LayoutParams(SizeUtils.dp2px(18),SizeUtils.dp2px(18));
        closeP.gravity = Gravity.CENTER;
        closeP.leftMargin = SizeUtils.dp2px(15);
        closeP.rightMargin = SizeUtils.dp2px(15);
        close.setLayoutParams(closeP);
        mBottomLayout.addView(mCloseView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RelativeLayout.LayoutParams eleP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SizeUtils.dp2px(1));
            eleP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            defaultEleView = new View(context);
            defaultEleView.setBackgroundColor(defaultEleColor);
            defaultEleView.setLayoutParams(eleP);
            defaultEleView.setElevation(defaultEleSize);
            mBottomLayout.addView(defaultEleView);
        }
        addView(mBottomLayout);


    }

    /**
     * 绑定监听
     * @param listener
     */
    public void setFinishListener(FinishBackListener listener){
        mListener = listener;
    }
    /**
     * 设置Title
     * @param title String
     */
    public void setTitle(String title){
        if(null != mBarText && !StringUtils.isEmpty(title)){
            mBarText.setText(title);
        }
    }

    /**
     * 设置TitleColor
     * @param color HexColor
     */
    public void setTitleColor(int color){
        if(null != mBarText){
            mBarText.setTextColor(color);
        }
    }
    /**
     * 返回
     */
    public interface FinishBackListener {
        /**
         * close back
         */
        void back();
    }

    /**
     * 换成指定高度
     * @param widthMeasureSpec width
     * @param heightMeasureSpec height
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,SizeUtils.dp2px(DEFAULT_HEIGHT));
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            //如果没有指定大小，就设置为默认大小
            case MeasureSpec.UNSPECIFIED: {
                mySize = defaultSize;
                break;
            }
            //如果测量模式是最大取值为size
            case MeasureSpec.AT_MOST: {
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            //如果是固定的大小，那就不要去改变它
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
            default:
        }
        return mySize;
    }
}
