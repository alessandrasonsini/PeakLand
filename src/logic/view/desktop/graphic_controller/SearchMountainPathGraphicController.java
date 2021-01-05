package logic.view.desktop.graphic_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import logic.controller.Controller;
import logic.controller.SearchMountainPathController;
import logic.model.bean.SimpleMountainPathBean;
import logic.view.desktop.graphic_element.SimpleMountainPathListCell;

public class SearchMountainPathGraphicController extends GraphicController {
		
	@FXML
	private TextField txtSearch;
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private ListView<SimpleMountainPathBean> listViewMountainPath;
	
	// Lista di bean risultante dalla ricerca
	private ObservableList<SimpleMountainPathBean> beanList;
	
	
	public SearchMountainPathGraphicController(Controller controller) {
		super(controller);
		this.beanList = FXCollections.observableArrayList();
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		// Pulisco la lista dalle ricerche precedenti
		this.beanList.clear();
		// recupero richiesta di ricerca (testo di input dell'utente)
		String request = txtSearch.getText();
		
		// controllo se stringa vuota
		if (request.isEmpty()) {
			//comunico all'utente che non può inserire stringhe vuote
		}
		else {
			// richiamo metodo di ricerca del controller applicativo SearchController
			this.beanList.addAll(getSearchMountainPathController().searchMountainPathByName(request));
			
			// riempimento della list view con i risultati della ricerca
			listViewMountainPath.setItems(this.beanList);
			listViewMountainPath.setCellFactory(new Callback<ListView<SimpleMountainPathBean>, ListCell<SimpleMountainPathBean>>() {
	            @Override
	            public ListCell<SimpleMountainPathBean> call(ListView<SimpleMountainPathBean> listViewMountainPath) {
	                return new SimpleMountainPathListCell();
	            }
	        });
		}
	}
	
	@Override
	protected String getFXMLFileName() {
		return "searchPathLayout";
	}

	@FXML
	public void onListViewItemClicked(MouseEvent event) {
		// switch alla pagina di view mountain path info
		System.out.println("hai cliccato l'item " + listViewMountainPath.getSelectionModel().getSelectedItem().getName());
		
		// Switch alla pagina del view info
		this.executeAction("View info");
	}
	
	public SearchMountainPathController getSearchMountainPathController() {
		return (SearchMountainPathController) myController;
	}
	
}
