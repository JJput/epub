package com.zy.myapplication;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.myapplication.epub.BookModel;
import com.zy.myapplication.utils.FileUtils;
import com.zy.myapplication.epub.ReadEpubHeadInfo;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_name;
    private Button butRead, butAllRead;
    private EditText etBookName;
    private ImageView iv;

    private ReadEpubHeadInfo readEpubHeadInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        StatusBarUtils.setFullScreen(this);
        setContentView(R.layout.main_activity);
        butRead = (Button) findViewById(R.id.but_read);
        butAllRead = (Button) findViewById(R.id.but_allread);
        etBookName = findViewById(R.id.et_bookname);

        iv = findViewById(R.id.iv);
        tv_name = findViewById(R.id.tv_name);

        butRead.setOnClickListener(this);
        butAllRead.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_read: {
                BookModel bookModel = ReadEpubHeadInfo.getePubBook("/sdcard/epub/" + etBookName.getText().toString() + ".epub");

                if (bookModel != null) {
                    tv_name.setText(bookModel.getName());
                    iv.setImageBitmap(BitmapFactory.decodeFile(bookModel.getCover()));
                    Log.i("epub", (bookModel.getCover() != null ? "有封面  " : "无封面  ") + bookModel.getName() + "  书封面图片路径：" + bookModel.getCover());
                }
            }

            break;
            case R.id.but_allread:
                List<File> vector = FileUtils.listFilesInDir("/sdcard/epub/");
                for (int i = 0; i < vector.size(); i++) {
                    final String filePath = vector.get(i).getPath();
                    BookModel bookModel = ReadEpubHeadInfo.getePubBook(filePath);

                    if (bookModel != null) {
                        Log.i("epub", (bookModel.getCover() != null ? "有封面  " : "无封面  ") + bookModel.getName() + "  书封面图片路径：" + bookModel.getCover());
                    }
                }
                break;

            default:
                break;
        }
    }

}
