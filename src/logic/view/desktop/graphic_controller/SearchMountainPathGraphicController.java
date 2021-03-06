package logic.view.desktop.graphic_controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import logic.bean.SimpleMountainPathBean;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.view.desktop.graphic_element.SimpleMountainPathListCell;

public class SearchMountainPathGraphicController extends GraphicController {
	
	private static final Logger LOGGER = Logger.getLogger(SearchMountainPathGraphicController.class.getName());
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private Label lbNoResults;
	
	@FXML 
	private Button btnAssistedResearch;
	
	@FXML
	private HBox itemBox;

	@FXML
	private ListView<SimpleMountainPathBean> listViewMountainPath;

	public SearchMountainPathGraphicController(Controller controller) {
		super(controller);
	}
	
	@FXML 
	public void onAssistedResearchRequest(ActionEvent event) {
		getViewMountainPathInfoController().assistedResearchRequest();
		this.executeAction(this.myController);
	}
	
	@FXML
	public void onSearchRequest(ActionEvent event) {
		lbNoResults.setText("");
		// Recupero richiesta di ricerca (testo di input dell'utente)
		String request = txtSearch.getText();
		
		// Controllo se stringa vuota
		if (request.isEmpty()) {
			//comunico all'utente che non può inserire stringhe vuote
		}
		else {
			setupLayout(getViewMountainPathInfoController().searchMountainPathByName(request));
			
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
	@Override
	protected void setupLayout() {
		// Recupera i risultati della precedente ricerca
		List<SimpleMountainPathBean> newSearchResults = getViewMountainPathInfoController().getPreviousSearchResults();
		if(newSearchResults == null) {
			lbNoResults.setText("");
		}
		else setupLayout(newSearchResults);
		
		
	}
	
	private void setupLayout(List<SimpleMountainPathBean> newSearchResult) {
		if(newSearchResult.isEmpty())
			lbNoResults.setText("No matches found for the inserted name");
		else {
			// Costruisco l'observable list da passare alla funzione che si occupa di rimpire la list view
			ObservableList<SimpleMountainPathBean> beanList = FXCollections.observableArrayList();
			beanList.addAll(newSearchResult);
			fillListView(beanList);
			
		}

	}
	
	@Override
	protected String getFXMLFileName() {
		return "searchPathLayout";
	}
	
	@FXML
	public void onListViewItemClicked(MouseEvent event) {
		// Comunico al controller applicativo il mountain path selezionato
		getViewMountainPathInfoController().setSelectedMountainPath(listViewMountainPath.getSelectionModel().getSelectedItem().getName());
		
		// Switch alla pagina per visualizzare le informazioni del path selezionato
		try {
			this.executeAction(this.myController);
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(),e);
		}
	}
	
	private ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}
	
	@Override
	public void switchPage(Pane paneToSwitch) {
		MainGraphicController.getInstance().switchPage(paneToSwitch);
		
	}
	
}
