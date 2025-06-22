TwitterDemo
TwitterDemo is a Spring Boot-based RESTful API application that simulates a simplified Twitter-like platform. It allows users to register, activate their accounts via email, authenticate with JWT tokens, create posts, and view posts made by themselves or others. The application integrates secure user management with role-based authorization, token-based authentication, and provides API documentation through Swagger.

Key Features:
- User Registration & Activation: Users can register with validation and receive an email activation code to activate their account within a limited time.
- JWT Authentication: Secure login mechanism issuing JWT tokens, which protect endpoints and manage session state without using server-side sessions.
- Role-Based Authorization: Admin and user roles are supported, controlling access to specific endpoints.
- Post Management: Users can create new posts and fetch all posts or only their own posts.
- Security: Integration with Spring Security to enforce authentication and authorization, including a custom JWT token filter.
- Email Service: Sends activation codes via email using Spring Mail.
- API Documentation: Integrated Swagger UI for easy API exploration and testing.

Technologies Used:
▪ Java 21
▪ Spring Boot 3.4.5
▪ Spring Security
▪ JWT (JSON Web Tokens) with Auth0 library
▪ Spring Data JPA with PostgreSQL
▪ MapStruct for DTO and Entity mapping
▪ Spring Mail for sending emails
▪ Swagger (SpringDoc OpenAPI) for API docs
▪ Lombok to reduce boilerplate code
