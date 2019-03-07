import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.FileNotFoundException;

public class StratRoulette extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //grid.setGridLinesVisible(true);

        //TEAM SIZE CHOICE
        Text teamSizeTitle = new Text("Team size:");

        ChoiceBox teamSizeChoiceBox = new ChoiceBox();
        teamSizeChoiceBox.getItems().addAll("1", "2", "3", "4");
        teamSizeChoiceBox.setValue("3");

        //LEGENDS CHOICE
        Text legendsTitle = new Text("Legends:");

        ChoiceBox legendsChoiceBox = new ChoiceBox();
        legendsChoiceBox.getItems().addAll("Yes", "No");
        legendsChoiceBox.setValue("Yes");

        //WEAPONS CHOICE
        Text weaponsTitle = new Text("Weapons:");

        ChoiceBox weaponsChoiceBox = new ChoiceBox();
        weaponsChoiceBox.getItems().addAll("Weapons", "Types", "Ammo", "No");
        weaponsChoiceBox.setValue("Weapons");

        //STRAT RADIOS
        Text stratTitle = new Text("Strat:");

        ChoiceBox stratsChoiceBox = new ChoiceBox();
        stratsChoiceBox.getItems().addAll("Yes", "No");
        stratsChoiceBox.setValue("Yes");

        //ROLL BUTTON
        Button rollButton = new Button("Roll!");
        HBox rollButtonHBox = new HBox(10);
        rollButtonHBox.getChildren().add(rollButton);
        rollButtonHBox.setAlignment(Pos.CENTER);

        //RESULTS TEXT
        final Text resultsText = new Text();
        VBox resultsTextVBox = new VBox(10);
        resultsTextVBox.getChildren().add(resultsText);
        resultsTextVBox.setMinSize(270, 360);
        resultsTextVBox.setId("resultsBox");

        //HANDLE ROLL EVENT
        rollButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String results;
                String[] args = {"3", "legends", "weapons", "strat"};
                args[0] = (String)teamSizeChoiceBox.getValue();
                args[1] = legendsChoiceBox.getValue().equals("Yes") ? "legends" : "nolegends";
                
                String arg2 = "weapons";
                if(weaponsChoiceBox.getValue().equals("Weapons"))
                    arg2 = "weapons";
                else if(weaponsChoiceBox.getValue().equals("Types"))
                    arg2 = "types";
                else if(weaponsChoiceBox.getValue().equals("Ammo"))
                    arg2 = "ammo";
                else if(weaponsChoiceBox.getValue().equals("No"))
                    arg2 = "noweapons";
                args[2] = arg2;

                args[3] = stratsChoiceBox.getValue().equals("Yes") ? "strat" : "nostrat";

                try
                {
                    results = Roll.roll(args);
                }
                catch(FileNotFoundException e)
                {
                    results = e.getMessage();
                }
                catch(IOException e)
                {
                    results = e.getMessage();
                }
                resultsText.setFill(Color.WHITE);
                resultsText.setText(results);
            }
        });

        //ADD EVERYTHING TO ROOT
        grid.add(teamSizeTitle, 0, 1);
        grid.add(teamSizeChoiceBox, 1, 1);
        grid.add(legendsTitle, 0, 2);
        grid.add(legendsChoiceBox, 1, 2);
        grid.add(weaponsTitle, 0, 3);
        grid.add(weaponsChoiceBox, 1, 3);
        grid.add(stratTitle, 0, 4);
        grid.add(stratsChoiceBox, 1, 4);
        grid.add(rollButtonHBox, 0, 5, 2, 1);
        grid.add(resultsTextVBox, 2, 0, 1, 7);

        Scene scene = new Scene(grid);
        scene.getStylesheets().add("style.css");
        
        primaryStage.setTitle("Apex Legends Strat Roulette");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
