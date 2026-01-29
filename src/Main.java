import javafx.application.Application;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/login.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Vehicle Rental System - Login");
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading login.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
