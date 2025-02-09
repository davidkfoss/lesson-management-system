# Re-import necessary libraries due to execution state reset
from graphviz import Digraph

# Define paths for saving UML diagrams
use_case_diagram_path = "./docs/Iteration 2/Diagrams/Use_Case_Diagram_Iteration2"
domain_model_diagram_path = "./docs/Iteration 2/Diagrams/Domain_Model_Diagram_Iteration2"
class_diagram_path = "./docs/Iteration 2/Diagrams/Class_Diagram_Iteration2"
sequence_diagram_booking_path = "./docs/Iteration 2/Diagrams/Sequence_Diagram_Booking_Iteration2"
sequence_diagram_cancellation_path = "./docs/Iteration 2/Diagrams/Sequence_Diagram_Cancellation_Iteration2"
sequence_reviewing_path = "./docs/Iteration 2/Diagrams/Sequence_Diagram_Reviewing_Iteration2"

# ===================== Use Case Diagram =====================
use_case_diagram = Digraph('Use_Case_Iteration2', format='png')
use_case_diagram.attr(rankdir='TB', size='10')

# Actors
use_case_diagram.node('Student', shape='actor', label="Student")
use_case_diagram.node('Tutor', shape='actor', label="Tutor")

# Use Cases
use_case_diagram.node('UC1', shape='ellipse', label="Register as a user")
use_case_diagram.node('UC2', shape='ellipse', label="Book a lesson")
use_case_diagram.node('UC3', shape='ellipse', label="Cancel a lesson")
use_case_diagram.node('UC4', shape='ellipse', label="Review a tutor")
use_case_diagram.node('UC5', shape='ellipse', label="View tutor profile")

# Relationships
use_case_diagram.edge('Student', 'UC1')
use_case_diagram.edge('Student', 'UC2')
use_case_diagram.edge('Student', 'UC3')
use_case_diagram.edge('Student', 'UC4')
use_case_diagram.edge('Student', 'UC5')
use_case_diagram.edge('Tutor', 'UC1')
use_case_diagram.edge('Tutor', 'UC5')

# Save Use Case Diagram
use_case_diagram.render(use_case_diagram_path)

# ===================== Domain Model Diagram =====================
domain_model = Digraph('Domain_Model_Iteration2', format='png')
domain_model.attr(rankdir='TB', size='10')

# Classes in Domain Model
domain_model.node('User', shape='record',
                  label="{ User | - name: String | - role: String }")
domain_model.node('Lesson', shape='record',
                  label="{ Lesson | - tutorName: String | - title: String | - price: double }")
domain_model.node('Booking', shape='record',
                  label="{ Booking | - studentName: String | - lessonTitle: String }")
domain_model.node('Review', shape='record',
                  label="{ Review | - studentName: String | - tutorName: String | - rating: int | - comment: String }")

# Relationships
domain_model.edge('User', 'Lesson', label="creates", arrowhead='vee')
domain_model.edge('User', 'Booking', label="books", arrowhead='vee')
domain_model.edge('User', 'Review', label="writes", arrowhead='vee')
domain_model.edge('Booking', 'Lesson', label="references", arrowhead='vee')

# Save Domain Model Diagram
domain_model.render(domain_model_diagram_path)

# ===================== Class Diagram =====================
class_diagram = Digraph('Class_Diagram_Iteration2', format='png')
class_diagram.attr(rankdir='TB', size='10')

# Classes
class_diagram.node('User', shape='record',
                   label="{ User | - name: String | - role: String | + getName() | + getRole() }")
class_diagram.node('Lesson', shape='record',
                   label="{ Lesson | - tutorName: String | - title: String | - price: double | + getTutorName() | + getTitle() | + getPrice() }")
class_diagram.node('Booking', shape='record',
                   label="{ Booking | - studentName: String | - lessonTitle: String | + getStudentName() | + getLessonTitle() }")
class_diagram.node('Review', shape='record',
                   label="{ Review | - studentName: String | - tutorName: String | - rating: int | - comment: String | + getStudentName() | + getTutorName() | + getRating() | + getComment() }")

# Services and Repositories
class_diagram.node('UserService', shape='record',
                   label="{ UserService | + addUser(User) | + viewUsers() }")
class_diagram.node('LessonService', shape='record',
                   label="{ LessonService | + addLesson(Lesson) | + bookLesson(String) | + cancelLesson(String) }")
class_diagram.node('ReviewService', shape='record',
                   label="{ ReviewService | + addReview(Review) | + viewTutorProfile(String) }")

class_diagram.node('UserRepository', shape='record',
                   label="{ UserRepository | + addUser(User) | + getUsers() }")
class_diagram.node('LessonRepository', shape='record',
                   label="{ LessonRepository | + addLesson(Lesson) | + getLessons() }")
class_diagram.node('BookingRepository', shape='record',
                   label="{ BookingRepository | + addBooking(Booking) | + getBookings() | + removeBooking(String) }")
