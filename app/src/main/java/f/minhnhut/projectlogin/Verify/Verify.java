package f.minhnhut.projectlogin.Verify;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Verify {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private static final int minNumberCharacter = 6;
    private static final int maxNumberCharacter = 25;

    //Apply for Sign In
    public boolean Verify(TextInputLayout layoutEmail,
                          TextInputLayout layoutPassword) {
        if (validateEmail(layoutEmail) &&
                validatePassword(layoutPassword)) {
            return true;
        } else {
            return false;
        }
    }

    // Apply for Sign Up
    public boolean Verify(TextInputLayout layoutEmail,
                          TextInputLayout layoutPassword,
                          TextInputLayout layoutConfirmPassword) {
        if (validateEmail(layoutEmail) &&
                validatePassword(layoutPassword) &&
                validateConfirmPassword(layoutConfirmPassword, layoutPassword)) {
            return true;
        } else {
            return false;
        }
    }

    // Apply for Forget Password
    public boolean Verify(TextInputLayout layoutEmail) {
        if (validateEmail(layoutEmail)) {
            return true;
        } else {
            return false;
        }
    }

    // Check when enter every symbol into e-mail
    public void checkEmail(Editable editable, TextInputLayout email) {
        if (editable.toString().equals("")) {
            email.setError(null);
        } else if (!validateEmail(editable.toString())) {
            email.setError("Please enter a valid email address.");
        } else {
            email.setError(null);
        }
    }

    //Hide error only one TextInputLayout. Check when enter every symbol into e-mail
    public void checkEmailOnly (Editable editable, TextInputLayout email, TextInputLayout password) {
        if (editable.toString().equals("")) {
            email.setError(null);
        } else if ((!validateEmail(editable.toString())) && (password.getError() == null)) {
            email.setError("Please enter a valid email address.");
        } else {
            email.setError(null);
        }
        if ((email.getEditText().getText().toString().equals("")) && (password.getEditText().getText().toString().equals("")))
        {
            email.setError(null);
            password.setError(null);
        }
        else if ((!validatePassword(password))
                && (!password.getEditText().getText().toString().equals(""))
                && (email.getError() == null)) {
            password.setError("Use from 6 to 25 characters for your password.");
        }

    }
    public void checkEmailOnly (Editable editable, TextInputLayout email, TextInputLayout password, TextInputLayout confirmpassword) {
        if (editable.toString().equals("")) {
            email.setError(null);
        } else if ((!validateEmail(editable.toString())) && (password.getError() == null) && (confirmpassword.getError() == null)) {
            email.setError("Please enter a valid email address.");
        } else {
            email.setError(null);
        }

        if ((email.getEditText().getText().toString().equals(""))
                && (password.getEditText().getText().toString().equals(""))
                && (confirmpassword.getEditText().getText().toString().equals("")))
        {
            email.setError(null);
            password.setError(null);
            confirmpassword.setError(null);
        }
        else if ((!validatePassword(password))
                && (!email.getEditText().getText().toString().equals(""))
                && (confirmpassword.getError() == null)
                && (email.getError() == null)) {
            password.setError("Use from 6 to 25 characters for your password.");
        }
        else if ((!validateConfirmPassword(confirmpassword, password) )
                && (!confirmpassword.getEditText().getText().toString().equals(""))
                && (!password.getEditText().getText().toString().equals(""))
                && (email.getError() == null)
                && (password.getError() == null))
        {
            confirmpassword.setError("Those password didn't match. Try again.");
        }
    }
    //Check when enter every symbol into password
    public void checkPassword(Editable editable, TextInputLayout password) {
        if (editable.toString().equals("")) {
            password.setError(null);
        } else if (!validatePassword(editable.toString())) {
            password.setError("Use from 6 to 25 characters for your password.");
        } else {
            password.setError(null);
        }
    }
    // Check when enter every symbol into password. It will compare between password and confirm password.
    public void checkPassword(Editable editable, TextInputLayout password, TextInputLayout confirmPassword) {
        if (editable.toString().equals("")) {
            password.setError(null);
        } else if (!validatePassword(editable.toString())) {
            password.setError("Use from 6 to 25 characters for your password.");
        } else {
            password.setError(null);
        }
        if (!validateConfirmPassword(editable.toString(), confirmPassword.getEditText().getText().toString())
                && !confirmPassword.getEditText().getText().toString().equals("")) {
            confirmPassword.setError("Those password didn't match. Try again.");
        } else {
            confirmPassword.setError(null);
        }
    }
    //Hide error only one TextInputLayout. Check when enter every symbol into password
    public void checkPasswordOnly(Editable editable, TextInputLayout email, TextInputLayout password) {
        if (editable.toString().equals("")) {
            password.setError(null);
        } else if ((!validatePassword(editable.toString())) && (email.getError() == null)) {
            password.setError("Use from 6 to 25 characters for your password.");
        } else {
            password.setError(null);
        }
        if ((email.getEditText().getText().toString().equals("")) && (password.getEditText().getText().toString().equals("")))
        {
           email.setError(null);
           password.setError(null);
        }
        else if ((!validateEmail(email))
                && (!password.getEditText().getText().toString().equals(""))
                && (password.getError() == null)){
            email.setError("Please enter a valid email address.");
        }

    }


    // Check when enter every symbol into confirm password. It will compare between password and confirm password.
    public void checkConfirmPassword(Editable editable, TextInputLayout password, TextInputLayout confirmPassword) {
        if (password.getEditText().getText().toString().equals("")) {
            if (!validatePassword(editable.toString())) {
                confirmPassword.setError("Use from 6 to 25 characters for your confirm password.");
            } else {
                confirmPassword.setError(null);
            }
        } else {
            if (!validateConfirmPassword(editable.toString(), password.getEditText().getText().toString())) {
                confirmPassword.setError("Those password didn't match. Try again.");
            } else {
                confirmPassword.setError(null);
            }
        }
        if (editable.toString().equals("")) {
            confirmPassword.setError(null);
        }
    }

    //Set the E-mail standard
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateEmail(TextInputLayout email) {
        matcher = pattern.matcher(email.getEditText().getText().toString());
        return matcher.matches();
    }

    //Set the password standard
    public boolean validatePassword(String password) {
        return (password.length() >= minNumberCharacter && password.length() <= maxNumberCharacter);
    }

    public boolean validatePassword(TextInputLayout password) {
        String strpassword = password.getEditText().getText().toString();
        return (strpassword.length() >= minNumberCharacter && strpassword.length() <= maxNumberCharacter);
    }

    //Set the confirm password standard
    public boolean validateConfirmPassword(String confirmPassword, String password) {
        return confirmPassword.equals(password);
    }

    public boolean validateConfirmPassword(TextInputLayout confirmPassword, TextInputLayout password) {
        return confirmPassword.getEditText().getText().toString().equals(password.getEditText().getText().toString());
    }


}
