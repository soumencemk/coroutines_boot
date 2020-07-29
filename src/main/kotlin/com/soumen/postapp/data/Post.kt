package com.soumen.postapp.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

/**
 * @author Soumen Karmakar
 * 27/07/2020
 */
@Table("posts")
data class Post(@Id val id: Long,
                @Column("title") val title: String,
                @Column("content") val content: String)

