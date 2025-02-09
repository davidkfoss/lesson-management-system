from graphviz import Digraph

# UML Class Diagram (DCD - Design Class Diagram) with Repository Functions
uml_class_diagram_fixed = Digraph('UML_ClassDiagram_Fixed', format='png')
uml_class_diagram_fixed.attr(rankdir='TB', size='10')

# Classes with methods (use \\n for better rendering)
uml_class_diagram_fixed.node('User', shape='record',
                             label="{ User | - name: String\\n - role: String\\n | + getName()\\n + getRole() }")
uml_class_diagram_fixed.node('Lesson', shape='record',
                             label="{ Lesson | - tutorName: String\\n - title: String\\n - price: double\\n | + getTutorName()\\n + getTitle()\\n + getPrice() }")

uml_class_diagram_fixed.node('UserRepository', shape='record',
                             label="{ UserRepository | + addUser(User)\\n + getUsers(): List<User>\\n + getUserByName(String): User }",
                             fixedsize="false")
uml_class_diagram_fixed.node('LessonRepository', shape='record',
                             label="{ LessonRepository | + addLesson(Lesson)\\n + getLessons(): List<Lesson>\\n + getLessonByTitle(String): Lesson }",
                             fixedsize="false")

uml_class_diagram_fixed.node('UserService', shape='record',
                             label="{ UserService | + addUser(User)\\n + viewUsers()\\n + isTutor(String): boolean }")
uml_class_diagram_fixed.node('LessonService', shape='record',
                             label="{ LessonService | + addLesson(Lesson)\\n + viewLessons() }")

uml_class_diagram_fixed.node('UserFactory', shape='record',
                             label="{ UserFactory | + createUser(String, String): User }")
uml_class_diagram_fixed.node('LessonFactory', shape='record',
                             label="{ LessonFactory | + createLesson(String, String, double): Lesson }")

# Relationships
uml_class_diagram_fixed.edge(
    'UserService', 'UserRepository', label='uses', arrowhead='vee')
uml_class_diagram_fixed.edge(
    'LessonService', 'LessonRepository', label='uses', arrowhead='vee')

uml_class_diagram_fixed.edge(
    'UserService', 'User', label='creates', arrowhead='vee')
uml_class_diagram_fixed.edge(
    'LessonService', 'Lesson', label='creates', arrowhead='vee')

uml_class_diagram_fixed.edge(
    'UserFactory', 'User', label='creates', arrowhead='vee')
uml_class_diagram_fixed.edge(
    'LessonFactory', 'Lesson', label='creates', arrowhead='vee')

uml_class_diagram_fixed.edge(
    'UserRepository', 'User', label='stores', arrowhead='vee')
uml_class_diagram_fixed.edge(
    'LessonRepository', 'Lesson', label='stores', arrowhead='vee')

# Save Diagram
uml_class_diagram_fixed_path = "./docs/Iteration 1/Diagrams/UML_ClassDiagram_Fixed"
uml_class_diagram_fixed.render(uml_class_diagram_fixed_path)

# Return file path
uml_class_diagram_fixed_path + ".png"
