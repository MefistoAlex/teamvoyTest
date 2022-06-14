package com.teamvoy.task.repository;

import com.teamvoy.task.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
    UserEntity findByUsername(String username);

}
