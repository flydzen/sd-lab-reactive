package com.flydzen.labreactive.repository;

import com.flydzen.labreactive.documents.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
interface ItemRepository: ReactiveMongoRepository<Item, UUID>