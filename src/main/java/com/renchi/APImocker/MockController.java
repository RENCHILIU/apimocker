package com.renchi.APImocker;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MockController {

    @Autowired
    private MockService mockService;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<String> handleRequest(@RequestHeader(value = "X-Method-Override", required = false) String methodOverride,
                                                HttpServletRequest request) throws IOException {
        String response = mockService.getResponse(request.getRequestURI(),
                methodOverride != null ? methodOverride : request.getMethod());
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
