package com.tiger.pocs.repository;

import com.tiger.pocs.domain.entity.rdbms.SampleEntity;
import com.tiger.pocs.paramResolver.SampleEntityParameterResolver;
import com.tiger.pocs.paramResolver.SampleResponseParameterResolver;
import com.tiger.pocs.repository.rdbms.SampleRepository;
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
        SampleEntityParameterResolver.class,
        SampleResponseParameterResolver.class
})
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({LOCAL_ENV, TEST_ENV})
@Execution(ExecutionMode.CONCURRENT)
class SampleEntityRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Save new sample succeeded")
    void save_new_sample_succeed(SampleEntity sampleEntity) {

        var fields = new String[]{"uuid", "createdAt", "modifiedAt", "createdByUser", "modifiedByUser", "version"};

        var underTest = sampleRepository.save(sampleEntity);
        assertThat(underTest)
                .usingRecursiveComparison()
                .ignoringFields(fields)
                .isEqualTo(sampleEntity);
    }


}