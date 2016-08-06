package com.fatty.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class PrimaryColor extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView mImageView;
    private SeekBar mSeekBarHue, mSeekBarSaturation, mSeekBarLum;

    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHue, mSaturation, mLum;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);

        //初始化图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);

        //初始化
        mImageView = (ImageView) findViewById(R.id.imageView);
        mSeekBarHue = (SeekBar) findViewById(R.id.seekBarHue);
        mSeekBarSaturation = (SeekBar) findViewById(R.id.seekBarSaturation);
        mSeekBarLum = (SeekBar) findViewById(R.id.seekBarLum);
        mImageView.setImageBitmap(bitmap);

        //注册监听器
        mSeekBarHue.setOnSeekBarChangeListener(this);
        mSeekBarSaturation.setOnSeekBarChangeListener(this);
        mSeekBarLum.setOnSeekBarChangeListener(this);

        //设置最大值
        mSeekBarHue.setMax(MAX_VALUE);
        mSeekBarSaturation.setMax(MAX_VALUE);
        mSeekBarLum.setMax(MAX_VALUE);

        //设置初始值在中间
        mSeekBarHue.setProgress(MID_VALUE);
        mSeekBarSaturation.setProgress(MID_VALUE);
        mSeekBarLum.setProgress(MID_VALUE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekBarHue:
                mHue = (i - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekBarSaturation:
                mSaturation = i * 1.0F / MID_VALUE;
                break;
            case R.id.seekBarLum:
                mLum = i * 1.0F / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageProcess(bitmap, mHue,mSaturation,mLum));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
