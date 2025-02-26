package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.model.Booking;
import com.example.lessonmanagement.model.LessonPackage;
import com.example.lessonmanagement.model.CustomizationRequest;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.repository.BookingRepository;
import com.example.lessonmanagement.repository.LessonPackageRepository;
import com.example.lessonmanagement.repository.CustomizationRequestRepository;
import com.example.lessonmanagement.strategy.PricingStrategy;
import com.example.lessonmanagement.strategy.DiscountPricing;
import com.example.lessonmanagement.strategy.DynamicDemandPricing;
import com.example.lessonmanagement.strategy.FlatRatePricing;

import java.util.List;

public class LessonService {
    private final LessonRepository lessonRepository;
    private final BookingRepository bookingRepository;
    private final InputService inputService;
    private final LessonPackageRepository lessonPackageRepository = LessonPackageRepository.getInstance();
    private final CustomizationRequestRepository customizationRequestRepository = CustomizationRequestRepository.getInstance();

    public LessonService(
            LessonRepository lessonRepository,
            BookingRepository bookingRepository,
            InputService inputService
    ) {
        this.lessonRepository = lessonRepository;
        this.bookingRepository = bookingRepository;
        this.inputService = inputService;
    }

    public void createLesson() {
        String tutorName = inputService.readString("Enter tutor name: ");
        String title = inputService.readString("Enter lesson title: ");
        double basePrice = inputService.readPositiveDouble("Enter base price (must be positive): ");
        PricingStrategy strategy = selectPricingStrategy(basePrice);
        Lesson lesson = new Lesson(tutorName, title, basePrice, strategy);
        lessonRepository.addLesson(lesson);
        System.out.println("Lesson created successfully with pricing strategy applied!");
    }

    private PricingStrategy selectPricingStrategy(double basePrice) {
        System.out.println("Select pricing strategy:");
        System.out.println("1. Flat Rate Pricing (No changes to price)");
        System.out.println("2. Discount Pricing (Apply a discount)");
        System.out.println("3. Dynamic Demand Pricing (Adjust price based on demand factor)");
        int choice = inputService.readInt("Enter choice: ");
        switch (choice) {
            case 1:
                return new FlatRatePricing();
            case 2:
                double discountRate = inputService.readDoubleInRange(
                        "Enter discount rate (between 0 and 1): ",
                        0, 1
                );
                return new DiscountPricing(discountRate);
            case 3:
                double demandFactor = inputService.readDoubleInRange(
                        "Enter demand factor (between 1 and 2): ",
                        1, 2
                );
                return new DynamicDemandPricing(demandFactor);
            default:
                System.out.println("Invalid choice. Defaulting to Flat Rate Pricing.");
                return new FlatRatePricing();
        }
    }

    public void viewLessons() {
        lessonRepository.getLessons().forEach(System.out::println);
    }

    public void bookLesson(String studentName) {
        if (lessonRepository.getLessons().isEmpty()) {
            System.out.println("No available lessons to book.");
            return;
        }
        System.out.println("Available lessons:");
        int index = 1;
        for (Lesson lesson : lessonRepository.getLessons()) {
            System.out.println(index + ". " + lesson.getTitle() + " by " + lesson.getTutorName()
                    + " - Before: " + lesson.getBasePrice() + "€ - Now: " + lesson.getFinalPrice() + "€");
            index++;
        }
        int choice = inputService.readInt("Enter the number of the lesson you want to book: ");
        if (choice < 1 || choice > lessonRepository.getLessons().size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }
        Lesson selectedLesson = lessonRepository.getLessons().get(choice - 1);
        if (!bookingRepository.isLessonBooked(selectedLesson.getTitle())) {
            bookingRepository.addBooking(new Booking(studentName, selectedLesson.getTitle(), selectedLesson.getTutorName()));
            System.out.println("Lesson booked successfully!");
        } else {
            System.out.println("This lesson is already booked.");
        }
    }

    public void cancelLesson(String studentName) {
        List<Booking> studentBookings = bookingRepository.getBookings().stream()
                .filter(booking -> booking.getStudentName().equals(studentName))
                .toList();
        if (studentBookings.isEmpty()) {
            System.out.println("No bookings found for student: " + studentName);
            return;
        }
        System.out.println("Your booked lessons:");
        for (int i = 0; i < studentBookings.size(); i++) {
            System.out.println((i + 1) + ". " + studentBookings.get(i).getLessonTitle());
        }
        int choice = inputService.readInt("Enter the number of the lesson you want to cancel: ");
        if (choice < 1 || choice > studentBookings.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }
        Booking toRemove = studentBookings.get(choice - 1);
        bookingRepository.removeBooking(toRemove.getStudentName(), toRemove.getLessonTitle());
        System.out.println("Lesson canceled successfully.");
    }

