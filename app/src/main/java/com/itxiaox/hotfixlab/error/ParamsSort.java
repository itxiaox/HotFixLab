package com.itxiaox.hotfixlab.error;

import android.content.Context;
import android.widget.Toast;

public class ParamsSort {

    public static void match(Context context){

        int a = 10;
        int b = 0;

        Toast.makeText(context, "match --" + a/b, Toast.LENGTH_SHORT).show();
    }
}
