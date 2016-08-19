package org.spockframework.services


final class GreetingUtil {

    static final String getGreeting(String lang) {
        return "en" == lang ? "Hello World" : "Hola Mundo"
    }
}
