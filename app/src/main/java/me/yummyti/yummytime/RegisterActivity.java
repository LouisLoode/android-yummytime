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

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private static final int REQUEST_SIGNIN = 0;

    private static final String REGISTER_URL = "http://91.134.136.124:3001/users.json";


    @BindView(R.id.input_username)
    protected EditText _usernameText;

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

        final String username = _usernameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String password_verif = _passwordVerifText.getText().toString();

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", username);
        params.put("mail", email);
        params.put("password", password);
        params.put("password_confirmation", password_verif);

        JsonObjectRequest req = new JsonObjectRequest(REGISTER_URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            onRegisterSuccess();

                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();

                            progressDialog.dismiss();

                            onRegisterFailed();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

                progressDialog.dismiss();

                onRegisterFailed();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req);
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


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        String password_verif = _passwordVerifText.getText().toString();

        if (username.isEmpty()) {
            _usernameText.setError("enter an username");
            valid = false;
        } else {
            _usernameText.setError(null);
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