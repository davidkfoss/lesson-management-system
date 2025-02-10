from graphviz import Digraph


def generate_uml_diagrams():
    diagrams = {
        "Use_Case_Diagram_Iteration3": "Use Case Diagram",
        "Domain_Model_Diagram_Iteration3": "Domain Model Diagram",
        "Class_Diagram_Iteration3": "Class Diagram",
        "Sequence_Diagram_Managing_Packages_Iteration3": "Sequence Diagram - Managing Lesson Packages",
        "Sequence_Diagram_Customization_Request_Iteration3": "Sequence Diagram - Sending a Lesson Customization Request",
        "Sequence_Diagram_Lesson_Calendar_Iteration3": "Sequence Diagram - Viewing the Lesson Calendar",
        "Sequence_Diagram_Purchase_Package_Iteration3": "Sequence Diagram - Purchasing a Lesson Package",
        "Sequence_Diagram_Pricing_Strategy_Iteration3": "Sequence Diagram - Applying Pricing Strategy to Lessons"
    }

    for filename, title in diagrams.items():
        d = Digraph(filename, format='png')
        d.attr(rankdir='TB', size='10')

        if "Use_Case" in filename:
            d.node('Student', shape='actor', label="Student")
            d.node('Tutor', shape='actor', label="Tutor")
            d.node('UC1', shape='ellipse', label="Register as a user")
            d.node('UC2', shape='ellipse', label="Book a lesson")
            d.node('UC3', shape='ellipse', label="Manage lesson packages")
            d.node('UC4', shape='ellipse',
                   label="Send a lesson customization request")
            d.node('UC5', shape='ellipse', label="Manage lessons and prices")
            d.node('UC6', shape='ellipse', label="Cancel a lesson")
            d.node('UC7', shape='ellipse', label="View lesson calendar")
            d.node('UC8', shape='ellipse', label="Review a tutor")
            d.node('UC9', shape='ellipse', label="Obtain tutor information")
            d.node('UC10', shape='ellipse', label="Find tutors by study field")
            d.node('UC11', shape='ellipse', label="View lessons for a tutor")
            d.edge('Student', 'UC1')
            d.edge('Student', 'UC2')
            d.edge('Student', 'UC4')
            d.edge('Student', 'UC6')
            d.edge('Student', 'UC7')
            d.edge('Student', 'UC8')
            d.edge('Student', 'UC9')
            d.edge('Student', 'UC10')
            d.edge('Student', 'UC11')
            d.edge('Tutor', 'UC1')
            d.edge('Tutor', 'UC3')
            d.edge('Tutor', 'UC5')
            d.edge('Tutor', 'UC6')
            d.edge('Tutor', 'UC7')

        elif "Domain_Model" in filename:
            d.node('User', shape='record',
                   label="{ User | - name: String | - role: String | - studyFields: List<String> }")
            d.node('Lesson', shape='record',
                   label="{ Lesson | - tutorName: String | - title: String | - basePrice: double }")
            d.node('LessonPackage', shape='record',
                   label="{ LessonPackage | - tutorName: String | - title: String | - price: double }")
            d.node('CustomizationRequest', shape='record',
                   label="{ CustomizationRequest | - studentName: String | - tutorName: String | - details: String }")
            d.node('Booking', shape='record',
                   label="{ Booking | - studentName: String | - lessonTitle: String }")
            d.node('Review', shape='record',
                   label="{ Review | - studentName: String | - tutorName: String | - rating: int | - comment: String }")
            d.edge('User', 'Lesson', label="creates", arrowhead='vee')
            d.edge('User', 'Booking', label="books", arrowhead='vee')
            d.edge('User', 'Review', label="writes", arrowhead='vee')
            d.edge('Booking', 'Lesson', label="references", arrowhead='vee')

        elif "Class_Diagram" in filename:
            d.node('LessonService', shape='record',
                   label="{ LessonService | + addLesson() | + bookLesson() | + cancelLesson() | + manageCustomizationRequests() }")
            d.node('PricingStrategy', shape='record',
                   label="{ PricingStrategy | + calculatePrice(double): double }")
            d.node('FlatRatePricing', shape='record',
                   label="{ FlatRatePricing | + calculatePrice(double): double }")
            d.node('DiscountPricing', shape='record',
                   label="{ DiscountPricing | - discountRate: double | + calculatePrice(double): double }")
            d.node('DynamicDemandPricing', shape='record',
                   label="{ DynamicDemandPricing | - demandFactor: double | + calculatePrice(double): double }")
            d.edge('LessonService', 'PricingStrategy',
                   label='uses', arrowhead='vee')
            d.edge('FlatRatePricing', 'PricingStrategy', arrowhead='empty')
            d.edge('DiscountPricing', 'PricingStrategy', arrowhead='empty')
            d.edge('DynamicDemandPricing', 'PricingStrategy', arrowhead='empty')

        elif "Sequence_Diagram_Managing_Packages" in filename:
            d.node('Tutor', shape='rectangle')
            d.node('LessonService', shape='rectangle')
            d.node('LessonPackageRepository', shape='rectangle')
            d.edge('Tutor', 'LessonService', label="manageLessonPackages()")
            d.edge('LessonService', 'LessonPackageRepository',
                   label="add/remove/view lesson package")
            d.edge('LessonPackageRepository',
                   'LessonService', label="confirmation")
            d.edge('LessonService', 'Tutor',
                   label="Package managed successfully")

        elif "Sequence_Diagram_Customization_Request" in filename:
            d.node('Student', shape='rectangle')
            d.node('LessonService', shape='rectangle')
            d.node('CustomizationRequestRepository', shape='rectangle')
            d.node('NotificationRepository', shape='rectangle')
            d.edge('Student', 'LessonService',
                   label="requestLessonCustomization()")
            d.edge('LessonService', 'CustomizationRequestRepository',
                   label="store request")
            d.edge('CustomizationRequestRepository',
                   'NotificationRepository', label="notify tutor")
            d.edge('LessonService', 'Student', label="Request submitted")

        elif "Sequence_Diagram_Lesson_Calendar" in filename:
            d.node('Student', shape='rectangle')
            d.node('LessonService', shape='rectangle')
            d.node('BookingRepository', shape='rectangle')
            d.edge('Student', 'LessonService', label="viewLessonCalendar()")
            d.edge('LessonService', 'BookingRepository',
                   label="fetch booked lessons")
            d.edge('BookingRepository', 'LessonService',
                   label="return booked lessons")
            d.edge('LessonService', 'Student', label="Display lesson calendar")

        elif "Sequence_Diagram_Purchase_Package" in filename:
            d.node('Student', shape='rectangle')
            d.node('LessonService', shape='rectangle')
            d.node('LessonPackageRepository', shape='rectangle')
            d.edge('Student', 'LessonService', label="purchaseLessonPackage()")
            d.edge('LessonService', 'LessonPackageRepository',
                   label="retrieve package details")
            d.edge('LessonPackageRepository', 'LessonService',
                   label="return package details")
            d.edge('LessonService', 'Student', label="confirm purchase")

        elif "Sequence_Diagram_Pricing_Strategy" in filename:
            d.node('Tutor', shape='rectangle')
            d.node('Lesson', shape='rectangle')
            d.node('PricingStrategy', shape='rectangle')
            d.edge('Tutor', 'Lesson', label="setPricingStrategy()")
            d.edge('Lesson', 'PricingStrategy', label="apply pricing strategy")
            d.edge('PricingStrategy', 'Lesson',
                   label="return calculated price")
            d.edge('Lesson', 'Tutor', label="confirm pricing applied")

        d.render(f'./docs/Iteration 3/Diagrams/{filename}')
        print(f"{title} generated successfully!")


if __name__ == "__main__":
    generate_uml_diagrams()
