package com.example.administrator.layouttobitmap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveimg = ((Button) findViewById(R.id.button2));
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.layout_test, null);
        saveimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button2:
//                方法二
//                final Bitmap bmp = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
//                view.draw(new Canvas(bmp));
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//                String time = dateFormat.format(new Date());
////                final String photoUrl = GloableParams.IMG_FILE_PATH + time + ".png";//换成自己的图片保存路径
////                final File file = new File(photoUrl);
//                String root = Environment.getExternalStorageDirectory()
//                        .toString();
//                final File file = new File(root +File.separator+ "layout.png");
//                if (!file.exists())
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            boolean bitMapOk = bmp.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
////                            out.flush();
//////                    out.close();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    }.start();



//                方法一：
                view.setDrawingCacheEnabled(true);
//                view.buildDrawingCache();
                view.measure(View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY),View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY));
                view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredWidth());
                Bitmap bm = view.getDrawingCache();

                String root = Environment.getExternalStorageDirectory()
                        .toString();
                File myDir = new File(root + "/_images");
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Image-" + n + ".jpg";
                File file = new File(myDir, fname);
                if (file.exists())
                    file.delete();

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}