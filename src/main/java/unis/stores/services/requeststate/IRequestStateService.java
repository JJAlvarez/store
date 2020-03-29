package unis.stores.services.requeststate;

import unis.stores.entities.RequestState;

import java.util.List;

public interface IRequestStateService {

    List<RequestState> getRequestStates();
    RequestState getRequestState(int id);
    RequestState createRequestState(String name);
    RequestState updateRequestState(int id, String name);
    boolean deleteRequestState(int id);
    RequestState searchByName(String name);
}
