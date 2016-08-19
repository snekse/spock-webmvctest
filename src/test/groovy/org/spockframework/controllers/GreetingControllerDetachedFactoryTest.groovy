package org.spockframework.controllers

import org.spockframework.services.GreetingService
import org.spockframework.services.GreetingUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * This test tries to utilize a static config that uses {@link DetachedMockFactory}
 * to create a Spring bean created from a Spock Mock
 *
 * The problem with this test is Spring is unable to inject the {@link MockMvc} once we introduce the extra config
 *
 * Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.springframework.test.web.servlet.MockMvc] found for dependency [org.springframework.test.web.servlet.MockMvc]: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true), @org.spockframework.runtime.model.FieldMetadata(line=26, name=mockMvc, ordinal=0)}
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1406)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1057)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1019)
    at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:566)
    ... 18 more
 *
 * @see GreetingNoDependencyControllerTest
 */
@ContextConfiguration(loader=AnnotationConfigContextLoader)
@WebMvcTest(GreetingController)
class GreetingControllerDetachedFactoryTest extends Specification {

    @Autowired MockMvc mockMvc

    @Autowired GreetingService greetingService

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

    static class Config {
        private DetachedMockFactory factory = new DetachedMockFactory()

        @Bean
        GreetingService greetingService() {
            factory.Mock(GreetingService, name:"greetingService")
        }
    }
}
