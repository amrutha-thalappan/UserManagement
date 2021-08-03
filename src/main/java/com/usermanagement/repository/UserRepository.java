package com.usermanagement.repository;

import com.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * An interface containing persistence layer methods
 * which handles database access and object-relational mappings
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * JPA method declaration to retrieve User object by passing username as argument
     *
     * @param username username of the user whose details have to be retrieved
     * @return A valid User object having the given username
     */
    User findByUsername(String username);
}
