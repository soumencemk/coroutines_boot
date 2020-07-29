package com.soumen.postapp.data

import com.soumen.postapp.PostNotCreatedException
import com.soumen.postapp.PostNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

/**
 * @author Soumen Karmakar
 * 27/07/2020
 */
@Repository
class PostRepository(private val client: DatabaseClient) {
    fun getAllPosts(): Flow<Post> =
            client.select()
                    .from("posts")
                    .`as`(Post::class.java)
                    .fetch()
                    .all()
                    .asFlow()

    suspend fun getPostById(id: Long): Post = client.execute("SELECT * FROM posts WHERE id = $1")
            .bind(0, id)
            .`as`(Post::class.java)
            .fetch()
            .one()
            .awaitFirstOrElse { throw PostNotFoundException("Post with id : $id not found") }

    suspend fun addNewPost(post: Post) = client.execute("INSERT INTO posts(title,content) VALUES ($1,$2)")
            .bind(0, post.title)
            .bind(1, post.content)
            .then()
            .awaitFirstOrElse { throw PostNotCreatedException("Post ${post.title} Not created.. sorry") }
}