package com.service;

import com.model.Request;
import com.model.User;
import com.repository.RequestRepositoryImpl;
import com.repository.UserRepositoryImpl;

import java.util.List;

public class RequestService {

    private final RequestRepositoryImpl requestRepository = new RequestRepositoryImpl();
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

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

    public void updateRequestAssignee(Long requestId, Long newAssigneeId) {
        // Get the request by its ID
        Request request = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new IllegalArgumentException("Request not found with id: " + requestId);
        }

        // Get the new assignee by their ID
        User newAssignee = userRepository.readUser(newAssigneeId);
        if (newAssignee == null) {
            throw new IllegalArgumentException("User not found with id: " + newAssigneeId);
        }

        // Set the new assignee
        request.setNewAssignee(newAssignee);

        // Update the request in the database
        requestRepository.updateRequest(request);
    }

    public void deleteRequest(Long requestId) {
         requestRepository.deleteById(requestId);
    }

}
