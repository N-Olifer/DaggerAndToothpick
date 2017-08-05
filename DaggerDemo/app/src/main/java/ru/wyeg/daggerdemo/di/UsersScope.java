package ru.wyeg.daggerdemo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Nikita Olifer
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UsersScope {
}