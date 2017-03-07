package com.tanvi.androidtddexample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EmailValidatorTest {
    private EmailValidator mEmailValidator;

    @Before
    public void setUp () {
        this.mEmailValidator = new EmailValidator();
    }

    @Test
    public void emailNullNotAllowed () {
        String input = null;
        boolean expectedOutput =  false;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email should not be null", expectedOutput, actualOutput);
    }

    @Test
    public void emailEmptyNotAllowed () {
        String input = "";
        boolean expectedOutput =  false;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email should not be empty", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithoutAtTheRateAndDomainNotAllowed () {
        String input = "abc";
        boolean expectedOutput =  false;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email should be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithLowerCaseAllowed () {
        String input = "sssssssc@gmail.com";
        boolean expectedOutput =  true;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email with lowercase letters should be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithUpperCaseAllowed () {
        String input = "SASDGTE@gmail.com";
        boolean expectedOutput =  true;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email with uppercase letters should be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithLowerPartAndDomainAllowed () {
        String input = "ssEEDCSss1233434sssc@gmail.com";
        boolean expectedOutput =  true;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email  should be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithNumberAllowed () {
        String input = "123456@gmail.com";
        boolean expectedOutput =  true;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email with numbers should be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailInvalidIfDomainMissing () {
        String input = "123456aa@gmail";
        boolean expectedOutput =  false;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email with domain missing should not be valid", expectedOutput, actualOutput);
    }

    @Test
    public void emailWithSpecialCharactersInLowerPartAllowed () {
        String input = "123456@^&aa@gmail.com";
        boolean expectedOutput =  false;

        boolean actualOutput = mEmailValidator.verifyEmail(input);

        Assert.assertEquals("Email with special character should not be valid", expectedOutput, actualOutput);
    }

    @After
    public void tearDown() {
        this.mEmailValidator = null;
    }
}
