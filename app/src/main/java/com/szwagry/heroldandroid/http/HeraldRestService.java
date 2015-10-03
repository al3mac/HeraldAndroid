package com.szwagry.heroldandroid.http;

import com.szwagry.heroldandroid.http.messages.DeleteThingResponse;
import com.szwagry.heroldandroid.http.messages.GetThingsResponse;
import com.szwagry.heroldandroid.http.messages.LoginRequest;
import com.szwagry.heroldandroid.http.messages.LoginResponse;
import com.szwagry.heroldandroid.http.messages.RegisterRequest;
import com.szwagry.heroldandroid.http.messages.RegisterResponse;
import com.szwagry.heroldandroid.http.messages.SaveThingRequest;
import com.szwagry.heroldandroid.http.messages.SaveThingResponse;
import com.szwagry.heroldandroid.http.messages.SaveTokenRequest;
import com.szwagry.heroldandroid.http.messages.SaveTokenResponse;
import com.szwagry.heroldandroid.http.messages.SendMessageRequest;
import com.szwagry.heroldandroid.http.messages.SendMessageResponse;
import com.szwagry.heroldandroid.http.messages.ThingResponse;

import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author wojciechrauner
 */
@Rest(rootUrl = "http://herald-cryptosync.rhcloud.com/api", converters = {MappingJackson2HttpMessageConverter.class})
public interface HeraldRestService extends RestClientErrorHandling {

    @Put("/user/")
    RegisterResponse registerUser(RegisterRequest registerRequest);

    @Post("/user/login")
    LoginResponse loginUser(LoginRequest loginRequest);

    @Get("/things/all")
    @RequiresHeader("Authorization")
    GetThingsResponse getThings();

    @Get("/things/{id}")
    @RequiresHeader("Authorization")
    ThingResponse getThing(String id);

    @Put("/things/")
    @RequiresHeader("Authorization")
    SaveThingResponse saveThing(SaveThingRequest request);

    @Delete("/things/{id}")
    @RequiresHeader("Authorization")
    DeleteThingResponse deleteThing(String id);

    @Post("/user/token")
    @RequiresHeader("Authorization")
    SaveTokenResponse saveToken(SaveTokenRequest saveTokenRequest);

    @Post("/api/messages")
    @RequiresHeader("Authorization")
    SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest);

    void setHeader(String name, String value);

    String getHeader(String name);
}
