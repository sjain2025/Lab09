package com.jainsoham.lab09;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;

class Sprite extends RectF {
    private static final int BMP_COLUMNS = 4;
    private static final int BMP_ROWS = 4;
    private static final int DOWN = 0, LEFT = 1, RIGHT = 2, UP = 3;
    private int animationRow = 0;
    private int dX, dY, color;
    private Bitmap bitmap;

    public Sprite() {
        this(8, 10, Color.RED);
    }

    public Sprite(int dX, int dY, int color) {
        this(1, 1, 25, 100, dX, dY, color);
    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1, 2, Color.RED);
    }

    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }

    public void update(Canvas canvas){
        if (left + dX < 0) {
            animationRow = RIGHT;
            dX *= -1;
        }
        if (right + dX > canvas.getWidth()) {
            animationRow = LEFT;
            dX *= -1;
        }
        if (top + dY > canvas.getHeight() - 250) {
            animationRow = UP;
            dY *= -1;
        }
        if (bottom + dY < 200) {
            animationRow = DOWN;
            dY *= -1;
        }
        offset(dX, dY);
    }

    public void draw(Canvas canvas){
        int iconWidth = bitmap.getWidth() / BMP_COLUMNS;
        int iconHeight = bitmap.getHeight() / BMP_ROWS;
        int srcX = 0;
        int srcY = animationRow * iconHeight;
        Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);
        canvas.drawBitmap(bitmap, src, this, null);
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void grow(int i) {
        right = right + i;
        bottom = bottom + i;
    }
}