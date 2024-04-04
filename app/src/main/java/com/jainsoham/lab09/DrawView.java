package com.jainsoham.lab09;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite = new Sprite(101, 101, 125, 200, 15, 10, Color.RED);
    Sprite sprite2 = new Sprite(551, 551, 575, 650, -8, 12, Color.RED);
    Sprite sprite3 = new Sprite(301, 1101, 325, 1200, 12, -8, Color.RED);

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite.grow(200);
        sprite.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sprite));
        sprite2.grow(200);
        sprite2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sprite2));
        sprite3.grow(200);
        sprite3.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sprite3));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        sprite.update(canvas);
        sprite.draw(canvas);
        sprite2.update(canvas);
        sprite2.draw(canvas);
        sprite3.update(canvas);
        sprite3.draw(canvas);
        invalidate();
    }
}