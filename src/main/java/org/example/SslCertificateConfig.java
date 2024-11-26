package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ssl")
public class SslCertificateConfig {

    private List<CertificateConfig> certificates;

    public List<CertificateConfig> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateConfig> certificates) {
        this.certificates = certificates;
    }

    public static class CertificateConfig {
        private String keystorePath;
        private String keystorePassword;
        private String alias;

        // Getters and Setters
        public String getKeystorePath() {
            return keystorePath;
        }

        public void setKeystorePath(String keystorePath) {
            this.keystorePath = keystorePath;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public void setKeystorePassword(String keystorePassword) {
            this.keystorePassword = keystorePassword;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
