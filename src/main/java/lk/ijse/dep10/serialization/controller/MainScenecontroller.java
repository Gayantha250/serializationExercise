package lk.ijse.dep10.serialization.controller;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.serialization.model.StudentInfo;

import java.io.*;
import java.util.ArrayList;

public class MainScenecontroller {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnSave;
    @FXML
    private TableView<StudentInfo> tblStudentDetails;

    private File homeDirec = new File(System.getProperty("user.home"));
    private File desktop = new File(homeDirec, "Desktop");
    private File targetDirec = new File(desktop, "dep10");
    private ArrayList<StudentInfo> list;

    //  private File targetDirec1=new File(new File(new File("user.home"),"Desktop"),"dep10");

    public void initialize() {
        tblStudentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        if (!targetDirec.exists()) return;


        try {
            FileInputStream fis = new FileInputStream(targetDirec);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<StudentInfo> list1 = (ArrayList) ois.readObject();
            txtName.setText(list.get(list.size() - 1).name);
            txtAddress.setText(list.get(list.size() - 1).address);
            txtId.setText(list.get(list.size() - 1).id);

          ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        txtId.clear();
        txtAddress.clear();
        txtName.clear();
        btnDelete.setDisable(true);
        btnSave.setDisable(false);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        list = new ArrayList<>();
        ObservableList<StudentInfo> info = tblStudentDetails.getItems();
        StudentInfo student = tblStudentDetails.getSelectionModel().getSelectedItem();
        if (student == null) {
            StudentInfo studentInfo = new StudentInfo(txtId.getText(), txtName.getText(), txtAddress.getText());
            tblStudentDetails.getItems().add(studentInfo);
            list.add(studentInfo);
            btnNewStudent.fire();

            try {
                FileOutputStream fos = new FileOutputStream(targetDirec);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                new Alert(Alert.AlertType.INFORMATION, "your file is saved").showAndWait();
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            btnDelete.setDisable(false);
        }


    }

}
