package org.spockframework.controllers

import org.spockframework.services.GreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "/WithDep")
class GreetingController {

    @Autowired GreetingService greetingService

    @SuppressWarnings("GrMethodMayBeStatic")
    @GetMapping(path = "/")
    String greetWorld(@PathVariable String lang) {
        return greetingService.greet(lang)
    }
}