    public void viewLessonsByTutor(String tutorName) {
        List<Lesson> tutorLessons = lessonRepository.getLessonsByTutor(tutorName);
        if (tutorLessons.isEmpty()) {
            System.out.println("No lessons available for this tutor.");
        } else {
            tutorLessons.forEach(System.out::println);
        }
    }

    public void manageCustomizationRequests(String tutorName) {
        List<CustomizationRequest> requests = customizationRequestRepository.getRequestsByTutor(tutorName);
        if (requests.isEmpty()) {
            System.out.println("No pending customization requests.");
            return;
        }
        System.out.println("Pending Customization Requests:");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ". " + requests.get(i));
        }
        int choice = inputService.readInt("Enter the number of the request to approve (or 0 to exit): ");
        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid selection. Exiting.");
            return;
        }
        CustomizationRequest selectedRequest = requests.get(choice - 1);
        customizationRequestRepository.approveRequest(tutorName, selectedRequest.getStudentName());
        System.out.println("Request approved.");
    }

    public void viewLessonCalendar(String studentName) {
        List<Booking> studentBookings = bookingRepository.getBookingsByStudent(studentName);
        if (studentBookings.isEmpty()) {
            System.out.println("No booked lessons found.");
        } else {
            System.out.println("Your lesson schedule:");
            studentBookings.forEach(b ->
                    System.out.println(b.getLessonTitle() + " by " + b.getTutorName()));
        }
    }

    public void requestLessonCustomization(String studentName) {
        String tutorName = inputService.readString("Enter tutor name: ");
        String requestDetails = inputService.readString("Enter customization request details: ");
        CustomizationRequest request = new CustomizationRequest(studentName, tutorName, requestDetails);
        customizationRequestRepository.addRequest(request);
        System.out.println("Customization request submitted.");
    }

    public void manageLessonPackages(String tutorName) {
        System.out.println("1. Create Package\n2. View Packages\n3. Delete Package");
        int choice = inputService.readInt("Enter choice: ");
        switch (choice) {
            case 1:
                String packageTitle = inputService.readString("Enter package title: ");
                double price = inputService.readPositiveDouble("Enter package price: ");
                LessonPackage lessonPackage = new LessonPackage(tutorName, packageTitle, price);
                lessonPackageRepository.addPackage(lessonPackage);
                System.out.println("Lesson package created.");
                break;
            case 2:
                lessonPackageRepository.getPackagesByTutor(tutorName).forEach(System.out::println);
                break;
            case 3:
                String deleteTitle = inputService.readString("Enter package title to delete: ");
                lessonPackageRepository.removePackage(tutorName, deleteTitle);
                System.out.println("Package deleted.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void purchaseLessonPackage(String studentName, String tutorName) {
        List<LessonPackage> tutorPackages = lessonPackageRepository.getPackagesByTutor(tutorName);
        if (tutorPackages.isEmpty()) {
            System.out.println("No lesson packages available for this tutor.");
            return;
        }
        System.out.println("Available packages:");
        for (int i = 0; i < tutorPackages.size(); i++) {
            System.out.println((i + 1) + ". " + tutorPackages.get(i));
        }
        int choice = inputService.readInt("Enter the number of the package to purchase: ");
        if (choice < 1 || choice > tutorPackages.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }
        LessonPackage selectedPackage = tutorPackages.get(choice - 1);
        bookingRepository.addBooking(new Booking(studentName, selectedPackage.getTitle(), selectedPackage.getTutorName()));
        System.out.println("Package purchased successfully.");
    }

    public void viewSignedUpStudents(String tutorName) {
        List<Booking> tutorBookings = bookingRepository.getBookingsByTutor(tutorName);
        if (tutorBookings.isEmpty()) {
            System.out.println("No students signed up for your lessons.");
        } else {
            System.out.println("Your students:");
            tutorBookings.forEach(b ->
                    System.out.println(b.getStudentName() + " - " + b.getLessonTitle()));
        }
    }
}
