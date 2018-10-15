package com.android.song.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * 값을 저장하기 위한 SharedPreferences 사용 테스트
 */
public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    EditText name_editText, add_editText;
    CheckBox checkBox1, checkBox2, checkBox3;
    RadioGroup radioGroup;
    Button saveBtn, resetBtn, loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 초기화
        initBtn();
        // SharedPreferences 초기화
        initPreferences();

        // 버튼의 클릭이벤트 추가
        saveBtn.setOnClickListener(myOnClickEvent);
        resetBtn.setOnClickListener(myOnClickEvent);
        loadBtn.setOnClickListener(myOnClickEvent);

    }

    /**
     * 버튼 초기화
     */
    private void initBtn(){
        name_editText = (EditText) findViewById(R.id.name_editText);
        add_editText = (EditText) findViewById(R.id.add_editText);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        loadBtn = (Button) findViewById(R.id.loadBtn);
    }

    /**
     * SharedPreferences 초기화
     * sharedPreferences = getSharedPreferences("이름", 모드);
     * SharedPreferences의 저장경로는 해당 단말기 "data/data/[패키지 이름]/shared_prefs/이름.xml" 에 저장됨
     *
     */
    private void initPreferences() {
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    View.OnClickListener myOnClickEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.saveBtn:
                    // 상태를 저장
                    // SharedPreferences 데이터에 변화를 주려면 Editor를 사용해야 함
                    // SharedPreferences.Editor editor = sharedPreferences.edit(); -> 변화를 주는 메소드를 작성하기 전 반드시 editor를 생성합니다.
                    // editor.putString("key", value) 로 데이터 저장가능
                    // putString, putInt, putBoolean ...
                    // 마지막 commit() 메소드를 호출해야 SharedPreferences에 저장
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name_editText.getText().toString());
                    editor.putString("add", add_editText.getText().toString());
                    editor.putBoolean("check1", checkBox1.isChecked());
                    editor.putBoolean("check2", checkBox2.isChecked());
                    editor.putBoolean("check3", checkBox3.isChecked());
                    editor.putInt("radioGroup", radioGroup.getCheckedRadioButtonId());
                    editor.commit();

                    break;
                case R.id.resetBtn:
                    // 필드값 초기화
                    name_editText.setText("");
                    add_editText.setText("");
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    radioGroup.check(0);

                    break;
                case R.id.loadBtn:
                    // 상태를 로드
                    // sharedPreferences.getString("key", default) 로 데이터 로드가능
                    // getString, getInt,getBoolean ...
                    name_editText.setText(sharedPreferences.getString("name",""));
                    add_editText.setText(sharedPreferences.getString("add",""));
                    checkBox1.setChecked(sharedPreferences.getBoolean("check1",false));
                    checkBox2.setChecked(sharedPreferences.getBoolean("check2",false));
                    checkBox3.setChecked(sharedPreferences.getBoolean("check3",false));
                    radioGroup.check(sharedPreferences.getInt("radioGroup",0));

                    break;
            }
        }
    };
}
