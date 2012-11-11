/**   
*  This file is part of SimpleCalendar.
    
*  SimpleCalendar is free software: you can redistribute it and/or modify   
*  it under the terms of the GNU General Public License as published by   
*  the Free Software Foundation, either version 3 of the License, or   
*  (at your option) any later version.
     
*  SimpleCalendar is distributed in the hope that it will be useful,   
*  but WITHOUT ANY WARRANTY; without even the implied warranty of   
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the   
*  GNU General Public License for more details.

*  <http://www.gnu.org/licenses/>.
     
*/  
package com.kcp.pos;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/**
 * A simple calendar to pick up a date.
 *
 */
public class SimpleCalendar extends VBox{

	private Popup popup;
	final DatePicker datePicker;

    public SimpleCalendar(Popup popup, DatePicker datePicker) {
        this.popup = popup;
        this.datePicker = datePicker;
    }

    public SimpleCalendar(Popup popup, DatePicker datePicker, double d) {
        super(d);
        this.popup = popup;
        this.datePicker = datePicker;
    }
        
        
        
      
	
	public SimpleCalendar(AnchorPane anchorPane) {
		popup = new Popup();
		popup.setAutoHide(true);
		popup.setAutoFix(true);
		popup.setHideOnEscape(true);

		datePicker = new DatePicker();
		datePicker.dateProperty().addListener(new ChangeListener<Date>() {

			@Override
			public void changed(ObservableValue<? extends Date> ov,
					Date oldDate, Date newDate) {
				if (popup.isShowing())
					popup.hide();
				
			}
		});
		popup.getContent().add(datePicker);

		final Button calenderButton = new Button();
		calenderButton.setId("CalenderButton");
                //calenderButton.setText("Calender Button");
		calenderButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ae) {
				Parent parent = SimpleCalendar.this.getParent();
				// Popup will be shown at upper left corner of calenderbutton
				Point2D point = calenderButton.localToScene(0, 0);
				final double layoutX = parent.getScene().getWindow().getX() + parent.getScene().getX() + point.getX();
				final double layoutY = parent.getScene().getWindow().getY() + parent.getScene().getY() + point.getY();
				popup.show(SimpleCalendar.this, layoutX, layoutY);

			}
		});
		
		getChildren().add(calenderButton);
	}
	
	/**
	 * @return a string bean property to bind the date information to a desired node
	 */
	public ObjectProperty<Date> dateProperty() {
		return datePicker.dateProperty();
	}
	
}