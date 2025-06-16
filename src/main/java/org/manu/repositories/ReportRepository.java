package org.manu.repositories;

import org.manu.models.VisitorReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<VisitorReport, UUID> {
    // Méthode personnalisée pour la recherche par nom/prénom (optionnelle)
    Optional<VisitorReport> findById(UUID id);
    List<VisitorReport> findByVisitor_FirstnameStartingWithAndVisitor_LastnameStartingWith(String firstname, String lastname);
    List<VisitorReport> findByVisitor_FirstnameStartingWithAndVisitor_LastnameStartingWithOrVisitor_FirstnameStartingWithAndVisitor_LastnameStartingWith(
            String firstname, String lastname, String lastname2, String firstname2
    );
}