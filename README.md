# AgrStore - Agricultural Products Store Management System

## Overview

AgrStore is a comprehensive web application designed to manage an online agricultural products store. The system provides a platform for buying and selling agricultural products, with complete functionality from product management, categories, orders to online payments.

## Technologies Used

- **Backend**: Java, Spring MVC, Hibernate
- **Frontend**: JSP, HTML, CSS, JavaScript
- **Database**: Microsoft SQL Server
- **Server**: Apache Tomcat 9.0
- **Payment**: PayPal API Integration
- **Security**: Spring Security, reCAPTCHA
- **Development Tools**: Eclipse IDE

## System Architecture

The project is built following the MVC (Model-View-Controller) pattern with a multi-layered architecture:

- **Model**: Entity classes representing data in the system
- **View**: JSP templates combined with HTML/CSS/JS
- **Controller**: Handles business logic and routing
- **Service**: Provides business services
- **DAO**: Database access and operations

## Key Features

### User Role Management

- **Customers**: Register, login, shop, make payments
- **Staff**: Manage products, categories, orders, customers
- **Administrators**: Manage the entire system, staff, suppliers

### Product Management

- Add, edit, delete products
- Categorize products
- Manage product images
- Inventory management

### Order Management

- Create new orders
- Track order status
- View order history
- Manage order details

### Shopping Cart and Payment

- Add products to cart
- Update product quantities
- Online payment via PayPal
- Order confirmation

### Supplier Management

- Add, edit, delete suppliers
- Manage contact information
- Track products by supplier

### Feedback Management

- Customer feedback submission
- Manage and respond to customer feedback

### Security

- User authentication
- Access control
- Form protection with reCAPTCHA
- Password encryption

## Project Structure

agrStore/
├── src/
│ └── main/
│ ├── java/
│ │ └── agrStore/
│ │ ├── DAO/ # Data Access Objects interfaces
│ │ ├── DAOImpl/ # DAO implementations
│ │ ├── bean/ # Java beans
│ │ ├── controller/ # Controllers for different user roles
│ │ │ ├── admin/ # Admin controllers
│ │ │ ├── customer/ # Customer controllers
│ │ │ └── staff/ # Staff controllers
│ │ ├── database/ # Database configurations
│ │ ├── entity/ # Entity classes
│ │ ├── interceptor/ # Request interceptors
│ │ ├── paypal/ # PayPal integration
│ │ ├── recaptcha/ # reCAPTCHA integration
│ │ ├── service/ # Service interfaces
│ │ ├── serviceImpl/ # Service implementations
│ │ └── utility/ # Utility classes
│ └── webapp/
│ ├── WEB-INF/
│ │ ├── configs/ # Spring configurations
│ │ ├── lib/ # Libraries
│ │ ├── views/ # JSP views
│ │ │ ├── admin/ # Admin views
│ │ │ ├── customer/ # Customer views
│ │ │ ├── include/ # Shared components
│ │ │ └── staff/ # Staff views
│ │ └── web.xml # Web application configuration
│ └── assets/ # Static resources
│ ├── admin/ # Admin resources
│ ├── cart/ # Cart resources
│ ├── category-images/ # Category images
│ ├── landing/ # Landing page resources
│ ├── logos/ # Logo images
│ ├── provider-images/ # Provider images
│ └── user-images/ # User profile images

## System Requirements

- JDK 19 or higher
- Apache Tomcat 9.0
- Microsoft SQL Server
- Maven (dependency management)

## Installation and Deployment

1. **Database Preparation**

   - Create database `DB_WEBNONGSAN4` in SQL Server
   - Configure connection in `src/main/webapp/WEB-INF/configs/database.properties`

2. **Environment Configuration**

   - Install JDK 19
   - Install Apache Tomcat 9.0
   - Configure Eclipse with Tomcat

3. **Import Project into Eclipse**

   - Import project as Maven project
   - Update Maven dependencies

4. **Deploy Application**
   - Build the project
   - Deploy to Tomcat server
   - Access the application at `http://localhost:8080/agrStoreManagement`

## Key Highlights

- **User-Friendly Interface**: Responsive design, easy to use for both users and administrators
- **Secure Payment**: PayPal integration for safe online payments
- **Multi-level Management**: Detailed permissions for each user type
- **Search and Filter**: Support for product search by multiple criteria
- **Reports and Statistics**: Provides reports on sales, orders, inventory

## Contact and Support

If you have any questions or need support, please contact:

- **Email**: huutri10a3vvk@gmail.com
- **Website deployed at**: agr.netlify.app
