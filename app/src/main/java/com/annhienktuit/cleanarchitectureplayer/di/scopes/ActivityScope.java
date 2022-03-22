package com.annhienktuit.cleanarchitectureplayer.di.scopes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Scope;

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
