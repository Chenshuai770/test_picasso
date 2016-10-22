package com.cs.test_picasso.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cs.test_picasso.R;
import com.squareup.picasso.Picasso;

/**
 * 图片下载的步骤
 * 1.使用异步任务，使用handler+Thread 获取图片资源
 * 2.使用bitmapFactory 对图片进行解码
 * 3.显示图片
 *
 *
 */

public class test_picasso extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private Button btn_load;
    private static final String SISTER="http://ww1.sinaimg.cn/large/610dc034jw1f8zlenaornj20u011idhv.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        btn_load = (Button) findViewById(R.id.btn_load);

        btn_load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
             Picasso.with(this).load(SISTER)
                     .placeholder(R.mipmap.noloading).
                     error(R.mipmap.nosccess).
                     resize(500,500).
                     centerCrop().
                     into(image);
                break;
        }
    }
}
