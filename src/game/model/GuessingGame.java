package game.model;

import game.view.AlertBox;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GuessingGame
{
	//Guessing game variables
	static int numLimit = 300;
	static int guessNum = (int) (Math.random() * numLimit);
	static Scene guessScene;
	
	public static void guess(String title)
	{
		Stage window = new Stage();
		window.setTitle(title);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 20, 20, 20));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label thinkingLabel = new Label("I'm thinking of a number between 1 and " + numLimit);
		GridPane.setConstraints(thinkingLabel, 0, 0);
		
		TextField guessField = new TextField();
		guessField.setPromptText("Guess a number");
		GridPane.setConstraints(guessField, 0, 1);
		
		Button guessButton = new Button("Guess");
		guessButton.setOnAction(e -> 
		{
			validateInt(guessField, guessField.getText());
			guessField.setText("");
		});
		GridPane.setConstraints(guessButton, 0, 2);
		
		grid.getChildren().addAll(thinkingLabel, guessField, guessButton);
		guessScene = new Scene(grid, 280, 120);
		
		window.setScene(guessScene);
		window.setResizable(false);
		window.show();
	}
	
	private static boolean validateInt(TextField input, String message)
	{
		try
		{
			int guess = Integer.parseInt(input.getText());
			if (guess != guessNum)
			{
				if (guess < guessNum)
				{
					AlertBox.display("Too low", "Your guess was too low.", "Try again");
				}
				
				else if (guess > guessNum)
				{
					AlertBox.display("Too high", "Your guess was too high.", "Try again");
				}
				
			}
			
			else
			{
				AlertBox.display("Correct", "You guessed correctly! The answer was: " + guessNum, "Go back to game select screen");
			}
			return true;
		}
		
		catch (NumberFormatException e)
		{
			AlertBox.display("Error", "" + message + " is not a valid integer.", "Close");
			return false;
		}
	}
	
}
