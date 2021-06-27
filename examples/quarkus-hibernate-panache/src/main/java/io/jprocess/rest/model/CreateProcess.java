package io.jprocess.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProcess {
    private String templateId;
    private List<KeyValue> properties = new ArrayList<>();

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<KeyValue> getProperties() {
        return properties;
    }

    public void setProperties(List<KeyValue> properties) {
        this.properties = properties;
    }

    @JsonIgnore
    public Map<String, String> getPropertiesAsMap() {
        Map<String, String> map = new HashMap<>();
        if (this.properties != null) {
            properties.forEach(kv -> map.put(kv.getKey(), kv.getValue()));
        }
        return map;
    }
}
