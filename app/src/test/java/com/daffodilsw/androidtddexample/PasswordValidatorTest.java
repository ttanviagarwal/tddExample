package com.daffodilsw.androidtddexample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordValidatorTest {
    private PasswordValidator mPasswordValidator;

    @Before
    public void setUp(){
        mPasswordValidator = new PasswordValidator();
    }

    @Test
    public void passwordLessThanSixCharNotAllowed(){
        String input = "123"; //input
        boolean expectedOutput =  false;

        boolean actualOutput = mPasswordValidator.verifyPasswordLength(input);

        Assert.assertEquals("Less Than 6 char are not allowed", expectedOutput, actualOutput);
    }

    @Test
    public void passwordShouldNotBeNull(){
        String input = null;
        boolean expectedOutput =  false;

        boolean actualOutput = mPasswordValidator.verifyPasswordLength(input);

        Assert.assertEquals("Password should not be null.", expectedOutput, actualOutput);
    }

    @Test
    public void passwordGreaterThanSixAllowed(){
        String input = "1234567";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.verifyPasswordLength(input);

        Assert.assertEquals("Password Greater Than 6 char are allowed", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWithoutAlphabetNotAllowed() {
        String input = "1234567";
        boolean expectedOutput = false;

        boolean actualOutput = mPasswordValidator.isPasswordValid(input);

        Assert.assertEquals("Password does not contains alphabet", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWithOneAlphabetAllowed() {
        String input = "12345a1@";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(input);

        Assert.assertEquals("Password contains alphabet", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWithMoreThanOneAlphabetAllowed() {
        String input = "E123abcWqQ223@";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(input);

        Assert.assertEquals("Password contains alphabet", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihoutNumberNotAllowed() {
        String password = "abcdef";
        boolean expectedOutput = false;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password does not contains number", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihOneNumberAllowed() {
        String password = "abcd1ef@";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password contains one number", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihMoreThanOneNumberAllowed() {
        String password = "ab4cd12es23@f";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password contains more than one number", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihoutSpecialCharacterNotAllowed() {
        String password = "abcdef";
        boolean expectedOutput = false;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password does not contains special character", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihOneSpecialCharacterAllowed() {
        String password = "@a1aaaaaaa";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password contains one special character", expectedOutput, actualOutput);
    }

    @Test
    public void passwordWihMoreThanOneSpecialCharacterAllowed() {
        String password = "!cd@$es$%f12";
        boolean expectedOutput = true;

        boolean actualOutput = mPasswordValidator.isPasswordValid(password);

        Assert.assertEquals("Password contains more than one special character", expectedOutput, actualOutput);
    }

    @After
    public void tearDown(){
        mPasswordValidator = null;
    }

}


