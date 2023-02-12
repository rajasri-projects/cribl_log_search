# Cribl - Log Search

The Cribl log search application is for querying the logs from a back-end server through a REST API exposed on that server. The logs can be queried through a UI, that is a part of the same application.

## Design

From a high level, the architecture has two main components.

A front-end, that provides a UI for the user to interact with the application. Using the search functionality that's part of the UI, the user can query for the logs (and specify the filters if required)

A back-end, that exposes the REST API that does the querying / searching of the logs. The front-end invokes the REST API on the back-end.

To help with the high level visualization of the system, refer the below diagram:

![High Level System Diagram](https://raw.githubusercontent.com/rajasri-projects/cribl_log_search/main/src/main/webapp/WEB-INF/resources/images/Cribl_Log_Search.png)

### Technologies

The front-end has been built using a combination of HTML, CSS and JavaScript. JQuery and Bootstrap have also been used to simplify the logic and to provide a richer set of functionalities available through those frameworks. For example, Bootstrap has very good support for Layouts through Containers, that helps with organizing the content cleanly on the UI. JQuery selectors are extremely powerful for selection of the elements in the DOM (Document Object Model).

The back-end has been built using Spring MVC, with Tomcat as the container (server) on which the application is deployed.

The back-end exposes a REST API for searching the logs, that the front-end invokes via AJAX. The response from the back-end is streamed to the front-end and the front-end has the capability to incrementally display the information coming from the back-end.

### Filters

The application supports 3 types of filters:

filename - The user can (optionally) specify a particular log file that they want to query.

pattern - The user can (optionally) specify a pattern that they want to filter on. The pattern can either be a string or a regex. The pattern cannot contain spaces.

match_count - The user can (optionally) specify the number of records that they want in the output.

## Usage

To bring up the application locally and to query the logs, perform the below steps:

#### Installation (Optional)
The application uses JDK 17 and Gradle (for build).

If these are not available on your environment, install them from these locations - [JDK](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) and [Gradle](https://gradle.org/install/)

#### Checkout the git repository locally

Issue a git clone command to check out the repository locally into your workspace.

```
git clone https://github.com/rajasri-projects/cribl_log_search.git
```

#### Set the log path

Set the log path in [SearchService.java](https://github.com/rajasri-projects/cribl_log_search/blob/main/src/main/java/com/cribl/service/SearchService.java#L19) to point to a path on your system where the log files are present.

Populate the path on your system with some sample log files (any text file with few lines in it)

*In the future, this could be configured to be a common path for everyone, provided everyone has permissions to that same common path OR It could be accepted as an input from the user. For now, modify the location as mentioned above.*

#### Bring up the application (server)

The application uses the gradle build system & tomcat as the container.

Issue the below command from within the workspace, where the git repository has been cloned into.

```
./gradlew tomcatRun --no-daemon -debug
```

Note:

The above command brings up the server in debug mode (because of specifying the debug option) and hence you will see a lot of messages on your terminal. This is expected.

Once the command stops running, you should see a line resembling the below (to indicate that the server is up and running fine)

```
The Server is running at http://localhost:8080
```

#### Open the application on your browser

Open chrome (or any other browser) and enter the URL:

```
http://localhost:8080
```

You should see the UI for the Cribl - Log Search application.

#### Search for logs

You can search for logs by specifying the filters in the text-box and hitting 'Search'

Few sample filters:

```
filename:application_log # Get all lines from that file
filename:application_log match_count:10 # Get the latest 10 lines from that file
filename:application_log pattern:ERROR # Get all lines that contain the word ERROR from that file
filename:application_log pattern:ERROR match_count:10 # Get all lines that contain the word ERROR from that file, but limit the output to 10 lines
```

#### Verify output

Upon clicking 'Search', the UI should start receiving the logs from the back-end. The logs are streamed from the back-end. So, the UI will auto-update with the (new) information as and when it's received.

## Limitations

The application currently uses an embedded tomcat container (server). The embedded container contains timeouts that are not configurable. So, streaming a lot of data from the back-end to the front-end could possibly (socket) time out before it's completely finished. This will / can be mitigated if the application is deployed into a stand-alone tomcat container, with customized values for timeouts.

## Future (Enhancements)

The setup process could be automated a bit, so that the installation (of JDK, Gradle), cloning of repository and bringing up the server can be done through a single script / command.

The application can be deployed into a stand-alone tomcat container (to overcome the limitation mentioned above)

The application currently uses Apache's [ReversedLinesFileReader](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/src-html/org/apache/commons/io/input/ReversedLinesFileReader.html), which uses an internal buffer of size 8K to read the file in the reverse order (newest first). It would be beneficial to compare the performance of the system with various different buffer sizes.

The application has good unit test coverage. We should add support for integration testing and E2E to test the functionality of the system thoroughly.







