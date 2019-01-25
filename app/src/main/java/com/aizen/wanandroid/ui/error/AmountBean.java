package com.aizen.wanandroid.ui.error;

import com.blankj.utilcode.util.StringUtils;

import java.io.Serializable;

/**
 * Created by ld on 2018/8/29.
 *
 * @author ld
 * @date 2018/8/29
 * 描    述：
 */
public class AmountBean implements Serializable {
    private String mAmount;
    private double mGiftAmount;
    private boolean isChecked;
    private String amountDesc;
    private String giftDesc;

    public AmountBean(String amount, double giftAmount, boolean isChecked) {
        mAmount = amount;
        mGiftAmount = giftAmount;
        this.isChecked = isChecked;
        amountDesc = "充" + mAmount +"元";
        giftDesc = "赠"+ subZeroAndDot(mGiftAmount+"") + "钻石";
    }


    public String getAmountDesc() {
        return amountDesc;
    }

    public void setAmountDesc(String amountDesc) {
        this.amountDesc = amountDesc;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public double getGiftAmount() {
        return mGiftAmount;
    }

    public void setGiftAmount(int giftAmount) {
        mGiftAmount = giftAmount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public static String subZeroAndDot(String s) {
        if (StringUtils.isEmpty(s)) {
            return "";
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
