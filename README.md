### Meeting : 01/27/2025 @ 430pm  
# Basic java-postgreSQL-LoginFlow-02  
Diagram example of code in Layers:  

```
+--------------------+
|      Client        |
| (Browser/Postman)  |
+--------------------+
          |
          v
+--------------------+
|   Controller Layer |
| Handles HTTP       |
| requests/responses |
+--------------------+
          |
          v
+--------------------+
|    Service Layer   |
| Business Logic     |
| Orchestrates tasks |
+--------------------+
          |
          v
+--------------------+
|    Model Layer     |  <-- Represents entities like `User`
| Maps to Database   |
+--------------------+
          |
          v
+--------------------+
|   Repository Layer |
| Database Access    |
| (CRUD Operations)  |
+--------------------+
          |
          v

+--------------------+
|    Database Layer  |
| (PostgreSQL)       |
+--------------------+

```
App includes CRUD Operations for: Registration | Authentication | Authorization


