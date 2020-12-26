package logic.view.desktop.graphic_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import logic.controller.SearchMountainPathController;
import logic.view.bean.SimpleMountainPathBean;
import logic.view.desktop.GraphicController;
import logic.view.desktop.graphic_element.CustomListCell;

public class SearchMountainPathGraphicController extends GraphicController{
	
	@FXML
	private BorderPane rootBorderPane;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private ListView<SimpleMountainPathBean> listViewMountainPath;
	
	private ObservableList<SimpleMountainPathBean> beanList;
	private SearchMountainPathController controller;
	
	public SearchMountainPathGraphicController() {
		super("searchPathLayout");
		this.beanList = FXCollections.observableArrayList();
		this.controller = new SearchMountainPathController();
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		// recupero richiesta di ricerca (testo di input dell'utente)
		String request = txtSearch.getText();
		
		// controllo se stringa vuota
		if (request.isEmpty()) {
			//comunicare all'utente che la stringa è vuota
		}
		else {
			// richiamo metodo di ricerca del controller applicativo SearchController
			this.beanList.addAll(controller.searchMountainPath(request));
			
			// riempimento della list view coi risultati della ricerca
			listViewMountainPath.setItems(this.beanList);
			listViewMountainPath.setCellFactory(new Callback<ListView<SimpleMountainPathBean>, ListCell<SimpleMountainPathBean>>() {
	            @Override
	            public ListCell<SimpleMountainPathBean> call(ListView<SimpleMountainPathBean> listViewMountainPath) {
	                return new CustomListCell();
	            }
	        });
		}
	}
	
	@FXML
	public void onListViewItemClicked(MouseEvent event) {
		// switch alla pagina di view mountain path info
		System.out.println("hai cliccato l'item " + listViewMountainPath.getSelectionModel().getSelectedItem().getName());
		
		//Switch alla pagina del view info
		ViewMountainPathInfoGraphicController viewInfoCtrl = new ViewMountainPathInfoGraphicController();
		viewInfoCtrl.switchPage(rootBorderPane);
	}
	
	
}
