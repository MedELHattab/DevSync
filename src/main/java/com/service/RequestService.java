package com.service;

import com.model.Request;
import com.repository.RequestRepositoryImpl;

import java.util.List;

public class RequestService {

    private final RequestRepositoryImpl requestRepository = new RequestRepositoryImpl();

    public void createRequest(Request request) {
        // Save the request
        requestRepository.saveRequest(request);
    }

    // Fetch requests by assignee ID
    public List<Request> getRequestsByAssignee(Long assigneeId) {
        // Fetch requests for the given assignee
        return requestRepository.findRequestsByAssignee(assigneeId);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAllRequests(); // Assuming you implement this method in the repository
    }

}
