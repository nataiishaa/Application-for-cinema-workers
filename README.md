# Application-for-cinema-workers


## Console application for employees (administrators and controllers)


It will allow an employee of a cinema (with a single auditorium) to manage data about films,
in rentals, and the schedule of their screenings, sell, and return tickets.
The ability to mark session visitors and view occupied and free seats after this mark has also been implemented.

All functional requirements for the application have been implemented:
1. Possibility of recording the sale of tickets to cinema visitors for a session with a choice of seats.
2. Implementation of the possibility of returning sold tickets to visitors before the start of the session.
3. Ability to display available and sold seats for the selected session.
4. The ability to edit data about films rented at the cinema and the schedule of their showings.
5. The ability to mark occupied seats in the hall by visitors to a particular session.


For additional points, the functionality of user registration with mandatory encryption of passwords and employee authorization at login has been implemented.


To work with the project, you need to store it from Github or unzip the folder.


Description of working with the application:



1. Authorization and Registration
When launching the application, the user is prompted to log in if they already have an account, or register.

Authorization (1): Enter your login and password to access the cinema functionality. If login is successful, you will be taken to the main menu.
Registration (2): If you do not have an account, create one by entering your unique username and password.
Exit (3): Quit the application if desired.


2. Main menu
After logging in, you will be taken to the main menu where you can select one of the following options:

Movie management (1): Add, delete and edit movies.
Working with sessions (2): Having a session time, selling and returning tickets, the ability to mark occupied seats in the hall by visitors to a specific session.
Exit (3): Quit the application



3. Movie management
When you select the "Manage Movies" option, you will be taken to the Movies menu where you can:

Add Movie (1): Enter the title of the new movie.
Delete Movie (2): Specify the movie number to delete.
Edit Movie (3): Change the title of the selected movie.
Return (4): Return to the main menu.


4. Working with sessions
By selecting the "Work with sessions" option, you can manage the schedule and ticket sales:

Change session time (1): Enter the session number and enter a new date and time.
Sell Ticket (2): Enter the session, the attendee's first and last name, and then select a seat.
Return ticket (3): Cancel your ticket reservation by indicating the session number and visitor details.
View Available Seats (4): Find out which seats are still available for sale at a specific session.
Mark visitors (5): For supervisors: indicate the session number and the numbers of seats that visitors have already occupied. (mark the visitor)
View occupied seats (6): For supervisory employees: view which seats are already occupied after the entry mark.
Add Show (7): Add a new show by selecting a movie and entering the date and time.
Return (8): Return to the main menu.


Data storage is also implemented in files (JSON). Choosing a data storage format such as JSON has several advantages that make it suitable for a given application:
JSON (JavaScript Object Notation) has an easy to read and understand format;
libraries for working with JSON are available in many programming languages (they are actively used in development and are often used in large projects);
It supports structured data in the form of objects and arrays. In the context of a cinema management application, this makes it easy to present films, shows and tickets sold in a hierarchical structure.


### Additional comments

The application makes extensive use of OOP and SOLID principles to effectively structure the code, ensuring high readability, flexibility and extensibility. The code follows JAVA design standards, which ensures uniformity and clean style in development.

During development, I wanted to use the DAO (Data Access Object) design pattern for the convenience of implementing a single interface for interacting with various data sources, such as databases, files, etc. But due to lack of time, I left only the implementation of classes and interfaces for further development and improvement of the code. I'm just learning how to write clean code and good code; I'll be glad to see comments

Thank you for being interested in this application and checking out my work! :)
