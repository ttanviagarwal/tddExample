package com.daffodilsw.androidtddexample;



import com.daffodilsw.androidtddexample.login.bl.LoginServiceBusinessLogic;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class FormValidatorTest {


    private MockWebServer mockWebServer;
    private Gson gson;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Test
    public void requestTypePostIsAllowed() throws IOException, InterruptedException {
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url("/");
        LoginServiceBusinessLogic loginServiceBusinessLogic = new LoginServiceBusinessLogic(baseUrl);
        loginServiceBusinessLogic.submitLoginRequest("a@gmail.com", "1234567A@", null);

        RecordedRequest request = this.mockWebServer.takeRequest();
        Assert.assertEquals("POST", request.getMethod());
    }

    @Test
    public void requestTypeOtherThanPostIsNotAllowed() throws IOException, InterruptedException {
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url("/");
        LoginServiceBusinessLogic loginServiceBusinessLogic = new LoginServiceBusinessLogic(baseUrl);
        loginServiceBusinessLogic.submitLoginRequest("a@gmail.com", "1234567A@", null);

        RecordedRequest request = this.mockWebServer.takeRequest();
        Assert.assertNotEquals("GET", request.getMethod());

        Assert.assertNotEquals("PUT", request.getMethod());

        Assert.assertNotEquals("DELETE", request.getMethod());
    }

    @Test
    public void pathLoginIsAllowed() throws IOException, InterruptedException {
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url("/");
        LoginServiceBusinessLogic loginServiceBusinessLogic = new LoginServiceBusinessLogic(baseUrl);
        loginServiceBusinessLogic.submitLoginRequest("a@gmail.com", "1234567A@", null);

        RecordedRequest request = this.mockWebServer.takeRequest();
        Assert.assertEquals("/login", request.getPath());
    }

    @Test
    public void pathOtherThanLoginIsNotAllowed() throws IOException, InterruptedException {
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url("/");
        LoginServiceBusinessLogic loginServiceBusinessLogic = new LoginServiceBusinessLogic(baseUrl);
        loginServiceBusinessLogic.submitLoginRequest("a@gmail.com", "1234567A@", null);

        RecordedRequest request = this.mockWebServer.takeRequest();
        Assert.assertNotEquals("/login123", request.getPath());
    }

    @Test
    public void emailPasswordBothStringTypeAllowed() throws IOException, InterruptedException {
        mockWebServer.start();
        HttpUrl baseUrl = mockWebServer.url("/");
        LoginServiceBusinessLogic loginServiceBusinessLogic = new LoginServiceBusinessLogic(baseUrl);
        loginServiceBusinessLogic.submitLoginRequest("a@gmail.com", "1234567A@", null);

        RecordedRequest request = this.mockWebServer.takeRequest();
        String body = request.getBody().readUtf8();
        int emailStartIndex = body.indexOf('=');
        int emailEndIndex = body.indexOf('&');
        String email = body.substring(emailStartIndex+1, emailEndIndex);
        boolean expectedOutput = true;
        boolean actualOutput = isString(email);
        Assert.assertEquals("String  type is Allowed", expectedOutput, actualOutput);
    }

    private boolean isString(String text) {
        return text != null;
    }

//    @Test
//    public void submitEmailAndPassword() throws IOException {
//        MockResponse response = new MockResponse()
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .addHeader("Cache-Control", "no-cache")
//                .setBody("{ email : a@gmail.com }");
//
//        mockWebServer.enqueue(response);
//        mockWebServer.start();
//
//        HttpUrl baseUrl = mockWebServer.url("/login");
//        LoginServiceBusinessLogic login = new LoginServiceBusinessLogic(baseUrl);
//
//        login.tryLogin("a@gmail.com", "1234567A@");
//
//        String expectedOutput = "{ email : a@gmail.com }";
//        //String actualOutput = login.getMessage();
//        //Assert.assertEquals("Email or password invalid", expectedOutput, actualOutput);
//    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
