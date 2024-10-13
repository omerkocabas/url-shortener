package com.example.demo.Config;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";  // Replace with your Couchbase cluster IP or hostname
    }

    @Override
    public String getUserName() {
        return "couchbase";
    }

    @Override
    public String getPassword() {
        return "couchbase";
    }

    @Override
    public String getBucketName() {
        return "UrlAnalytics";
    }

    @Bean
    public Cluster couchbaseCluster(ClusterEnvironment environment) {
        return Cluster.connect(getConnectionString(), ClusterOptions.clusterOptions(getUserName(), getPassword()).environment(environment));
    }

    @Bean
    public Bucket couchbaseBucket(Cluster couchbaseCluster) {
        return couchbaseCluster.bucket(getBucketName());
    }
}

