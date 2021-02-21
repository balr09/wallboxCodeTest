# Other Tasks

Answers to Tasks 2 and 3 are contained in 'tasks2-3.md' file, included in this project.

# Disclaimer

Giphy API is not properly working and pretty unstable. Never, during test development, returned a positive result. 

On the other hand, after a few requests it wouldn't even return an empty JSON, but a persisting 429 Unknown error. 

After several minutes without executing requests, API goes back to the empty response.

So, tests are based on two things:

1. Endpoint description at https://developers.giphy.com/docs/api/endpoint#search
2. A lot of guessing

And that's why some tests be lack data consistency. Sorry about that.

Also, the URL above shows, in fact, two different endpoints. The one for gifs persistently returns the error "You cannot consume this service". So only the 'stickers' one is being tested.

A total of 14 tests are being run. If any request had ever been successful the response content would have led to other useful tests. But never saw a "good" answer from this API, so...

# Requirements

In order to execute tests you need (locally):

1. JDK 9+
2. Maven 3.6
3. Internet connection

# How to execute test

Inside project's (using terminal) just type:

> mvn site

This command will also generate the report.

# Reports

The folder 'site' is created (inside 'target' folder) containing reports. 

In order to see test execution results, point your browser to:

> target/site/surefire-report.html


