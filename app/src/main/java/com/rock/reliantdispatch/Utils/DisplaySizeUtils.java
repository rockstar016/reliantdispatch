package com.rock.reliantdispatch.Utils;

import android.content.res.Resources;

/**
 * Created by rock on 3/26/18.
 */

public class DisplaySizeUtils{
        public static int getScreenWidth() {
            return Resources.getSystem().getDisplayMetrics().widthPixels;
        }

        public static int getScreenHeight() {
            return Resources.getSystem().getDisplayMetrics().heightPixels;
        }
}
