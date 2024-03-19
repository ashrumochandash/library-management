package za.co.librarrymanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.librarrymanagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
