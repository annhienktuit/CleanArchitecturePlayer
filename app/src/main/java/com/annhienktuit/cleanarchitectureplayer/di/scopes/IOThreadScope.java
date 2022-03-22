package com.annhienktuit.cleanarchitectureplayer.di.scopes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Scope;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
@Scope
@Retention(RUNTIME)
public @interface IOThreadScope {}
