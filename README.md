# GithubEvents-Listener-And-Retriever

## Clone the project and start as springboot application or use ./gradlew run command.
## Start ngrok to tunnel the port 8080 on localhost to web.Then use the ngrok generated url inside the dummy-github-events repo webhook url.
### The endpoint to listen github events in project is ngrokurl+ "/events".If you made any commit or other event on dummy-github-events repo, the github api will send the event to /events endpoint in our project web handler ,where we will be logging the received event data.

### To fetch the details for a repo simply provide the repoName and owner along with github api access token inside the application.properties file and hit baseProjectUrl + /fetchEvent endpoint.
