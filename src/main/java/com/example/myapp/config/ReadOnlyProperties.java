package com.example.myapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.datasource")
public class ReadOnlyProperties {
    private List<ReadOnlyDataSourceProperty> readOnly;

    public List<ReadOnlyDataSourceProperty> getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(List<ReadOnlyDataSourceProperty> readOnly) {
        this.readOnly = readOnly;
    }

    public static class ReadOnlyDataSourceProperty {
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }
}
