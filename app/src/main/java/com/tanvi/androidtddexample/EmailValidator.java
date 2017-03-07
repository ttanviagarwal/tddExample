package com.tanvi.androidtddexample;

/**
 * Created by daffolap on 28/02/17.
 */

public class EmailValidator {

    public boolean verifyEmail(String input) {
       return isEmailNotNull(input) && isEmailNotEmpty(input) && isEmailValid(input);
    }

    private boolean isEmailNotEmpty(String input) {
        if (input.length()==0) {
            return false;
        }
        return true;
    }

    private boolean isEmailNotNull(String input) {
        if (input == null) {
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String input) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return input.matches(emailRegex);
    }
}
