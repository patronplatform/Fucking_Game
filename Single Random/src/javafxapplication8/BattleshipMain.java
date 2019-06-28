package javafxapplication8;
import java.awt.Desktop;
import java.io.File;
import java.util.Random;
import javafxapplication8.Board.Cell;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;

//    private int shipsToPlace = 10;
    
    private int NumShip[] = {4,3,3,2,2,2,1,1,1,1} ;
    private int Co = 0 , Ko = 0 ;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

//        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                SimpleLog.history(1);
                SimpleLog.log(" You Win ");
                SimpleLog.log("----------------------");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            
            
            
        
// Choose Ship 

//            if (playerBoard.placeShip(new Ship(NumShip[Co], event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
////                System.out.println(Co +);
//                
//                if (Co-- == 0) {
//                    System.out.print(Co);
//                    startGame();
//                }
//            }

        int type = 10;
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (playerBoard.placeShip(new Ship(NumShip[Co], Math.random() < 0.5), x, y)) {
                Co++;
                type--;
            }
        }
        startGame();
            
            
            
        });

        VBox vbox = new VBox(10, enemyBoard, playerBoard);
       
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);
       
        return root;
    }
    
    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                SimpleLog.history(2);
                SimpleLog.log(" You Lose ");
                SimpleLog.log("----------------------");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 10;
        
        

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(NumShip[Ko], Math.random() < 0.5), x, y)) {
                Ko++;
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SimpleLog.log("Starting Scene In Custom Mod");
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
//        File file = new File("C:\\Users\\sina\\Documents\\NetBeansProjects\\Test\\dist\\Test.jar");
//        Desktop.getDesktop().open(file);
        primaryStage.show();
    }
    @Override
    public void stop(){
        SimpleLog.history(-1);
        SimpleLog.log("Closing Custom Mode");
        SimpleLog.log("----------------------");
        
        
    }
    public static void main(String[] args) {
        
        launch(args);
    }
}

