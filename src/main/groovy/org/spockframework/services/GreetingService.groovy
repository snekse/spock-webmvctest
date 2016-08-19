package org.spockframework.services

import org.springframework.stereotype.Service

@Service
class GreetingService {

    /**
     * @see {@link GreetingUtil#getGreeting(java.lang.String)}
     * @param lang
     * @return
     */
    String greet(String lang) {
        //Should mock as: GreetingUtil#getGreeting(java.lang.String)
        throw new RuntimeException("Not yet implemented")
    }
}
