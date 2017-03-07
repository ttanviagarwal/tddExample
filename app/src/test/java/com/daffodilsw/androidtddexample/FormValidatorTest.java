package com.daffodilsw.androidtddexample;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class FormValidatorTest {
    private EmailValidator mEmailValidator;
    private PasswordValidator mPasswordValidator;

    @Before
    public void setUp() {
        mEmailValidator = new EmailValidator();
        mPasswordValidator = new PasswordValidator();
    }

    @Test
    public void submitEmailAndPassword() throws IOException {
        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody("{ email : a@gmail.com }");

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(response);
        mockWebServer.start();



        HttpUrl baseUrl = mockWebServer.url("/login");
        Login login = new Login(baseUrl);

        login.tryLogin("a@gmail.com", "1234567A@");

        String expectedOutput = "{ email : a@gmail.com }";
        //String actualOutput = login.getMessage();
        //Assert.assertEquals("Email or password invalid", expectedOutput, actualOutput);


        mockWebServer.shutdown();
    }

    @After
    public void tearDown() {
        mEmailValidator = null;
        mPasswordValidator = null;
    }
}
