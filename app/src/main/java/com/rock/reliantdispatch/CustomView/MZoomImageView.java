package com.rock.reliantdispatch.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.otaliastudios.zoom.ZoomImageView;

import butterknife.OnClick;

public class MZoomImageView extends android.support.v7.widget.AppCompatImageView {
    private static final int CLICK_TIME_SPAN = 100;
    long startDownTimeSpan;
    IClickZoomImageView mClickListener;
    float posX, posY;

    public void setClickZoomImageView(IClickZoomImageView handler){
        mClickListener = handler;
    }

    public MZoomImageView(Context context) {
        super(context);
        initClick();
    }

    public MZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClick();
    }

    public MZoomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initClick();
    }

    void initClick(){
        setOnClickListener(v -> {
            if(mClickListener != null)
                mClickListener.OnAddDamage(posX,posY);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            posX = event.getX();
            posY = event.getY();
            startDownTimeSpan = System.currentTimeMillis();
        }

        return super.onTouchEvent(event);
    }

    public interface IClickZoomImageView{
        void OnAddDamage(float scrPosX, float scrPosY);
    }
}
