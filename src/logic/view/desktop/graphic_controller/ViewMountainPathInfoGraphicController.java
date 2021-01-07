package logic.view.desktop.graphic_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.model.bean.MountainPathBean;

public class ViewMountainPathInfoGraphicController extends GraphicController{
	
	@FXML
	private ImageView btnBack;
	
	@FXML
	private Label lbName;
	
	@FXML
	private Label lbRegion;
	
	@FXML
	private Label lbProvince;
	
	@FXML
	private Label lbCity;
	
	@FXML
	private Label lbAltitude;
	
	@FXML
	private Label lbLenght;
	
	@FXML
	private Label lbLevel;
	
	@FXML
	private Label lbLandscape;
	
	@FXML
	private Label lbGround;
	
	@FXML
	private Label lbCycleable;
	
	@FXML
	private Label lbHistElements;
	
	@FXML
	private Label lbFam;

	@FXML
	private Label lbHours;
	
	@FXML
	private Label lbMinutes;
	
	@FXML
	private Label lbVotesNumber;
	
	@FXML
	private Label lbVote;
	
	@FXML
	private ImageView imageStar1;
	
	@FXML
	private ImageView imageStar2;
	
	@FXML
	private ImageView imageStar3;
	
	@FXML
	private ImageView imageStar4;
	
	@FXML
	private ImageView imageStar5;
	
	private MountainPathBean selectedMountainPath;
	
	
	public ViewMountainPathInfoGraphicController(Controller controller) {
		super(controller);
		
		// Recupero delle informazioni relative al mountain path selezionato dall'utente
		this.selectedMountainPath = getViewMountainPathInfoController().getMountainPathInfo();
		
		// GESTIRE CONTROLLO IN CASO DI BEAN == NULL
		if (this.selectedMountainPath != null)
			this.setupLayout();
		else
			System.out.println("Nessun parametro passato");
	}

	@Override
	protected String getFXMLFileName() {
		return "viewMountainPathInfoLayout";
	}
	
	// Permette di tornare al layout della ricerca
	@FXML
	private void onBackPressed(MouseEvent event) {
		this.switchPage(this.myController);
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}

	// Fa il setup dell'FXML inserendo opportunamente le informazioni del path
	// che ha ricevuto in input dal controllore applicativo
	private void setupLayout() {
		// NON SO SE INSERIRE QUI CONTROLLO SE BEAN E' NULL O SOPRA
		lbName.setText(selectedMountainPath.convertToText(selectedMountainPath.getName()));
		lbRegion.setText(selectedMountainPath.convertToText(selectedMountainPath.getRegion()));
		lbProvince.setText(selectedMountainPath.convertToText(selectedMountainPath.getProvince()));
		lbCity.setText(selectedMountainPath.convertToText(selectedMountainPath.getCity()));
		lbAltitude.setText(selectedMountainPath.convertToText(selectedMountainPath.getAltitude()));
		lbLenght.setText(selectedMountainPath.convertToText(selectedMountainPath.getLenght()));
		lbLevel.setText(selectedMountainPath.convertToText(selectedMountainPath.getLevel()));
		lbLandscape.setText(selectedMountainPath.convertToText(selectedMountainPath.getLandscape()));
		lbGround.setText(selectedMountainPath.convertToText(selectedMountainPath.getGround()));
		lbCycleable.setText(selectedMountainPath.convertToText(selectedMountainPath.isCycleble()));
		lbHistElements.setText(selectedMountainPath.convertToText(selectedMountainPath.isHistoricalElements()));
		lbFam.setText(selectedMountainPath.convertToText(selectedMountainPath.isFamilySuitable()));
		lbHours.setText(selectedMountainPath.convertToText(selectedMountainPath.getHours()));
		lbMinutes.setText(selectedMountainPath.convertToText(selectedMountainPath.getMinutes()));
		lbVotesNumber.setText(selectedMountainPath.convertToText(selectedMountainPath.getNumberOfVotes()));
		
		switch(selectedMountainPath.convertToText(selectedMountainPath.getVote())) {
			case "5":
				imageStar5.setVisible(true);
			case "4":
				imageStar4.setVisible(true);
			case "3":
				imageStar3.setVisible(true);
			case "2":
				imageStar2.setVisible(true);
			case "1":
				imageStar1.setVisible(true);
				break;
			default:
				lbVote.setText(selectedMountainPath.convertToText(selectedMountainPath.getVote()));
				lbVote.setVisible(true);
				break;
		}
	}
}
