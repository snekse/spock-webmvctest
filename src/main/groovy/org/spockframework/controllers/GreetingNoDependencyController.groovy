package org.spockframework.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.spockframework.services.GreetingUtil.getGreeting

/**
 * An example of a controller with no dependencies to illustrate how MockMvc works
 */
@RestController
@RequestMapping(path = "/WithoutDep")
class GreetingNoDependencyController {

    @SuppressWarnings("GrMethodMayBeStatic")
    @GetMapping(path = "/{lang}")
    String greetWorld(@PathVariable String lang) {
        return getGreeting(lang)
    }
}
