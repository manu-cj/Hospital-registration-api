package org.manu.repositories;

import org.manu.models.ChambreAssignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChambreAssignementRepository extends JpaRepository<ChambreAssignement, UUID> {

    Optional<ChambreAssignement> findByPatientIdAndEndDateIsNull(UUID patientId);

    Optional<ChambreAssignement> findByPatientId(UUID patientId);

    boolean existsByPatientId(UUID patientId);

    List<ChambreAssignement> findByChambreNumber(String number);

    List<ChambreAssignement> findAllByPatientId(UUID id);

}
