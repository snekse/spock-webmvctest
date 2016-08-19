package org.spockframework.controllers

import org.spockframework.services.GreetingService
import org.spockframework.services.GreetingUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Ignore
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * This is just an example of what we might hope to see as Spock support
 */
@Ignore
@WebMvcTest(GreetingController)
class GreetingControllerIdealTest extends Specification {

    @Autowired MockMvc mockMvc

    /*
        A new annotation that creates the mock and injects it into the context
     */
    //@SpockMockBean
    GreetingService greetingService

    void setup() {
        greetingService.greet(_ as String) >> { String lang ->
            GreetingUtil.getGreeting(lang)
        }
    }

    def "GreetWorld"() {
        def resultActions = mockMvc.perform(get("/WithDep/$lang"))

        expect:
        resultActions.andExpect(status().is(200))

        and:
        resultActions.andReturn().response.contentAsString == result

        where:
        lang    || result
        'en'    || "Hello World"
        'es'    || "Hola Mundo"
    }
}
