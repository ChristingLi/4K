package com.aizen.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aizen.common.R;
import com.aizen.utils.LoggerUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.lang.reflect.Field;

/**
 * Created by ld on 2018/11/30.
 *
 * @author ld
 * @date 2018/11/30
 * 描    述：废弃
 */
public class DisuseClearEditText extends RelativeLayout {
    private boolean isPassWord = false;

    private int defaultSize = 18;

    private ImageView clearView;
    private int clearId = 1000;

    private ImageView eyeView;
    private int defaultClearRes = R.mipmap.delete;
    private int defaultEyeRes = R.mipmap.eye;

    private EditText mInputEdit;
    private String defaultHint = "请输入内容";
    private int defaultLength = 15;
    private int defaultTextSize = 15;
    private int defaultTextColor = 0x333333;


    public DisuseClearEditText(Context context) {
        super(context);
        throw new IllegalStateException("该View已经废弃,请勿使用");
    }

    public DisuseClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs);
        throw new IllegalStateException("该View已经废弃,请勿使用");
    }

    public DisuseClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context,attrs);
        throw new IllegalStateException("该View已经废弃,请勿使用");
    }

    private void initLayout(Context context,AttributeSet attr){
        TypedArray array = context.obtainStyledAttributes(attr,R.styleable.DisuseClearEditText);
        isPassWord = array.getBoolean(R.styleable.DisuseClearEditText_isPassWord,isPassWord);
        defaultClearRes = array.getResourceId(R.styleable.DisuseClearEditText_clearRes,defaultClearRes);
        defaultEyeRes = array.getResourceId(R.styleable.DisuseClearEditText_showRes,defaultEyeRes);
        defaultHint = array.getString(R.styleable.DisuseClearEditText_editHint);
        defaultLength = array.getInt(R.styleable.DisuseClearEditText_editLength,defaultLength);
        defaultTextSize = array.getDimensionPixelSize(R.styleable.DisuseClearEditText_editTextSize,defaultTextSize);
        defaultTextColor = array.getColor(R.styleable.DisuseClearEditText_editTextColor,defaultTextColor);
        defaultSize = array.getDimensionPixelSize(R.styleable.DisuseClearEditText_iconSize,defaultSize);

        array.recycle();

        initEditView();


    }

    /**
     *
     * @param isPassWord pass-word -> 显示eye view
     */
    private void initResView(boolean isPassWord){
        clearView = new ImageView(getContext());
        clearView.setId(clearId);
        LayoutParams cp = new LayoutParams(defaultSize,defaultSize);
        cp.addRule(RelativeLayout.CENTER_VERTICAL);
        cp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        cp.rightMargin = SizeUtils.dp2px(15);
        clearView.setImageResource(defaultClearRes);
        addView(clearView);
    }


    //<editor-fold desc="Edit Related">
    /**
     * 初始化输入栏
     */
    private void initEditView() {
        mInputEdit = new EditText(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.rightMargin = 0;
        params.leftMargin = 0;
        mInputEdit.setLayoutParams(params);
        mInputEdit.setLines(1);
        mInputEdit.setHint(defaultHint);
        mInputEdit.setTextColor(defaultTextColor);
        mInputEdit.setTextSize(defaultTextSize);
        mInputEdit.setCursorVisible(true);
        try {
            Field cursor = mInputEdit.getClass().getDeclaredField("mCursorDrawableRes");
            cursor.setAccessible(true);
            cursor.set(this,null);
        }catch (Exception e){
            LoggerUtils.Logger("Ex",e.getMessage());
            ToastUtils.showShort(e.getMessage());
        }
        mInputEdit.setBackground(null);
         addView(mInputEdit);
    }

    /**
     * 设置text
     *
     * @param str input
     */
    public void setText(String str) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        if (mInputEdit == null) {
            return;
        }
        mInputEdit.setText(str);
    }

    /**
     * 获取文本
     *
     * @return str
     */
    public String getText() {
        if (null != mInputEdit) {
            return mInputEdit.getText().toString();
        } else {
            return "";
        }
    }

    /**
     * 返回操作对象
     *
     * @return edit
     */
    public EditText getEditText() {
        return mInputEdit;
    }
    //</editor-fold>
}
