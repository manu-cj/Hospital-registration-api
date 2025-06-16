package org.manu.repositories;

import org.manu.models.ChambreAssignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChambreAssignementRepository extends JpaRepository<ChambreAssignement, UUID> {
}
