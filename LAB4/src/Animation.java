//T Harvey
// Loosely based on https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3.java 

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

// Animation of Orc walking
public class Animation extends Application {
    final int canvasCount = 10;
    int picInd = 0;
    double xloc = 0;
    double yloc = 0;
    double xIncr = 8;
    double yIncr = 2;
    final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

    // TODO: Change the code so that at least eight orc animation pngs are loaded

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
    	List <Image> orcList = new ArrayList <>();
    	orcList.add(createImage("Images/orc/orc_forward_southeast.png"));
        orcList.add(createImage("Images/orc/orc_forward_northeast.png"));
    	orcList.add(createImage("Images/orc/orc_forward_northwest.png"));
    	orcList.add(createImage("Images/orc/orc_forward_west.png"));
    	orcList.add(createImage("Images/orc/orc_forward_north.png"));
    	orcList.add(createImage("Images/orc/orc_forward_south.png"));
    	orcList.add(createImage("Images/orc/orc_forward_southwest.png"));
    	orcList.add(createImage("Images/orc/orc_forward_east.png"));
    	orcList.add(createImage("Images/orc/orc_jump_northwest.png"));
    	orcList.add(createImage("Images/orc/orc_jump_west.png"));
  	
    	
        theStage.setTitle("Orc");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
       

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1e9; 

                xloc += xIncr;
                yloc += yIncr;

                // Clear the canvas
                gc.clearRect(0, 0, canvasWidth, canvasHeight);

                // draw the subimage from the original png to animate the orc's motion
                gc.drawImage(orcList.get(Selector()), imgWidth*picInd, 0, imgWidth, imgHeight,
                                    xloc, yloc, imgWidth, imgHeight);
                picInd = (picInd + 1) % canvasCount;
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (xloc >= (canvasWidth - imgWidth)) {
                	xIncr *= -1;
                }         
                if (yloc >= (canvasHeight - imgHeight)) {
                	yIncr *= -1;
                }
                
                if (xloc < 0) {
                	xIncr *= -1;
                }
                
                if (yloc < 0) {
                	yIncr *= -1;
                }
                
                // TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
                //Be sure that animation picture direction matches what is happening on screen.
            }
            }.start();
            theStage.show();
        
            
        
    }
    

    //Read image from file and return
    private Image createImage(String args) {
        Image img = new Image(args);
        return img;   	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
    
    public int Selector() {
    	
    	if(xIncr >=0 && yIncr >= 0) {
    return 0;		
    }
    	if (xIncr >0 && yIncr <0) {
    		return 1;
    	}
    	if (xIncr <0 && yIncr <0) {
    		return 2;
    	}
    	if (xIncr <0 && yIncr >0) {
    		return 3;
    	}
		return 9;
    
}
}

