package za.co.librarrymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.librarrymanagement.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
