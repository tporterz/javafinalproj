package game.model;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javafx.embed.swing.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.geometry.*;
import game.view.*;

public class Drawing
{

	static GraphicsContext graphicsContext;
	static BoxBlur blur = new BoxBlur();
	static StackPane pane = new StackPane();
	static GridPane topGrid = new GridPane();
	static Scene drawScene = new Scene(pane, 800, 500);
	static final Canvas canvas = new Canvas(800, 500);
	
	public static void draw(String title)
	{
		Stage window = new Stage();
		window.setTitle(title);
		
		ColorPicker colorPicker = new ColorPicker();
		ColorPicker secondColorPicker = new ColorPicker();
		Slider slider = new Slider();
		Label lineWidth = new Label("1.0");
		Button saveButton = new Button("Save drawing");
		Button fillButton = new Button("Fill");
		Button gradientStrokeButton = new Button("Gradient Drawing");
		Button gradientFillButton = new Button("Gradient Fill");
		
		graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.setStroke(Color.BLACK);
		graphicsContext.setLineWidth(1);
		graphicsContext.setEffect(blur);
		graphicsContext.setLineCap(StrokeLineCap.ROUND);
		graphicsContext.setLineJoin(StrokeLineJoin.ROUND);		
		
		colorPicker.setValue(Color.BLACK);
		colorPicker.setOnAction(e ->
		{
			graphicsContext.setStroke(colorPicker.getValue());
		});
		
		secondColorPicker.setValue(Color.BLACK);
		secondColorPicker.setOnAction(e ->
		{
			graphicsContext.setStroke(secondColorPicker.getValue());
		});
		
		slider.setMin(1);
		slider.setMax(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener(e ->
		{
			double sliderValue = slider.getValue();
			String svString = String.format("%.1f", sliderValue);
			lineWidth.setText(svString);
			graphicsContext.setLineWidth(sliderValue);
		});
		
		
		saveButton.setOnAction(e ->
		{
			FileChooser fileChooser = new FileChooser();
                
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
              
            //Show save file dialog
            File file = fileChooser.showSaveDialog(window);
                
            if(file != null)
            {
                 try 
                 {
                     WritableImage writableImage = new WritableImage(800, 500);
                     canvas.snapshot(null, writableImage);
                     RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                     ImageIO.write(renderedImage, "png", file);
                 } 
                    
                 catch (IOException ex) 
                 {
                     Logger.getLogger(Drawing.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
            
		});
		
		fillButton.setOnAction(e ->
		{
			graphicsContext.setFill(colorPicker.getValue());
			graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
		
		gradientStrokeButton.setOnAction(e ->
		{
			graphicsContext.setStroke(new LinearGradient(0, 0, 1, 1,
					true, CycleMethod.REFLECT,
					new Stop(0.0, colorPicker.getValue()),
					new Stop(1.0, secondColorPicker.getValue())
					));
		});
		
		gradientFillButton.setOnAction(e ->
		{
			graphicsContext.setFill(new LinearGradient(0, 0, 1, 1,
					true, CycleMethod.REFLECT,
					new Stop(0.0, colorPicker.getValue()),
					new Stop(1.0, secondColorPicker.getValue())
					));
			graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
		
		drawScene.setOnMousePressed(e ->
		{
			graphicsContext.beginPath();
			graphicsContext.lineTo(e.getSceneX(), e.getSceneY());
			graphicsContext.stroke();
		});
			
		drawScene.setOnMouseDragged(e ->
		{
			graphicsContext.lineTo(e.getSceneX(), e.getSceneY());
			graphicsContext.stroke();
		});
		
		blur.setWidth(1);
		blur.setHeight(1);
		blur.setIterations(1);
		
		topGrid.addRow(0, colorPicker, secondColorPicker, slider, lineWidth);
		topGrid.addRow(1, saveButton, fillButton, gradientStrokeButton, gradientFillButton);
		topGrid.setHgap(20);
		topGrid.setAlignment(Pos.TOP_CENTER);
		topGrid.setPadding(new Insets(20, 0, 0, 0));
		pane.getChildren().addAll(canvas, topGrid);
			
		window.setScene(drawScene);
		window.setResizable(false);
		window.show();
	}
}
