package org.manu.repositories;

import org.manu.models.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
}