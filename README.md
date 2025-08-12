# SCORE-SIZZLE - Spring Boot Version

A personalized cricket scoring website built with Spring Boot, providing real-time match scoring, team management, and player statistics.

## 🏏 Features

- **Live Cricket Scoring**: Real-time match scoring with detailed statistics
- **Team Management**: Create and manage cricket teams efficiently
- **Player Profiles**: Comprehensive player statistics and performance tracking
- **Match Management**: Schedule, start, and track cricket matches
- **User Authentication**: Secure user registration and login system
- **Email Verification**: OTP-based email verification for new users
- **Responsive Design**: Modern, mobile-friendly user interface

## 🚀 Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: MongoDB
- **Frontend **: React
- **Email Service**: Spring Mail (Gmail SMTP)
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MongoDB instance (local or cloud)
- Gmail account for email service

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd score-sizzle
```

### 2. Configure Application Properties
Edit `src/main/resources/application.properties`:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=your_mongodb_connection_string
spring.data.mongodb.database=global

# Email Configuration
spring.mail.username=your_gmail@gmail.com
spring.mail.password=your_app_password
```

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:3030`

## 🏗️ Project Structure

```
src/main/java/com/scoresizzle/
├── ScoreSizzleApplication.java     # Main Spring Boot application
├── config/                         # Configuration classes
├── controller/                     # REST controllers
├── model/                         # Entity classes
├── repository/                    # Data access layer
└── service/                       # Business logic layer

src/main/resources/
├── static/                        # Static resources (CSS, JS, images)
├── templates/                     # Thymeleaf templates
└── application.properties         # Application configuration
```

## 🔧 API Endpoints

### Authentication & User Management
- `POST /get-started/checkduplicate` - Check email availability
- `POST /get-started/confirm_email` - Send OTP verification
- `POST /get-started/register` - User registration
- `POST /get-started/signin` - User login

### Feedback
- `POST /sendFeedback` - Submit user feedback

### Pages
- `GET /` - Home page
- `GET /get-started` - Signup/Login page
- `GET /dashboard` - User dashboard
- `GET /myteams` - Teams management
- `GET /mymatches` - Matches management
- `GET /new-match` - Create new match
- `GET /new-team` - Create new team

## 🗄️ Database Collections

- `registration_details` - User registration information
- `feedbacks` - User feedback submissions
- `teams` - Cricket teams data
- `matches` - Match information and scoring data

## 📧 Email Configuration

The application uses Gmail SMTP for sending emails. To configure:

1. Enable 2-factor authentication on your Gmail account
2. Generate an App Password
3. Update the email configuration in `application.properties`

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/score-sizzle-1.0.0.jar
```

### Docker (Optional)
```bash
docker build -t score-sizzle .
docker run -p 3030:3030 \
  -e SPRING_DATA_MONGODB_URI="mongodb://host.docker.internal:27017" \
  -e SPRING_DATA_MONGODB_DATABASE="global" \
  -e SPRING_MAIL_USERNAME="your_gmail@gmail.com" \
  -e SPRING_MAIL_PASSWORD="your_app_password" \
  score-sizzle
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the ISC License.

## 🆘 Support

For support and questions, please contact the development team or create an issue in the repository.

---

**Note**: This is a converted version of the original Node.js project to Spring Boot. The original project used Express.js and MongoDB with a different architecture.
