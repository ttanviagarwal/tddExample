package com.tanvi.androidtddexample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public boolean isPasswordValid(String input) {
        return verifyPasswordLength(input) && isPasswordContainAlphabet(input) && isPasswordContainNumber(input) && isPasswordContainSpecialCharacter(input);
    }

    private boolean isPasswordContainSpecialCharacter(String input) {
        Pattern regex = Pattern.compile(".*[@#$%^&+=]+.*");
        Matcher matcher = regex.matcher(input);
        return matcher.find();

    }

    /**
     * Checks for the <p>BR#3 Atleast 1 number allowed</p>
     * @param input input string representing password
     * @return true if password contains atleast 1 number else false
     */
    private boolean isPasswordContainNumber(String input) {
        String numberRegex = ".*\\d+.*";
        return input.matches(numberRegex);
    }

    /**
     * Checks for the <p>BR#2 Atleast 1 alphabet allowed</p>
     * @param input input
     * @return true if allowed, false otherwise
     */
    private boolean isPasswordContainAlphabet(String input) {
        String alphabetRegex = ".*[A-Z a-z]+.*";
        return input.matches(alphabetRegex);
    }

    /**
     * Checks for the
     * <p>BR#1 Minimum 6 letters allowed</p>
     * @param input
     * @return
     */
    public boolean verifyPasswordLength(String input) {
        if (isEmpty(input) || validatePasswordLength(input)) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * Checks if the provided input is empty.
     * @param input the input.
     * @return true if empty, false otherwise.
     */
    private boolean isEmpty(String input) {
        return input == null;
    }

    /**
     * Checks if the input length is not less than 6.
     * @param input the input provided.
     * @return true if length greater than equal to 6.
     */
    private boolean validatePasswordLength(String input) {
        return input.length() < 6;
    }
}