class_diagram.node('ReviewRepository', shape='record',
                   label="{ ReviewRepository | + addReview(Review) | + getReviewsByTutor(String) }")

# Relationships (connecting services/repositories to model classes)
class_diagram.edge('UserService', 'UserRepository',
                   label="uses", arrowhead='vee')
class_diagram.edge('LessonService', 'LessonRepository',
                   label="uses", arrowhead='vee')
class_diagram.edge('LessonService', 'BookingRepository',
                   label="uses", arrowhead='vee')
class_diagram.edge('ReviewService', 'ReviewRepository',
                   label="uses", arrowhead='vee')
class_diagram.edge('ReviewService', 'BookingRepository',
                   label="checks", arrowhead='vee')

# Connecting repositories to model classes
class_diagram.edge('UserRepository', 'User', label="stores", arrowhead='vee')
class_diagram.edge('LessonRepository', 'Lesson',
                   label="stores", arrowhead='vee')
class_diagram.edge('BookingRepository', 'Booking',
                   label="stores", arrowhead='vee')
class_diagram.edge('ReviewRepository', 'Review',
                   label="stores", arrowhead='vee')

# Connecting services to model classes
class_diagram.edge('UserService', 'User', label="manages", arrowhead='vee')
class_diagram.edge('LessonService', 'Lesson', label="manages", arrowhead='vee')
class_diagram.edge('ReviewService', 'Review', label="manages", arrowhead='vee')

# Save Class Diagram
class_diagram.render(class_diagram_path)

# ===================== Sequence Diagram (Booking a Lesson) =====================
sequence_booking = Digraph('Sequence_Booking_Iteration2', format='png')
sequence_booking.attr(rankdir='TB', size='10')

sequence_booking.node('Student', shape='rectangle')
sequence_booking.node('LessonService', shape='rectangle')
sequence_booking.node('BookingRepository', shape='rectangle')

sequence_booking.edge('Student', 'LessonService',
                      label="bookLesson(studentName)")
sequence_booking.edge('LessonService', 'BookingRepository',
                      label="addBooking(Booking)")
sequence_booking.edge('BookingRepository',
                      'LessonService', label="confirmation")
sequence_booking.edge('LessonService', 'Student',
                      label="Lesson booked successfully")

# Save Sequence Diagram (Booking)
sequence_booking.render(sequence_diagram_booking_path)

# ===================== Sequence Diagram (Cancelling a Lesson) =====================
sequence_cancellation = Digraph(
    'Sequence_Cancellation_Iteration2', format='png')
sequence_cancellation.attr(rankdir='TB', size='10')

sequence_cancellation.node('Student', shape='rectangle')
sequence_cancellation.node('LessonService', shape='rectangle')
sequence_cancellation.node('BookingRepository', shape='rectangle')

sequence_cancellation.edge('Student', 'LessonService',
                           label="cancelLesson(studentName)")
sequence_cancellation.edge(
    'LessonService', 'BookingRepository', label="removeBooking(studentName)")
sequence_cancellation.edge(
    'BookingRepository', 'LessonService', label="confirmation")
sequence_cancellation.edge('LessonService', 'Student',
                           label="Lesson canceled successfully")

# Save Sequence Diagram (Cancellation)
sequence_cancellation.render(sequence_diagram_cancellation_path)

# ===================== Sequence Diagram (Reviewing a Tutor) =====================
sequence_reviewing = Digraph('Sequence_Reviewing_Iteration2', format='png')
sequence_reviewing.attr(rankdir='TB', size='10')

# Actors and Components
sequence_reviewing.node('Student', shape='rectangle')
sequence_reviewing.node('ReviewService', shape='rectangle')
sequence_reviewing.node('BookingRepository', shape='rectangle')
sequence_reviewing.node('ReviewRepository', shape='rectangle')

# Interactions
sequence_reviewing.edge('Student', 'ReviewService',
                        label="addReview(studentName, tutorName, rating, comment)")
sequence_reviewing.edge('ReviewService', 'BookingRepository',
                        label="check if student booked lesson with tutor")
sequence_reviewing.edge('BookingRepository', 'ReviewService',
                        label="return booking confirmation")
sequence_reviewing.edge(
    'ReviewService', 'ReviewRepository', label="addReview(Review)")
sequence_reviewing.edge(
    'ReviewRepository', 'ReviewService', label="confirmation")
sequence_reviewing.edge('ReviewService', 'Student',
                        label="Review added successfully")

# Save Sequence Diagram (Reviewing)
sequence_reviewing.render(sequence_reviewing_path)

# Return generated image paths
use_case_diagram_path + ".png", domain_model_diagram_path + ".png", class_diagram_path + \
    ".png", sequence_diagram_booking_path + \
    ".png", sequence_diagram_cancellation_path + \
    ".png", sequence_reviewing_path + ".png"
