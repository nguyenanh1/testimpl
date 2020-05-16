package com.anhnguyen.test.impt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Button
    private Button btnSo1;
    private Button btnSo2;
    private Button btnSo3;
    private Button btnSo4;
    private Button btnSo5;
    private Button btnSo6;
    private Button btnSo7;
    private Button btnSo8;
    private Button btnSo9;
    private Button btnSo0;
    private Button btnMoNgoac;
    private Button btnDongNgoac;
    private Button btnDauCong;
    private Button btnDauTru;
    private Button btnDauChia;
    private Button btnDauNhan;
    private Button btnDauBang;
    private Button btnC;
    private Button btnAC;
    //TextView
    private TextView mResult;
    private TextView mInput;
    private TextView mDongNgoac;

    private TinhToan mMayTinh;

    private int mo = 0;

    private boolean anDauBang = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMayTinh = new TinhToan();
    }

    private void initView() {
        btnSo0 = findViewById(R.id.btn_so_0);
        btnSo1 = findViewById(R.id.btn_so_1);
        btnSo2 = findViewById(R.id.btn_so_2);
        btnSo3 = findViewById(R.id.btn_so_3);
        btnSo4 = findViewById(R.id.btn_so_4);
        btnSo5 = findViewById(R.id.btn_so_5);
        btnSo6 = findViewById(R.id.btn_so_6);
        btnSo7 = findViewById(R.id.btn_so_7);
        btnSo8 = findViewById(R.id.btn_so_8);
        btnSo9 = findViewById(R.id.btn_so_9);
        btnMoNgoac = findViewById(R.id.btn_mo_ngoac);
        btnDongNgoac = findViewById(R.id.btn_dong_ngoac);
        btnDauCong = findViewById(R.id.btn_dau_cong);
        btnDauTru = findViewById(R.id.btn_dau_tru);
        btnDauNhan = findViewById(R.id.btn_dau_nhan);
        btnDauChia = findViewById(R.id.btn_dau_chia);
        btnDauBang = findViewById(R.id.btn_dau_bang);
        btnC = findViewById(R.id.btn_c);
        btnAC = findViewById(R.id.btn_ac);
        mResult = findViewById(R.id.text_result);
        mInput = findViewById(R.id.text_nhap);
        mDongNgoac = findViewById(R.id.text_dong_ngoac);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSo1.setOnClickListener(this);
        btnSo2.setOnClickListener(this);
        btnSo3.setOnClickListener(this);
        btnSo4.setOnClickListener(this);
        btnSo5.setOnClickListener(this);
        btnSo6.setOnClickListener(this);
        btnSo7.setOnClickListener(this);
        btnSo8.setOnClickListener(this);
        btnSo9.setOnClickListener(this);
        btnSo0.setOnClickListener(this);
        btnMoNgoac.setOnClickListener(this);
        btnDongNgoac.setOnClickListener(this);
        btnDauCong.setOnClickListener(this);
        btnDauTru.setOnClickListener(this);
        btnDauNhan.setOnClickListener(this);
        btnDauBang.setOnClickListener(this);
        btnDauChia.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnAC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_so_0:
                input("0");
                break;
            case R.id.btn_so_1:
                input("1");
                break;
            case R.id.btn_so_2:
                input("2");
                break;
            case R.id.btn_so_3:
                input("3");
                break;
            case R.id.btn_so_4:
                input("4");
                break;
            case R.id.btn_so_5:
                input("5");
                break;
            case R.id.btn_so_6:
                input("6");
                break;
            case R.id.btn_so_7:
                input("7");
                break;
            case R.id.btn_so_8:
                input("8");
                break;
            case R.id.btn_so_9:
                input("9");
                break;
            case R.id.btn_mo_ngoac:
                input("(");
                break;
            case R.id.btn_dong_ngoac:
                input(")");
                break;
            case R.id.btn_dau_cong:
                input("+");
                break;
            case R.id.btn_dau_tru:
                input("-");
                break;
            case R.id.btn_dau_nhan:
                input("*");
                break;
            case R.id.btn_dau_chia:
                input("/");
                break;
            case R.id.btn_dau_bang:
                anDauBang = true;
                tinhToan();
                break;
            case R.id.btn_c:
                del();
                break;
            case R.id.btn_ac:
                delAll();
                break;

        }
    }

    private void tinhToan() {
        String request = mInput.getText().toString();
        while (mo>0){
            mo--;
            request = request+")";
        }
        String result = mMayTinh.calculate(request);
        mResult.setText(result);
    }

    private void delAll() {
        mInput.setText("");
        mResult.setText("");
        mo = 0;
        showHideDongNgoac();
    }

    private void del() {
        String mTextNow = mInput.getText().toString();
        if(mTextNow.isEmpty()){
            return;
        }
        Log.d("Test",mTextNow.substring(mInput.length() -1));
        if(mTextNow.substring(mInput.length() -1).equals("(")) {
            mo++;
        }
        if(mTextNow.substring(mInput.length() -1).equals(")")) {
            mo--;
        }
        mTextNow = mTextNow.substring(0, mTextNow.length() - 1);
        showHideDongNgoac();
        mInput.setText(mTextNow);
    }

    private void input(String c) {
        String number = "1234567890";
        String dau = "+-*/";
        String other = "()";
        String mTextNow = mInput.getText().toString();
        String kyTuCuoi = "";
        if(!mTextNow.isEmpty())
            kyTuCuoi = String.valueOf(mTextNow.charAt(mTextNow.length() - 1));
        if(anDauBang) {
            if(number.contains(c)) {
                mTextNow = "";
            }
            if(dau.contains(c)){
                mTextNow = mResult.getText().toString();
                mResult.setText("");
            }
        }
        if(number.contains(c)){
            mTextNow+= c;
            mInput.setText(mTextNow);
        }

        if(dau.contains(c)) {
            if("*/".contains(kyTuCuoi)){
                if(!c.equals("*") && !c.equals("/")) {
                    mTextNow+= c;
                    mInput.setText(mTextNow);
                }
            } else {
                mTextNow+= c;
                mInput.setText(mTextNow);
            }
        }

        if(other.contains(c)){
            if(!number.contains(kyTuCuoi)) {
                if(c.equals("(")) {
                    mo++;
                    mTextNow+= c;
                    mInput.setText(mTextNow);
                } else {
                    if(mo != 0) {
                        mo--;
                        mTextNow+= c;
                        mInput.setText(mTextNow);
                    }
                }
            }else {
                if(c.equals(")")) {
                    mo--;
                    mTextNow+= c;
                    mInput.setText(mTextNow);
                }
                if(kyTuCuoi.isEmpty() && c.equals("(")){
                    mTextNow+= c;
                    mInput.setText(mTextNow);
                }
            }
        }
        showHideDongNgoac();
        anDauBang = false;
    }

    private void showHideDongNgoac() {
        mDongNgoac.setVisibility(mo == 0 ? View.INVISIBLE : View.VISIBLE);
    }
}
