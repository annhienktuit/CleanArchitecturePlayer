package com.annhienktuit.cleanarchitectureplayer.di.scopes;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Documented
@Retention(RUNTIME)
public @interface ApplicationScope {}