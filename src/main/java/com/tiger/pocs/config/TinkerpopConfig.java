package com.tiger.pocs.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Configuration
@ConfigurationProperties(prefix = "thinkerpop.db")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TinkerpopConfig {
    private String host;
    private Integer port;

    @Bean("cluster")
    public Cluster cluster() {
        Cluster.Builder builder = Cluster.build();
        builder.addContactPoint(host);
        builder.port(port);
        return builder.create();

    }

    @Primary
    @Bean("CustomGraphTraversalSource")
    public GraphTraversalSource graphTraversalSource(Cluster cluster) {
        return traversal().withRemote(DriverRemoteConnection.using(cluster));
    }
}
