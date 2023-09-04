package com.tiger.pocs.repository.rdbms;

import com.tiger.pocs.domain.entity.rdbms.SampleEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("RdsSampleRepository")
public interface SampleRepository extends JpaRepository<SampleEntity, String>, JpaSpecificationExecutor<SampleEntity> {
}
