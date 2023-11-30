package application.repositories;

import application.entities.SportsEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsEquipmentRepository extends JpaRepository<SportsEquipment, Integer> {
}
