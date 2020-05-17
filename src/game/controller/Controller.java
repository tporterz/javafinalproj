package game.controller;

import javafx.application.Application;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.ArrayList;

import game.model.*;
import game.view.*;

////////////////////////////////////////
//      DEDICATIONS AND CREDITS       //
//------------------------------------//
// James - Guessing Game Reference    //
// Cody - Teacher                     //
// Alan - Emotional Support           //
// Ryan - Motivation                  //
// Kess - Motivation                  //
// Matthew - Motivation               //
// thenewboston - Tutorials on JavaFX //
////////////////////////////////////////

public class Controller extends Application
{
	Stage window;
	Scene chooseScene;
	
	public static void main (String [] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Java Final Project | Tyler Porter");
		
		Button creditsButton = new Button("Credits");
		GridPane.setConstraints(creditsButton, 0, 4);
		
		//Choose game layout
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 20, 20, 20));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label welcomeLabel = new Label("Welcome to my application - Please select a game from the dropdown below.");
		GridPane.setConstraints(welcomeLabel, 0, 0);
		
		ComboBox<String> comboBox = new ComboBox<>();
		GridPane.setConstraints(comboBox, 0, 1);
		comboBox.getItems().addAll("Guessing Game", "Drawing");
		comboBox.setOnAction(e -> getChoice(comboBox));
		
		creditsButton.setOnAction(e -> CreditsDisplay.display("Credits"));
		
		//Adding variables to the grids for each scene
		grid.getChildren().addAll(welcomeLabel, comboBox, creditsButton);
		
		chooseScene = new Scene(grid, 460, 150);
		
		window.setScene(chooseScene);
		window.setResizable(false);
		window.show();
		
	}
	
	private void getChoice(ComboBox<String> comboBox)
	{
		String choice = comboBox.getValue();
		if (choice.equalsIgnoreCase("Guessing Game"))
		{
			GuessingGame.guess("Guessing Game");
		}
		
		else if (choice.equalsIgnoreCase("Drawing"))
		{
			Drawing.draw("Drawing");
		}

	}
	
}
