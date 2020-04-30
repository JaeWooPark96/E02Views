package net.skhu.e02views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinners);

        String[] stringArray = { "집주소", "직장주소", "기타" };//ArrayAdapter에 등록할 String 배열입니다.
        //android 시스템 내에 미리 만들어진 layout을 사용해주었습니다.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//목록 view를 만들기 위한 id입니다. 이 id에 따라 view의 모양이 달라집니다.
        // 지금은 simple_spinner_dropdown_item을 쓰고 있습니다.

        Spinner spinner = (Spinner) findViewById(R.id.spinner_addressType);//Spinner 객체를 얻어주었습니다.
        spinner.setAdapter(adapter);//spinner 에 adapter를 등록해주었습니다.

        //선택된 항목이 바뀔 때의 리스너를 anonymous inner class 로 만들어주었습니다.
        //AdapterView.OnItemSelectedListener 가 abstract method 이기 때문에, 안에 있는 함수를 모두 정의해야 합니다.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                /*초기화 시의 선택 제외시 */
                //우리가 추가한 adapter로 선택된 Item을 출력해주었습니다.//position은 선택된 item의 index 입니다.
                Toast.makeText(getApplicationContext(), adapter.getItem(position) + " 선택했음!.", Toast.LENGTH_SHORT).show();
            }

            //AdapterView.OnItemSelectedListener 가 abstract method 이기 때문에, 안에 있는 함수를 모두 정의해야 합니다.
            //이렇게 비워 놓더라도 정의해야 합니다.
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner 객체를 찾아주었습니다.
        Spinner spinnerPhoneType = (Spinner)findViewById(R.id.spinner_phoneType);
        //선택된 항목이 바뀔 때의 리스너를 anonymous inner class 로 만들어주었습니다
        //AdapterView.OnItemSelectedListener 가 abstract method 이기 때문에, 안에 있는 함수를 모두 정의해야 합니다.
        spinnerPhoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                /*초기화 시의 선택 제외시 */
                //AdapterView 를 사용해서 선택된 Item 을 출력해주었습니다.
                Toast.makeText(getApplicationContext(), parent.getSelectedItem().toString() + " 선택했음!.", Toast.LENGTH_SHORT).show();
            }

            //AdapterView.OnItemSelectedListener 가 abstract method 이기 때문에, 안에 있는 함수를 모두 정의해야 합니다.
            //이렇게 비워 놓더라도 정의해야 합니다.
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //저장 버튼이 눌릴 때의 리스너입니다.
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner_phoneType);
                int index1 = spinner1.getSelectedItemPosition();
                String text1 = spinner1.getSelectedItem().toString();//spinner1의 선택된 item의 문자열을 저장해주었습니다.

                Spinner spinner2 = (Spinner) findViewById(R.id.spinner_addressType);
                int index2 = spinner2.getSelectedItemPosition();
                String text2 = spinner2.getSelectedItem().toString();//spinner2의 선택된 item의 문자열을 저장해주었습니다.

                //선택된 문자열을 Toast 메세지로 출력해주었습니다.
                String s = String.format("전화:%s(%d)   주소:%s(%d)", text1, index1, text2, index2);
                Toast.makeText(SpinnersActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        Button button = (Button) findViewById(R.id.btnSave);
        button.setOnClickListener(listener1);//버튼에 리스너를 등록해주었습니다.
    }

}
