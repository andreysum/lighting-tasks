package com.github.andreysum.lighting.db.schema

import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.AccessType
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Отметка времени.
 *
 * @author andreysum
 */
@Entity
@Table(name = "time_mark")
@AccessType(AccessType.Type.FIELD)
abstract class TimeMarkerEntity : BaseEntity() {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskid")
    open lateinit var task: TaskEntity

    @NotNull
    @Column(nullable = false)
    @Type(type = "timestamp")
    open var marker = LocalDateTime.now()
}
