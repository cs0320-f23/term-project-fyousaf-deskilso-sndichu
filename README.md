<a name="readme-top"></a>

# Term Project - Fatima Yousaf, David Eskilson, and Stanley Ndichu

<!-- ABOUT THE PROJECT -->

## Contributors

David Eskilson (deskilso) - primarily focused on the backend server and databases, also worked on frontend Google OAuth and React Routing basics.

Fatima Yousaf (fyousaf) - primarily focused on the frontend, including React Routing, Google OAuth, design of the app, and the form.

Stanley Ndichu (sndichu) - primarily focused on the backend recommendation algorithm.

## Time to Complete the Project

100+ hours combined

## Github Repo

[Link](https://github.com/cs0320-f23/term-project-fyousaf-deskilso-sndichu)

## Project Description

This project is a full stack application that allows for giving and receiving recommendations about outfits appropriate to the weather. Our backend server includes endpoints to get an appropriate recommendation and to write to our backend database. Out frontend provides users the opportunity to report a recommendation and receive one.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

### Running the Program

This is an example of how to run the program:

- **Using IntelliJ, in the /back directory**:

  Use the built-in IntelliJ run feature in the Server file to start up the backend.

- **In the terminal, in the /front directory, type**:

```sh
  npm install
```

to install the necessary dependencies and then

```sh
  npm start
```

to start up the frontend.

Please note that you only need to install the dependencies locally once and each time they are updated.

### Testing the Program

- **Using IntelliJ, in the /back directory**:
  Use the built-in IntelliJ run feature in the testing files to run each test.

- **In the terminal, in the /front directory, type**:

```sh
  npm test
```

to run the jest unit tests and

```sh
  npx playwright test
```

to run the Playwright end to end tests.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Tests

### Testing Using Mocks:

On the backend, our datasource representing the database can be mocked in order to unit test the handler and recommendation algorithm without worrying about corrupting the real data that exists in the database.

### JUnit Unit Testing

We unit tested the databaseread handler through mocksing.

### Jest Unit Testing

Basic testing of event handlers and ensuring state is properly updated.

### JUnit Integration Testing

We used integration testing in our backend handler for the databasewrite endpoint in order to ensure that the writing worked properly and that the data could then be read through accessing the database proxy class.

### Playwright Integration Testing

Basic testing of website layout.

### Random Testing

Basic testing of writing wide array of information to the database and then reading it to ensure it was properly written.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Design Choices

### Classes and Interfaces

On the backend, the structure is the standard Spark Java server structure with handlers implementing the Route interface. We also have an interface representing the database to facilitate mocking.

### Accesibility:

Our page can be navigated using a ScreenReader. We added aria-labels to all interactive components, mainly the buttons.

### Errors/Bugs:

There are currently no known errors or bugs in our program.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
