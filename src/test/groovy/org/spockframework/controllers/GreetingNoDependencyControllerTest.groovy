package org.spockframework.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * This is an example of how the test works using {@link MockMvc} with no problems
 */
@ContextConfiguration
@WebMvcTest(GreetingNoDependencyController)
class GreetingNoDependencyControllerTest extends Specification {

    @Autowired MockMvc mockMvc

    def "GreetWorld"() {
        def resultActions = mockMvc.perform(get("/WithoutDep/$lang"))

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
