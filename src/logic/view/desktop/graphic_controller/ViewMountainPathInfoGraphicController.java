package logic.view.desktop.graphic_controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.controller.Controller;
import logic.controller.ViewMountainPathInfoController;
import logic.model.bean.MountainPathBean;

public class ViewMountainPathInfoGraphicController extends GraphicController{
	
	private static final Logger LOGGER = Logger.getLogger(ViewMountainPathInfoGraphicController.class.getName());
	
	@FXML
	private Button btnBack;
	
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
	private Label lbTime;
	
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
	
	@FXML
	private ImageView imgMountainPath;
	
	@FXML
	private ImageView imgProfile;
	
	@FXML
	private Button btnPrevImg;
	
	@FXML
	private Button btnNextImg;
	
	private List<Image> selectedMountainPathImages;
	private int currentImageNumber = -1;
	
	
	public ViewMountainPathInfoGraphicController(Controller controller) {
		super(controller);
		
		// Recupero delle informazioni relative al mountain path selezionato dall'utente
		MountainPathBean selectedMountainPath = getViewMountainPathInfoController().getSelectedMountainPath();
		
		// GESTIRE CONTROLLO IN CASO DI BEAN == NULL
		if (selectedMountainPath != null) {
			this.getImages();
			this.setupLayout(selectedMountainPath);
		}
			
		else
			LOGGER.log(Level.FINE, "No parameters inserted");
		
	}
	
	@FXML
	public void onChangeImage(ActionEvent event) {
		if(((Button)event.getSource()).getId().equals("btnPrevImg"))
			this.currentImageNumber--;
		else 
			this.currentImageNumber++;
		setupImage();
	}
	
	private void enableCorrectButtons() {
		if(this.currentImageNumber == 0)
			this.btnPrevImg.setVisible(false);
		else 
			this.btnPrevImg.setVisible(true);
		if(this.selectedMountainPathImages.size() -1 - this.currentImageNumber <= 0)
			this.btnNextImg.setVisible(false);
		else 
			this.btnNextImg.setVisible(true);
	}
	
	private void setupImage() {
		if(this.currentImageNumber == -1) {
			this.currentImageNumber = 0;
			imgProfile.setImage(this.selectedMountainPathImages.get(this.currentImageNumber));
		}
		
		imgMountainPath.setImage(this.selectedMountainPathImages.get(this.currentImageNumber));
		this.enableCorrectButtons();
	}
	
	private void getImages() {
		this.selectedMountainPathImages = new ArrayList<>();
		List<ByteArrayInputStream> streams = getViewMountainPathInfoController().getImageStreams();
		for(ByteArrayInputStream stream : streams)
			this.selectedMountainPathImages.add(new Image(stream));
	}

	@Override
	protected String getFXMLFileName() {
		return "viewMountainPathInfoLayout";
	}
	
	// Permette di tornare al layout della ricerca
	@FXML
	private void onBackPressed(ActionEvent event) {
		getViewMountainPathInfoController().onBackPressed();
		this.executeAction(this.myController);
	}
	
	public ViewMountainPathInfoController getViewMountainPathInfoController() {
		return (ViewMountainPathInfoController) myController;
	}

	// Fa il setup dell'FXML inserendo opportunamente le informazioni del path
	// che ha ricevuto in input dal controllore applicativo
	private void setupLayout(MountainPathBean selectedMountainPath) {
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
		lbTime.setText(selectedMountainPath.convertToText(selectedMountainPath.getHours()) + " : " + selectedMountainPath.convertToText(selectedMountainPath.getMinutes()));
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
		
		if(!this.selectedMountainPathImages.isEmpty()) {
			setupImage();
		}
		
		
	}
}
