package com.swingfrog.summer.app;

public class SummerConfig {

    private SummerApp app;
    private String projectPackage;
    private String libPath;
    private String serverProperties;
    private String redisProperties;
    private String dbProperties;
    private String taskProperties;
    private Long sessionQueueExpireTimeMs;
    private Long singleQueueExpireTimeMs;
    private boolean enableServiceRemoteProxy;

    public SummerApp getApp() {
        return app;
    }

    public void setApp(SummerApp app) {
        this.app = app;
    }

    public String getProjectPackage() {
        return projectPackage;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public String getLibPath() {
        return libPath;
    }

    public void setLibPath(String libPath) {
        this.libPath = libPath;
    }

    public String getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(String serverProperties) {
        this.serverProperties = serverProperties;
    }

    public String getRedisProperties() {
        return redisProperties;
    }

    public void setRedisProperties(String redisProperties) {
        this.redisProperties = redisProperties;
    }

    public String getDbProperties() {
        return dbProperties;
    }

    public void setDbProperties(String dbProperties) {
        this.dbProperties = dbProperties;
    }

    public String getTaskProperties() {
        return taskProperties;
    }

    public void setTaskProperties(String taskProperties) {
        this.taskProperties = taskProperties;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getSessionQueueExpireTimeMs() {
        return sessionQueueExpireTimeMs;
    }

    public void setSessionQueueExpireTimeMs(Long sessionQueueExpireTimeMs) {
        this.sessionQueueExpireTimeMs = sessionQueueExpireTimeMs;
    }

    public Long getSingleQueueExpireTimeMs() {
        return singleQueueExpireTimeMs;
    }

    public void setSingleQueueExpireTimeMs(Long singleQueueExpireTimeMs) {
        this.singleQueueExpireTimeMs = singleQueueExpireTimeMs;
    }

    public boolean isEnableServiceRemoteProxy() {
        return enableServiceRemoteProxy;
    }

    public void setEnableServiceRemoteProxy(boolean enableServiceRemoteProxy) {
        this.enableServiceRemoteProxy = enableServiceRemoteProxy;
    }

    public static final class Builder {
        private SummerApp app;
        private String projectPackage;
        private String libPath;
        private String serverProperties;
        private String redisProperties;
        private String dbProperties;
        private String taskProperties;
        private Long sessionQueueExpireTimeMs;
        private Long singleQueueExpireTimeMs;
        private boolean enableServiceRemoteProxy;

        private Builder() {
        }

        public static Builder aSummerConfig() {
            return new Builder();
        }

        public Builder app(SummerApp app) {
            this.app = app;
            return this;
        }

        public Builder projectPackage(String projectPackage) {
            this.projectPackage = projectPackage;
            return this;
        }

        public Builder libPath(String libPath) {
            this.libPath = libPath;
            return this;
        }

        public Builder serverProperties(String serverProperties) {
            this.serverProperties = serverProperties;
            return this;
        }

        public Builder redisProperties(String redisProperties) {
            this.redisProperties = redisProperties;
            return this;
        }

        public Builder dbProperties(String dbProperties) {
            this.dbProperties = dbProperties;
            return this;
        }

        public Builder taskProperties(String taskProperties) {
            this.taskProperties = taskProperties;
            return this;
        }

        public Builder sessionQueueExpireTimeMs(Long sessionQueueExpireTimeMs) {
            this.sessionQueueExpireTimeMs = sessionQueueExpireTimeMs;
            return this;
        }

        public Builder singleQueueExpireTimeMs(Long singleQueueExpireTimeMs) {
            this.singleQueueExpireTimeMs = singleQueueExpireTimeMs;
            return this;
        }

        public Builder enableServiceRemoteProxy(boolean enableServiceRemoteProxy) {
            this.enableServiceRemoteProxy = enableServiceRemoteProxy;
            return this;
        }

        public SummerConfig build() {
            SummerConfig summerConfig = new SummerConfig();
            summerConfig.setApp(app);
            summerConfig.setProjectPackage(projectPackage);
            summerConfig.setLibPath(libPath);
            summerConfig.setServerProperties(serverProperties);
            summerConfig.setRedisProperties(redisProperties);
            summerConfig.setDbProperties(dbProperties);
            summerConfig.setTaskProperties(taskProperties);
            summerConfig.setSessionQueueExpireTimeMs(sessionQueueExpireTimeMs);
            summerConfig.setSingleQueueExpireTimeMs(singleQueueExpireTimeMs);
            summerConfig.setEnableServiceRemoteProxy(enableServiceRemoteProxy);
            return summerConfig;
        }
    }
}
