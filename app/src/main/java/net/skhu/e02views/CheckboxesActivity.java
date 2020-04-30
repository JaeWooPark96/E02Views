package net.skhu.e02views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class CheckboxesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);

        //CompoundButton.OnCheckedChangeListener 를 구현해주었습니다.
        CompoundButton.OnCheckedChangeListener listener1 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //선택된 buttonView의 문자열을 Toast 문자열로 출력해주었습니다.
                String s = String.format("%s : %b", buttonView.getText(), isChecked);//이렇게 format을 만들고 Toast에 전달하는 것이 좋습니다.
                Toast.makeText(CheckboxesActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);//CheckBox 객체를 찾아주었습니다.
        final Switch switch1 = (Switch) findViewById(R.id.switch1);//Switch 객체를 찾아주었습니다.

        //CheckBox, Switch 둘 다 같은 리스너를 사용합니다.
        checkBox1.setOnCheckedChangeListener(listener1);//위에서 만든 CompoundButton.OnCheckedChangeListener 를 등록해주었습니다.
        switch1.setOnCheckedChangeListener(listener1);

        //RadioGroup.OnCheckedChangeListener 를 구현해주었습니다.
        RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);//RadioGroup 중에서, check된 Id를 받아옵니다.

                //체크된 radio 버튼의 문자열을 Toast message 로 출력해주었습니다.
                String s = radioButton.getText().toString();
                Toast.makeText(CheckboxesActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };

        //RadioGroup에 RadioGroup.OnCheckedChangeListener 를 등록해주었습니다.
        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(listener2);

        //RadioGroup.OnCheckedChangeListener 를 구현해주었습니다.
        RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int drawableId = 0;
                switch (checkedId) {//checkedId 에 따라 drawableId에 다른 이미지의 id가 저장되도록 했습니다.
                    case R.id.radioCat: drawableId = R.drawable.animal_cat_large; break;
                    case R.id.radioDog: drawableId = R.drawable.animal_dog_large; break;
                    case R.id.radioOwl: drawableId = R.drawable.animal_owl_large; break;
                }

                ImageView imageView1 = (ImageView) findViewById(R.id.imageView);//ImageView 객체를 찾아주었습니다.
                imageView1.setImageResource(drawableId);//해당 ImageView 를 drawableId의 이미지로 바꾸어주었습니다.
            }
        };

        //RadioGroup에 RadioGroup.OnCheckedChangeListener 를 등록해주었습니다.
        final RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(listener3);

        //저장 버튼의 리스너를 anonymous inner class 으로 구현해주었습니다.
        Button button = (Button)findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "checkBox1=" + checkBox1.isChecked() + " ";//checkBox1의 상태를 문자열로 저장했습니다.
                s += "switch1=" + switch1.isChecked() + " ";//switch1의 상태를 문자열로 저장했습니다.

                //radioGroup1의 상태를 문자열로 저장했습니다.
                s += "radioGroup1=";
                switch (radioGroup1.getCheckedRadioButtonId()) {//radioGroup1 상태에 따라 다른 문자열이 저장되도록 했습니다.
                    case R.id.radioRed: s += "red "; break;
                    case R.id.radioYellow: s += "yellow "; break;
                    case R.id.radioBlue: s += "blue "; break;
                }

                //radioGroup2의 상태를 문자열로 저장했습니다.
                switch (radioGroup2.getCheckedRadioButtonId()) {//radioGroup2 상태에 따라 다른 문자열이 저장되도록 했습니다.
                    case R.id.radioCat: s += "cat"; break;
                    case R.id.radioDog: s += "dog"; break;
                    case R.id.radioOwl: s += "owl"; break;
                }

                //문자열 s를 Toast 메세지로 출력해주었습니다.
                Toast.makeText(CheckboxesActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
