# spock-webmvctest
Spring Boot added some cool context slicing tools in version 1.4, 
but all of the examples involve using JUnit with Mockito. 
It's embedded into point that they even introduced a new annotation `@MockBean` 
which injects your mocks into the Spring context.

We want the same thing for Spock.  Mockito doesn't play well with Groovy classes,
but Spock doesn't just work out of the box.

This project provides an example of how to integrate Spock with the Spring `@MockMvc` annotation.

See the `GreetingControllerDetachedFactoryTest` for the example.
