package basico;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PingPong extends Application {

    // tamanho da janela
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // tamanho da raquete
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;

    // tamanho da bola
    private static final int BALL_RADIUS = 10;

    // velocidade da bola
    private static final double BALL_SPEED = 5;

    // pontuação inicial
    private int playerScore = 0;
    private int computerScore = 0;

    // exibe a pontuação na tela
    private Text scoreText;

    // bola e raquetes
    private Circle ball;
    private Rectangle playerPaddle;
    private Rectangle computerPaddle;

    // movimento da bola
    private double ballX = BALL_SPEED;
    private double ballY = BALL_SPEED;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        // cria a bola
        ball = new Circle(BALL_RADIUS, Color.WHITE);
        ball.relocate(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);

        // cria as raquetes
        playerPaddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
        playerPaddle.relocate(0, HEIGHT / 2 - PADDLE_HEIGHT / 2);
        computerPaddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT, Color.WHITE);
        computerPaddle.relocate(WIDTH - PADDLE_WIDTH, HEIGHT / 2 - PADDLE_HEIGHT / 2);

        // cria o texto da pontuação
        scoreText = new Text("0 - 0");
        scoreText.setFont(new Font(40));
        scoreText.setFill(Color.WHITE);
        scoreText.relocate(WIDTH / 2 - 30, 50);

        // adiciona os elementos ao painel
        root.getChildren().addAll(ball, playerPaddle, computerPaddle, scoreText);

        // cria o movimento da bola
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // atualiza a posição da bola
            ball.setLayoutX(ball.getLayoutX() + ballX);
            ball.setLayoutY(ball.getLayoutY() + ballY);

            // verifica se a bola bateu na parede superior ou inferior
            if (ball.getLayoutY() <= 0 || ball.getLayoutY() >= HEIGHT - BALL_RADIUS * 2) {
                ballY *= -1;
            }

            // verifica se a bola bateu na raquete do jogador
            if (ball.getBoundsInParent().intersects(playerPaddle.getBoundsInParent())) {
                ballX *= -1;
            }

            // verifica se a bola bateu na raquete do computador
            if (ball.getBoundsInParent().intersects(computerPaddle.getBoundsInParent())) {
                ballX *= -1;
            }

            // verifica se a bola saiu da tela e atualiza a pontuação
            if (ball.getLayoutX() < 0) {
            	computerScore++;
                updateScore();
                resetBall();
            } else if (ball.getLayoutX() > WIDTH - BALL_RADIUS * 2) {
                playerScore++;
                updateScore();
                resetBall();
            }

            // movimento da raquete do computador
            if (ballY > 0) {
                if (ball.getLayoutY() > computerPaddle.getLayoutY() + PADDLE_HEIGHT / 2) {
                    computerPaddle.setLayoutY(computerPaddle.getLayoutY() + BALL_SPEED);
                } else {
                    computerPaddle.setLayoutY(computerPaddle.getLayoutY() - BALL_SPEED);
                }
            }

            // impede que as raquetes saiam da tela
            checkBounds(playerPaddle);
            checkBounds(computerPaddle);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // movimento da raquete do jogador
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                playerPaddle.setLayoutY(playerPaddle.getLayoutY() - 10);
            } else if (event.getCode() == KeyCode.DOWN) {
                playerPaddle.setLayoutY(playerPaddle.getLayoutY() + 10);
            }
        });

        // exibe a janela
        primaryStage.setTitle("Ping Pong");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetBall() {
        ball.setLayoutX(WIDTH / 2 - BALL_RADIUS);
        ball.setLayoutY(HEIGHT / 2 - BALL_RADIUS);
        ballX = BALL_SPEED;
        ballY = BALL_SPEED;
    }

    private void updateScore() {
        scoreText.setText(playerScore + " - " + computerScore);
    }

    private void checkBounds(Rectangle paddle) {
        Bounds bounds = paddle.getBoundsInParent();
        if (bounds.getMinY() < 0) {
            paddle.setLayoutY(0);
        } else if (bounds.getMaxY() > HEIGHT) {
            paddle.setLayoutY(HEIGHT - PADDLE_HEIGHT);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
