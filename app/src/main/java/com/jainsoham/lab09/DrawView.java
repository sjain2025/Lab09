package com.jainsoham.lab09;

import static android.graphics.BitmapFactory.decodeResource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawView extends View {
    Sprite sprite = new Sprite(101, 101, 125, 200, 15, 10, Color.RED);
    Sprite sprite2 = new Sprite(551, 551, 575, 650, -8, 12, Color.RED);
    Sprite sprite3 = new Sprite(301, 1101, 325, 1200, 12, -8, Color.RED);
    int count = 0;
    int NUMCIRCLES = 100;
    int[] circleLocationsX = new int[NUMCIRCLES];
    int[] circleLocationsY = new int[NUMCIRCLES];
    ArrayList<Integer> collectedTokens = new ArrayList<>();
    boolean isTouching = false;
    float spriteWidth;
    float spriteHeight;
    float sprite2Width;
    float sprite2Height;
    float sprite3Width;
    float sprite3Height;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite.grow(150);
        sprite.setBitmap(decodeResource(getResources(), R.drawable.sprite));
        sprite2.grow(150);
        sprite2.setBitmap(decodeResource(getResources(), R.drawable.sprite2));
        sprite3.grow(150);
        sprite3.setBitmap(decodeResource(getResources(), R.drawable.sprite3));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (count == 0) {
            for (int i = 0; i < NUMCIRCLES; i++) {
                int randX = ((int)(Math.random() * getWidth())) % (getWidth() - 150) + 75;
                int randY = ((int)(Math.random() * getHeight())) % (getHeight() - 150) + 75;
                circleLocationsX[i] = randX;
                circleLocationsY[i] = randY;
            }
        }
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        sprite.update(canvas);
        sprite.draw(canvas);
        sprite2.update(canvas);
        sprite2.draw(canvas);
        sprite3.update(canvas);
        sprite3.draw(canvas);
        if (count == 0) {
            spriteWidth = sprite.getSpriteWidth();
            spriteHeight = sprite.getSpriteHeight();
            sprite2Width = sprite2.getSpriteWidth();
            sprite2Height = sprite2.getSpriteHeight();
            sprite3Width = sprite3.getSpriteWidth();
            sprite3Height = sprite3.getSpriteHeight();
        }
        for (int i = 0; i < NUMCIRCLES; i++) {
            if (!collectedTokens.contains(i)) {
                if (Math.abs(sprite.getCenterX() - circleLocationsX[i]) < (sprite.getSpriteWidth() / 2) && Math.abs(sprite.getCenterY() - circleLocationsY[i]) < (sprite.getSpriteHeight() / 2)) {
                    collectedTokens.add(i);
                    continue;
                }
                if (Math.abs(sprite2.getCenterX() - circleLocationsX[i]) < (sprite2.getSpriteWidth() / 2) && Math.abs(sprite2.getCenterY() - circleLocationsY[i]) < (sprite2.getSpriteHeight() / 2)) {
                    collectedTokens.add(i);
                    continue;
                }
                if (Math.abs(sprite3.getCenterX() - circleLocationsX[i]) < (sprite3.getSpriteWidth() / 2) && Math.abs(sprite3.getCenterY() - circleLocationsY[i]) < (sprite3.getSpriteHeight() / 2)) {
                    collectedTokens.add(i);
                    continue;
                }
                canvas.drawCircle(circleLocationsX[i], circleLocationsY[i], 35.0f, p);
            }
        }
        if (isTouching && sprite.getSpriteWidth() < 300) {
            sprite.grow(5);
            sprite2.grow(5);
            sprite3.grow(5);
        } else if (!isTouching && sprite.getSpriteWidth() > spriteWidth) {
            sprite.shrink(5);
            sprite2.shrink(5);
            sprite3.shrink(5);
        }
        count++;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isTouching = true;
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            isTouching = false;
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}