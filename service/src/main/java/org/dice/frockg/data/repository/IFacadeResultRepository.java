package org.dice.frockg.data.repository;

import org.dice.frockg.data.model.ServicesResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFacadeResultRepository extends JpaRepository<ServicesResponses,Long > {
    List<ServicesResponses> findByTaskId(String taskId);
}
