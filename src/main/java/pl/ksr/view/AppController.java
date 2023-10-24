package pl.ksr.view;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import pl.ksr.logic.database.DatabaseConnector;
import pl.ksr.logic.database.Earthquake;
import pl.ksr.logic.summarization.SummarizationHelper;
import pl.ksr.logic.summarization.multiSubjects.*;
import pl.ksr.logic.summarization.oneSubject.OneSubjectSummarization;
import pl.ksr.logic.summarization.oneSubject.OneSubjectSummarizationFirstForm;
import pl.ksr.logic.summarization.oneSubject.OneSubjectSummarizationSecondForm;
import pl.ksr.logic.variables.AbsoluteQuantifier;
import pl.ksr.logic.variables.LinguisticVariable;
import pl.ksr.logic.variables.Quantifier;
import pl.ksr.logic.variables.RelativeQuantifier;

import java.io.*;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

    @FXML
    public CheckComboBox variableComboBox;

    @FXML
    public TableView tableViewOneSubject;
    @FXML
    public TableView tableViewMultipleSubject;
    public ArrayList<Earthquake> earthquakes;

    public TableColumn Summary = new TableColumn<>("Summary");
    public TableColumn SummaryMS = new TableColumn<>("Summary");
    public TableColumn T1 = new TableColumn<>("T1");
    public TableColumn T1MS = new TableColumn<>("T1");
    public TableColumn T2 = new TableColumn<>("T2");
    public TableColumn T3 = new TableColumn<>("T3");
    public TableColumn T4 = new TableColumn<>("T4");
    public TableColumn T5 = new TableColumn<>("T5");
    public TableColumn T6 = new TableColumn<>("T6");
    public TableColumn T7 = new TableColumn<>("T7");
    public TableColumn T8 = new TableColumn<>("T8");
    public TableColumn T9 = new TableColumn<>("T9");
    public TableColumn T10 = new TableColumn<>("T10");
    public TableColumn T11 = new TableColumn<>("T11");
    public TableColumn TOS = new TableColumn<>("T");

    public ArrayList<Double> weights;

    private String subject1 = "Short-Period earthquakes";
    private String subject2 = "Local earthquakes";
    @FXML
    private Label numberOfSummaries;

    private int number;

    @FXML
    private ProgressBar progressBar = new ProgressBar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<LinguisticVariable> variables = Initializer.initializeLinguisticVariables();
        List<LinguisticVariable> addedVariables = Initializer.ADDED_VARIABLES;
        weights = Initializer.WEIGHTS;
        variables.addAll(addedVariables);
        fillCheckComboBox(variableComboBox, variables);
        earthquakes = DatabaseConnector.read(DatabaseConnector.connect());
        Summary.setPrefWidth(280);
        T1.setPrefWidth(50);
        T2.setPrefWidth(50);
        T3.setPrefWidth(50);
        T4.setPrefWidth(50);
        T5.setPrefWidth(50);
        T6.setPrefWidth(50);
        T7.setPrefWidth(50);
        T8.setPrefWidth(50);
        T9.setPrefWidth(50);
        T10.setPrefWidth(50);
        T11.setPrefWidth(50);
        TOS.setPrefWidth(50);
        tableViewOneSubject.getColumns().addAll(Summary, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, TOS);
        tableViewOneSubject.setVisible(false);
        tableViewMultipleSubject.getColumns().addAll(SummaryMS, T1MS);
        tableViewMultipleSubject.setVisible(false);
        SummaryMS.setPrefWidth(800);
        T1MS.setPrefWidth(80);
        Summary.setCellValueFactory(new PropertyValueFactory<>("summary"));
        SummaryMS.setCellValueFactory(new PropertyValueFactory<>("summary"));
        T1.setCellValueFactory(new PropertyValueFactory<>("t1"));
        T1MS.setCellValueFactory(new PropertyValueFactory<>("t1"));
        T2.setCellValueFactory(new PropertyValueFactory<>("t2"));
        T3.setCellValueFactory(new PropertyValueFactory<>("t3"));
        T4.setCellValueFactory(new PropertyValueFactory<>("t4"));
        T5.setCellValueFactory(new PropertyValueFactory<>("t5"));
        T6.setCellValueFactory(new PropertyValueFactory<>("t6"));
        T7.setCellValueFactory(new PropertyValueFactory<>("t7"));
        T8.setCellValueFactory(new PropertyValueFactory<>("t8"));
        T9.setCellValueFactory(new PropertyValueFactory<>("t9"));
        T10.setCellValueFactory(new PropertyValueFactory<>("t10"));
        T11.setCellValueFactory(new PropertyValueFactory<>("t11"));
        TOS.setCellValueFactory(new PropertyValueFactory<>("t"));
        numberOfSummaries.setVisible(false);
        progressBar.setVisible(false);


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
                 weights = Initializer.WEIGHTS;
                List<LinguisticVariable> variables = Initializer.initializeLinguisticVariables();
                List<LinguisticVariable> addedVariables = Initializer.ADDED_VARIABLES;
                variables.addAll(addedVariables);
                fillCheckComboBox(variableComboBox, variables);
            } catch (IOException e) {
                AlertBox.messageBox("Save Error",
                        "Error while loading", Alert.AlertType.ERROR);
            } catch (ClassNotFoundException e) {
                AlertBox.messageBox("Save Error",
                        "Error while loading", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void onGenerateOSClick(ActionEvent actionEvent) {
        number = 0;

        if (variableComboBox.getCheckModel().getCheckedItems().size() == 0) {
            AlertBox.messageBox("Warning",
                    "Select feature", Alert.AlertType.WARNING);
        }


        Task<Void> generation = new Task<Void>() {
            @Override
                    protected Void call()throws Exception {
                progressBar.setVisible(true);
                numberOfSummaries.setVisible(false);
                tableViewOneSubject.getItems().clear();
                tableViewMultipleSubject.getItems().clear();
                tableViewMultipleSubject.setVisible(false);
                tableViewOneSubject.setVisible(true);
                generateOneSubjectFirstFormSummaries();
                generateOneSubjectSecondFormSummaries();
                Platform.runLater(
                        (new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisible(false);
                                numberOfSummaries.setText("Total number of summaries: " + number);
                                numberOfSummaries.setVisible(true);
                            }
                        }));
                return null;

            }

        };


        Thread th = new Thread(generation);
        th.setDaemon(true);
        th.start();

    }

    @FXML
    protected void onGenerateMSClick(ActionEvent actionEvent) {
        number = 0;
        Task<Void> generation = new Task<Void>() {
            @Override
            protected Void call()throws Exception {
                progressBar.setVisible(true);
                numberOfSummaries.setVisible(false);
                tableViewOneSubject.setVisible(false);
                tableViewMultipleSubject.setVisible(true);
                tableViewOneSubject.getItems().clear();
                tableViewMultipleSubject.getItems().clear();
                generateTwoSubjectFirstFormSummaries();
                generateTwoSubjectSecondAndThirdFormSummaries();
                generateTwoSubjectFourthFormSummaries();
                Platform.runLater(
                        (new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisible(false);
                                numberOfSummaries.setText("Total number of summaries: " + number);
                                numberOfSummaries.setVisible(true);
                            }
                        }));
                return null;

            }

        };


        Thread th = new Thread(generation);
        th.setDaemon(true);
        th.start();

    }

    @FXML
    protected void onEditClick(ActionEvent actionEvent) throws IOException {
        StageController.reloadStage("/pl/ksr/view/editView.fxml", "Editor");
    }


    public static void fillCheckComboBox(CheckComboBox comboBox, Collection collection) {
        List items = comboBox.getItems();
        items.clear();
        collection.forEach((it) -> items.add(it));
    }

    private void generateOneSubjectFirstFormSummaries() {
        List<LinguisticVariable> variables = variableComboBox.getCheckModel().getCheckedItems();
        List quantifiers = Initializer.initializeRelativeQualifiers();
        List absoluteQuantifiers = Initializer.initializeAbsoluteQualifiers();
        List addedRelativeQuantifiers = Initializer.ADDED_RELATIVE_QUANTIFIERS;
        List addedAbsoluteQuantifiers = Initializer.ADDED_ABSOLUTE_QUANTIFIERS;
        quantifiers.addAll(absoluteQuantifiers);
        quantifiers.addAll(addedRelativeQuantifiers);
        quantifiers.addAll(addedAbsoluteQuantifiers);
        List<List<LinguisticVariable>> combinations = getAllCombinations(variables);
        for (List<LinguisticVariable> combination : combinations) {
            for (Object quantifier : quantifiers) {
                OneSubjectSummarization summarization = new OneSubjectSummarizationFirstForm(
                        "Earthquakes",
                        combination,
                        (Quantifier) quantifier,
                        earthquakes);
                number++;
                SummaryTableRecord st = new SummaryTableRecord(summarization.toString(), summarization.generate(weights));
                tableViewOneSubject.getItems().add(st);

            }
        }
    }

    private void generateOneSubjectSecondFormSummaries() {
        List<LinguisticVariable> variables = variableComboBox.getCheckModel().getCheckedItems();
        List<RelativeQuantifier> quantifiers = Initializer.initializeRelativeQualifiers();
        List addedRelativeQuantifiers = Initializer.ADDED_RELATIVE_QUANTIFIERS;
        List<List<LinguisticVariable>> combinations = getAllCombinations(variables);
        combinations.remove(combinations.get(combinations.size() - 1));
        quantifiers.addAll(addedRelativeQuantifiers);
        for (List<LinguisticVariable> combination : combinations) {
            for (RelativeQuantifier quantifier : quantifiers) {
                List<LinguisticVariable> copy = new ArrayList<>(Arrays.asList(new LinguisticVariable[variables.size()]));
                Collections.copy(copy, variables);
                copy.removeAll(combination);
                List<List<LinguisticVariable>> complementCombinations = getAllCombinations(copy);
                for (List<LinguisticVariable> complementCombination : complementCombinations) {
                    OneSubjectSummarizationSecondForm summarization1 = new OneSubjectSummarizationSecondForm(
                            "Earthquakes",
                            combination,
                            complementCombination,
                            quantifier,
                            earthquakes
                    );

                    SummaryTableRecord st1 = new SummaryTableRecord(summarization1.toString(), summarization1.generate(weights));
                    tableViewOneSubject.getItems().add(st1);
                    number += 1;
                }

            }

        }
    }

    private void generateTwoSubjectFirstFormSummaries() {
        List<LinguisticVariable> variables = variableComboBox.getCheckModel().getCheckedItems();
        List<RelativeQuantifier> quantifiers = Initializer.initializeRelativeQualifiers();
        List<RelativeQuantifier> addedRelativeQuantifiers = Initializer.ADDED_RELATIVE_QUANTIFIERS;
        quantifiers.addAll(addedRelativeQuantifiers);
        List<List<LinguisticVariable>> combinations = getAllCombinations(variables);
        for (List<LinguisticVariable> combination : combinations) {
            for (RelativeQuantifier quantifier : quantifiers) {
                TwoSubjectsSummarization summarization1 = new TwoSubjectsSummarizationFirstForm(
                        subject1,
                        subject2,
                        combination,
                        earthquakes,
                        quantifier);
                TwoSubjectsSummarization summarization2 = new TwoSubjectsSummarizationFirstForm(
                        subject2,
                        subject1,
                        combination,
                        earthquakes,
                        quantifier);
                number += 2;
                SummaryTableRecord st = new SummaryTableRecord(summarization1.toString(),
                        String.valueOf(SummarizationHelper.format(summarization1.getDegreeOfTruth())));
                SummaryTableRecord st2 = new SummaryTableRecord(summarization2.toString(),
                        String.valueOf(SummarizationHelper.format(summarization2.getDegreeOfTruth())));
                tableViewMultipleSubject.getItems().add(st);
                tableViewMultipleSubject.getItems().add(st2);
            }
        }

    }

    private void generateTwoSubjectSecondAndThirdFormSummaries() {
        List<LinguisticVariable> variables = variableComboBox.getCheckModel().getCheckedItems();
        List<RelativeQuantifier> quantifiers = Initializer.initializeRelativeQualifiers();
        List addedRelativeQuantifiers = Initializer.ADDED_RELATIVE_QUANTIFIERS;
        List<List<LinguisticVariable>> combinations = getAllCombinations(variables);
        combinations.remove(combinations.get(combinations.size() - 1));
        quantifiers.addAll(addedRelativeQuantifiers);

        for (List<LinguisticVariable> combination : combinations) {
            for (RelativeQuantifier quantifier : quantifiers) {
                List<LinguisticVariable> copy = new ArrayList<>(Arrays.asList(new LinguisticVariable[variables.size()]));
                Collections.copy(copy, variables);
                copy.removeAll(combination);
                List<List<LinguisticVariable>> complementCombinations = getAllCombinations(copy);
                for (List<LinguisticVariable> complementCombination : complementCombinations) {
                    TwoSubjectsSummarization summarizationSecondForm1 = new TwoSubjectsSummarizationSecondForm(
                            subject1,
                            subject2,
                            combination,
                            earthquakes,
                            quantifier,
                            complementCombination
                    );
                    TwoSubjectsSummarization summarizationSecondForm2 = new TwoSubjectsSummarizationSecondForm(
                            subject2,
                            subject1,
                            combination,
                            earthquakes,
                            quantifier,
                            complementCombination
                    );
                    TwoSubjectsSummarization summarizationThirdForm1 = new TwoSubjectsSummarizationThirdForm(
                            subject1,
                            subject2,
                            combination,
                            earthquakes,
                            quantifier,
                            complementCombination
                    );
                    TwoSubjectsSummarization summarizationThirdForm2 = new TwoSubjectsSummarizationThirdForm(
                            subject2,
                            subject1,
                            combination,
                            earthquakes,
                            quantifier,
                            complementCombination
                    );
                    SummaryTableRecord st1 = new SummaryTableRecord(summarizationSecondForm1.toString(), SummarizationHelper.format(summarizationSecondForm1.getDegreeOfTruth()));
                    tableViewMultipleSubject.getItems().add(st1);
                    SummaryTableRecord st2 = new SummaryTableRecord(summarizationSecondForm2.toString(), SummarizationHelper.format(summarizationSecondForm2.getDegreeOfTruth()));
                    tableViewMultipleSubject.getItems().add(st2);
                    SummaryTableRecord st3 = new SummaryTableRecord(summarizationThirdForm1.toString(), SummarizationHelper.format(summarizationThirdForm1.getDegreeOfTruth()));
                    tableViewMultipleSubject.getItems().add(st3);
                    SummaryTableRecord st4 = new SummaryTableRecord(summarizationThirdForm2.toString(), SummarizationHelper.format(summarizationThirdForm2.getDegreeOfTruth()));
                    tableViewMultipleSubject.getItems().add(st4);
                    number += 4;
                }

            }
        }
    }


    private void generateTwoSubjectFourthFormSummaries() {
        List<LinguisticVariable> variables = variableComboBox.getCheckModel().getCheckedItems();
        List<List<LinguisticVariable>> combinations = getAllCombinations(variables);
        for (List<LinguisticVariable> combination : combinations) {
            TwoSubjectsSummarization summarization1 = new TwoSubjectsSummarizationFourthForm(
                    subject1,
                    subject2,
                    combination,
                    earthquakes
            );
            TwoSubjectsSummarization summarization2 = new TwoSubjectsSummarizationFourthForm(
                    subject2,
                    subject1,
                    combination,
                    earthquakes
            );
            number += 2;
            SummaryTableRecord st = new SummaryTableRecord(summarization1.toString(),
                    String.valueOf(SummarizationHelper.format(summarization1.getDegreeOfTruth())));
            SummaryTableRecord st2 = new SummaryTableRecord(summarization2.toString(),
                    String.valueOf(SummarizationHelper.format(summarization2.getDegreeOfTruth())));
            tableViewMultipleSubject.getItems().add(st);
            tableViewMultipleSubject.getItems().add(st2);
        }
    }

    private List<List<LinguisticVariable>> getAllCombinations(List<LinguisticVariable> elements) {
        List<List<LinguisticVariable>> combinationList = new ArrayList<List<LinguisticVariable>>();
        for (long i = 1; i < Math.pow(2, elements.size()); i++) {
            List<LinguisticVariable> list = new ArrayList<LinguisticVariable>();
            for (int j = 0; j < elements.size(); j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    list.add(elements.get(j));
                }
            }
            combinationList.add(list);
        }
        return combinationList;
    }
}