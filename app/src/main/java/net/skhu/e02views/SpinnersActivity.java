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

        String[] stringArray = { "집주소", "직장주소", "기타" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_addressType);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                /*초기화 시의 선택 제외시 */
                //우리가 추가한 adapter로 선택된 Item을 출력해주었습니다.
                Toast.makeText(getApplicationContext(), adapter.getItem(position) + " 선택했음!.", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerPhoneType = (Spinner)findViewById(R.id.spinner_phoneType);
        spinnerPhoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                /*초기화 시의 선택 제외시 */
                //AdapterView를 사용해서 선택된 Item을 출력해주었습니다.
                Toast.makeText(getApplicationContext(), parent.getSelectedItem().toString() + " 선택했음!.", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner_phoneType);
                int index1 = spinner1.getSelectedItemPosition();
                String text1 = spinner1.getSelectedItem().toString();

                Spinner spinner2 = (Spinner) findViewById(R.id.spinner_addressType);
                int index2 = spinner2.getSelectedItemPosition();
                String text2 = spinner2.getSelectedItem().toString();

                String s = String.format("전화:%s(%d)   주소:%s(%d)", text1, index1, text2, index2);
                Toast.makeText(SpinnersActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        Button button = (Button) findViewById(R.id.btnSave);
        button.setOnClickListener(listener1);
    }

}
