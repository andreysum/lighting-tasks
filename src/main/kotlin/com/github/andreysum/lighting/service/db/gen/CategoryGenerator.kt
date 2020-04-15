package com.github.andreysum.lighting.service.db.gen

import com.github.andreysum.lighting.db.dao.CategoryRepo
import org.springframework.stereotype.Service

@Service
class CategoryGenerator (private val repo: CategoryRepo): Generating {
    override fun generate() {
    }
}