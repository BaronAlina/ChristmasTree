package com.example.christmastree;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    MyThread myThread;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        myThread = new MyThread();
        myThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        myThread.work = false;
    }

    class MyThread extends Thread{
        boolean work = true;
        @Override
        public void run() {
            Canvas canvas;
            SurfaceHolder holder=getHolder();
            Paint paint=new Paint();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
            int w =bitmap.getWidth();
            int h=bitmap.getHeight();

            int viewWidth = getHolder().getSurfaceFrame().right;
            int viewHeight =getHolder().getSurfaceFrame().bottom;
            Rect pic = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            Rect position =new Rect(0, 0, getHolder().getSurfaceFrame().right, getHolder().getSurfaceFrame().bottom);

            while (work) {
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.BLUE);
                canvas.drawBitmap(bitmap, pic, position, paint);

                int x = (int)(viewWidth * 0.4);
                int y = (int)(viewHeight*0.7 );
                int x2 = (int)(viewWidth * 0.64);
                int y2 = (int)(viewHeight*0.44);
                int x3 = (int)(viewWidth * 0.62);
                int y3 = (int)(viewHeight*0.3);


                for (int i = 0; i < 6; i++) {
                    paint.setColor(Color.argb(250, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                    canvas.drawCircle(x, y, 30, paint);
                    x += (int)(viewWidth * 0.054);
                    y -= (int)(viewHeight *0.025);

                }

                for (int i = 0; i < 6; i++) {
                    if (i==5){
                        break;
                    }
                    paint.setColor(Color.argb(250, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                    canvas.drawCircle(x2, y2, 30, paint);
                    x2 -= (int)(viewWidth * 0.054);
                    y2 += (int)(viewHeight *0.025);

                }

                for (int i = 0; i < 5; i++) {
                    paint.setColor(Color.argb(250, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                    canvas.drawCircle(x3, y3, 30, paint);
                    x3 -= (int)(viewWidth * 0.054);
                    y3 += (int)(viewHeight *0.025);

                }

                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
