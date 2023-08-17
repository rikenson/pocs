package com.tiger.pocs.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "aws.neptune")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class NeptuneConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private Boolean enableSsl;
    private String keystore;
    private String keystorePassword;
    private Integer maxConnectionPoolSize;
    private Integer maxInProcessPerConnection;
    private Integer maxSimultaneousUsagePerConnection;
    private Integer minSimultaneousUsagePerConnection;


    /**
     * @return Cluster
     */
    @Primary
    @Bean("NeptuneCluster")
    public Cluster cluster() {
        Cluster.Builder builder = Cluster.build()
                .addContactPoint(host)
                .port(port)
                .enableSsl(enableSsl)
                .credentials(username, password)
                .maxConnectionPoolSize(maxConnectionPoolSize)
                .maxInProcessPerConnection(maxInProcessPerConnection)
                .maxSimultaneousUsagePerConnection(maxSimultaneousUsagePerConnection)
                .minSimultaneousUsagePerConnection(minSimultaneousUsagePerConnection);

        if (Boolean.TRUE.equals(enableSsl))
            builder
                    .keyStore(keystore)
                    .keyStorePassword(keystorePassword);

        return builder.create();
    }
}
