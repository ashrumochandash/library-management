package za.co.librarrymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.librarrymanagement.entity.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long>{

}
