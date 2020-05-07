package com.github.andreysum.lighting.db.schema

import org.hibernate.annotations.Type
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
class TimeMarkerEntity(
    @Enumerated
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskid")
    var task: TaskEntity
) : BaseEntity() {

    @Column
    @Type(type = "timestamp")
    var marker = LocalDateTime.now()
}
