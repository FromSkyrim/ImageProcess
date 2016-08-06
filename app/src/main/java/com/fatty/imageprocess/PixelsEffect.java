package com.fatty.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PixelsEffect extends AppCompatActivity {

    private ImageView mImageView1, mImageView2, mImageView3, mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);

        mImageView1 = (ImageView) findViewById(R.id.imageView1);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView4 = (ImageView) findViewById(R.id.imageView4);

        mImageView1.setImageBitmap(bitmap);
        mImageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        mImageView3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
        mImageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }
}
