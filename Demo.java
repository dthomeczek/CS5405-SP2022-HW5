package code;
// Minimum requirement
import javafx.scene.layout.*;// Pane, StackPane etc
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
// Other Nodes
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.TextAlignment;
import java.io.FileInputStream; 

/**
 * @author Devin Thomeczek
 */
public class Demo extends Application {
    
    Pane root;
    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        // Create a pane to hold objects
        root = new Pane();
        createCircle();   // create circle and add it to layout
        // Create a scene for layout and place it in the stage
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
        stage.setTitle("Demo - Circle Overlap Tester"); // Set the stage title
    }
    Label prompt, label;
    TextField tField;
    Circle circle, circle2;
    
    public void createCircle(){

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("./data/infile.txt"));
        } catch(Exception e){ e.printStackTrace();}

        int circle_val1 = scanner.nextInt();
        int circle_val2 = scanner.nextInt();
        double circle_val3 = scanner.nextDouble();
    
        int circle2_val1 = scanner.nextInt();
        int circle2_val2 = scanner.nextInt();
        double circle2_val3 = scanner.nextDouble();

        scanner.close();

        // Prompt & Name
        prompt = new Label();
        prompt.setText("By: Devin Thomeczek - Determine if the two circles overlap.\n");
        prompt.relocate(0, 0);

        // First Circle
        circle = new Circle();
        circle.setCenterX(circle_val1);
        circle.setCenterY(circle_val2);
        circle.setRadius(circle_val3);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.RED);

        // Second Circle
        circle2 = new Circle();
        circle2.setCenterX(circle2_val1);
        circle2.setCenterY(circle2_val2);
        circle2.setRadius(circle2_val3);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.LIGHTGREEN);

        // TextField for manual inputs
        tField = new TextField(circle_val1 + " " + circle_val2 + " " + circle_val3 + " " + circle2_val1 + " " + circle2_val2 + " " + circle2_val3);
        tField.setOnAction(new TextFieldHandler());
        tField.setPrefWidth(300);
        tField.relocate(0, 40);

        // Used to determine if the values of the circles are overlapping
        double center_distance = Math.sqrt(((circle2_val1 - circle_val1) * (circle2_val1 - circle_val1)) + ((circle2_val2 - circle_val2) * (circle2_val2 - circle_val2)));
        double radii_sum = circle_val3 + circle2_val3;

        // Used to output whether the circles are overlapping or not along with their values
        label = new Label();
        if (center_distance < radii_sum)
        {
            label.setText("These Circles Overlap. Circle 1 Values: " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " Circle 2 Values " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");
        }
        if (center_distance > radii_sum)
        {
            label.setText("These Circles Don\'t Overlap. Circle 1 Values: " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " Circle 2 Values " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");
        }
        label.relocate(0, 20);

        root.getChildren().addAll(circle,circle2,prompt,label,tField);
        return;
    }

    public class TextFieldHandler implements 
    EventHandler<ActionEvent>{
        public void handle(ActionEvent e)
        {
            String str = tField.getText();
            String delimiter = " ";
            String[] nums_str;
            nums_str= str.split(delimiter);

            int circle_val1 = Integer.parseInt(nums_str[0]);
            int circle_val2 = Integer.parseInt(nums_str[1]);
            double circle_val3 = Double.parseDouble(nums_str[2]);

            int circle2_val1 = Integer.parseInt(nums_str[3]);
            int circle2_val2 = Integer.parseInt(nums_str[4]);
            double circle2_val3 = Double.parseDouble(nums_str[5]);

            circle.setCenterX(circle_val1);
            circle.setCenterY(circle_val2);
            circle.setRadius(circle_val3);

            circle2.setCenterX(circle2_val1);
            circle2.setCenterY(circle2_val2);
            circle2.setRadius(circle2_val3);

            label.setText("First circle values are " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " and second circle values are " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");

            double center_distance = Math.sqrt(((circle2_val1 - circle_val1) * (circle2_val1 - circle_val1)) + ((circle2_val2 - circle_val2) * (circle2_val2 - circle_val2)));
            double radii_sum = circle_val3 + circle2_val3;

            if (center_distance < radii_sum)
            {
                label.setText("These Circles Overlap. Circle 1 Values: " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " Circle 2 Values " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");
            }
            if (center_distance > radii_sum)
            {
                label.setText("These Circles Don\'t Overlap. Circle 1 Values: " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " Circle 2 Values " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}