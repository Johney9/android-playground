
package io.mihalek.nikola.gitretrofittest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Repository implements Serializable
{

    public String ownerUrl;
    public String url;
    public String htmlUrl;
    public String columnsUrl;
    public Integer id;
    public String nodeId;
    public String name;
    public String body;
    public Integer number;
    public String state;
    public Creator creator;
    public String createdAt;
    public String updatedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5967555899315381660L;

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Repository: " + name;
    }
}
