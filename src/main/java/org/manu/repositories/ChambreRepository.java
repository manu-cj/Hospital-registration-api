package org.manu.repositories;

import org.manu.models.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, UUID> {
    Optional<Chambre> findByAvailable(boolean available);
}
