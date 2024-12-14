# Hotel Reservation System

## Description
The Hotel Reservation System is a comprehensive JavaFX application that integrates PostgreSQL for managing hotel room availability, reservations, and hotel details. This project demonstrates the use of OOP principles, including inheritance and encapsulation,and connection to database to create a robust and user-friendly interface for hotel management tasks.

###Overview
The project aims to create a relational database for an online hotel reservation, including functionalities for  reserving a room with  entering details about ID of room , name of the customer and duration of stay in this hotel.


## Team Members List
- **Aiganysh Abdukaarova COMFCI-23**
- **Risolatkhon Madaminova COMFCI-23**


## Roles of Group Members
- **Aiganysh Abdukaarova**:
  - Database setup and schema design.
  - Data connection.
  - UI/UX design and integration using JavaFX.
- **Risolatkhon Madaminova**
  - Implementation of backend logic (data connections, queries).
  - Implement classes and their functions
  - Testing and debugging.

 
## Requirements
1. Connection with database
2. JavaFX design
3. Creating some buttons
4. View rooms button for knowing what kind of rooms we have
5. Admins of this application could make reservation with ID, name and how many days can will stay at hotel our clients
6. Application should show us list of reserved people
7. Information about hotel reservation on application
8. Add rooms button that will help admins to add one more room
9. Exit button 
10. Create vusually appealing application


## Deployment Instructions
1. Install Java 20 or later on the target system.
2. Install MySQL 8.x and create the BookstoreDB database using the provided SQL scripts in /scripts/create_tables.sql.
3. Update the database configuration in DatabaseConnection.java.
4. Build the application using Maven: mvn clean package.
5. Run the application:



## Using Instructions

1. Run the application: Start the program to begin managing room reservations.  
2. View Available Rooms: Click the "View Rooms" button to display a list of all available rooms, including their ID, type, and cost.  
3. Make a Reservation:  
   - After reviewing the room information, select a room for reservation by clicking the "Make Reservation" button.  
   - You will be prompted to enter the required customer details, including the room ID, customer name, and the duration of stay (in days).  
   - Once all details are entered, a confirmation message will display the reservation information.  
4. View Reservations: To see the list of all reservations, click the "View Reservations" button. The information displayed will include the room ID, customer name, reservation date, and the stay duration.  
5. Add New Rooms: As a receptionist, you can add new rooms by clicking the "Add Rooms" button. You will be asked to enter details such as the new room ID, type of room, and price. Once submitted, the new room will be added to the available rooms list.  
6. Exit the Application: When you are done, click the "Exit" button to close the application.


## Database diagram
![2024-12-12 16 59 07](https://github.com/user-attachments/assets/607bad87-7231-46d2-bd13-dfc2ca286303)



## Commits
<img width="914" alt="Screenshot 2024-12-15 at 00 20 55" src="https://github.com/user-attachments/assets/630a1e22-e50f-46bf-aac4-6a9aef19af07" />


## Screenshots
### 1. Main Screen  
<img width="599" alt="Screenshot 2024-12-15 at 00 21 47" src="https://github.com/user-attachments/assets/a3a2a186-9e18-43ed-87cf-039622d889b7" />


<img width="599" alt="Screenshot 2024-12-15 at 00 36 18" src="https://github.com/user-attachments/assets/d3f0f00b-34af-40ef-bef7-eda66959d640" />



<img width="599" alt="Screenshot 2024-12-15 at 00 36 44" src="https://github.com/user-attachments/assets/aaa8f4e1-771b-4861-85d9-ffd883a8a274" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 08" src="https://github.com/user-attachments/assets/5c693607-fa28-42d4-8127-0c21cc1b4afe" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 14" src="https://github.com/user-attachments/assets/4533095b-776b-4c0f-a5d6-e0e8922c26c5" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 24" src="https://github.com/user-attachments/assets/e442227f-c1f9-4dc3-a902-4effbe997875" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 39" src="https://github.com/user-attachments/assets/c678cc43-4c49-49ed-a34f-cbb44af196d8" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 43" src="https://github.com/user-attachments/assets/3de4c9c5-8e3a-4a49-8a34-f76d47a0c2b6" />



<img width="599" alt="Screenshot 2024-12-15 at 00 37 55" src="https://github.com/user-attachments/assets/679097a3-0783-4199-831f-11f63b48576d" />



<img width="599" alt="Screenshot 2024-12-15 at 00 38 04" src="https://github.com/user-attachments/assets/2e780d3e-ebdb-424c-885c-9dd2a139ab34" />




<img width="599" alt="Screenshot 2024-12-15 at 00 38 09" src="https://github.com/user-attachments/assets/5a2fbfc5-7a1f-42cd-925c-ce4f9d326691" />
\


<img width="599" alt="Screenshot 2024-12-15 at 00 38 12" src="https://github.com/user-attachments/assets/bc96bed0-855e-40eb-900a-def151fe6183" />



<img width="599" alt="Screenshot 2024-12-15 at 00 38 15" src="https://github.com/user-attachments/assets/f3a58819-0d76-40f4-84a3-0331ec890da2" />



<img width="599" alt="Screenshot 2024-12-15 at 00 38 22" src="https://github.com/user-attachments/assets/6586fbe1-0de2-475a-8adc-871897ea8395" />


<img width="599" alt="Screenshot 2024-12-15 at 00 38 25" src="https://github.com/user-attachments/assets/18d235be-4bcc-485b-a2e2-05edc4cce990" />



<img width="599" alt="Screenshot 2024-12-15 at 00 38 30" src="https://github.com/user-attachments/assets/c31a048e-19c7-40a5-8ddf-eb2656ee33ef" />







### UML diagram
![photo_2024-12-07 16 55 29](https://github.com/user-attachments/assets/29c15ef3-5efa-4660-a93d-d30714058e8c)


### Weekly Meeting Documentation
https://docs.google.com/document/d/11mxoQj7_o6EOdj6-RhD9X_zd2i2wZ--7CBbgX4DyAZA/edit?usp=sharing
