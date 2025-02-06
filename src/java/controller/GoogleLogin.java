/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.Iconstant;
import java.io.IOException;
import model.GoogleAccount;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author 11
 */
public class GoogleLogin {

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // Print the URL and parameters for debugging
        System.out.println("Entering getToken method");
        System.out.println("URL: " + Iconstant.GOOGLE_LINK_GET_TOKEN);
        System.out.println("client_id: " + Iconstant.GOOGLE_CLIENT_ID);
        System.out.println("client_secret: " + Iconstant.GOOGLE_CLIENT_SECRET);
        System.out.println("redirect_uri: " + Iconstant.GOOGLE_REDIRECT_URI);
        System.out.println("code: " + code);
        System.out.println("grant_type: " + Iconstant.GOOGLE_GRANT_TYPE);

        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        // Print the response for debugging
        System.out.println("Response: " + response);

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        System.out.println("Access Token: " + accessToken);
        return accessToken;
    }

    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        System.out.println("Entering getUserInfo method");
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString();

        System.out.println("User Info Response: " + response);

        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);

        return googlePojo;
    }
}
