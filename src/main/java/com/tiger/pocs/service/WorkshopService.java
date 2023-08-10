package com.tiger.pocs.service;


import com.tiger.pocs.payload.ParticipantRequest;
import com.tiger.pocs.payload.UpdatedWorkshopRequest;
import com.tiger.pocs.payload.WorkshopRequest;
import com.tiger.pocs.payload.WorkshopResponse;
import com.tiger.pocs.domain.filter.WorkshopFilter;

import java.util.List;
import java.util.UUID;

public interface WorkshopService {

    WorkshopResponse add(WorkshopRequest request);

    WorkshopResponse edit(UpdatedWorkshopRequest request, UUID uuid);

    WorkshopResponse addParticipants(List<ParticipantRequest> participants, UUID uuid);

    WorkshopResponse retrieve(UUID uuid);

    List<WorkshopResponse> retrieveAll();
    List<WorkshopResponse> retrieveByCriteria(WorkshopFilter filter);

    void remove(UUID uuid);
}
