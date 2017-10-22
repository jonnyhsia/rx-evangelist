package com.citytogo.jonnyhsia.rxevangelist.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by JonnyHsia on 17/10/22.
 */
public class ToastUtil {
    private static Toast sToast;

    public static void showToast(Context context, String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (null == context) {
            return;
        }

        sToast.cancel();
        sToast = null;
        sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        sToast.show();
    }
}
