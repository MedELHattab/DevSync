package com.repository;

import com.model.Request;
import java.util.List;

public interface RequestRepository {
    void saveRequest(Request request);
    List<Request> findRequestsByAssignee(Long assigneeId);
    List<Request> findAllRequests();
}

