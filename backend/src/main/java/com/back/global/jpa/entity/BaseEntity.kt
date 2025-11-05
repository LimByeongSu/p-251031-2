package com.back.global.jpa.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @JvmField
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter(AccessLevel.PROTECTED)
    val id: Long = 0    //비지니스 모델 상 id를 setter로 설정하는 부분이있어서 주 생성자에서 설정할수도 있게함
    //자바는 Long? 로 쓰면 객체타입으로 판단하고
    //Long 으로 쓰면 원시타입으로 판단한다.
    //그래서 id.equals()같은 원시타입에서 못쓰는걸 쓴 비지니스가 있으면 수정이 필요하다.
) {


    @CreatedDate
    lateinit var createDate: LocalDateTime

    @LastModifiedDate
    lateinit var modifyDate: LocalDateTime


    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as BaseEntity
        return id == that.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}