package com.renchi.APImocker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "mock")
public class MockConfiguration {

    private List<MockServiceConfig> mocks;

    // getters and setters

    public List<MockServiceConfig> getMocks() {
        return mocks;
    }

    public void setMocks(List<MockServiceConfig> mocks) {
        this.mocks = mocks;
    }

    public static class MockServiceConfig {
        private String path;
        private List<String> methods;
        private Map<String, String> responses;

        // Getters and setters

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<String> getMethods() {
            return methods;
        }

        public void setMethods(List<String> methods) {
            this.methods = methods;
        }

        public Map<String, String> getResponses() {
            return responses;
        }

        public void setResponses(Map<String, String> responses) {
            this.responses = responses;
        }
    }
}
