package unis.stores.controller;

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
    public ResponseEntity<Object> create(@RequestBody Request request) {
        if (request.getFabric() == null || request.getProductRequests() == null || request.getProductRequests().size() == 0)
            return ResponseEntity.badRequest().body(new CreateRequestResult(false, "Bad Request", null));

        Request createdRequest = requestService.createRequest(request);

        if (createdRequest == null)
            return  ResponseEntity.badRequest().body(new CreateRequestResult(false, "Error creating the request", null));
        else
            return ResponseEntity.ok(new CreateRequestResult(true, "Request created", createdRequest));
    }

    @PutMapping("/request/cancel/{id}")
    public ResponseEntity<Object> cancelRequest(@PathVariable("id") String id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
        try {
            int requestId = Integer.parseInt(id);
            Request updatedRequest = requestService.updateRequestState(requestId, 3);

            if (updatedRequest == null)
                return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request", null));
            else
                return ResponseEntity.ok(new UpdateRequestStateResult(false, "Request updated", updatedRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
    }

    @PutMapping("/request/receive/{id}")
    public ResponseEntity<Object> receiveRequest(@PathVariable("id") String id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
        try {
            int requestId = Integer.parseInt(id);
            Request updatedRequest = requestService.receiveRequest(requestId);

            if (updatedRequest == null)
                return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Error updating the request", null));
            else
                return ResponseEntity.ok(new UpdateRequestStateResult(false, "Request updated", updatedRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateRequestStateResult(false, "Bad Request", null));
        }
    }
}
