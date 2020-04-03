package unis.stores.services.request;

import unis.stores.entities.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getRequests();
    Request getRequest(int id);
    Request createRequest(Request request);
    Request updateRequestState(int id, int requestStateId);
}
