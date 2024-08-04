package com.inotePoc.InotePoc.repository;

import com.inotePoc.InotePoc.model.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {

    @Query("{assignee: ?0}")
    List<AddressEntity> findByAssigne(String assigne);

//    @Query("{assignee: ?0 , filedName: ?1}")
//    List<Task> findByAssigne(String assigne);
}
