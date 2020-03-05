package com.myshop.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.widget.Button;
import android.widget.EditText;

import com.myshop.R;
import com.myshop.base.BaseActivity;
import com.myshop.interfaces.login.LoginConstract;
import com.myshop.model.bean.LoginBean;
import com.myshop.presenter.login.LoginPresenter;
import com.myshop.utils.SpUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View {
    @BindView(R.id.edit_nickname)
    EditText editNickname;
    @BindView(R.id.edit_pw)
    EditText editPw;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginConstract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    //登录成功
    @Override
    public void loginReturn(LoginBean result) {
        String token = result.getData().getToken();
        SpUtils.getInstance().setValue("token",token);
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        login();
    }

    //执行登录
    private void login(){
        String nickname = editNickname.getText().toString();
        String pw = editPw.getText().toString();
        if(!TextUtils.isEmpty(nickname) && !TextUtils.isEmpty(pw)){
            presenter.login(nickname,pw);
        }
    }
}
