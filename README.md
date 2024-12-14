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


<img width="453" alt="Screenshot 2024-12-10 at 18 52 30" src="https://github.com/user-attachments/assets/8a16ac78-7a13-4e0a-8775-aa40da075fd0">



<img width="453" alt="Screenshot 2024-12-10 at 18 52 39" src="https://github.com/user-attachments/assets/f3aa7ae6-b49d-4f8b-9953-f0a9237e9b23">


<img width="453" alt="Screenshot 2024-12-10 at 18 52 52" src="https://github.com/user-attachments/assets/aedaa0dd-7bb7-477f-9718-0e76be39a027">


<img width="453" alt="Screenshot 2024-12-10 at 18 53 12" src="https://github.com/user-attachments/assets/54ea7d7b-385d-4571-8e23-4307a9a98d02">


<img width="453" alt="Screenshot 2024-12-10 at 18 53 25" src="https://github.com/user-attachments/assets/e247a55d-2a21-4641-8db8-5d23a4debc61">


<img width="453" alt="Screenshot 2024-12-10 at 18 53 28" src="https://github.com/user-attachments/assets/ef16e972-6c84-4131-9322-6d8142bebbc6">


<img width="493" alt="Screenshot 2024-12-10 at 18 53 44" src="https://github.com/user-attachments/assets/a1171018-c5f9-4723-845e-82bb3a60e7d2">


<img width="493" alt="Screenshot 2024-12-10 at 18 54 00" src="https://github.com/user-attachments/assets/c5f101f8-432c-48b4-87d9-d70c0739424c">


<img width="493" alt="Screenshot 2024-12-10 at 18 54 05" src="https://github.com/user-attachments/assets/6704b485-581b-4bfd-b5cb-b6fe17399d8c">


<img width="493" alt="Screenshot 2024-12-10 at 18 54 09" src="https://github.com/user-attachments/assets/71aaace1-c320-4b0f-ab88-6075af0059ae">



### UML diagram
![photo_2024-12-07 16 55 29](https://github.com/user-attachments/assets/29c15ef3-5efa-4660-a93d-d30714058e8c)


### Weekly Meeting Documentation
https://docs.google.com/document/d/11mxoQj7_o6EOdj6-RhD9X_zd2i2wZ--7CBbgX4DyAZA/edit?usp=sharing
