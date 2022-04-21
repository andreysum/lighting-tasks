package com.github.andreysum.lighting.db.schema

import org.springframework.data.annotation.AccessType
import javax.persistence.*

/**
 * Задача.
 *
 * @author andreysum
 */
@Entity
@Table(name = "task", uniqueConstraints = [UniqueConstraint(name = "uc_task_title", columnNames = ["title"])])
@AccessType(AccessType.Type.FIELD)
class TaskEntity constructor(@Column val title: String) : BaseEntity() {
    constructor() : this("Task")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    var parent: TaskEntity? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    val markers: MutableList<TimeMarkerEntity> = mutableListOf()

    override fun toString(): String {
        return title
    }
}
