/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.Items;
import com.kcp.pos.service.ItemService;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pavankumar
 */
@Component 
public class MainController  implements Initializable {
    
    @FXML private Label label;
    
    @FXML private TextField itemName;
     
    @FXML private TextField itemBarcode;
    
    @FXML private TextField itemMrp;
    
    @FXML private TextField itemWeight;
    
    @FXML private TextField actualPrice;
     
    @FXML private TextField sellingPrice;
    
    @FXML private CheckBox hasGift = new CheckBox();
    
    @FXML private ChoiceBox weightUnit = new ChoiceBox();
    
    @FXML public TableView<ItemDo> dataTable;
  
    private final ObservableList<ItemDo> dataTableData = FXCollections.observableArrayList();
    
    @FXML private TableColumn<ItemDo, String> itemNameCol;
    @FXML private TableColumn<ItemDo, String> itemBarcodeCol;
    @FXML private TableColumn<ItemDo, Double> itemMRP;
    @FXML private TableColumn<ItemDo, Double> itemWeightCol;
    @FXML private TableColumn<ItemDo, String> itemWeightUnitCol;
    @FXML private TableColumn<ItemDo, Double> itemActualPriceCol;
    @FXML private TableColumn<ItemDo, Double> itemSellingPriceCol;
    @FXML private TableColumn<ItemDo, Double> itemHasGiftCol;
    @FXML private TableColumn<ItemDo, Double> itemTotalAmountsCol;
    
   // @Autowired
    private ItemService itemService;

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    private ApplicationMain application;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Items item = new Items();
        item.setItemName(itemName.getText());
        item.setBarcode(itemBarcode.getText());
        item.setMrp(Double.valueOf(itemMrp.getText()));
        item.setWeight(Double.valueOf(itemWeight.getText()));
        item.setActualPrice(Double.valueOf(actualPrice.getText()));
        System.out.println("hasGift.getText()"+hasGift);
        item.setWeightUnit((String)weightUnit.getSelectionModel().getSelectedItem());
        item.setModifiedDate(new Date());
        itemService = (ItemService) application.springContext.getBean("itemService");
        item.setUsers(application.getLoggedUser());
        itemService.itemSave(item);
        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         weightUnit.getItems().addAll("choose", "mg", "cg","dg","g","kg");
         dataTable.setItems(dataTableData);
         itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("itemBarcode"));
         itemNameCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("itemName"));
          itemMRP.setCellValueFactory(
                new PropertyValueFactory<ItemDo, Double>("itemMrp"));
          itemWeightCol.setCellValueFactory
                  (new PropertyValueFactory<ItemDo, Double>("itemWeight"));
          itemWeightUnitCol.setCellValueFactory
                  (new PropertyValueFactory<ItemDo, String>("weightUnit"));
          itemActualPriceCol.setCellValueFactory
                  (new PropertyValueFactory<ItemDo, Double>("actualPrice"));
          itemSellingPriceCol.setCellValueFactory
                  (new PropertyValueFactory<ItemDo, Double>("sellingPrice"));
          itemHasGiftCol.setCellValueFactory
                  (new PropertyValueFactory<ItemDo, Double>("hasGift"));
         
          
         fillDataTable();
    }   
    
    private void clearForm(){
         itemName.clear();
        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();
        actualPrice.clear();
        sellingPrice.clear();
    
    }
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void fillDataTable(){
        itemService=(ItemService) application.springContext.getBean("itemService");
        List<ItemDo> items=itemService.getAllItems();
       dataTableData.setAll(items);
    }

    void setApp(ApplicationMain aThis) {
         System.out.println("aThis"+aThis);
        this.application = aThis;
    }
    
    // @FXML
    private void handleLogoutAction(ActionEvent event) {
         application.userLogout();
     }
   
}
