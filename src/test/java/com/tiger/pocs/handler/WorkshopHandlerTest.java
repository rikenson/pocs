package com.tiger.pocs.handler;

import com.tiger.pocs.paramResolver.*;
import com.tiger.pocs.payload.*;
import com.tiger.pocs.paramResolver.*;
import com.tiger.pocs.service.WorkshopService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.tiger.pocs.utils.Constants.UPDATED_WORKSHOP_NAME;
import static com.tiger.pocs.utils.Constants.UUID_VALUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith({
        MockitoExtension.class,
        WorkshopRequestParameterResolver.class,
        UpdatedWorkshopRequestParameterResolver.class,
        WorkshopResponseParameterResolver.class,
        ParticipantRequestParameterResolver.class,
        ParticipantResponseParameterResolver.class,
        ParticipantEntityParameterResolver.class
})
@Execution(ExecutionMode.CONCURRENT)
class WorkshopHandlerTest {

    @Mock
    private WorkshopService mockWorkshopService;

    @InjectMocks
    private WorkshopHandler workshopHandler;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Create new workshop succeeded -> Status: 201")
    void post_new_workshop_succeeded_201(WorkshopRequest request, WorkshopResponse response) {

        when(mockWorkshopService.add(request)).thenReturn(response);
        var underTest = workshopHandler.workshopPost(request);
        verify(mockWorkshopService, times(1)).add(request);

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(response);

    }

    @Test
    @DisplayName("Update existing workshop succeeded -> Status: 200")
    void put_exited_workshop_succeed_200(UpdatedWorkshopRequest request, WorkshopResponse response) {

        request.setName(UPDATED_WORKSHOP_NAME);
        response.setName(UPDATED_WORKSHOP_NAME);
        response.setVersion(1);
        response.setUpdatedAt(LocalDateTime.now());

        when(mockWorkshopService.edit(request, UUID_VALUE)).thenReturn(response);
        var underTest = workshopHandler.workshopEdit(request, UUID_VALUE);
        verify(mockWorkshopService, times(1)).edit(request, UUID_VALUE);

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(response);
        assertThat(Objects.requireNonNull(underTest.getBody()).getUpdatedAt()).isAfter(request.getUpdatedAt());
    }

    @Test
    @DisplayName("Add participants to a specific workshop succeeded -> Status: 200")
    void add_participants_to_specific_workshop_succeeded(
            ParticipantRequest participantRequest,
            ParticipantResponse participantResponse,
            WorkshopResponse response) {

        response.addParticipantsItem(participantResponse);
        when(mockWorkshopService.addParticipants(List.of(participantRequest), UUID_VALUE)).thenReturn(response);
        var underTest = workshopHandler.addParticipants(List.of(participantRequest), UUID_VALUE);
        verify(mockWorkshopService, times(1)).addParticipants(List.of(participantRequest), UUID_VALUE);

        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody())
                .usingRecursiveComparison()
                .isEqualTo(response);
        assertThat(Objects.requireNonNull(underTest.getBody()).getParticipants()).isNotNull();
    }


//    @Test
//    @DisplayName("Retrieve all workshops succeeded -> Status: 200")
//    void read_all_workshops_succeeded(WorkshopResponse response) {
//
//        var resp = List.of(response);
//        when(mockWorkshopService.retrieveAll()).thenReturn(resp);
//        var underTest = workshopHandler.workshopRetrieveAll();
//        verify(mockWorkshopService, times(1)).retrieveAll();
//
//        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(underTest.getBody())
//                .usingRecursiveComparison()
//                .isEqualTo(resp);
//    }


    @Test
    @DisplayName("Read a specific workshop by id succeeded -> Status: 200")
    void read_a_specific_workshop_by_id_succeeded(WorkshopResponse response) {

        when(mockWorkshopService.retrieve(UUID_VALUE)).thenReturn(response);
        var underTest = workshopHandler.workshopRetrieve(UUID_VALUE);
        verify(mockWorkshopService, times(1)).retrieve(UUID_VALUE);
        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody()).isEqualTo(response);
    }


    @Test
    @DisplayName("Remove a specific workshop by id succeeded -> Status: 200")
    void remove_specific_workshop_by_id_succeed() {

        var underTest = workshopHandler.remove(UUID_VALUE);
        verify(mockWorkshopService, times(1)).remove(UUID_VALUE);
        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(underTest.getBody()).isNull();
    }

//    @Test
//    @DisplayName("Remove a specific workshop by id failed -> Status: 200")
//    void remove_specific_workshop_by_id_failed() {
//        var currentId = UUID.randomUUID();
//        var underTest = workshopHandler.remove(currentId);
//        verify(mockWorkshopService, times(1)).remove(currentId);
//        doThrow(new RuntimeException()).when(mockWorkshopService).remove(currentId);
//        assertThat(underTest.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}