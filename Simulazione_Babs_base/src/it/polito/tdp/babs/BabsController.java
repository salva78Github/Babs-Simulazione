package it.polito.tdp.babs;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.babs.exception.BabsException;
import it.polito.tdp.babs.model.Model;
import it.polito.tdp.babs.model.StationStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class BabsController {

	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private DatePicker pickData;

	@FXML
	private Slider sliderK;

	@FXML
	private TextArea txtResult;

	@FXML
	void doContaTrip(ActionEvent event) {
		LocalDate ld = this.pickData.getValue();
		System.out.println("<doContaTrip> ld: " + ld.toString());
		
		try {
			List<StationStatistics> ssList = this.model.getStationStatistics(ld);
			this.txtResult.setText("Statistiche per la data " + ld.toString() + "\n");
			for(StationStatistics ss : ssList){
				this.txtResult.appendText(String.format("[%s - %f] P:%d - A:%d\n",ss.getStation().getName(), ss.getStation().getPosition().getLatitude(),ss.getStartTrips(), ss.getEndTrips()));
			}
				
				
		} catch (BabsException e) {
			this.txtResult.setText(e.getMessage());
		}
		
	}

	@FXML
	void doSimula(ActionEvent event) {
		this.txtResult.setText("");
		LocalDate ld = this.pickData.getValue();
		System.out.println("<doContaTrip> ld: " + ld.toString());
		
		DayOfWeek dow = ld.getDayOfWeek();
		System.out.println("<doContaTrip> dow: " + dow);
		if(DayOfWeek.SATURDAY.equals(dow) || DayOfWeek.SUNDAY.equals(dow)){
			this.txtResult.setText("Scegliere un giorno feriale");
		}
		else{
			
		}
		
	}

	@FXML
	void initialize() {
		assert pickData != null : "fx:id=\"pickData\" was not injected: check your FXML file 'Babs.fxml'.";
		assert sliderK != null : "fx:id=\"sliderK\" was not injected: check your FXML file 'Babs.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Babs.fxml'.";

		pickData.setValue(LocalDate.of(2013, 9, 1));
	}
}
