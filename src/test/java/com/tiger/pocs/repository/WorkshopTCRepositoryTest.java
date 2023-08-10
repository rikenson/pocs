package com.tiger.pocs.repository;

import com.tiger.pocs.domain.entity.Workshop;
import com.tiger.pocs.paramResolver.WorkshopEntityParameterResolver;
import com.tiger.pocs.paramResolver.WorkshopResponseParameterResolver;
import com.tiger.pocs.utils.CommonPostgresqlContainer;
import com.tiger.pocs.utils.FixedValues;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class,
        WorkshopEntityParameterResolver.class,
        WorkshopResponseParameterResolver.class
})
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({FixedValues.LOCAL_ENV})
@Execution(ExecutionMode.CONCURRENT)
class WorkshopTCRepositoryTest {


    private final static PostgreSQLContainer<CommonPostgresqlContainer> container = CommonPostgresqlContainer.getInstance();

    @Autowired
    private WorkshopRepository workshopRepository;


    @BeforeEach
    void setUp() {
        container.withReuse(true);
//        container.withInitScript("");
        container.start();
    }

    @AfterAll
    static void tearDown() {
        container.stop();
    }

    @Test
    @DisplayName("Save new workshop succeeded")
    void save_new_workshop_succeed(Workshop workshop) {

        var underTest = workshopRepository.save(workshop);
        var saved = workshopRepository.findById(workshop.getUuid());
        assertThat(underTest)
                .usingRecursiveComparison()
                .isEqualTo(saved.orElse(null));
    }

    @Test
    @DisplayName("Update new workshop succeeded")
    void update_existing_workshop_succeed(Workshop workshop) {
        var underTest = workshopRepository.save(workshop);
        var saved = workshopRepository.findById(workshop.getUuid());
        assertThat(underTest)
                .usingRecursiveComparison()
                .isEqualTo(saved.orElse(null));
    }


}