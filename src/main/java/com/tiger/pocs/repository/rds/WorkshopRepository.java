package com.tiger.pocs.repository.rds;

import com.tiger.pocs.domain.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, UUID>, JpaSpecificationExecutor<Workshop> {
}
