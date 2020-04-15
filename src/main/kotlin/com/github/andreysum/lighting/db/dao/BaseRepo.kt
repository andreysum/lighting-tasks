package com.github.andreysum.lighting.db.dao

import com.github.andreysum.lighting.db.schema.IdentifiedEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepo<E: IdentifiedEntity> : JpaRepository<E, Long>