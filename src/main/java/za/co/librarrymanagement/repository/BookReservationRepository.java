package za.co.librarrymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.librarrymanagement.entity.BookReservationInfo;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservationInfo, Long>{

}
