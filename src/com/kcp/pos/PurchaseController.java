/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.DistributorDao;
import com.kcp.pos.dao.UserDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.PurchaseDetailsDo;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Purchase;
import com.kcp.pos.modal.PurchaseDetails;
import com.kcp.pos.modal.Stocks;
import com.kcp.pos.service.InvoiceService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.service.PurchaseService;
import com.kcp.pos.service.StocksService;
import com.kcp.pos.utils.KCPUtils;
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

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
    private TextField unitsPerCase;
    @FXML
    private Button button;
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
    private TableColumn<PurchaseDetailsDo, Double> unitsPerCaseCol;
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
    
     @FXML
     private GridPane datePane;
     @FXML
     private Button dateSubmit;
     
     private DatePicker birthdayDatePicker;
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
    @Autowired
    private PurchaseService purchaseService;
    Purchase purchase = null;

    @FXML
    private void handleBarcodeAction(ActionEvent event) {
        System.out.println("handleBarcodeAction() start");
        mrp.requestFocus();
        /*Platform.runLater(new Runnable() {
         @Override
         public void run() {
         mrp.requestFocus();
         }
         });*/
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        purchaseService = (PurchaseService) ApplicationMain.springContext.getBean("purchaseService");
        PurchaseDetails purchaseDetails = new PurchaseDetails();
        Object selectedItem = itemName.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();

            System.out.println("reEnter item");
            return;
        }
        UserDao userDao = (UserDao) ApplicationMain.springContext.getBean("userDaoImpl");
        String purchaseId = purchaseNumber.getText();
        System.out.println("UI purchase Id:" + purchaseId);
        DistributorDao distributorDao = (DistributorDao) ApplicationMain.springContext.getBean("distributorDaoImpl");

        if (purchaseId == null || KCPUtils.isNullString(purchaseId) || Integer.parseInt(purchaseId) == 0) {
            purchase = new Purchase();
            purchase.setModifiedDate(new Date());
            purchase.setUsers(userDao.findById(1));
            purchase.setDistributor(distributorDao.findById(1));
            purchase.setNetAmount(0.0);
            purchase.setCd(0.0);
            purchase.setCdAmount(0.0);
            purchase.setTotalAmount(0.0);

            purchaseService.purchaseSave(purchase);
            purchaseNumber.setText(new Integer(purchase.getIdPk()).toString());
            System.out.println("new purchase number:" + purchaseId);
        }

        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        Items item = itemService.getItemByName(selectedItem.toString());
        ItemDetails itemDetails = itemService.getItemDetailsByItemIdBillingType(item.getIdPk(), 1);

        purchaseDetails.setPurchase(purchase);
        purchaseDetails.setItemDetails(itemDetails);
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

        purchaseDetails.setUnitsQuantity(Double.parseDouble(unitsQuantity.getText()));
        purchaseDetails.setCaseQuantity(Integer.parseInt(caseQuantity.getText()));
        purchaseDetails.setFreeUnits(Integer.parseInt(freeUnits.getText()));
        purchaseDetails.setBasicRate(Double.parseDouble(basicRate.getText()));
        purchaseDetails.setGrossAmount(Double.parseDouble(grossAmount.getText()));
        purchaseDetails.setScheme(Integer.parseInt(scheme.getText()));
        purchaseDetails.setCd(Double.parseDouble(CD.getText()));
        purchaseDetails.setTaxPercentage(Double.parseDouble(taxPercentage.getText()));
        purchaseDetails.setTax(Double.parseDouble(taxAmount.getText()));
        purchaseDetails.setNetAmount(Double.parseDouble(netAmount.getText()));
        purchaseDetails.setUnitsPerCase(Integer.parseInt(unitsPerCase.getText()));
        System.out.println("itemQuantity:" + itemQty);

        purchaseService.purchaseDetailsSave(purchaseDetails);

        purchase.setNetAmount(purchase.getNetAmount() + purchaseDetails.getNetAmount());
        purchase.setCd(0.0);
        purchase.setCdAmount(0.0);
        purchase.setTotalAmount(purchase.getNetAmount() - purchase.getCdAmount());
        purchaseService.purchaseUpdate(purchase);

        StocksService stocksService;
        stocksService = (StocksService) ApplicationMain.springContext.getBean("stocksService");
        Stocks stocks = stocksService.getStocksByItemId(purchaseDetails.getItemDetails().getIdPk());
        if (stocks == null) {

            stocks = new Stocks();
            stocks.setItems(itemDetails.getItem());
            stocks.setItemDetails(itemDetails);
        }
        stocks.setItemDetails(purchaseDetails.getItemDetails());
        userDao = (UserDao) ApplicationMain.springContext.getBean("userDaoImpl");
        stocks.setUsers(userDao.findById(1));
        stocks.setModifiedDate(new Date());
        
        stocks.setUnitsPerCase(purchaseDetails.getUnitsPerCase());
        if (stocks.getUnitQuantity() == null) {
            stocks.setUnitQuantity(purchaseDetails.getUnitsQuantity());
        } else {
            stocks.setUnitQuantity(stocks.getUnitQuantity() + purchaseDetails.getUnitsQuantity());
        }
        if (stocks.getCaseQuantity() == null) {
            stocks.setCaseQuantity(purchaseDetails.getCaseQuantity());
        } else {
            stocks.setCaseQuantity(stocks.getCaseQuantity() + purchaseDetails.getCaseQuantity());
        }


        stocksService.saveStocks(stocks);

        Double gAmount = null;
        Integer totalQuantity = null;
        if (purchaseDetails.getCaseQuantity() != null) {
        }

        /*totalQuantity=purchaseDetails.getUnitsQuantity()+(purchaseDetails.getCaseQuantity()==null)?0:
         (purchaseDetails.getCaseQuantity()*purchaseDetails.getBasicRate());*/

        if (grossAmount.getText() != null) {
            gAmount = purchaseDetails.getBasicRate() * purchaseDetails.getUnitsQuantity();
        }

        Double tax = null;
        if (purchaseDetails.getGrossAmount() != null && purchaseDetails.getTaxPercentage() != null) {
            tax = (purchaseDetails.getGrossAmount() - purchaseDetails.getCd()) * purchaseDetails.getTaxPercentage();
        }
        label.setText("Items Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }

    @FXML
    private void handleDateSubmitAction(ActionEvent event) {
        System.out.println("selected date is:"+
    birthdayDatePicker.getSelectedDate());
        
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

        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                ItemDo item = itemMap.get(newItem);
                ItemDetails details=itemService.getItemDetailsByItemId(item.getIdPk());
                
                barcode.setText(item.getBarcode());
                mrp.setText(Double.toString(details.getMrp()));

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
        unitsPerCaseCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDetailsDo, Double>("unitsPerCase"));

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

        birthdayDatePicker = new DatePicker(Locale.ENGLISH);
  birthdayDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
  birthdayDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
  birthdayDatePicker.getCalendarView().setShowWeeks(false);
  birthdayDatePicker.getStylesheets().add("ch/makery/address/view/DatePicker.css");

  // Add DatePicker_backup to grid
//  datePane.add(birthdayDatePicker, 1, 5);

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

        List<PurchaseDetailsDo> purchaseDetailsList = null;
        purchaseNumber.getText();
        if (!KCPUtils.isNullString(purchaseNumber.getText())) {


            purchaseDetailsList = purchaseService.getPurchaseDetails(Integer.parseInt(purchaseNumber.getText()));


            dataTableData.setAll(purchaseDetailsList);
        } else {
            purchaseDetailsList = new ArrayList<PurchaseDetailsDo>();
        }


        dataTableData.setAll(purchaseDetailsList);
    }
    private ApplicationMain application;

    void setApp(ApplicationMain aThis) {
        System.out.println("aThis" + aThis);
        this.application = aThis;
    }

    @FXML
    public void OpenInvoice(ActionEvent e) {
        application.gotoInvoice();
    }

    @FXML
    public void openInvoiceDetails(ActionEvent e) {
        application.gotoInvoiceDetails();
    }
      
    @FXML
    public void openInvoiceDetailsMisc(ActionEvent e) {
        application.gotoInvoiceDetailsMisc();
    }
    
    @FXML
    public void openPurchase(ActionEvent e) {
        application.gotoPurchase();
    }

    @FXML
    public void openMain(ActionEvent e) {
        application.gotoMain();
    }

    @FXML
    public void gotoBarcode(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            barcode.requestFocus();
        }
    }

    @FXML
    public void gotoMrp(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            mrp.requestFocus();
        }
    }

    @FXML
    public void gotoUnits(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            unitsQuantity.requestFocus();
        }
    }

    @FXML
    public void gotoCase(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            caseQuantity.requestFocus();
        }
    }

    @FXML
    public void gotoFreeUnits(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            freeUnits.requestFocus();
        }
    }

    @FXML
    public void gotoBasicRate(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            basicRate.requestFocus();
        }
    }

    @FXML
    public void gotoGrossAmount(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            grossAmount.requestFocus();
        }
    }

    @FXML
    public void gotoScheme(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            scheme.requestFocus();
        }
    }

    @FXML
    public void gotoCd(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            CD.requestFocus();
        }
    }

    @FXML
    public void gotoTaxPercentage(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            taxPercentage.requestFocus();
        }
    }

    @FXML
    public void gotoTaxAmount(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            taxAmount.requestFocus();
        }
    }

    @FXML
    public void gotoUnitsPerCase(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            unitsPerCase.requestFocus();
        }
    }

    @FXML
    public void gotoNetAmount(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            netAmount.requestFocus();
        }
    }

    @FXML
    public void gotoSave(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            button.requestFocus();
        }
    }

    @FXML
    public void openPurchaseDetails(ActionEvent e) {
        application.gotoPurchaseDetails();
    }

    @FXML
    public void openStocks(ActionEvent e) {
        application.gotoStocks();
    }
}
