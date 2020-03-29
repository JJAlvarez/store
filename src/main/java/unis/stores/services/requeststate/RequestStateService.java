package unis.stores.services.requeststate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.RequestState;
import unis.stores.repositories.RequestStateRepository;

import java.util.List;

@Service
public class RequestStateService implements IRequestStateService {

    @Autowired
    private RequestStateRepository requestStateRepository;

    @Override
    public List<RequestState> getRequestStates() {
        return (List<RequestState>) requestStateRepository.findAll();
    }

    @Override
    public RequestState getRequestState(int id) {
        return requestStateRepository.findOne(id);
    }

    @Override
    public RequestState createRequestState(String name) {
        RequestState requestState = new RequestState();
        requestState.setName(name);

        return requestStateRepository.save(requestState);
    }

    @Override
    public RequestState updateRequestState(int id, String name) {
        if (!requestStateRepository.exists(id))
            return null;

        RequestState requestState = requestStateRepository.findOne(id);
        requestState.setName(name);

        return requestStateRepository.save(requestState);
    }

    @Override
    public boolean deleteRequestState(int id) {
        if (!requestStateRepository.exists(id))
            return false;

        requestStateRepository.delete(id);
        return true;
    }

    @Override
    public RequestState searchByName(String name) {
        return requestStateRepository.findByName(name);
    }
}
