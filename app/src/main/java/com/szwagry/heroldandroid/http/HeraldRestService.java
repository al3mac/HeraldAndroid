package com.szwagry.heroldandroid.http;

import com.szwagry.heroldandroid.http.messages.RegisterResponse;

import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author wojciechrauner
 */
@Rest(rootUrl = "http://server:3000/api", converters = { MappingJackson2HttpMessageConverter.class })
public interface HeraldRestService {

    @Put("/user")
    RegisterResponse registerUser(User user);
}
