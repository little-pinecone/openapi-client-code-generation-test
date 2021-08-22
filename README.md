# openapi-client-code-generation-test

[![keep_growing logo](readme-images/logo_250x60.png)](https://keepgrowing.in/)

This is a demo project to test how [springdoc-openapi](https://github.com/springdoc/springdoc-openapi) and 
[openapi-generator-maven](https://openapi-generator.tech/docs/plugins#maven) plugins work in an example Spring Boot project.

## Prerequisites

* JDK 13+
* [Maven](https://maven.apache.org/) (or you can use `mvnw` provided in the project)

## Getting started

First, [clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository-from-github/cloning-a-repository) this repository.

Then, build it locally with:
```bash
mvn clean install
```

Finally, you can run the application with:

```bash
mvn spring-boot:run
```

## Profiles summary

The project can be built with various different profiles to allow for flexible configuration. Below you'll find a short summary of the available profiles.

### Spring profiles

* `angular` - used for client code generation. Applied automatically when the `angular` Maven profile is enabled.

### Maven profiles

* `angular` - for [generating client code](https://codesoapbox.dev/generate-client-code-from-spring-boot-using-maven/)

## API documentation

First, build and run the application. Then you'll be able to reach the API docs.

### Swagger

The Swagger UI page: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html):

![swagger screenshot](readme-images/swagger-screenshot.png)

### OpenAPI

The OpenAPI description in json format is available at the following url: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs). 
The API specification is also available in the `yaml` format under the http://localhost:8080/v3/api-docs.yaml link.

## Client code generation

### Compatibility

Right now `openapi-generator` supports Angular 11.0.0. 
Although the generated code works with Angular 12+, it's strongly recommended to update the generator plugin as soon as 
it starts supporting Angular 12.0.0 (see [Issue #9511](https://github.com/OpenAPITools/openapi-generator/issues/9511)).

### Generating code

To run client code generation using the `openapi-generator-maven-plugin` execute the following command:

```shell
cd backend
mvn clean verify -Pangular -DskipTests
```

The application will be started so that the API specification can be obtained from the open api endpoint. 
The generated code is available in the `/target/generated-sources/angular` directory. 
Don't edit files in this directory manually.

## Built With

* [Spring Boot v2.5+](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [springdoc-openapi](https://springdoc.org/)
* [OpenAPI Generator maven plugin](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [Dummy4j](https://daniel-frak.github.io/dummy4j/)
