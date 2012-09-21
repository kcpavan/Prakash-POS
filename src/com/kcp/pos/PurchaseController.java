/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;


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
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.CheckBox;
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
    private TableColumn<Items, String> itemNameCol;
    @FXML
    private TableColumn<Items, String> itemBarcodeCol;
    @FXML
    private TableColumn<Items, Double> itemMRPCol;
    @FXML
    private TableColumn<Items, Double> caseQuantityCol;
    @FXML
    private TableColumn<Items, Double> unitsQuantityCol;
    @FXML
    private TableColumn<Items, Double> freeUnitsCol;
    @FXML
    private TableColumn<Items, Double> basicRateCol;
    @FXML
    private TableColumn<Items, Double> grossAmountCol;
    @FXML
    private TableColumn<Items, Double> schemeCol;
    @FXML
    private TableColumn<Items, Double> cDCol;
    @FXML
    private TableColumn<Items, Double> taxPercentageCol;
    @FXML
    private TableColumn<Items, Double> taxCol;
    @FXML
    private TableColumn<Items, Double> netAmountCol;
    private List<PurchaseDetailsDo> purchaseDetails = new ArrayList<PurchaseDetailsDo>();
    private List<ItemDo> itemList = new ArrayList<ItemDo>();
    private Map<String, Items> itemMap = new HashMap<String, Items>();
    ItemService itemService = new ItemService();

    public List<ItemDo> getItemList() {
        List<ItemDo> list = itemService.getAllItems();
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
        System.out.println("UI purchase Id:"+purchaseId);
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
       /* List<Items> itemList = itemDao.getAllItems();

        for (Items item : itemList) {
            itemMap.put(item.getName(), item);
            itemName.getItems().add(item.getItemName());
            System.out.println("name:"+item.getItemName());
        }


        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                Items item = itemMap.get(newItem);
                



            }
        });


        
        dataTable.setItems(dataTableData);
   
    
  
  
 
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Items, String>("itemName"));
         itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<Items, String>("itemBarcode"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemMrp"));
        caseQuantityCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemCaseQuantity"));
        unitsQuantityCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemUnitsQuantity"));
        freeUnitsCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemFreeUnits"));
        basicRateCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemBasicPrice"));
        grossAmountCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("itemGrossAmount"));
        schemeCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("purchaseScheme"));
        cDCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("purchaseCd"));
        taxPercentageCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("purchaseTaxPercentage"));
        taxCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("purchaseTax"));
        netAmountCol.setCellValueFactory(
                new PropertyValueFactory<Items, Double>("purchaseNetAmount"));
       
*/

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
        /*ItemDao itemDao = new ItemDaoImpl();
        purchaseNumber.getText();
        if(!KCPUtils.isNullString(purchaseNumber.getText()))
                {
                    
               
        List<PurchaseDetailsDo> purchaseDetailsList = purchaseService.getPurchaseItems(purchaseNumber.getText());
        
        
        dataTableData.setAll(purchaseDetailsList);
                }
*/    }

    
   
}
