package com.github.andreysum.lighting.db.gen

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class DataGenerationExecutor(generators: List<Generating>) {
    init {
        generators.forEach { it.generate() }
    }
}