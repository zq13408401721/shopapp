package com.myshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myshop.model.bean.TestBean;
import com.myshop.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * android Base64 使用学习地址 https://www.jianshu.com/p/48fac4e0fe1e
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_own)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //创建一个测试类
        TestBean testBean = new TestBean();
        testBean.setIndex(1);
        testBean.setName("hello");

        //以图片为例转换成base64 传递到 TestDataActivity
        /*String bmpBase64 = Base64.encodeToString(parseBitmapBytes(),Base64.DEFAULT);
        //openLogin();
        Intent intent = new Intent(this,TestDataActivity.class);
        intent.putExtra("data",testBean);
        intent.putExtra("bitmap64",bmpBase64);*/
        //startActivity(intent);
    }

    /**
     * 获取mipmap图片数据
     * @return
     */
    private byte[] parseBitmapBytes(){
        //读取mipmap到bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        //把bitmap转成byte数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();  //图片二进制数据
        byte[] words = "this is string".getBytes();  //插入的文字内容
        int lg = datas.length;   //图片数据的长度
        datas = concat(datas,words); //把图片和文字内容的byte数组拼接成一个
        byte[] lengthByte = intToByte(lg); //把图片长度写入到byte数组
        datas = concat(datas,lengthByte); //把图片字节的长度放入的数据中
        return datas;
    }

    //拼接两个byte数组
    private byte[] concat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    //用来固定写入的图片内容的长度
    private byte[] intToByte(int val){
        byte[] b = new byte[4];
        b[0] = (byte)(val & 0xff);
        b[1] = (byte)((val >> 8) & 0xff);
        b[2] = (byte)((val >> 16) & 0xff);
        b[3] = (byte)((val >> 24) & 0xff);
        return b;
    }

    private void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
