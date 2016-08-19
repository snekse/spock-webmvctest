package org.spockframework.controllers

import org.spockframework.services.GreetingService
import org.spockframework.services.GreetingUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * This spec shows how not defining the service bean results a NoSuchBeanDefinitionException
 * as the Spring context tries to find a qualifying bean to inject into the controller
 *
 * Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.spockframework.services.GreetingService] found for dependency [org.spockframework.services.GreetingService]: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1406)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1057)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1019)
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:566)
    ... 39 more
 */
@ContextConfiguration
@WebMvcTest(GreetingController)
class GreetingControllerNoServiceTest extends Specification {

    @Autowired MockMvc mockMvc

    //Obviously not injected
    GreetingService greetingService = Mock(GreetingService)

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
