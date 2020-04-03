package unis.stores.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.entities.Request;
import unis.stores.result.request.CreateRequestResult;
import unis.stores.result.request.GetRequestResult;
import unis.stores.result.request.UpdateRequestStateResult;
import unis.stores.services.request.RequestService;

@CrossOrigin
@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/request")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(requestService.getRequests());
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetRequestResult(false, "Bad Request", null));

        try {
            int requestId = Integer.parseInt(id);

            Request request = requestService.getRequest(requestId);

            if (request == null)
                return ResponseEntity.badRequest().body(new GetRequestResult(false, "Request doesn't exists", null));
            else
                return ResponseEntity.ok().body(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetRequestResult(false, e.getMessage(), null));
        }
    }

    @PostMapping("/request")
    public ResponseEntity<Object> create(Request request) {
        if (request.getDate() == null || request.getFabric() == null || request.getProducts() == null || request.getProducts().size() == 0)
            return ResponseEntity.badRequest().body(new CreateRequestResult(false, "Bad Request", null));

        Request createdRequest = requestService.createRequest(request);

        if (createdRequest == null)
            return  ResponseEntity.badRequest().body(new CreateRequestResult(false, "Error creating the request", null));
        else
            return ResponseEntity.ok(new CreateRequestResult(true, "Request created", createdRequest));
    }

    @PutMapping("/request")
    public ResponseEntity<Object> update(Request request) {
        if (request.getRequestState() == null)
            return ResponseEntity.badRequest().body(new CreateRequestResult(false, "Bad Request", null));

        Request updatedRequest = requestService.updateRequestState(request.getId(), request.getRequestState().getId());

        if (updatedRequest == null)
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request", null));
        else
            return ResponseEntity.ok(new UpdateRequestStateResult(false, "Request updated", updatedRequest));
    }
}
