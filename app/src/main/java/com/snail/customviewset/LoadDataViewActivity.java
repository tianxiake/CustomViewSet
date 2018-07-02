package com.snail.customviewset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.snail.customviewset.view.ShapeView;

public class LoadDataViewActivity extends AppCompatActivity {

    private Button button;
    private ShapeView shapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_view_test);
//        button = findViewById(R.id.btn_test);
//        shapeView = findViewById(R.id.sv_shape);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shapeView.changeShape();
//            }
//        });
    }
}
