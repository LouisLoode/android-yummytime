package me.yummyti.yummytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class LoginActivity extends AppCompatActivity  {
    Button loginButton;
    Button registerButton;
    EditText ed1,ed2;

    TextView tx1;
    //int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button)findViewById(R.id.loginButton);
        ed1 = (EditText)findViewById(R.id.emailAdress);
        ed2 = (EditText)findViewById(R.id.password);

        registerButton = (Button)findViewById(R.id.registerButton);
        //tx1.setVisibility(View.GONE);

        final Context context = this;


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong  Credentials",Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        loginButton.setEnabled(false);
                    }
                }*/


                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);



            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);

            }

        });




    }
}
