package com.rock.reliantdispatch.Utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by RockZeus on 3/23/18.
 */

public class ScrollDisableLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public ScrollDisableLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}