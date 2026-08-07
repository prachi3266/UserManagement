/*Repository- Ready made DB operation
* It allows us to use the predefined methods in JPA Repostiory to perform operation on DB without writing SQL queries*/

package com.example.UserManagement.Repository;

import com.example.UserManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
