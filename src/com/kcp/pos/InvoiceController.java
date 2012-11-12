/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.UserDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Stocks;
import com.kcp.pos.service.InvoiceService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.service.StocksService;
import com.kcp.pos.utils.KCPUtils;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author pbhimanna
 */
public class InvoiceController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label TotalAmount;
    @FXML
    private Label invoiceNumber;
    @FXML
    private ComboBox itemName;
    @FXML
    private TextField itemBarcode;
    @FXML
    private TextField itemMrp;
    @FXML
    private TextField itemWeight;
    @FXML
    private TextField billingPrice;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField tax;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab invoiceDetails_tab;
    @FXML
    private Button button;
    @FXML
    private ComboBox combo;
    @FXML
    private CheckBox hasGift = new CheckBox();
    @FXML
    //private ChoiceBox weightUnit = new ChoiceBox();
    private TextField weightUnit;
    @FXML
    public TableView<InvoiceDetailsDo> dataTable;
    private final ObservableList<InvoiceDetailsDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<InvoiceDetailsDo, String> itemNameCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, String> itemBarcodeCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, Double> itemMRPCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, String> itemUomCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, Double> itemBillingPriceCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, Double> itemQuantityCol;
    @FXML
    private TableColumn<InvoiceDetailsDo, Double> itemTotalAmountCol;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemService itemService;
    private List<InvoiceDetailsDo> invoiceDetailsDoList = new ArrayList<InvoiceDetailsDo>();
    private List<Items> itemList = new ArrayList<Items>();
    private Map<String, ItemDo> itemMap = new HashMap<String, ItemDo>();
    private Invoice invoice;
    private IntegerProperty index = new SimpleIntegerProperty();

    public final double getIndex() {
        return index.get();
    }

    public final void setIndex(Integer value) {
        index.set(value);
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setItemList(List<Items> itemList) {
        this.itemList = itemList;
    }

    public List<InvoiceDetailsDo> getInvoiceDetailsDoList() {
        return invoiceDetailsDoList;
    }

    public void setInvoiceDetailsDoList(List<InvoiceDetailsDo> invoiceDetailsDoList) {
        this.invoiceDetailsDoList = invoiceDetailsDoList;
    }

    public Label getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Label invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        invoiceService = (InvoiceService) ApplicationMain.springContext.getBean("invoiceService");
        UserDao userDao = (UserDao) ApplicationMain.springContext.getBean("userDaoImpl");

        Object selectedItem = itemName.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            label.setText("Please select item");
            animateMessage();
            fillInvoiceDataTable();
            System.out.println("reenter item");
            return;
        }

        String invoiceNum = invoiceNumber.getText();

        if (invoiceNum == null || invoiceNum.equalsIgnoreCase("")) {
            invoice = new Invoice();
            invoice.setModifiedDate(new Date());


            invoice.setUsers(userDao.findById(1));
            invoiceService.invoiceSave(invoice);
            System.out.println("invoice id pk:" + invoice.getIdPk());

            invoiceNumber.setText(new Integer(invoice.getIdPk()).toString());

        }

        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        String itemQty = itemQuantity.getText();

        if (KCPUtils.isNullString(itemQty)) {
            label.setText("Please select item quantity");
            animateMessage();
            fillInvoiceDataTable();

            System.out.println("reenter item");
            return;
        }

        System.out.println("Selected item is :" + selectedItem.toString());
        Items item = itemService.getItemByName(selectedItem.toString());
        ItemDetails itemDetails = itemService.getItemDetailsByItemIdBillingType(item.getIdPk(), 1);

        String itemName = item.getItemName();


        System.out.println("invoiceIdPk:" + invoice.getIdPk());
        System.out.println("itemIdPk:" + item.getIdPk());


        InvoiceDetails invoiceDetails = invoiceService.getInvoiceDetailsByInvoiceItemId(invoice.getIdPk(), item.getIdPk());

        if (invoiceDetails != null) {

            System.out.println("invoiceDetails.getIdPk():" + invoiceDetails.getIdPk());

            invoiceDetails.setQuantity(invoiceDetails.getQuantity() + Integer.parseInt(itemQty));

            double itemTotalPrice = itemDetails.getRetailBillingPrice() * invoiceDetails.getQuantity();
            invoiceDetails.setTotal(itemTotalPrice);
            invoiceDetails.setItemDetails(itemDetails);

        } else {
            invoiceDetails = new InvoiceDetails();
            invoiceDetails.setItemDetails(itemDetails);
            invoiceDetails.setQuantity(Double.parseDouble(itemQty));
            System.out.println("itemDetails.getRetailBillingPrice() :" + itemDetails.getRetailBillingPrice());
            System.out.println("itemQty:" + itemQty);
            double itemTotalPrice = itemDetails.getRetailBillingPrice() * Integer.parseInt(itemQty);
            invoiceDetails.setTotal(itemTotalPrice);
            invoiceDetails.setInvoice(invoice);
        }


        invoiceDetails.setMargin(invoiceDetails.getItemDetails().getMargin()
                * invoiceDetails.getQuantity());

        invoiceService.invoiceDetailsSave(invoiceDetails);
        invoice.setTotalItems(invoice.getTotalItems() + 1);
        invoice.setTotalAmount(invoice.getTotalAmount() + invoiceDetails.getTotal());
        invoiceService.invoiceUpdate(invoice);
        
        StocksService stocksService;
        stocksService = (StocksService) ApplicationMain.springContext.getBean("stocksService");
        Stocks stocks=stocksService.getStocksByItemId(invoiceDetails.getItemDetails().getIdPk());
        stocks.setUnitQuantity(stocks.getUnitQuantity()-invoiceDetails.getQuantity());



        label.setText("Item Saved");
        animateMessage();
        fillInvoiceDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataTable.setEditable(true);
        itemName.setEditable(true);
        
        indexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                System.out.println("Index has changed!");
            }
        });


        dataTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                InvoiceDetailsDo item = (InvoiceDetailsDo) newValue;
                setIndex(dataTableData.indexOf(newValue));
                tabPane.getSelectionModel().select(invoiceDetails_tab);

                System.out.println("OK");
            }
        });


        Callback<TableColumn<InvoiceDetailsDo, Double>, TableCell<InvoiceDetailsDo, Double>> cellFactory =
                new Callback<TableColumn<InvoiceDetailsDo, Double>, TableCell<InvoiceDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        itemQuantityCol.setCellFactory(cellFactory);
        itemQuantityCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InvoiceDetailsDo, Double> t) {
                        InvoiceDetailsDo data = (InvoiceDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        data.setQuantity(t.getNewValue());
                        InvoiceDetails det = invoiceService.getInvoiceDetailsById(data.getInvoiceDetailsId());
                        det.setQuantity(data.getQuantity());
                        det.setTotal(det.getQuantity() * det.getItemDetails().getRetailBillingPrice());
                        invoiceService.invoiceDetailsSave(det);
                        fillInvoiceDataTable();
                    }
                });
        itemName.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        List<ItemDo> itemList = itemService.getAllItemsDo();

        for (ItemDo item : itemList) {
            itemMap.put(item.getItemName(), item);
            itemName.getItems().add(item.getItemName());
        }
        System.out.println("item list size:" + itemList.size());

        itemName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                System.out.println("new item:" + newItem+":");


                /*itemName.getItems().removeAll(itemName.getItems());
                Iterator k = itemMap.keySet().iterator();


                if (newItem==null || newItem.equals("")) {
                    System.out.println("itemName is null");
                    
                    while (k.hasNext()) {
                        String key = (String) k.next();

                        itemName.getItems().add(key);
                    }
                } else {
                    System.out.println("selected value:" + itemName.getSelectionModel().getSelectedItem());
                    while (k.hasNext()) {
                        String key = (String) k.next();
                        System.out.println("key:" + key);
                        int i = 0;
                        Boolean flag = false;
                        while (i < newItem.length() && newItem.toUpperCase().charAt(i) == key.toUpperCase().charAt(i)) {
                            i++;
                        }

                        if (i == newItem.length()) {
                            System.out.println("i:" + i);
                            System.out.println("length:" + newItem.length());
                            itemName.getItems().add(key);
                            System.out.println("added item:" + key);
                        }

                    }
                }
                
                itemName.setValue(newItem);
                */
                if(newItem !=null && itemMap.get(newItem.toString())!=null)
                {
                ItemDo item = itemMap.get(newItem.toString());

                ItemDetailsDo itemDetailsDo = itemService.getItemDetailsDoByItemId(item.getIdPk());

                itemBarcode.setText(item.getBarcode());
                System.out.println("MRP:" + item.getMrp());
                itemMrp.setText(new Double(item.getMrp()).toString());
                itemWeight.setText(new Double(item.getWeight()).toString());
                weightUnit.setText(item.getUom());
                //weightUnit.setSelectionModel(item.getWeightUnit());
                billingPrice.setText(new Double(itemDetailsDo.getBillingPrice()).toString());
                System.out.println("tax:" + itemDetailsDo.getTax());
                System.out.println("tax string:" + new Double(itemDetailsDo.getTax()).toString());
                tax.setText(new Double(itemDetailsDo.getTax()).toString());
                itemQuantity.requestFocus();
        
                
            }
            }
        });

        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, String>("barcode"));

        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, String>("itemName"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, Double>("mrp"));

        itemUomCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, String>("uom"));
        itemBillingPriceCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, Double>("billingPrice"));

        itemQuantityCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, Double>("quantity"));

        itemTotalAmountCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, Double>("total"));


        fillInvoiceDataTable();
    }

    private void clearForm() {

        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();
        itemQuantity.clear();
        billingPrice.clear();
        tax.clear();



    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillInvoiceDataTable() {
        System.out.println("fillInvoiceDataTable() start");
        invoiceNumber.getText();

        if (invoiceNumber.getText() == null || invoiceNumber.getText().equalsIgnoreCase("")) {
            invoiceDetailsDoList = new ArrayList<InvoiceDetailsDo>();
        } else {
            invoiceDetailsDoList = invoiceService.getInvoiceDetailsDoById(Integer.parseInt(invoiceNumber.getText()));
        }


        System.out.println("invoice number:" + invoiceNumber.getText());
        System.out.println("invoice items list:" + invoiceDetailsDoList.size());
        for (InvoiceDetailsDo data : invoiceDetailsDoList) {

            System.out.println("billing price:" + data.getBillingPrice());
        }
        getInvoiceTotalAmount(invoiceDetailsDoList);
        dataTableData.setAll(invoiceDetailsDoList);
    }

    public void getInvoiceTotalAmount(List<InvoiceDetailsDo> invoiceDetailsList) {
        Double amount = 0.0;

        for (InvoiceDetailsDo data : invoiceDetailsList) {
            amount = amount + data.getTotal();
        }

        if (amount != null) {
            TotalAmount.setText(amount.toString());
        }


    }

    public void saveAllInvoiceItems() {
        /*  InvoiceDetails invoiceDetails = new InvoiceDetails();

         invoiceService.invoiceDetailsSave(getInvoiceDetailsList());

         String invoiceNum = invoiceNumber.getText();

         if (invoiceNum == null) {
            
         invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();
         }

         invoiceDao.getInvoiceById();
         invoiceDetails.setInvoiceIdFk(Integer.parseInt(invoiceNum));


         ItemDao itemDao = new ItemDaoImpl();
         // itemDao.getIdByName(selectedItem.toString());

         String itemQty = itemQuantity.getText();
         invoiceDetails.setInvoiceItemQuantity(Integer.parseInt(itemQty));
         Object selectedItem = itemName.getSelectionModel().getSelectedItem();
         Item item = itemDao.getItemByName(selectedItem.toString());

         double itemTotalPrice = item.getBillingPrice() * Integer.parseInt(itemQty);

         invoiceDetails.setInvoiceItemTotalPrice(itemTotalPrice);

         InvoiceDao invoiceDao = new InvoiceDaoImpl();
         //invoiceDao.addInvoiceItem(invoiceDetails);

         //invoiceDao.saveInvoice(invoiceDetails);



         label.setText("Item Saved");
         animateMessage();
         fillInvoiceDataTable();
         clearForm();
         System.out.println("saved");*/
    }
    
    @FXML
    public void gotoAdd(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            button.requestFocus();
        }
    }

   
        /*combo.getItems().removeAll(combo.getItems());


        System.out.println("key typed:" + e.getSource());
        System.out.println("e.toString():" + e.toString());
        System.out.println("combo.getSelectionModel().getSelectedItem():" + combo.getSelectionModel().getSelectedItem());
        String value = null;


        Iterator k = itemMap.keySet().iterator();
        System.out.println("selected value:" + combo.getSelectionModel().getSelectedItem());
        while (k.hasNext()) {
            String key = (String) k.next();
            System.out.println("key:" + key);
            int i = 0;
            Boolean flag = false;
            while (i < value.length() && value.charAt(i) == key.charAt(i)) {
                i++;
            }

            if (i == value.length()) {
                System.out.println("i:" + i);
                System.out.println("length:" + value.length());
                combo.getItems().add(key);
                System.out.println("added item:" + key);
            }

        }
    }*/

    
    private ApplicationMain application;

    public void setApp(ApplicationMain aThis) {
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
    public void openPurchase(ActionEvent e) {
        application.gotoPurchase();
    }

    @FXML
    public void openPurchaseDetails(ActionEvent e) {
        application.gotoPurchaseDetails();
    }

    
    @FXML
    public void openStocks(ActionEvent e) {
        application.gotoStocks();
    }
    
    @FXML
    public void openMain(ActionEvent e) {
        application.gotoMain();
    }
}
class EditingCell extends TableCell<InvoiceDetailsDo, Double> {

    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }

        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.selectAll();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.requestFocus();
            }
        });
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);

        System.out.println("UpdateItem:" + item);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createTextField() {
        System.out.println("createTextField() start");
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {

                    commitEdit(Double.parseDouble(textField.getText()));
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
    
    
}