package com.fatty.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ColorMatrix extends AppCompatActivity {

    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap bitmap;

    private int etWidth;
    private int etHeight;

    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        mImageView = (ImageView) findViewById(R.id.color_matrix_imageView);
        mGroup = (GridLayout) findViewById(R.id.group);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mImageView.setImageBitmap(bitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                etWidth = mGroup.getWidth() / 5;
                etHeight = mGroup.getHeight() / 4;

                addEts();
                initMatrix();
            }
        });
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }

    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(ColorMatrix.this);
            mEts[i] = editText;
            mGroup.addView(editText, etWidth, etHeight);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }
}
