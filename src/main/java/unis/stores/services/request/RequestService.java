package unis.stores.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Product;
import unis.stores.entities.ProductRequest;
import unis.stores.entities.Request;
import unis.stores.entities.RequestState;
import unis.stores.repositories.ProductRepository;
import unis.stores.repositories.ProductRequestRepository;
import unis.stores.repositories.RequestRepository;
import unis.stores.repositories.RequestStateRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestStateRepository requestStateRepository;

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
    private ProductRepository productRepository;

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

        Request newRequest = new Request();
        newRequest.setDate(new Date());
        newRequest.setFabric(request.getFabric());
        newRequest.setProductRequests(new ArrayList<>());
        newRequest.setRequestState(request.getRequestState());

        newRequest = requestRepository.save(newRequest);
        for (ProductRequest productRequest :
                request.getProductRequests()) {
            ProductRequest newProductRequest = new ProductRequest();
            newProductRequest.setProduct(productRequest.getProduct());
            newProductRequest.setRequest(newRequest);
            newProductRequest.setRequestedStock(productRequest.getRequestedStock());
            newProductRequest = productRequestRepository.save(newProductRequest);

            newRequest.getProductRequests().add(newProductRequest);
        }
        return requestRepository.findOne(newRequest.getId());
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

    @Override
    public Request receiveRequest(int id) {
        if (!requestRepository.exists(id))
            return null;

        Request request = requestRepository.findOne(id);
        List<ProductRequest> productRequests = productRequestRepository.findAllByRequest_Id(request.getId());
        for (ProductRequest productRequest :
                productRequests) {
            Product product = productRepository.findOne(productRequest.getProduct().getId());
            product.setStock(product.getStock() + productRequest.getRequestedStock());
            productRepository.save(product);
        }

        RequestState requestState = requestStateRepository.findOne(2);
        request.setRequestState(requestState);
        return requestRepository.save(request);
    }
}
