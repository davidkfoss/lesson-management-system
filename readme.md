[---------------nav---------------](/docs/nav.md)

## Requirements

Note that [maven](https://maven.apache.org/install.html) is required to run the application.

## How to Run

### Step 1: Build the project

```bash
cd LessonManagement
mvn clean package
```

### Step 2: Run the Application

```bash
mvn exec:java -Dexec.mainClass="com.example.lessonmanagement.client.ConsoleApp"
```

---

## Application Overview

### **Main Application**

- [ConsoleApp.java](LessonManagement/src/main/java/com/example/lessonmanagement/client/ConsoleApp.java): The main entry point for the application.

### **Models**

- [Lesson.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/Lesson.java): Represents a lesson.
- [User.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/User.java): Represents a student or tutor.
- [Booking.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/Booking.java): Represents a lesson booking.
- [Review.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/Review.java): Represents a review for a tutor.
- [LessonPackage.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/LessonPackage.java): Represents a lesson package containing multiple sessions.
- [CustomizationRequest.java](LessonManagement/src/main/java/com/example/lessonmanagement/model/CustomizationRequest.java): Represents a request for lesson customization.

### **Repositories**

- [UserRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/UserRepository.java): Stores and manages users.
- [LessonRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/LessonRepository.java): Stores and manages lessons.
- [BookingRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/BookingRepository.java): Stores and manages lesson bookings.
- [ReviewRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/ReviewRepository.java): Stores and retrieves reviews.
- [LessonPackageRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/LessonPackageRepository.java): Manages lesson packages.
- [CustomizationRequestRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/CustomizationRequestRepository.java): Manages lesson customization requests.
- [NotificationRepository.java](LessonManagement/src/main/java/com/example/lessonmanagement/repository/NotificationRepository.java): Stores and manages tutor notifications.

### **Services**

- [UserService.java](LessonManagement/src/main/java/com/example/lessonmanagement/service/UserService.java): Handles user-related operations.
- [LessonService.java](LessonManagement/src/main/java/com/example/lessonmanagement/service/LessonService.java): Handles lesson-related operations.
- [ReviewService.java](LessonManagement/src/main/java/com/example/lessonmanagement/service/ReviewService.java): Manages reviews and tutor profiles.
- [TutorNotificationService.java](LessonManagement/src/main/java/com/example/lessonmanagement/observer/TutorNotificationService.java): Manages notifications sent to tutors.

### **Factories**

- [UserFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/UserFactory.java): Creates user objects.
- [LessonFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/LessonFactory.java): Creates lesson objects.
- [LessonPackageFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/LessonPackageFactory.java): Creates lesson package objects.
- [CustomizationRequestFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/CustomizationRequestFactory.java): Creates customization request objects.
- [ReviewFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/ReviewFactory.java): Creates review objects.
- [BookingFactory.java](LessonManagement/src/main/java/com/example/lessonmanagement/factory/BookingFactory.java): Creates booking objects.

### **Strategy Pattern (Pricing Strategies)**

- [PricingStrategy.java](LessonManagement/src/main/java/com/example/lessonmanagement/strategy/PricingStrategy.java): Interface for lesson pricing strategies.
- [FlatRatePricing.java](LessonManagement/src/main/java/com/example/lessonmanagement/strategy/FlatRatePricing.java): Implements flat-rate pricing.
- [DiscountPricing.java](LessonManagement/src/main/java/com/example/lessonmanagement/strategy/DiscountPricing.java): Implements discount-based pricing.
- [DynamicDemandPricing.java](LessonManagement/src/main/java/com/example/lessonmanagement/strategy/DynamicDemandPricing.java): Implements demand-based pricing.

### **Observer Pattern (Notifications for Tutors)**

- [CustomizationObserver.java](LessonManagement/src/main/java/com/example/lessonmanagement/observer/CustomizationObserver.java): Interface for customization request observers, which [TutorNotificationService](LessonManagement/src/main/java/com/example/lessonmanagement/observer/TutorNotificationService.java) implements.
