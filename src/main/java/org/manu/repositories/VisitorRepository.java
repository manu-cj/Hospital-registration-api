package org.manu.repositories;

import org.manu.models.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VisitorRepository {
    List<Visitor> visitorRepository = new ArrayList<>();

    /**
     * Save a new visitor
     * @param visitor
     * @return
     */
    public Visitor save(Visitor visitor) {
        visitorRepository.add(visitor);
        return visitor;
    }
}