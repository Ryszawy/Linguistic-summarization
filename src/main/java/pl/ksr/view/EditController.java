package pl.ksr.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.ksr.logic.database.AttributeExtractor;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.function.GaussianFunction;
import pl.ksr.logic.function.MembershipFunction;
import pl.ksr.logic.function.TrapezoidalFunction;
import pl.ksr.logic.function.TriangularFunction;
import pl.ksr.logic.set.FuzzySet;
import pl.ksr.logic.universeOfDiscourse.UniverseOfDiscourse;
import pl.ksr.logic.variables.AbsoluteQuantifier;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.RelativeQuantifier;

import java.io.*;
import java.net.URL;
import java.util.*;

public class EditController implements Initializable {
    @FXML
    public TextField degreeOfTruth;
    @FXML
    public TextField degreeOfImprecision;
    @FXML
    public TextField degreeOfCoverage;
    @FXML
    public TextField degreeOfAppropriateness;
    @FXML
    public TextField lengthOfSummarizer;
    @FXML
    public TextField degreeOfQuantifierImprecision;
    @FXML
    public TextField degreeOfQuantifierCardinality;
    @FXML
    public TextField degreeOfSummarizerCardinality;
    @FXML
    public TextField degreeOfQualifierImprecision;

    @FXML
    public TextField degreeOfQualifierCardinality;
    @FXML
    public TextField lengthOfQualifier;
    @FXML
    public RadioButton relativeQuantifierRadioButton;

    @FXML
    public RadioButton absoluteQuantifierRadioButton;

    @FXML
    public RadioButton variableRadioButton;
    @FXML
    public RadioButton gaussian;
    @FXML
    public RadioButton triangular;
    @FXML
    public RadioButton trapezoidal;
    @FXML
    public Label attributeName;
    @FXML
    public Button createButton;
    @FXML
    public Label pointALabel;
    @FXML
    public TextField pointATextField;
    @FXML
    public Label pointBLabel;
    @FXML
    public TextField pointBTextField;
    @FXML
    public Label pointCLabel;
    @FXML
    public TextField pointCTextField;
    @FXML
    public Label pointDLabel;
    @FXML
    public TextField pointDTextField;
    @FXML
    public TextField variableName;
    @FXML
    public Button saveMeasuresButton;


    @FXML
    private ComboBox linguisticVariableComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBox(linguisticVariableComboBox, Initializer.listOfAttributes());
        setWeights();
        ToggleGroup group = new ToggleGroup();
        relativeQuantifierRadioButton.setToggleGroup(group);
        variableRadioButton.setToggleGroup(group);
        absoluteQuantifierRadioButton.setToggleGroup(group);

        relativeQuantifierRadioButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (relativeQuantifierRadioButton.isSelected()) {
                            linguisticVariableComboBox.setVisible(false);
                            attributeName.setVisible(false);
                        }
                    }
                }
        );

        variableRadioButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (variableRadioButton.isSelected()) {
                            linguisticVariableComboBox.setVisible(true);
                            attributeName.setVisible(true);
                        }
                    }
                }
        );

        absoluteQuantifierRadioButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (absoluteQuantifierRadioButton.isSelected()) {
                            linguisticVariableComboBox.setVisible(false);
                            attributeName.setVisible(false);
                        }
                    }
                }
        );


        ToggleGroup group2 = new ToggleGroup();
        gaussian.setToggleGroup(group2);
        triangular.setToggleGroup(group2);
        trapezoidal.setToggleGroup(group2);
        gaussian.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pointALabel.setVisible(true);
                pointALabel.setText("Sigma");
                pointBLabel.setVisible(true);
                pointBLabel.setText("Mi");
                pointATextField.setVisible(true);
                pointBTextField.setVisible(true);
                pointCLabel.setVisible(false);
                pointCTextField.setVisible(false);
                pointDLabel.setVisible(false);
                pointDTextField.setVisible(false);
            }
        });

        triangular.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pointALabel.setVisible(true);
                pointALabel.setText("Point A");
                pointBLabel.setVisible(true);
                pointBLabel.setText("Point B");
                pointATextField.setVisible(true);
                pointBTextField.setVisible(true);
                pointCLabel.setVisible(true);
                pointCTextField.setVisible(true);
                pointDLabel.setVisible(false);
                pointDTextField.setVisible(false);
            }
        });
        trapezoidal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pointALabel.setVisible(true);
                pointALabel.setText("Point A");
                pointBLabel.setVisible(true);
                pointBLabel.setText("Point B");
                pointATextField.setVisible(true);
                pointBTextField.setVisible(true);
                pointCLabel.setVisible(true);
                pointCTextField.setVisible(true);
                pointDLabel.setVisible(true);
                pointDTextField.setVisible(true);
            }
        });
    }

    private void setWeights() {
        degreeOfTruth.setText(String.valueOf(Initializer.WEIGHTS.get(0)));
        degreeOfImprecision.setText(String.valueOf(Initializer.WEIGHTS.get(1)));
        degreeOfCoverage.setText(String.valueOf(Initializer.WEIGHTS.get(2)));
        degreeOfAppropriateness.setText(String.valueOf(Initializer.WEIGHTS.get(3)));
        lengthOfSummarizer.setText(String.valueOf(Initializer.WEIGHTS.get(4)));
        degreeOfQuantifierImprecision.setText(String.valueOf(Initializer.WEIGHTS.get(5)));
        degreeOfQuantifierCardinality.setText(String.valueOf(Initializer.WEIGHTS.get(6)));
        degreeOfSummarizerCardinality.setText(String.valueOf(Initializer.WEIGHTS.get(7)));
        degreeOfQualifierImprecision.setText(String.valueOf(Initializer.WEIGHTS.get(8)));
        degreeOfQualifierCardinality.setText(String.valueOf(Initializer.WEIGHTS.get(9)));
        lengthOfQualifier.setText(String.valueOf(Initializer.WEIGHTS.get(10)));
    }

    @FXML
    protected void onSaveClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("txt file",
                ".txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        final File file = fileChooser.showSaveDialog(new Stage());
        try (FileOutputStream writer = new FileOutputStream(file);
             ObjectOutputStream objectWriter = new ObjectOutputStream(writer)) {
            ArrayList<Double> weights = new ArrayList<>(Arrays.asList(
                    Double.valueOf(degreeOfTruth.getText()),
                    Double.valueOf(degreeOfImprecision.getText()),
                    Double.valueOf(degreeOfCoverage.getText()),
                    Double.valueOf(degreeOfAppropriateness.getText()),
                    Double.valueOf(lengthOfSummarizer.getText()),
                    Double.valueOf(degreeOfQuantifierImprecision.getText()),
                    Double.valueOf(degreeOfQuantifierCardinality.getText()),
                    Double.valueOf(degreeOfSummarizerCardinality.getText()),
                    Double.valueOf(degreeOfQualifierImprecision.getText()),
                    Double.valueOf(degreeOfQualifierCardinality.getText()),
                    Double.valueOf(lengthOfQualifier.getText())));
            objectWriter.writeObject(Initializer.ADDED_RELATIVE_QUANTIFIERS);
            objectWriter.writeObject(Initializer.ADDED_ABSOLUTE_QUANTIFIERS);
            objectWriter.writeObject(Initializer.ADDED_VARIABLES);
            objectWriter.writeObject(weights);
        } catch (IOException e) {
            AlertBox.messageBox("Save Error",
                    "Error while saving", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLoadClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        final File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try (FileInputStream reader = new FileInputStream(file);
                 ObjectInputStream objectReader = new ObjectInputStream(reader)) {
                Initializer.ADDED_RELATIVE_QUANTIFIERS = (List<RelativeQuantifier>) objectReader.readObject();
                Initializer.ADDED_ABSOLUTE_QUANTIFIERS = (List<AbsoluteQuantifier>) objectReader.readObject();
                Initializer.ADDED_VARIABLES = (ArrayList<LinguisticVariable>) objectReader.readObject();
                Initializer.WEIGHTS = (ArrayList<Double>) objectReader.readObject();
                setWeights();
            } catch (IOException e) {
                AlertBox.messageBox("Save Error",
                        "Error while loading", Alert.AlertType.ERROR);
            } catch (ClassNotFoundException e) {
                AlertBox.messageBox("Save Error",
                        "Error while loading", Alert.AlertType.ERROR);
            }
            AlertBox.messageBox("Loading", "Loading Successful", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    protected void onSaveMeasuresClick(ActionEvent actionEvent) {
        try {
            ArrayList<Double> weights = new ArrayList<>(Arrays.asList(
                    Double.valueOf(degreeOfTruth.getText()),
                    Double.valueOf(degreeOfImprecision.getText()),
                    Double.valueOf(degreeOfCoverage.getText()),
                    Double.valueOf(degreeOfAppropriateness.getText()),
                    Double.valueOf(lengthOfSummarizer.getText()),
                    Double.valueOf(degreeOfQuantifierImprecision.getText()),
                    Double.valueOf(degreeOfQuantifierCardinality.getText()),
                    Double.valueOf(degreeOfSummarizerCardinality.getText()),
                    Double.valueOf(degreeOfQualifierImprecision.getText()),
                    Double.valueOf(degreeOfQualifierCardinality.getText()),
                    Double.valueOf(lengthOfQualifier.getText())

            ));
            Initializer.WEIGHTS = weights;
        } catch (NumberFormatException e) {
            AlertBox.messageBox("Error",
                    "Input error", Alert.AlertType.ERROR);
        }
        if (Initializer.WEIGHTS.stream().mapToDouble(x -> x).sum() != 1.0) {
            AlertBox.messageBox("Warning",
                    "All weights must sum to 1", Alert.AlertType.WARNING);
        }
        AlertBox.messageBox("Measures Saved", "Measures Saved Successful", Alert.AlertType.INFORMATION);
    }

    @FXML
    protected void onCreateClick(ActionEvent actionEvent) {
        MembershipFunction mf = null;
        UniverseOfDiscourse universe;
        String name = variableName.getText();
        if (Objects.equals(name, "")) {
            AlertBox.messageBox("Error",
                    "Name must not be null", Alert.AlertType.ERROR);
        }
      

        if (trapezoidal.isSelected()) {
            try {
                Double.parseDouble(pointATextField.getText());
                Double.parseDouble(pointBTextField.getText());
                Double.parseDouble(pointCTextField.getText());
                Double.parseDouble(pointDTextField.getText());
            } catch (NumberFormatException e) {
                AlertBox.messageBox("Error",
                        "Wrong Input", Alert.AlertType.WARNING);
            }
            mf = new TrapezoidalFunction(Double.parseDouble(pointATextField.getText()),
                    Double.parseDouble(pointBTextField.getText()), Double.parseDouble(pointCTextField.getText())
                    , Double.parseDouble(pointDTextField.getText()));
        } else if (gaussian.isSelected()) {
            try {
                Double.parseDouble(pointATextField.getText());
                Double.parseDouble(pointBTextField.getText());
            } catch (NumberFormatException e) {
                AlertBox.messageBox("Error",
                        "Wrong Input", Alert.AlertType.WARNING);
            }
            mf = new GaussianFunction(Double.parseDouble(pointATextField.getText()),
                    Double.parseDouble(pointBTextField.getText()));
        } else if (triangular.isSelected()){
            try {
                Double.parseDouble(pointATextField.getText());
                Double.parseDouble(pointBTextField.getText());
                Double.parseDouble(pointCTextField.getText());
            } catch (NumberFormatException e) {
                AlertBox.messageBox("Error",
                        "Wrong Input", Alert.AlertType.WARNING);
            }
            mf = new TriangularFunction(Double.parseDouble(pointATextField.getText()),
                    Double.parseDouble(pointBTextField.getText()), Double.parseDouble(pointCTextField.getText()));
        }

        if (!trapezoidal.isSelected() && !gaussian.isSelected() && !triangular.isSelected()) {
            AlertBox.messageBox("Error",
                    "Select Function", Alert.AlertType.WARNING);
        }

        if (!absoluteQuantifierRadioButton.isSelected() &&
        !relativeQuantifierRadioButton.isSelected() &&
        !variableRadioButton.isSelected()) {
            AlertBox.messageBox("Error",
                    "Select type", Alert.AlertType.WARNING);
        }

        if (absoluteQuantifierRadioButton.isSelected()) {
            universe = Initializer.ABSOLUTE;
            FuzzySet<Double> fs = new FuzzySet<>(universe, mf, x -> x);
            AbsoluteQuantifier quantifier = new AbsoluteQuantifier(name, fs);
            Initializer.ADDED_ABSOLUTE_QUANTIFIERS.add(quantifier);
        }
        if (relativeQuantifierRadioButton.isSelected()) {
            universe = Initializer.RELATIVE;
            FuzzySet<Double> fs = new FuzzySet<>(universe, mf, x -> x);
            RelativeQuantifier quantifier = new RelativeQuantifier(name, fs);
            Initializer.ADDED_RELATIVE_QUANTIFIERS.add(quantifier);
        } else if(variableRadioButton.isSelected()) {
            if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == null) {
                AlertBox.messageBox("Error",
                        "Select attribute", Alert.AlertType.WARNING);
            }

            LinguisticVariable variable;
            if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "time") {
                universe = Initializer.MINUTES;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractTime);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "latitude") {
                universe = Initializer.CLIMATE_ZONE;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractLatitude);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "longitude") {
                universe = Initializer.GROUP_OF_CONTINENTS;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractLongitude);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "depth") {
                universe = Initializer.DEPTH;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractDepth);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "mag") {
                universe = Initializer.MAGNITUDE;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractMag);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "gap") {
                universe = Initializer.GAP;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractGap);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "dmin") {
                universe = Initializer.DMIN;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractDmin);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "RMS") {
                universe = Initializer.RMS;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractRMS);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "horizontalError") {
                universe = Initializer.HORIZONTAL_ERROR;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractHorizontalError);
                variable = new LinguisticVariable<>(name, fs);
            } else if (linguisticVariableComboBox.getSelectionModel().getSelectedItem() == "depthError") {
                universe = Initializer.D_ERROR;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractDepthError);
                variable = new LinguisticVariable<>(name, fs);
            } else {
                universe = Initializer.MAGNITUDE_ERROR;
                FuzzySet<Earthquake> fs = new FuzzySet<>(universe, mf, AttributeExtractor::extractMagError);
                variable = new LinguisticVariable<>(name, fs);
            }
            Initializer.ADDED_VARIABLES.add(variable);
        }
        AlertBox.messageBox("Variable Creation", "Variable Creation Successful", Alert.AlertType.INFORMATION);
    }

    @FXML
    protected void onBackClick(ActionEvent actionEvent) throws IOException {
        StageController.reloadStage("/pl/ksr/view/appView.fxml", "Database Summarizator");
    }

    public static void fillComboBox(ComboBox comboBox, Collection collection) {
        List items = comboBox.getItems();
        items.clear();
        collection.forEach((it) -> items.add(it));
    }

}
