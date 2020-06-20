package io.github.hotspacode.neeza.server.api.dto;

import io.github.hotspacode.neeza.base.dto.NeezaClazz;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MockCacheContainer implements Serializable {
    private String appName;
    private List<Client> clients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockCacheContainer that = (MockCacheContainer) o;
        return appName.equals(that.appName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public static class Client{
        private String ip;
        private String port;
        private Set<NeezaClazz> mockClasses;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public Set<NeezaClazz> getMockClasses() {
            return mockClasses;
        }

        public void setMockClasses(Set<NeezaClazz> mockClasses) {
            this.mockClasses = mockClasses;
        }
    }

}
