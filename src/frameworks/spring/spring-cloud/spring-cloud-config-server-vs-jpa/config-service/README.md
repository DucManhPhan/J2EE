





## Table of Contents
- [Running config-service](#running-config-service)
- [Get all configuration from config-service's database](#get-all-configuration-from-config-services-database)
- [Update config parameter to config-service's database](#update-config-parameter-to-config-services-database)
- [Cache Management APIs](#cache-management-apis)
    
    - [Debug Cache Contents](#debug-cache-contents)
    - [Test Cache Population](#test-cache-population)

- [Wrapping up](#wrapping-up)

<br>

## Running config-service

```
./gradlew bootRun
```


<br>

## Get all configuration from config-service's database

```bash
curl --location 'http://localhost:8080/config-service/client-service/default'
```

Sample response:

```JSON
{
    "name": "client-service",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "client-service",
            "source": {
                "account.prefix.name": "changed_value1_as_4",
                "acc.key2": "value3_as",
                "acc.key1": "value2_as"
            }
        }
    ]
}
```


<br>

## Update config parameter to config-service's database

```bash
curl --location --request PUT 'http://localhost:8081/config-service/config?serviceName=client-service&key=account.prefix.name&value=changed_value1_as_5&status=active'
```

Sample response:

```
Successfully update config parameter
```


<br>

## Cache Management APIs

### Debug Cache Contents
View all cached configuration data:

```bash
curl --location 'http://localhost:8081/config-service/config/cache/debug'
```

Response example:

```bash
Cache Contents:
Cache: config-environments
Cache implementation: ConcurrentMapCache
Cache size: 1
Cache Key: client-service
Application: client-service
Profiles: [Ljava.lang.String;@113f886f
Label: master
Properties:
  PropertySource: client-service
    account.prefix.name = changed_value1_as_5
    acc.key2 = value3_as
    acc.key1 = value2_as
---
```

### Test Cache Population

Manually populate cache for specific application:

```
curl --location 'http://localhost:8081/config-service/config/cache/test/client-service'
```

Sample response:

```
Cache populated for: client-service
```


<br>

## Wrapping up



