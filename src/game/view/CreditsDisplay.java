package game.view;

import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CreditsDisplay
{
	static Scene creditsScene;
	static GridPane grid = new GridPane();
	
	public static void display(String title)
	{
		Stage window = new Stage();
		window.setTitle(title);
		
		Label jamesLabel = new Label("James - Guessing Game Reference");
		GridPane.setConstraints(jamesLabel, 0, 2);
		
		Label codyLabel = new Label("Cody - CTEC Teacher");
		GridPane.setConstraints(codyLabel, 0, 4);
		
		Label alanLabel = new Label("Alan - Best friend and supportive figure");
		GridPane.setConstraints(alanLabel, 0, 6);
		
		Label ryanLabel = new Label("Ryan - Motivation for Programming");
		GridPane.setConstraints(ryanLabel, 0, 8);
		
		Label kessLabel = new Label("Kess - Motivation in General");
		GridPane.setConstraints(kessLabel, 0, 10);
		
		Label matthewLabel = new Label("Matthew - Motivation for Programming");
		GridPane.setConstraints(matthewLabel, 0, 12);
		
		Label tutorialLabel = new Label("thenewboston - Tutorials on JavaFX");
		GridPane.setConstraints(tutorialLabel, 0, 14);
		
		Button closeWindow = new Button("Close");
		closeWindow.setOnAction(e -> window.close());
		GridPane.setConstraints(closeWindow, 0, 20);
			
		grid.getChildren().addAll(jamesLabel, codyLabel, alanLabel,
				ryanLabel, kessLabel, matthewLabel, tutorialLabel, closeWindow);
		grid.setPadding(new Insets(20, 20, 20, 20));
		
		creditsScene = new Scene(grid, 270, 200);
		window.setScene(creditsScene);
		window.setResizable(false);
		window.show();
		
	}
}
