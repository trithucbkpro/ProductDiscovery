package com.teko.proddiscovery.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.teko.proddiscovery.R;


public class ProgressDialogUtil extends Dialog {

    Context mContext;
    public ProgressDialogUtil(Context context) {
        super(context);
        this.mContext = context;
    }

    public ProgressDialogUtil(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }


    public ProgressDialogUtil show(
            boolean cancelable) {

        ProgressDialogUtil dialog = new ProgressDialogUtil(mContext, R.style.CustomProgress);

        dialog.setTitle("");
        dialog.setContentView(R.layout.prg_dialog);
        dialog.setCancelable(cancelable);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);

        dialog.show();
        return dialog;
    }

}
