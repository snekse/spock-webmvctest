package org.spockframework.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GreetingService {

    //NOTE: If you autowire the field instead of the constructor, the DetachedFactory test fails.
    //   Cause: No bean def found for GreetingFormalityService when trying to mock this class

    GreetingFormalityService greetingFormalityService

    GreetingService() {

    }

    @Autowired
    GreetingService(GreetingFormalityService gfs) {
        this.greetingFormalityService = gfs
    }

    /**
     * @see {@link GreetingUtil#getGreeting(java.lang.String)}
     * @param lang
     * @return
     */
    String greet(String lang) {
        def title = greetingFormalityService.getTitle()
        //Should mock as: GreetingUtil#getGreeting(java.lang.String)
        throw new RuntimeException("Not yet implemented for title: $title")
    }
}
