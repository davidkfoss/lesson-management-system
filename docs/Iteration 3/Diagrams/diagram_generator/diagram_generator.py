# Re-import necessary libraries due to execution state reset
from graphviz import Digraph

# Define paths for saving UML diagrams
use_case_diagram_path = "./docs/Iteration 3/Diagrams/Use_Case_Diagram_Iteration3"
domain_model_diagram_path = "./docs/Iteration 3/Diagrams/Domain_Model_Diagram_Iteration3"
class_diagram_path = "./docs/Iteration 3/Diagrams/Class_Diagram_Iteration3"
sequence_diagram_booking_path = "./docs/Iteration 3/Diagrams/Sequence_Diagram_Booking_Iteration3"
sequence_diagram_cancellation_path = "./docs/Iteration 3/Diagrams/Sequence_Diagram_Cancellation_Iteration3"
sequence_reviewing_path = "./docs/Iteration 3/Diagrams/Sequence_Diagram_Reviewing_Iteration3"


# Return generated image paths
use_case_diagram_path + ".png", domain_model_diagram_path + ".png", class_diagram_path + \
    ".png", sequence_diagram_booking_path + \
    ".png", sequence_diagram_cancellation_path + \
    ".png", sequence_reviewing_path + ".png"
