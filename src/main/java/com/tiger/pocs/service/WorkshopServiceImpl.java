package com.tiger.pocs.service;

import com.tiger.pocs.payload.ParticipantRequest;
import com.tiger.pocs.payload.UpdatedWorkshopRequest;
import com.tiger.pocs.payload.WorkshopRequest;
import com.tiger.pocs.payload.WorkshopResponse;
import com.tiger.pocs.domain.filter.WorkshopFilter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    @Override
    public WorkshopResponse add(WorkshopRequest request) {
        return null;
    }

    @Override
    public WorkshopResponse edit(UpdatedWorkshopRequest request, UUID uuid) {
        return null;
    }

    @Override
    public WorkshopResponse addParticipants(List<ParticipantRequest> participants, UUID uuid) {
        return null;
    }

    @Override
    public WorkshopResponse retrieve(UUID uuid) {
        return null;
    }

    @Override
    public List<WorkshopResponse> retrieveAll() {
        return Collections.emptyList();
    }

    @Override
    public List<WorkshopResponse> retrieveByCriteria(WorkshopFilter filter) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {
    }


}
