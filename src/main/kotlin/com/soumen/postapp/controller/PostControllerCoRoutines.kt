package com.soumen.postapp.controller

import com.soumen.postapp.data.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * @author Soumen Karmakar
 * 29/07/2020
 */
@RestController
class PostControllerCoRoutines {

    @Autowired
    lateinit var postRepository: PostRepository

    @GetMapping("/posts/{id}")
    suspend fun findOne(@PathVariable id:Long) = postRepository.getPostById(id)

    @GetMapping("/posts")
    suspend fun findAll() = postRepository.getAllPosts()
}