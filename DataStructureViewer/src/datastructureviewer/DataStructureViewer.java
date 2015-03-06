package datastructureviewer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import views.Toolbox;
import views.WorkSpaceView;

/**
 *
 * @author Iain Workman 11139430 ipw969
 */
public class DataStructureViewer extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        Toolbox toolBox = new Toolbox();
        WorkSpaceView workSpace = new WorkSpaceView();

        root.setLeft(toolBox);
        root.setCenter(workSpace);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Data Structure Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
