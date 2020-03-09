package com.myshop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myshop.base.BaseActivity;
import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.test.TestConstract;
import com.myshop.model.bean.AppLoginBean;
import com.myshop.presenter.test.TestPresenter;

import org.json.JSONException;
import org.json.JSONObject;

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
