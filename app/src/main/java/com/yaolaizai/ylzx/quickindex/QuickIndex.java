package com.yaolaizai.ylzx.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ylzx on 2017/6/21.
 */
public class QuickIndex extends View {


    private Paint paint;

    private String[] words = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


    public QuickIndex(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
    }


    private float viewWidth,viewHeight,itemWidth,itemHeight;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = itemWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        itemHeight = viewHeight / words.length;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_MOVE:

                float eventY = event.getY();
                int index = (int) (eventY / itemHeight);
                if(currentIndex != index){//如果move以后，仍然在当前的item上，则不需要重绘
                    currentIndex = index;
                    invalidate();
                    //当index发生变化时，调用onIndexChanged()
                    if(onIndexChangedListener != null){

                        onIndexChangedListener.onIndexChanged(words[index]);

                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                currentIndex = -1;
                invalidate();
                if(onIndexChangedListener != null){
                    onIndexChangedListener.onUp();
                }


                break;
        }

        return true;
    }

    int currentIndex = -1;//指明当前选中的字符对应的index
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < words.length; i++) {

            //得到包裹字符的边框的宽高
            Rect bounds = new Rect();
            paint.getTextBounds(words[i],0,words[i].length(),bounds);

            //得到字符左下顶点的坐标
            int textX = (int) (itemWidth / 2 - bounds.width()/2);
            int textY = (int) (itemHeight / 2 + bounds.height()/2 + i * itemHeight);

            //针对于不同的index,使用不同的画笔
            if(i == currentIndex){
                paint.setColor(Color.GRAY);
                paint.setTextSize(18);
            }else{
                paint.setColor(Color.WHITE);
                paint.setTextSize(15);
            }

            canvas.drawText(words[i],textX,textY,paint);

        }




    }

    private OnIndexChangedListener onIndexChangedListener;

    public void setOnIndexChangedListener(OnIndexChangedListener onIndexChangedListener) {
        this.onIndexChangedListener = onIndexChangedListener;
    }

    //内部提供的监听器的接口
    public interface OnIndexChangedListener {

        public void onIndexChanged(String word);

        public void onUp();
    }



}
