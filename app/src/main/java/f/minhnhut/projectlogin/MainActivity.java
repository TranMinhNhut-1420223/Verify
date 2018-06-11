package f.minhnhut.projectlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import f.minhnhut.projectlogin.Verify.Verify;
import f.minhnhut.projectlogin.Verify.resultLogin;

public class MainActivity extends AppCompatActivity implements resultLogin {
    Button btnSignIn;
    TextInputLayout layoutemailSignIn, layoutpasswordSignIn, layoutconfirmPasswordSignIn;
    TextInputEditText editemailSignIn, editpasswordSignIn, editconfirmpasswordSignIn;
    TextView txtSignUp, txtForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        txtForgetPassword = (TextView) findViewById(R.id.txtForgetPassword);
        layoutemailSignIn = (TextInputLayout) findViewById(R.id.txtinlayoutSignInEmail);
        editemailSignIn = (TextInputEditText) findViewById(R.id.editSignInEmail);
        layoutpasswordSignIn = (TextInputLayout) findViewById(R.id.txtinlayoutSignInPassword);
        editpasswordSignIn = (TextInputEditText) findViewById(R.id.editSignInPassword);

        final Verify verifySignIn = new Verify();
        layoutpasswordSignIn.setPasswordVisibilityToggleEnabled(true);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intentSignUp);
            }
        });

        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForgetPassword = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intentForgetPassword);
            }
        });
        editemailSignIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifySignIn.checkEmailOnly(editable, layoutemailSignIn, layoutpasswordSignIn);
            }
        });

        editpasswordSignIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifySignIn.checkPasswordOnly(editable,layoutemailSignIn, layoutpasswordSignIn);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (verifySignIn.Verify(layoutemailSignIn, layoutpasswordSignIn)) {
                    successLogin();
                } else {
                    failedLogin();
                }
                if (!verifySignIn.validateEmail(layoutemailSignIn)) {
                    layoutemailSignIn.setError("Please enter a valid email address.");
                }
                else if (!verifySignIn.validatePassword(layoutpasswordSignIn)) {
                    layoutpasswordSignIn.setError("Use from 6 to 25 characters for your password.");
                }
            }
        });
    }

    //Hide keyboard
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void successLogin() {
        Toast.makeText(getApplicationContext(), "Correct standard ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedLogin() {
        Toast.makeText(getApplicationContext(), "Incorrect standard", Toast.LENGTH_SHORT).show();
    }
}
