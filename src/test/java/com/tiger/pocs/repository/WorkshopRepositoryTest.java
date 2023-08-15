package com.tiger.pocs.repository;

import com.tiger.pocs.domain.entity.Workshop;
import com.tiger.pocs.paramResolver.WorkshopEntityParameterResolver;
import com.tiger.pocs.paramResolver.WorkshopResponseParameterResolver;
import com.tiger.pocs.repository.rds.WorkshopRepository;
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

import static com.tiger.pocs.utils.FixedValues.LOCAL_ENV;
import static com.tiger.pocs.utils.FixedValues.TEST_ENV;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class,
        WorkshopEntityParameterResolver.class,
        WorkshopResponseParameterResolver.class
})
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({LOCAL_ENV, TEST_ENV})
@Execution(ExecutionMode.CONCURRENT)
class WorkshopRepositoryTest {

    @Autowired
    private WorkshopRepository workshopRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Save new workshop succeeded")
    void save_new_workshop_succeed(Workshop workshop) {

        var fields = new String[]{"uuid", "createdAt", "modifiedAt", "createdByUser", "modifiedByUser", "version"};

        var underTest = workshopRepository.save(workshop);
        assertThat(underTest)
                .usingRecursiveComparison()
                .ignoringFields(fields)
                .isEqualTo(workshop);
    }


}