package com.flydzen.labreactive.repository

import com.flydzen.labreactive.documents.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : ReactiveMongoRepository<User?, UUID?>