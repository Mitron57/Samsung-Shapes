package com.mitron57.firstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
        entry = true;
        circles = new int[5][4];
        random = new Random();
        random.setSeed(1000);
    }

    boolean entry;
    int[][] circles;
    Random random;

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (entry) {
            for (int i = 0; i < 5; ++i) {
                circles[i] = new int[]{canvas.getWidth() / 2, canvas.getHeight() / 2, random.nextInt() % 10 - 5, random.nextInt() % 10 - 5};
            }
            entry = false;
        }
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        //canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 100, paint);

        for (int i = 0; i < canvas.getHeight(); i += 10) {
            canvas.drawLine(0, i, canvas.getWidth(), i, paint);
        }

        for (int[] circle: circles) {
            circle[0] += circle[2];
            circle[1] += circle[3];
            if (circle[0] + 100 > canvas.getWidth() || circle[0] - 100 < 0) {
                //circle[0] = canvas.getWidth() / 2;
                //circle[1] = canvas.getHeight() / 2;

                circle[2] *= -1;//random.nextInt() % 10 - 5;

            }
            if(circle[1] + 100 > canvas.getHeight() || circle[1] - 100 < 0) {
                circle[3] *= -1;
            }
            //random.nextInt() % 10 - 5;
            canvas.drawCircle(circle[0], circle[1], 100, paint);
        }
        invalidate();
    }
}
