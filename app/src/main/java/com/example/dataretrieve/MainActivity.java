//データベースにintをどうやって入れてるかをみる。
//次にタイマーを設定してそのタイマーをデータベースのほうに入れられるかためす
//まずはデータのretrieveの仕方をもう一度見直す
//そこから縦の列で足すことができるかを試してみる。



package com.example.dataretrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv1;
    DatabaseAdapter db;
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText)findViewById(R.id.et1);
        ed2 = (EditText)findViewById(R.id.et2);
        ed3 = (EditText)findViewById(R.id.et3);
        ed4 = (EditText)findViewById(R.id.et4);
        tv1 = (TextView)findViewById(R.id.textView);
        db = new DatabaseAdapter(this);
    }

    //ここでintの値をでーたべーすにいれることができる
    public void insertMe(View v)
    {
        db.insert123(ed1.getText().toString(),ed2.getText().toString(),
                ed3.getText().toString(),ed4.getText().toString());
        Toast.makeText(getBaseContext(), "Data Inserted", Toast.LENGTH_LONG).show();
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        tv1.setText("");
    }

    public void findSum(View v)
    {
        Cursor c = db.getInsertedData();
        if(c.moveToFirst())
        {
            do {
                    int sum = Integer.parseInt(c.getString(1))+Integer.parseInt(c.getString(2))+
                            Integer.parseInt(c.getString(3))+Integer.parseInt(c.getString(4));
                    tv1.append("\n"+"id: "+c.getString(0)+"\n"+
                            "PHY:" + c.getString(1)+ " " +
                            "CHE:" + c.getString(2)+ " " +
                            "MATH:" + c.getString(3)+ " " +
                            "ENG:" + c.getString(4)+ " " +
                            "Total: " + sum);

                }while(c.moveToNext());
            }
            db.close();
        }
        public void eraseTV(View v) {
            tv1.setText("");
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
        }
}