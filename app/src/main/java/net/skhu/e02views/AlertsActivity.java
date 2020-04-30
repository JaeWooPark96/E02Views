package net.skhu.e02views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AlertsActivity extends AppCompatActivity {

    int selectedAnimalIndex = 0;
    static ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
    }

    //버튼의 리스너
    public void button1_clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//AlertDialog.Builder 를 만들어주었습니다.
        builder.setMessage(R.string.saveSuccess);//message 설정
        builder.setNeutralButton(R.string.close, null);//중립버튼 NeutralButton 설정해주었습니다.
        AlertDialog dialog = builder.create();//다이얼로그를 만들어주었습니다. AlertDialog 를 만들어주었습니다.
        dialog.show();//AlertDialog 를 보여주었습니다.
    }

    //버튼의 리스너
    public void button2_clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//AlertDialog.Builder 를 만들어주었습니다.
        builder.setTitle(R.string.confirm);//title 설정
        builder.setMessage(R.string.doYouWantToDelete);//message 설정
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {//긍정 버튼 PositiveButton을 설정해주었습니다.
            //DialogInterface.OnClickListener 를 anonymous inner class 로 만들어 주었습니다.

            //해당 버튼이 클릭되었을 때의, onClick 메소드입니다.
            @Override
            public void onClick(DialogInterface dialog, int index) {
                // 삭제 작업 실행
                Toast.makeText(AlertsActivity.this, "삭제성공", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.no, null);//부정 버튼 NegativeButton 설정입니다.
        //DialogInterface.OnClickListener 를 null로 해주었습니다.

        AlertDialog dialog = builder.create();//다이얼로그를 만들어주었습니다. AlertDialog 를 만들어주었습니다.
        dialog.show();//AlertDialog 를 보여주었습니다.
    }

    //버튼의 리스너
    public void button3_clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//AlertDialog.Builder 를 만들어주었습니다.
        builder.setTitle(R.string.selectAnimal);//title 설정
        builder.setSingleChoiceItems(R.array.animals, selectedAnimalIndex, null);//하나 고르는 대화상자//R.array.animals 배열, 선택된 항목은 selectedAnimalIndex 로 설정, 리스너는 null으로 해주었습니다.
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {//PositiveButton 설정입니다//DialogInterface.OnClickListener 를 anonymous inner class로 구현해주었습니다.
            @Override
            public void onClick(DialogInterface dialog, int index) {
                // 선택된 항목에 대한 작업 실행
                ListView listView = ((AlertDialog)dialog).getListView();//ListView를 얻어주었습니다. dialog에 listView가 있는 것입니다.
                selectedAnimalIndex = listView.getCheckedItemPosition();//listView에 선택된 항목을 얻어주었습니다.
                String msg = selectedAnimalIndex + " 번째 항목이 선택되었습니다.";//선책된 항목의 index를 Toast 메세지로 출력해주었습니다.
                Toast.makeText(AlertsActivity.this, msg, Toast.LENGTH_SHORT).show();

                //선택된 항목에 따라 imageView1의 이미지가 달라지도록 했습니다.
                int drawableId = 0;
                switch (selectedAnimalIndex) {//선택된 항목에 따라 drawableId를 다르게 해주었습니다.
                    case 0: drawableId = R.drawable.animal_cat_large; break;
                    case 1: drawableId = R.drawable.animal_dog_large; break;
                    case 2: drawableId = R.drawable.animal_owl_large; break;
                }
                //imageView1 = (ImageView) findViewById(R.id.imageView1);//ImageView 객체를 찾아주었습니다.
                imageView1.setImageResource(drawableId);//imageView1의 이미지를 drawableId로 바꾸어주었습니다.
            }
        });
        builder.setNegativeButton(R.string.cancel, null);//NegativeButton 설정//취소버튼 누르면 아무것도 안 하도록 해주었습니다.
        AlertDialog dialog = builder.create();//다이얼로그를 만들어주었습니다. AlertDialog 를 만들어주었습니다.
        dialog.show();//AlertDialog 를 보여주었습니다.
    }

    //버튼의 리스너
    public void button4_clicked(View button) {
        String[] stringArray = { "고양이", "강아지", "부엉이" };//adapter에 등록할 배열
        //android에 이미 있는 layout으로 adpater를 만들어주었습니다.android.R.layout.simple_list_item_single_choice//라디오 버튼 같은 걸로 선택할 수 있도록 나옵니다. 구글 검색
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, stringArray);//ArrayAdapter 생성
        //android에 이미 있는 layout으로 adpater를 설정해주었습니다. android.R.layout.simple_spinner_dropdown_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//호출하여 어댑터가 Spinner 선택 항목의 목록을 표시하는 데 사용해야 하는 레이아웃을 지정해야 합니다

        AlertDialog.Builder builder = new AlertDialog.Builder(this);//AlertDialog.Builder 를 만들어주었습니다.
        builder.setTitle(R.string.selectAnimal);//title 설정
        builder.setSingleChoiceItems(adapter, selectedAnimalIndex, null);//하나 고르는 대화상자//R.array.animals 배열, 선택된 항목은 selectedAnimalIndex 로 설정, 리스너는 null으로 해주었습니다.
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {//PositiveButton 설정
            @Override
            public void onClick(DialogInterface dialog, int index) {
                // 선택된 항목에 대한 작업 실행
                ListView listView = ((AlertDialog)dialog).getListView();//dialog의 listView를 가져왔습니다.
                selectedAnimalIndex = listView.getCheckedItemPosition();//체크된 아이템의 index를 가져와주었습니다.
                String msg = selectedAnimalIndex + " 번째 항목이 선택되었습니다.";// 선택된 index를 Toast로 출력해주었습니다.
                Toast.makeText(AlertsActivity.this, msg, Toast.LENGTH_SHORT).show();

                //선택된 항목에 따라, drawableId 에 다른 이미지 id를 저장해주었습니다.
                int drawableId = 0;
                switch (selectedAnimalIndex) {
                    case 0: drawableId = R.drawable.animal_cat_large; break;
                    case 1: drawableId = R.drawable.animal_dog_large; break;
                    case 2: drawableId = R.drawable.animal_owl_large; break;
                }
                //ImageView 객체를 찾아 이미지를 drawableId로 변경해주었습니다.
                //imageView1 = (ImageView) findViewById(R.id.imageView1);
                imageView1.setImageResource(drawableId);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);//NegativeButton 설정
        AlertDialog dialog = builder.create();//다이얼로그를 만들어주었습니다. AlertDialog 를 만들어주었습니다.
        dialog.show();//AlertDialog 를 보여주었습니다.
    }

    //버튼의 리스너
    public void button5_clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//AlertDialog.Builder 를 만들어주었습니다.
        builder.setTitle(R.string.selectAnimal);//title 설정

        final String[] stringArray = { "고양이", "강아지", "부엉이" };//각 버튼에 쓸 문자열 배열입니다.
        builder.setPositiveButton(stringArray[0], new DialogInterface.OnClickListener() {//PositiveButton 설정입니다.
            @Override
            public void onClick(DialogInterface dialog, int index) {//클릭되면 R.drawable.animal_cat_large로 imageView1이 바뀌도록 해주었습니다.
                //imageView1 = (ImageView) findViewById(R.id.imageView1);
                imageView1.setImageResource(R.drawable.animal_cat_large);
            }
        });
        builder.setNegativeButton(stringArray[1], new DialogInterface.OnClickListener() {//NegativeButton 설정입니다.
            @Override
            public void onClick(DialogInterface dialog, int index) {//클릭되면 R.drawable.animal_dog_large imageView1이 바뀌도록 해주었습니다.
                //imageView1 = (ImageView) findViewById(R.id.imageView1);
                imageView1.setImageResource(R.drawable.animal_dog_large);
            }
        });

        builder.setNeutralButton(stringArray[2], new DialogInterface.OnClickListener() {//NeutralButton 설정입니다.
            @Override
            public void onClick(DialogInterface dialog, int index) {//클릭되면 R.drawable.animal_owl_large imageView1이 바뀌도록 해주었습니다.
                //imageView1 = (ImageView) findViewById(R.id.imageView1);
                imageView1.setImageResource(R.drawable.animal_owl_large);
            }
        });
        AlertDialog dialog = builder.create();//다이얼로그를 만들어주었습니다. AlertDialog 를 만들어주었습니다.
        dialog.show();//AlertDialog 를 보여주었습니다.
    }
}
