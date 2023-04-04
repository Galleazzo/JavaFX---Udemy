package basico;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleJumpGame extends Application {
    
    private Circle player;
    private Rectangle obstacle;
    private int score = 0;
    private Text scoreText;
    private Button startButton;
    private Timeline timeline;
	private Node scene;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        player = new Circle(20, Color.BLUE);
        player.setTranslateX(50);
        player.setTranslateY(400);
        
        obstacle = new Rectangle(50, 50, Color.RED);
        obstacle.setTranslateX(800);
        obstacle.setTranslateY(400);
        
        scoreText = new Text("Score: 0");
        scoreText.setFont(new Font(24));
        
        startButton = new Button("Start");
        startButton.setOnAction(event -> startGame());
        
        VBox vbox = new VBox(20, scoreText, startButton);
        vbox.setAlignment(Pos.CENTER);
        StackPane root = new StackPane(vbox);
        root.getChildren().add(player);
        root.getChildren().add(obstacle);
        
        Scene scene = new Scene(root, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void startGame() {
        score = 0;
        scoreText.setText("Score: 0");
        player.setTranslateX(50);
        player.setTranslateY(400);
        obstacle.setTranslateX(800);
        startButton.setDisable(true);
        
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            obstacle.setTranslateX(obstacle.getTranslateX() - 5);
            if (obstacle.getTranslateX() < -obstacle.getWidth()) {
                obstacle.setTranslateX(1000);
                score++;
                scoreText.setText("Score: " + score);
            }
            
            Bounds playerBounds = player.getBoundsInParent();
            Bounds obstacleBounds = obstacle.getBoundsInParent();
            if (playerBounds.intersects(obstacleBounds)) {
                timeline.stop();
                startButton.setDisable(false);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        scene.setOnMouseClicked(event -> jump());
    }
    
    private void jump() {
        Timeline jumpTimeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> player.setTranslateY(player.getTranslateY() - 5)),
                new KeyFrame(Duration.millis(100), event -> player.setTranslateY(player.getTranslateY() + 5))
        );
        jumpTimeline.play();
        jumpTimeline.setOnFinished(event -> {
            if (player.getTranslateY() < 400) {
                jump();
            }
        });
    }
}
