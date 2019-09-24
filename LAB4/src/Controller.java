import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
	
	private Model model;
	private View view;

	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage theStage) {
        view = new View(theStage);
        model = new Model(view.getWidth(), view.getHeight(), 
                view.getImageWidth(), view.getImageHeight());
        
        new AnimationTimer() {
        	
            public void handle(long currentNanoTime)
            {
                
                model.updateLocationandDirection();
                //input the x cord, y cord, and direction 
                view.update(model.getX(), model.getY(), model.getDirection());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.show();
    }
}