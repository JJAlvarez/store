package unis.stores.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Request;
import unis.stores.entities.RequestState;
import unis.stores.repositories.RequestRepository;
import unis.stores.repositories.RequestStateRepository;

import java.util.List;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestStateRepository requestStateRepository;

    @Override
    public List<Request> getRequests() {
        return (List<Request>) requestRepository.findAll();
    }

    @Override
    public Request getRequest(int id) {
        return requestRepository.findOne(id);
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequestState(int id, int requestStateId) {
        if (!requestRepository.exists(id) || !requestStateRepository.exists(requestStateId))
            return null;

        RequestState requestState = requestStateRepository.findOne(requestStateId);
        Request request = requestRepository.findOne(id);

        request.setRequestState(requestState);

        return requestRepository.save(request);
    }
}
