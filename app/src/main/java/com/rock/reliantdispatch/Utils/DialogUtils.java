package com.rock.reliantdispatch.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by Rock on 2016.11.09.
 */

public class DialogUtils {
    public static void showOkDialog(Context context, String title, String content) {
        new AlertDialog.Builder(context)
                       .setTitle(title)
                       .setMessage(content)
                       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.dismiss();
                           }
                       })
                        .create()
                        .show();
    }

    public static void showOkDialogWithListener(Context context, String title, String content, DialogInterface.OnClickListener okListener)
    {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("Ok", okListener)
                .create()
                .show();
    }
}
