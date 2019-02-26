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

public class StratRoulette extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setGridLinesVisible(false);

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
        resultsTextVBox.setMinSize(220, 300);
        resultsTextVBox.setPadding(new Insets(0, 10, 0, 10));

        //HANDLE ROLL EVENT
        rollButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
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

                String results = Roll.roll(args);
                resultsText.setText(results);
            }
        });

        //ADD EVERYTHING TO ROOT
        root.add(teamSizeTitle, 0, 1);
        root.add(teamSizeChoiceBox, 1, 1);
        root.add(legendsTitle, 0, 2);
        root.add(legendsChoiceBox, 1, 2);
        root.add(weaponsTitle, 0, 3);
        root.add(weaponsChoiceBox, 1, 3);
        root.add(stratTitle, 0, 4);
        root.add(stratsChoiceBox, 1, 4);
        root.add(rollButtonHBox, 0, 5, 2, 1);
        root.add(resultsTextVBox, 2, 0, 1, 5);

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Apex Legends Strat Roulette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
