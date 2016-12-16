package me.yummyti.yummytime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yummyti.yummytime.models.User;
import me.yummyti.yummytime.network.UserService;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "RegisterActivity";
    private static final int REQUEST_SIGNIN = 0;


    @BindView(R.id.input_name)
    protected EditText _nameText;

    @BindView(R.id.input_email)
    protected EditText _emailText;

    @BindView(R.id.input_password)
    protected EditText _passwordText;

    @BindView(R.id.input_password_verif)
    protected EditText _passwordVerifText;

    @BindView(R.id.btn_register)
    protected Button _registerButton;

    @BindView(R.id.link_signin)
    protected Button _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        _registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                register();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_SIGNIN);
            }
        });
    }

    public void register() {
        Log.d(TAG, "Register");

        if (!validate()) {
            onRegisterFailed();
            return;
        }

        _registerButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String password_verif = _passwordVerifText.getText().toString();


        UserService.registerUser(name, email, password, password_verif, new UserService.UserPostResultListener() {
            @Override
            public void onPostUsers(User user) {

                Log.e("DEBUG onRegisterUsers", user.getName().toString());
                Integer id = user.getId();
                //Log.e("DEBUG onLoginUsers", user.getName().toString());
                ((ApplicationController) getApplication()).setUserProfileToken(id);

                Integer userId = ApplicationController.getInstance().getUserProfileToken();
                Log.e(TAG, "UserService.registerUser:" + userId.toString());

                onRegisterSuccess();

                progressDialog.dismiss();

            }


            @Override
            public void onFailed() {
                Log.e("DEBUG onRegisterUsers", "FAILED");
                onRegisterFailed();

                progressDialog.dismiss();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNIN) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful registering logic here
                // By default we just finish the Activity and log them in automatically

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onRegisterSuccess() {
        _registerButton.setEnabled(true);


        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(intent, REQUEST_SIGNIN);


        finish();
    }

    public void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "Email or username already used", Toast.LENGTH_LONG).show();
        _registerButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
        String password_verif = _passwordVerifText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError("enter an username");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (password == password_verif) {
            _passwordVerifText.setError("passwords can be differents");
            valid = false;
        } else {
            _passwordVerifText.setError(null);
        }

        return valid;
    }

}