package logic.view.desktop.graphic_controller;

import java.util.logging.Level;
import java.util.logging.Logger;

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
import logic.controller.ViewMountainPathInfoController;
import logic.model.bean.SimpleMountainPathBean;
import logic.model.dao.MountainPathDao;
import logic.view.desktop.graphic_element.SimpleMountainPathListCell;

public class SearchMountainPathGraphicController extends GraphicController {
	
	private static final Logger LOGGER = Logger.getLogger(SearchMountainPathGraphicController.class.getName());
	
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
		setupLayout();
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		// Pulisco la lista dalle ricerche precedenti
		this.beanList.clear();
		// Recupero richiesta di ricerca (testo di input dell'utente)
		String request = txtSearch.getText();
		
		// Controllo se stringa vuota
		if (request.isEmpty()) {
			//comunico all'utente che non può inserire stringhe vuote
		}
		else {
			// Richiamo metodo di ricerca del controller applicativo SearchController
			this.beanList.addAll(getSearchMountainPathController().searchMountainPathByName(request));
			fillListView(this.beanList);
			
		}
	}
	
	// Metodo che si occupa del riempimento della list view con i risultati della ricerca
	private void fillListView(ObservableList<SimpleMountainPathBean> list) {
		listViewMountainPath.setItems(list);
		listViewMountainPath.setCellFactory(new Callback<ListView<SimpleMountainPathBean>, ListCell<SimpleMountainPathBean>>() {
            @Override
            public ListCell<SimpleMountainPathBean> call(ListView<SimpleMountainPathBean> listViewMountainPath) {
                return new SimpleMountainPathListCell();
            }
        });
	}
	
	// Fa il setup dell'FXML coi dati ricevuti dal controllore applicativo
	// qualora l'utente avesse precedentemente effettuato una ricerca
	private void setupLayout() {
		if (getSearchMountainPathController().getSearchResultsList() != null) {
			this.beanList.addAll(getSearchMountainPathController().getSearchResultsList());
			fillListView(this.beanList);
		}
	}
	
	@Override
	protected String getFXMLFileName() {
		return "searchPathLayout";
	}

	@FXML
	public void onListViewItemClicked(MouseEvent event) {
		// Passa la bean del MountainPath selezionato dalla list view al controller applicativo
		this.getSearchMountainPathController().setSelectedItem(listViewMountainPath.getSelectionModel().getSelectedItem());
		
		// Switch alla pagina per visualizzare le informazioni del path selezionato
		try {
			this.switchPage(this.myController);
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(),e);
			
		}
	}
	
	public ViewMountainPathInfoController getSearchMountainPathController() {
		return (ViewMountainPathInfoController) myController;
	}
	
}
