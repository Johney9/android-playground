
package io.mihalek.nikola.gitretrofittest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Creator implements Serializable
{

    public String login;
    public Integer id;
    public String nodeId;
    public String avatarUrl;
    public String gravatarId;
    public String url;
    public String htmlUrl;
    public String followersUrl;
    public String followingUrl;
    public String gistsUrl;
    public String starredUrl;
    public String subscriptionsUrl;
    public String organizationsUrl;
    public String reposUrl;
    public String eventsUrl;
    public String receivedEventsUrl;
    public String type;
    public Boolean siteAdmin;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -654309379140142449L;

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
