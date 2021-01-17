package logic.view.desktop.graphic_controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.model.bean.SimpleMountainPathBean;
import logic.view.desktop.graphic_element.SimpleMountainPathListCell;

public class SearchMountainPathGraphicController extends GraphicController {
	
	private static final Logger LOGGER = Logger.getLogger(SearchMountainPathGraphicController.class.getName());
	
	@FXML
	private TextField txtSearch;
	
	@FXML 
	private Button btnAssistedResearch;
	
	@FXML
	private HBox itemBox;
	
	@FXML
	private ListView<SimpleMountainPathBean> listViewMountainPath;	

	private static final String searchResultKey = "searchResult"; 
	private static final String selectedPathKey = "selectedPath";
	
	public SearchMountainPathGraphicController(Controller controller) {
		super(controller);
		setupLayout();
	}
	
	@FXML 
	public void onAssistedResearchRequest(ActionEvent event) {
		Button source = (Button)event.getSource();
		
		// da fare
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		// Recupero richiesta di ricerca (testo di input dell'utente)
		String request = txtSearch.getText();
		
		// Controllo se stringa vuota
		if (request.isEmpty()) {
			//comunico all'utente che non può inserire stringhe vuote
		}
		else {
			ObservableList<SimpleMountainPathBean> beanList = FXCollections.observableArrayList();
			// Richiamo metodo di ricerca del controller applicativo SearchController
			beanList.addAll(getViewMountainPathInfoController().searchMountainPathByName(request));
			
			// Salva i risultati sulla sessione
			MainGraphicController.getInstance().updateSession(searchResultKey,beanList);
			
			fillListView(beanList);
			
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
	
	// Fa il setup dell'FXML con i dati salvati nella sessione
	// qualora l'utente avesse precedentemente effettuato una ricerca
	private void setupLayout() {
		// Recupera dalla sessione la lista dei risultati precedenti
		@SuppressWarnings("unchecked")
		ObservableList<SimpleMountainPathBean> beanList = (ObservableList<SimpleMountainPathBean>) MainGraphicController.getInstance().getSession().get(searchResultKey);
		fillListView(beanList);
	}
	
	@Override
	protected String getFXMLFileName() {
		return "searchPathLayout";
	}

	@FXML
	public void onListViewItemClicked(MouseEvent event) {
		// Memorizza la bean del path selezionato nella sessione
		MainGraphicController.getInstance().updateSession(selectedPathKey, listViewMountainPath.getSelectionModel().getSelectedItem().getName());
		
		// Switch alla pagina per visualizzare le informazioni del path selezionato
		try {
			this.executeAction(this.myController,"Item selected");
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(),e);
		}
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}
	
}
