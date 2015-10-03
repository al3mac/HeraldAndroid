package com.szwagry.heroldandroid.http;

import com.szwagry.heroldandroid.http.messages.GetThingsResponse;
import com.szwagry.heroldandroid.http.messages.LoginRequest;
import com.szwagry.heroldandroid.http.messages.LoginResponse;
import com.szwagry.heroldandroid.http.messages.RegisterRequest;
import com.szwagry.heroldandroid.http.messages.RegisterResponse;
import com.szwagry.heroldandroid.http.messages.SaveThingRequest;
import com.szwagry.heroldandroid.http.messages.SaveThingResponse;
import com.szwagry.heroldandroid.http.messages.ThingResponse;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author wojciechrauner
 */
@Rest(rootUrl = "http://172.27.1.83:3000/api", converters = { MappingJackson2HttpMessageConverter.class })
public interface HeraldRestService {

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

    void setHeader(String name, String value);
    String getHeader(String name);
}
