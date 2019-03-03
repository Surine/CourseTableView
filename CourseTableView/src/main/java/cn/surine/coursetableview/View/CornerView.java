package cn.surine.coursetableview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Surine on 2019/2/26.
 * 参考：https://blog.csdn.net/wei1583812/article/details/53130637
 */

class CornerView extends FrameLayout {

    public CornerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(radius);
    }

    public CornerView(Context context, float cornerRadius) {
        super(context);
        init(cornerRadius);
    }

    private final RectF roundRect = new RectF();
    private float radius = 5;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private void init(float cornerRadius) {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        float density = getResources().getDisplayMetrics().density;
        radius = cornerRadius * density;
    }


    public void setCorner(float adius) {
        radius = adius;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, radius, radius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

}
