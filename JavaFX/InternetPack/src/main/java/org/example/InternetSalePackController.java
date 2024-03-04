package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;


import java.nio.channels.Pipe;
import java.util.ArrayList;

public class InternetSalePackController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private ToggleGroup speed;
    @FXML
    private ToggleGroup bandwidth;
    @FXML
    private ToggleGroup duration;
    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<InternetSalePack> tableView;
    @FXML
    private TableColumn firstNameColumn;
    @FXML
    private TableColumn lastNameColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn speedColumn;
    @FXML
    private TableColumn bandwidthColumn;
    @FXML
    private TableColumn durationColumn;

    //ObservableList<InternetSalePack> packs = FXCollections.<InternetSalePack>observableArrayList();
    ObservableList<InternetSalePack> data;
    InternetSalePack pack;

    public InternetSalePackController() {

    }

    @FXML
    private void initialize() {

        pack = new InternetSalePack();

        firstName.textProperty().bindBidirectional(pack.firstNameProperty());
        lastName.textProperty().bindBidirectional(pack.lastNameProperty());
        address.textProperty().bindBidirectional(pack.addressProperty());

        speed.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    ToggleButton selected = (ToggleButton) newValue;
                    switch (selected.getId()) {
                        case "2":
                            pack.speedProperty().set("2");
                            break;
                        case "5":
                            pack.speedProperty().set("5");
                            break;
                        case "10":
                            pack.speedProperty().set("10");
                            break;
                        case "20":
                            pack.speedProperty().set("20");
                            break;
                        case "50":
                            pack.speedProperty().set("50");
                            break;
                        case "100":
                            pack.speedProperty().set("100");
                            break;
                    }
                }
            }
        });

        bandwidth.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    ToggleButton selected = (ToggleButton) newValue;
                    switch (selected.getId()) {
                        case "unu":
                            pack.bandwidthProperty().set("1");

                            break;
                        case "cinci":
                            pack.bandwidthProperty().set("5");
                            break;
                        case "zece":
                            pack.bandwidthProperty().set("10");
                            break;
                        case "suta":
                            pack.bandwidthProperty().set("100");
                            break;
                        case "Flat":
                            pack.bandwidthProperty().set("Flat");
                            break;

                    }
                }
            }
        });

        duration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    ToggleButton selected = (ToggleButton) newValue;
                    switch (selected.getId()) {
                        case "1year":
                            pack.durationProperty().set("1 year");
                            break;
                        case "2years":
                            pack.durationProperty().set("2 years");
                            break;
                    }
                }
            }
        });

        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        firstNameColumn.setCellFactory(cellFactory);
        firstNameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        ((InternetSalePack) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());

                    }
                }
        );

        lastNameColumn.setCellFactory(cellFactory);
        lastNameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        ((InternetSalePack) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                    }
                }
        );

        addressColumn.setCellFactory(cellFactory);
        addressColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        ((InternetSalePack) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAddress(t.getNewValue());
                    }
                }
        );

        speedColumn.setCellFactory(cellFactory);
        speedColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        if (t.getNewValue().equals("2") || t.getNewValue().equals("5") || t.getNewValue().equals("10") || t.getNewValue().equals("20") || t.getNewValue().equals("50") || t.getNewValue().equals("100")) {
                            ((InternetSalePack) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setSpeed(t.getNewValue());

                        } else {
                            alert("Insert a valid value for speed");
                            ;
                        }
                    }
                }

        );


        bandwidthColumn.setCellFactory(cellFactory);
        bandwidthColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        if (t.getNewValue().equals("1") || t.getNewValue().equals("5") || t.getNewValue().equals("10") || t.getNewValue().equals("100") || t.getNewValue().equals("Flat")) {
                            ((InternetSalePack) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setBandwidth(t.getNewValue());
                        } else {
                            alert("Insert a valid value for bandwidth");
                        }
                    }
                }
        );

        durationColumn.setCellFactory(cellFactory);
        durationColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InternetSalePack, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InternetSalePack, String> t) {
                        if (t.getNewValue().equals("1 year") || t.getNewValue().equals("2 years")) {
                            ((InternetSalePack) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setDuration(t.getNewValue());
                        } else {
                            alert("Insert a valid value for duration");
                        }
                    }
                }
        );

    }

    @FXML
    private void savePackage(ActionEvent event) {
        if (pack.isValid()) {
            pack.save();
//            ObservableList<InternetSalePack> data = tableView.getItems();
            data = tableView.getItems();
            data.add(new InternetSalePack(pack.getFirstName(), pack.getLastName(), pack.getAddress(), pack.getSpeed(), pack.getBandwidth(), pack.getDuration()));

            clearPackage();

        } else {
            StringBuilder errMsg = new StringBuilder();
            ArrayList<String> errList = pack.errorsProperty().get();
            for (String errList1 : errList) {
                errMsg.append(errList1);
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Package can't be saved");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }

    @FXML
    private void clearPackage() {
        firstName.setText("");
        lastName.setText("");
        address.setText("");


        if (speed.getSelectedToggle() != null || !pack.getSpeed().equals("")) {
            speed.getSelectedToggle().setSelected(false);
            pack.setSpeed("");
        }
        if (bandwidth.getSelectedToggle() != null || !pack.getBandwidth().equals("")) {
            bandwidth.getSelectedToggle().setSelected(false);
            pack.setBandwidth("");
        }
        if (duration.getSelectedToggle() != null || !pack.getDuration().equals("")) {
            duration.getSelectedToggle().setSelected(false);
            pack.setDuration("");
        }
    }

    @FXML
    private void deletePackage() {
        try {
            InternetSalePack selectedItem = tableView.getSelectionModel().getSelectedItem();
            data.remove(selectedItem);
//        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        ObservableList<InternetSalePack> selectedRows = tableView.getSelectionModel().getSelectedItems();
//        ArrayList<InternetSalePack> rows = new ArrayList<>(selectedRows);
//        rows.forEach(row -> data.remove(row));
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No data in table to delete");
            alert.setHeaderText(null);
            alert.setContentText("No data in table to delete");
            alert.showAndWait();
        }
    }

    private void alert(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
