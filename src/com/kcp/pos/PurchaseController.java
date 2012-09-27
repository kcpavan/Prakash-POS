/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.PurchaseDetailsDo;
import com.kcp.pos.modal.Items;
import com.kcp.pos.service.InvoiceService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.service.PurchaseService;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 *
 * @author Prakash
 */
public class PurchaseController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label TotalAmount;
    @FXML
    private Label purchaseNumber;
    @FXML
    private ChoiceBox itemName;
    @FXML
    private TextField barcode;
    @FXML
    private TextField mrp;
    @FXML
    private TextField basicRate;
    @FXML
    private TextField grossAmount;
    @FXML
    private TextField scheme;
    @FXML
    private TextField caseQuantity;
    @FXML
    private TextField unitsQuantity;
    @FXML
    private TextField freeUnits;
    @FXML
    private TextField CD;
    @FXML
    private TextField taxPercentage;
    @FXML
    private TextField taxAmount;
    @FXML
    private TextField netAmount;
    @FXML
    public TableView<PurchaseDetailsDo> dataTable;
    private final ObservableList<PurchaseDetailsDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<PurchaseDetailsDo, String> itemNameCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, String> itemBarcodeCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> itemMRPCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> caseQuantityCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> unitsQuantityCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> freeUnitsCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> basicRateCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> grossAmountCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> schemeCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> cDCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> taxPercentageCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> taxCol;
    @FXML
    private TableColumn<PurchaseDetailsDo, Double> netAmountCol;
    private List<PurchaseDetailsDo> purchaseDetails = new ArrayList<PurchaseDetailsDo>();
    private List<ItemDo> itemList = new ArrayList<ItemDo>();
    private Map<String, ItemDo> itemMap = new HashMap<String, ItemDo>();
    ItemService itemService = new ItemService();

    public List<ItemDo> getItemList() {
        List<ItemDo> list = itemService.getAllItemsDo();
        setItemList(list);
        return list;
    }

    public void setItemList(List<ItemDo> itemList) {
        this.itemList = itemList;
    }
    InvoiceService invoiceService = new InvoiceService();

    public List<PurchaseDetailsDo> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetailsDo> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
    PurchaseService purchaseService = new PurchaseService();

    @FXML
    private void handleButtonAction(ActionEvent event) {

        PurchaseDetailsDo purchaseDetails = new PurchaseDetailsDo();


        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();

            System.out.println("reEnter item");
            return;
        }

        String purchaseId = purchaseNumber.getText();
        System.out.println("UI purchase Id:" + purchaseId);
        /*
         if (purchaseId == null || KCPUtils.isNullString(purchaseId)|| Integer.parseInt(purchaseId)==0)
         {
         purchaseId = new Integer(purchaseService.getPurchaseId()).toString();
         purchaseNumber.setText(purchaseId);
         System.out.println("new purchase number:" + purchaseId);
         }

         Items item=itemDao.getItemByName(selectedItem.toString());
        
        
         purchaseDetails.setPurchaseId(Integer.parseInt(purchaseId));
         purchaseDetails.setItemId(item.getItemId());
         purchaseDetails.setMrp(Double.parseDouble(mrp.getText()));
         purchaseDetails.setCaseQuantity(Integer.parseInt(caseQuantity.getText()));
        
         String itemQty = unitsQuantity.getText();
         if (KCPUtils.isNullString(itemQty)) {
         label.setText("Please select item quantity");
         animateMessage();
         fillDataTable();

         System.out.println("reenter item");
         return;
         }
         purchaseDetails.setUnitsQuantity(Integer.parseInt(unitsQuantity.getText()));
        
        
         
         purchaseDetails.setFreeUnits(Integer.parseInt(freeUnits.getText()));

         purchaseDetails.setBasicRate(Double.parseDouble(basicRate.getText()));
         purchaseDetails.setGrossAmount(Double.parseDouble(grossAmount.getText()));
         purchaseDetails.setScheme(Integer.parseInt(scheme.getText()));
         purchaseDetails.setCd(Double.parseDouble(CD.getText()));

         purchaseDetails.setTaxPercentage(Double.parseDouble(taxPercentage.getText()));
         purchaseDetails.setTax(Double.parseDouble(taxAmount.getText()));
         purchaseDetails.setNetAmount(Double.parseDouble(netAmount.getText()));


      


       


       
         System.out.println("itemQuantity:" + itemQty);

      
         purchaseService.addPurcaseItem(purchaseDetails);
        
      
         */


        label.setText("Items Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize() start");
        itemName.getItems().removeAll("Items 1", "Items 2", "Items 3", " ");

        itemName.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        List<ItemDo> itemList = itemService.getAllItemsDo();

        for (ItemDo item : itemList) {
            itemMap.put(item.getItemName(), item);
            itemName.getItems().add(item.getItemName());
        }
        /* List<Items> itemList = itemDao.getAllItemsDo();

         for (Items item : itemList) {
         itemMap.put(item.getName(), item);
         itemName.getItems().add(item.getItemName());
         System.out.println("name:"+item.getItemName());
         }*/

        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                ItemDo item = itemMap.get(newItem);

                barcode.setText(item.getBarcode());
                mrp.setText(new Double(item.getMrp()).toString());

            }
        });



        dataTable.setItems(dataTableData);


        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, String>("itemName"));

        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, String>("barcode"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("mrp"));
        caseQuantityCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("caseQuantity"));
        unitsQuantityCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("unitsQuantity"));
        freeUnitsCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("freeUnits"));
        basicRateCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("basicRate"));
        grossAmountCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("grossAmount"));
        schemeCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("scheme"));
        cDCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("cd"));
        taxPercentageCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("taxPercentage"));
        taxCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("tax"));
        netAmountCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("netAmount"));



        fillDataTable();
    }

    private void clearForm() {

        barcode.clear();
        mrp.clear();
        basicRate.clear();
        grossAmount.clear();
        scheme.clear();
        caseQuantity.clear();
        unitsQuantity.clear();
        freeUnits.clear();
        CD.clear();
        taxPercentage.clear();
        taxAmount.clear();
        netAmount.clear();


    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {

        List<PurchaseDetailsDo> purchaseDetailsList=null;
        purchaseNumber.getText();
        if (!KCPUtils.isNullString(purchaseNumber.getText())) 
        {


            purchaseDetailsList = purchaseService.getPurchaseDetails(Integer.parseInt(purchaseNumber.getText()));


            dataTableData.setAll(purchaseDetailsList);
        }
        else
        {
            purchaseDetailsList=new ArrayList<PurchaseDetailsDo>();
        }



        

       

      
        dataTableData.setAll(purchaseDetailsList);
    }
}
