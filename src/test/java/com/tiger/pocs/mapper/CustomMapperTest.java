package com.tiger.pocs.mapper;

import com.tiger.pocs.domain.entity.rdbms.SampleEntity;
import com.tiger.pocs.mapper.rdbms.CustomMapper;
import com.tiger.pocs.mapper.rdbms.CustomMapperImpl;
import com.tiger.pocs.paramResolver.SampleEntityParameterResolver;
import com.tiger.pocs.paramResolver.SampleRequestParameterResolver;
import com.tiger.pocs.paramResolver.SampleResponseParameterResolver;
import com.tiger.pocs.payload.SampleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.tiger.pocs.utils.Constants.CREATE_BY_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith({
        MockitoExtension.class,
        SampleEntityParameterResolver.class,
        SampleRequestParameterResolver.class,
        SampleResponseParameterResolver.class
})
@Execution(ExecutionMode.CONCURRENT)
class CustomMapperTest {
    private final CustomMapper mapper = new CustomMapperImpl();


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Map Sample request to sample entity succeeded ✅")
    void request_sample_request_to_sample_entity_succeeded(SampleRequest request, SampleEntity sampleEntity) {

        var fields = new String[]{"uuid", "createdAt", "createdBy", "version"};

        var underTest = mapper.requestToSampleEntity(request);
        assertAll(
                "Grouped Assertions of User",
                () -> assertThat(underTest)
                        .usingRecursiveComparison()
                        .ignoringFields(fields)
                        .isEqualTo(sampleEntity),
                () -> assertThat(underTest.getCreatedBy()).isEqualTo(CREATE_BY_USER)
        );
    }
}
