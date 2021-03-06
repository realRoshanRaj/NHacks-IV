package fxApp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class TabController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			tabChanged(tabPane.getSelectionModel().getSelectedItem().toString());
		});
	}

	private void tabChanged(String tabName) {
		if (tabPane.getSelectionModel().isSelected(3)) {
			GraphController.graph();
		} else if (tabPane.getSelectionModel().isSelected(2)) {
			StatsController.doGraph();
		}
	}

	@FXML
	private TabPane tabPane;

}
