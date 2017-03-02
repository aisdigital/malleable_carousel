package br.com.aistech.carousel.animations;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CarouselAnimation extends RelativeLayout {

    public final static Float SMALL_SCALE = 0.35f;

    private Float scale = SMALL_SCALE;

    public CarouselAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarouselAnimation(Context context) {
        super(context);
    }

    public void setScaleBoth(float scale) {
        this.scale = scale;
        this.invalidate();    // If you want to see the scale every time you set
        // scale you need to have this line here,
        // invalidate() function will call onDraw(Canvas)
        // to redraw the view for you
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // The main mechanism to display scale animation, you can customize it
        // as your needs
        int w = this.getWidth();
        int h = this.getHeight();
        canvas.scale(scale, scale, w / 2, h / 2);

        super.onDraw(canvas);
    }
}
