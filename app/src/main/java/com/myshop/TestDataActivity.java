package com.myshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.myshop.base.BaseActivity;
import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.test.TestConstract;
import com.myshop.model.bean.AppLoginBean;
import com.myshop.model.bean.TestBean;
import com.myshop.presenter.test.TestPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestDataActivity extends BaseActivity<TestConstract.Presenter> implements TestConstract.View {
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.img)
    ImageView imgView;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestConstract.Presenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //检查上一个页面是否有数据传递过来
        if(getIntent().hasExtra("data")){
            TestBean bean = (TestBean) getIntent().getSerializableExtra("data");
            Log.i("intent data:",bean.getName());
        }

        //上一个页面传递过来的图片内容
        if(getIntent().hasExtra("bitmap64")){
            String bmp64Str = getIntent().getStringExtra("bitmap64");
            if(!TextUtils.isEmpty(bmp64Str)){
                byte[] bmpAndWordBytes = Base64.decode(bmp64Str,Base64.DEFAULT);  //解析上一个页面传过来的数据
                int totalSize = bmpAndWordBytes.length; //当前数据的总长度
                byte[] lgByte = new byte[4]; //创建一个图片长度的byte数据，用来读取图片的字节长度
                int offset = totalSize-4; //计算图片长度读取的偏移量
                //通过System.arraycopy读取字节byte数组的数据
                System.arraycopy(bmpAndWordBytes, offset, lgByte,0,4);
                int bmpSize = toInt(lgByte);  //通过lgByte字节数据读取图片的大小
                //图片字节数据中的图片内容
                Bitmap bmp = BitmapFactory.decodeByteArray(bmpAndWordBytes,0,bmpSize);
                //用总数据的长度-4-图片的长度=文字内容长度
                if(totalSize-4-bmpSize > 0){
                    byte[] wordByte = new byte[totalSize-4-bmpSize];  //创建一个读取文本内容的byte数据
                    //从总数据中提取文本数据
                    System.arraycopy(bmpAndWordBytes,bmpSize,wordByte,0,totalSize-4-bmpSize);
                    //文本的byte数据转化成String
                    String word = new String(wordByte);
                    Log.i("word:",word);
                }
                //把bmp绑定到imgView上
                imgView.setImageBitmap(bmp);

            }
        }
    }

    //把byte数组转成int
    private int toInt(byte[] bRefArr) {
        int iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return iOutcome;
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.appLogin("张三","123");
                break;
            case R.id.btn_register:
                JSONObject json = new JSONObject();
                try {
                    json.put("username","qqq");
                    json.put("pw","123");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(MediaType.parse("application/json"),json.toString());
                presenter.appLogin(body);
                break;
        }
    }

    @Override
    public void appLoginReturn(AppLoginBean result) {
        Log.i("Result",result.toString());
    }

    @Override
    public void appLoginJsonReturn(AppLoginBean result) {
        Log.i("ResultJSON",result.toString());
    }
}
