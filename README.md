# Fratcher - Friend Matcher

This project was build following the Web Engineering course at the University of Kassel. The idea was to build a web application
including different technologies. 
The concept of the app is as follows:
Users should be able to register and login securely and get encoded tokens fro their activity inside the app. When a users is succesfully logged in, he can choose between options of finding a match or listening the matches he got. Finding a Match lists one random status from another anonymous user in the app. You can like/dislike the status. In each case you dont get to see the status anymore. If the othe user liked your status too, you get a match and you can see it by switching to the Match List. By clicking the match which is named after the email of the other user, you switch to a chat room and you can chat with each other.

## Installation

1. Download and install node.js from https://nodejs.org/en/
2. Go to the ./src/main/frontend directory and run
```
 npm install in
```
3. In ./src/main/frontend/src run
```
 npm index.js
```
4. Open your browser and give the url
```
 localhost:8080/
```

### For developers

While editing the frontend components you "npm start" from the terminal to notify the changes to the browser in RT.

### Current state

1. Adding Features
2. **Refactoring**
3. **Frontend Styling**
4. Scaling

## Primary objectives

1. Working web application with all functionality listed in the latter paragraph
2. Usage of the presented technologies in the lecture
3. Deployment on web service providers for app deployment (Heroku)
4. Platform independent

### Secondary objective

1. Fancy design
2. Scalability

### Application design - Features
1. Login
2. Register
3. Find Match
4. List matches
5. Chat with messages
6. Authentication security

## Deployment

* [Heroku](https://fratcher-app.herokuapp.com/) - a platform as a service (PaaS) that enables developers to build, run, and operate applications entirely in the cloud.- 

## Technologies

1. Spring as framework for the backend
2. Hibernate as a file-based Database
3. Postgreas as a Database for Heroku deployment
4. React 
5. Axios

## Structure
![alt text](https://github.com/Perdoci/Fratcher/blob/master/src/main/frontend/assets/fratcher%20class%20diagram.PNG)

## Author

Marsel Perdoci - marsel.perdoci@hotmail.com 



