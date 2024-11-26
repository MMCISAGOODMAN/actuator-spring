package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;

@Component
public class SslCertificateInfoContributor implements InfoContributor {

    @Autowired
    private SslCertificateConfig sslCertificateConfig;

    @Override
    public void contribute(Info.Builder builder) {
        List<SslCertificateConfig.CertificateConfig> certificates = sslCertificateConfig.getCertificates();

        for (SslCertificateConfig.CertificateConfig certificateConfig : certificates) {
            addCertificateInfo(builder, certificateConfig);
        }
    }

    private void addCertificateInfo(Info.Builder builder, SslCertificateConfig.CertificateConfig certificateConfig) {
        try {

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(certificateConfig.getKeystorePath())) {
                keyStore.load(fis, certificateConfig.getKeystorePassword().toCharArray());
            }

            X509Certificate cert = (X509Certificate) keyStore.getCertificate(certificateConfig.getAlias());
            if (cert != null) {

                builder.withDetail(certificateConfig.getAlias() + "CertificateExpiry", cert.getNotAfter());
            } else {
                builder.withDetail(certificateConfig.getAlias() + "CertificateExpiry", "Certificate not found");
            }
        } catch (Exception e) {
            builder.withDetail(certificateConfig.getAlias() + "CertificateExpiry", "Error reading certificate: " + e.getMessage());
        }
    }
}

