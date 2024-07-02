package com.renchi.APImocker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class MockService {

    private final List<MockConfiguration.MockServiceConfig> mocks;

    @Autowired
    public MockService(MockConfiguration mockConfiguration) {
        this.mocks = mockConfiguration.getMocks();
    }

    public String getResponse(String path, String method) throws IOException {
        for (MockConfiguration.MockServiceConfig mock : mocks) {
            if (mock.getPath().equals(path)) {
                Map<String, String> responses = mock.getResponses();
                String filePath = responses.get(method);
                if (filePath != null) {
                    Resource resource = new ClassPathResource(filePath);
                    return new String(Files.readAllBytes(Paths.get(resource.getURI())));
                }
            }
        }
        return null;
    }
}
