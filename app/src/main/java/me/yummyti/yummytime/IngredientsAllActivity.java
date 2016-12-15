package me.yummyti.yummytime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IngredientsAllActivity extends AppCompatActivity {

    public static final String TAG = IngredientsAllActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_all);
    }

    public void buttonClick(View v) {
        switch(v.getId()) {
            case R.id.btn_recipies:
                    finish();
                break;
        }
    }
}
