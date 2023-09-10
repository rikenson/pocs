package com.tiger.pocs.handler;

import com.tiger.pocs.handler.rdbms.SampleHandler;
import com.tiger.pocs.paramResolver.SampleRequestParameterResolver;
import com.tiger.pocs.paramResolver.SampleResponseParameterResolver;
import com.tiger.pocs.paramResolver.UpdatedSampleRequestParameterResolver;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.service.sample.rdbms.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.tiger.pocs.utils.Constants.UPDATED_SAMPLE_NAME;
import static com.tiger.pocs.utils.Constants.UUID_VALUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith({
        MockitoExtension.class,
        SampleRequestParameterResolver.class,
        UpdatedSampleRequestParameterResolver.class,
        SampleResponseParameterResolver.class
})
@Execution(ExecutionMode.CONCURRENT)
class SampleEntityHandlerTest {

    @Mock
    private SampleService mockSampleService;

    @InjectMocks
    private SampleHandler sampleHandler;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Create new sample succeeded -> Status: 201")
    void post_new_sample_succeeded_201(SampleRequest request, SampleResponse response) {

        when(mockSampleService.add(request)).thenReturn(response);
        var underTest = sampleHandler.samplePost(request);
        verify(mockSampleService, times(1)).add(request);

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(response);

    }

    @Test
    @DisplayName("Update existing sample succeeded -> Status: 200")
    void put_exited_sample_succeed_200(SampleResponse request, SampleResponse response) {

        request.setName(UPDATED_SAMPLE_NAME);
        response.setName(UPDATED_SAMPLE_NAME);

        when(mockSampleService.edit(request, UUID_VALUE)).thenReturn(response);
        var underTest = sampleHandler.sampleEdit(request, UUID_VALUE);
        verify(mockSampleService, times(1)).edit(request, UUID_VALUE);

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(response);
    }


    @Test
    @DisplayName("Retrieve all samples succeeded -> Status: 200")
    void read_all_samples_succeeded(SampleResponse response) {

        var resp = List.of(response);
        when(mockSampleService.retrieveAll()).thenReturn(resp);
        var underTest = sampleHandler.sampleRetrieveAll();
        verify(mockSampleService, times(1)).retrieveAll();

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(resp);
    }


    @Test
    @DisplayName("Read a specific sample by id succeeded -> Status: 200")
    void read_a_specific_sample_by_id_succeeded(SampleResponse response) {

        when(mockSampleService.retrieve(UUID_VALUE)).thenReturn(response);
        var underTest = sampleHandler.sampleRetrieve(UUID_VALUE);
        verify(mockSampleService, times(1)).retrieve(UUID_VALUE);
        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody()).isEqualTo(response);
    }


    @Test
    @DisplayName("Remove a specific sample by id succeeded -> Status: 200")
    void remove_specific_sample_by_id_succeed() {

        var underTest = sampleHandler.remove(UUID_VALUE);
        verify(mockSampleService, times(1)).remove(UUID_VALUE);
        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody()).isNull();
    }

//    @Test
//    @DisplayName("Remove a specific sample by id failed -> Status: 200")
//    void remove_specific_sample_by_id_failed() {
//        var currentId = UUID.randomUUID();
//        var underTest = sampleHandler.remove(currentId);
//        verify(mockSampleService, times(1)).remove(currentId);
//        doThrow(new RuntimeException()).when(mockSampleService).remove(currentId);
//        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}