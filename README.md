# TedTalks backend
This project is meant to simulate a web scrapper that fetches data on TedTalks, stores it in a local DB and provide
tools to manage it and run some analysis.

## Specifications
- Docker / Docker compose (Version: 24.0.2+)
- Java 17

## Deployment
Step 1 - Deploy the environment
```
sudo ./deploy.sh
```

Step 2 - Apply migrations (**_do not skip this step, especially on the 1st run_**)
```
sudo ./migrations/migrate.sh
```

# Postman collection
For ease of use, a Postman collection is provided: `ioDigitalTedTalks.postman_collection.json`

# Usage
Initially, after following the deployment steps, the database will be empty.<br/>
Call the refresh endpoint to "_fetch TedTalks from the web_". It will fetch a few talks at a time, after which all other endpoints can be used to perform analysis and normal CRUD operations.
