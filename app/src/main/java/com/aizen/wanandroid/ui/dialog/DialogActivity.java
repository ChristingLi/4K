package com.aizen.wanandroid.ui.dialog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.aizen.common.BaseDialog;
import com.aizen.common.BaseDialogFragment;
import com.aizen.common.mvpbase.BaseActivity;
import com.aizen.wanandroid.R;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * Created by ld on 2018/12/27.
 *
 * @author ld
 * @date 2018/12/27
 * 描    述：
 */
public class DialogActivity extends BaseActivity {
    @BindView(R.id.custom)
    Button mCustom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCustom.setOnClickListener(v->{
            new BaseDialogFragment.Builder(this)
                    .setContentView(R.layout.dialog_custom)
                    .setAnimStyle(BaseDialog.AnimStyle.SCALE)
                    .setOnClickListener(R.id.btn_dialog_custom_ok,
                            (BaseDialog.OnClickListener <ImageView>) (dialog, view) ->
                            dialog.dismiss())
                    .show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }
}
