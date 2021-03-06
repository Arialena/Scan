package com.example.administrator.scan;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/3/22.
 */

public class SimpleCaptureActivity extends CaptureActivity {

    protected Activity mActivity = this;
    private AlertDialog mDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handleResult(String resultString) {
        if (TextUtils.isEmpty(resultString)) {
//            Toast.makeText(mActivity, io.github.xudaojie.qrcodelib.R.string.scan_failed, Toast.LENGTH_SHORT).show();
            restartPreview();
        } else {
            if (mDialog == null) {

                mDialog = new AlertDialog.Builder(mActivity)
                        .setMessage(resultString)
                        .setNegativeButton("取消", null)
                        .create();
                mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        restartPreview();
                    }
                });

            }
            if (!mDialog.isShowing()) {
                mDialog.setMessage(resultString);
                mDialog.show();
            }
        }
    }
}
