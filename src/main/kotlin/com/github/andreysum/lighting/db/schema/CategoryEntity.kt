package com.github.andreysum.lighting.db.schema

import org.springframework.data.annotation.AccessType
import javax.persistence.*

/**
 * Категория.
 *
 * @author andreysum
 */
@Entity
@Table(name = "category")
@AccessType(AccessType.Type.FIELD)
class CategoryEntity constructor(var title: String, val type: CategoryType = CategoryType.SAVING) : BaseEntity() {
    constructor() : this("")

    @Enumerated
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    var parent: CategoryEntity? = null
    var totalAmount: Double? = null
}
