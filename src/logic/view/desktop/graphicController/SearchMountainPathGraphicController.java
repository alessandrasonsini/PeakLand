package logic.view.desktop.graphicController;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import logic.controller.SearchController;
import logic.view.bean.SimpleMountainPathBean;

public class SearchMountainPathGraphicController {
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private ListView<SimpleMountainPathBean> listViewMountainPath;
	
	private ObservableList<SimpleMountainPathBean> beanList;
	private SearchController controller;
	
	public SearchMountainPathGraphicController() {
		this.beanList = FXCollections.observableArrayList();
		this.controller = new SearchController();
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		// recupero richiesta di ricerca
		String request = txtSearch.getText();
		
		// controllo se stringa vuota
		if (request.isEmpty()) {
			//comunicare all'utente che la stringa è vuota
		}
		else {
			// richiamo metodo di ricerca del controller applicativo
			this.beanList.addAll(controller.searchMountainPath(request));
			listViewMountainPath.setItems(this.beanList);
			listViewMountainPath.setCellFactory(new Callback<ListView<SimpleMountainPathBean>, ListCell<SimpleMountainPathBean>>() {
	            @Override
	            public ListCell<SimpleMountainPathBean> call(ListView<SimpleMountainPathBean> listViewMountainPath) {
	                return new CustomListCell();
	            }
	        });
		}
	}
}
