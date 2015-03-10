package datastructureviewer;

import controllers.WorkSpaceController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.WorkSpaceGraph;
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
        workSpace.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        WorkSpaceGraph modelGraph = new WorkSpaceGraph();
        workSpace.setModel(modelGraph);

        WorkSpaceController controller = new WorkSpaceController(modelGraph, workSpace);

        root.setLeft(toolBox);
        root.setCenter(workSpace);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Data Structure Editor");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
