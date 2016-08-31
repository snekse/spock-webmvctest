package org.spockframework.services

import org.springframework.stereotype.Service

@Service
class GreetingFormalityService {

    String getTitle() {
        "Monsieur"
    }
}
