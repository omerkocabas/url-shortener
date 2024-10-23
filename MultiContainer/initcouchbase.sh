#!/bin/bash

# Wait until Couchbase UI is up
until $(curl --output /dev/null --silent --head --fail http://localhost:8091/ui/index.html); do
    echo "Waiting for Couchbase to start..."
    sleep 5
done

# Initialize the cluster and create an admin user
curl -v -X POST http://127.0.0.1:8091/pools/default -d 'memoryQuota=512' -d 'indexMemoryQuota=512'
curl -v -u couchbase:couchbase -X POST http://127.0.0.1:8091/settings/web -d 'password=couchbase&username=couchbase&port=8091'

# Create a Couchbase bucket
curl -v -u couchbase:couchbase -X POST http://127.0.0.1:8091/pools/default/buckets -d 'name=UrlAnalytics' -d 'ramQuotaMB=100' -d 'bucketType=couchbase' -d 'flushEnabled=1'

# Optional: Add indexes or other setup
