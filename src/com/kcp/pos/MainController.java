/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.BillingTypeDao;
import com.kcp.pos.dao.ItemCategoryDao;
import com.kcp.pos.dao.UserDao;
import com.kcp.pos.dao.commonDao;
import com.kcp.pos.dao.commonDaoImpl;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.ItemCategoryDo;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.UOMDo;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.service.ItemCategoryService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.service.UOMService;
import com.kcp.pos.utils.KCPUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pavankumar
 */
@Component
public class MainController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label outputLabel;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemBarcode;
    @FXML
    private TextField itemMrp;
    @FXML
    private TextField itemWeight;
    @FXML
    private TextField actualPrice;
    /*@FXML
     private TextField sellingPrice;*/
    @FXML
    private CheckBox hasGift = new CheckBox();
    @FXML
    private ChoiceBox uom = new ChoiceBox();
    @FXML
    private ChoiceBox category = new ChoiceBox();
    @FXML
    private TextField retailPrice;
    @FXML
    private TextField wholesalePrice;
    @FXML
    private TextField tax;
    @FXML
    private TextField searchCriteria;
    @FXML
    private TextField add;
    @FXML
    public TableView<ItemDetailsDo> dataTable;
    //private final ObservableList<ItemDo> dataTableData = FXCollections.observableArrayList();
    private final ObservableList<ItemDetailsDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ItemDo, String> itemNameCol;
    @FXML
    private TableColumn<ItemDo, String> itemBarcodeCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemMRP;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemWeightCol;
    @FXML
    private TableColumn<ItemDo, String> itemWeightUnitCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemActualPriceCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemRetailPriceCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemWholesalePriceCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> taxCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemHasGiftCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab itemDetails_tab;
    List<ItemDetailsDo> itemDetailsList = new ArrayList<ItemDetailsDo>();
    @Autowired
    private ItemService itemService;

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
    @Autowired
    private ItemCategoryService itemCategoryService;

    public ItemCategoryService getItemCategoryService() {
        return itemCategoryService;
    }

    public void setItemCategoryService(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }
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

    public Boolean validate() {
        if (KCPUtils.isNullString(itemName.getText())) {
            label.setText("Please enter item name");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
        
        if (KCPUtils.isNullString(itemBarcode.getText())) {
            label.setText("Please enter itemBarcode ");
            animateMessage();
            fillDataTable();
            System.out.println("reEnter item");
            return true;
        }
        
        String selectedItem = (String) category.getSelectionModel().getSelectedItem();

        if (KCPUtils.isNullString(selectedItem)) {
            label.setText("Please select category");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
        
        if (KCPUtils.isNullString(itemMrp.getText())) {
            label.setText("Please enter MRP");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
        
        Double rPrice=null,wPrice=null,aPrice=null;
        
        if (KCPUtils.isNullString(actualPrice.getText())) {
            label.setText("Please enter actualPrice");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
        else
        {
            aPrice=Double.parseDouble(actualPrice.getText());
        }
        
        if (KCPUtils.isNullString(retailPrice.getText())) {
            label.setText("Please enter retailPrice");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
        else
        {
            rPrice=Double.parseDouble(retailPrice.getText());
        }
        
         if (KCPUtils.isNullString(wholesalePrice.getText())) {
            label.setText("Please enter retailPrice");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        }
         else
         {
             wPrice=Double.parseDouble(wholesalePrice.getText());
         }

         if(rPrice<aPrice)
         {
             label.setText("Retail price cannot be less than actual price"); 
             return true;
         }
         
         if(wPrice<aPrice)
         {
            label.setText("Wholesale price cannot be less than actual price"); 
            return true;
         }
        
         return false;

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(!validate())
        {
            System.out.println("Validated!!");  
        
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");

        for (Items item : itemService.getAllItems()) {
            if (item.getItemName().equalsIgnoreCase(itemName.getText())) {
                label.setText("Item already exists");
                animateMessage();
                fillDataTable();
                System.out.println("reenter item");
                return;
            }

        }

        Items item = new Items();
        item.setItemName(itemName.getText());
        item.setBarcode(itemBarcode.getText());

        ItemDetails itemDetails = new ItemDetails();

        UOMService uOMService = (UOMService) ApplicationMain.springContext.getBean("UOMService");

        itemDetails.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
        itemDetails.setMrp(Double.valueOf(itemMrp.getText()));
        itemDetails.setWeight(Double.valueOf(itemWeight.getText()));
        itemDetails.setActualPrice(Double.valueOf(actualPrice.getText()));
        itemDetails.setHasfree(Boolean.valueOf(hasGift.getText()));
        
        commonDao comDao = new commonDaoImpl();
        /*List list = comDao.getLookUpValues(
         ItemCategory.class, "typeDesc");
         if (!KCPUtils.isNullList(list)) {
         for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         ItemCategory obj = (ItemCategory) iterator.next();
         }*/
        String selectedItem = (String) category.getSelectionModel().getSelectedItem();

        if (KCPUtils.isNullString(selectedItem)) {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return;
        }
        itemCategoryService = (ItemCategoryService) ApplicationMain.springContext.getBean("itemCategoryService");
        itemCategoryService.getItemCategoryByName((String) selectedItem);





        item.setModifiedDate(new Date());
        // Object selectedItem = category.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem:" + selectedItem);
        ItemCategoryDao itemCategoryDao = (ItemCategoryDao) ApplicationMain.springContext.getBean("itemCategoryDaoImpl");
        item.setItemCategory(itemCategoryDao.findByName(selectedItem.toString()));


        UserDao userDao = (UserDao) ApplicationMain.springContext.getBean("userDaoImpl");
        item.setUsers(userDao.findById(1));

        BillingTypeDao billingTypeDao = (BillingTypeDao) ApplicationMain.springContext.getBean("billingTypeDaoImpl");

        itemDetails.setBillingType(billingTypeDao.findByName("retail"));


        System.out.println("UOM:" + uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()).getUomDesc());
        //itemDetails.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
        itemDetails.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
        for (UOMDo uOMDo : uOMService.getAllUOM()) {
            System.out.println("size:" + uOMService.getAllUOM().size());
            uom.getItems().add(uOMDo.getUomDesc());
        }

        itemDetails.setRetailBillingPrice(Double.valueOf(retailPrice.getText()));
        itemDetails.setWholesaleBillingPrice(Double.valueOf(wholesalePrice.getText()));
        itemDetails.setMargin(itemDetails.getWholesaleBillingPrice()-itemDetails.getActualPrice());
        itemDetails.setModifiedDate(new Date());
        itemDetails.setUsers(userDao.findById(1));
        itemDetails.setEnabled(Boolean.TRUE);
        itemService.itemSave(item);

        itemDetails.setItem(item);


        itemService.itemDetailsSave(itemDetails);
        label.setText("Item Saved");
        animateMessage();
        itemDetailsList = new ArrayList<ItemDetailsDo>();

        for (Items data : itemService.getAllItems()) {

            // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(item.getIdPk())) {
            System.out.println("itemId:" + data.getIdPk());
            itemDetailsList.add(itemService.getItemDetailsDoByItemId(data.getIdPk()));
        }

        fillDataTable();
        clearForm();
        System.out.println("saved");
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dataTable.setEditable(true);
        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryActual =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        itemActualPriceCol.setCellFactory(cellFactoryActual);
        itemActualPriceCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        System.out.println("edit actual price");
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());

                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
                        data.setActualPrice(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());


                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setActualPrice(data.getActualPrice());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });

        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryRetail =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        itemRetailPriceCol.setCellFactory(cellFactoryRetail);
        itemRetailPriceCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());

                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
                        data.setRetailPrice(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());


                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setRetailBillingPrice(data.getRetailPrice());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });


        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryMrp =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        itemMRP.setCellFactory(cellFactoryMrp);
        itemMRP.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        System.out.println("edit mrp");
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());

                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());

                        data.setMrp(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());


                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setMrp(data.getMrp());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });


        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryWholeSale =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        itemWholesalePriceCol.setCellFactory(cellFactoryWholeSale);
        itemWholesalePriceCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        System.out.println("edit wholesale");
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
                        data.setWholesalePrice(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());


                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setWholesaleBillingPrice(data.getWholesalePrice());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });



        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryWeight =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        itemWeightCol.setCellFactory(cellFactoryWeight);
        itemWeightCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        System.out.println("edit wholesale");
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
                        data.setWeight(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());


                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setWeight(data.getWeight());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });


        Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>> cellFactoryTax =
                new Callback<TableColumn<ItemDetailsDo, Double>, TableCell<ItemDetailsDo, Double>>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellDouble();
                    }
                };

        taxCol.setCellFactory(cellFactoryTax);
        taxCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
                        System.out.println("edit wholesale");
                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
                        data.setTax(t.getNewValue());

                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());

                        ItemDetails itemDetails = new ItemDetails(det);

                        itemDetails.setTax(data.getTax());
                        itemDetails.setEnabled(true);

                        itemService.itemDetailsSave(itemDetails);
                        fillDataTable();
                    }
                });


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
                ItemDetailsDo item = (ItemDetailsDo) newValue;
                setIndex(dataTableData.indexOf(newValue));

                System.out.println("OK");
            }
        });

        uom.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        //uom.getItems().addAll("choose", "mg", "cg", "dg", "g", "kg");
        category.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");

        ItemCategoryService itemCategoryService =
                (ItemCategoryService) ApplicationMain.springContext.getBean("itemCategoryService");
        List<ItemCategoryDo> itemCategoryList = itemCategoryService.getAllItems();



        for (ItemCategoryDo item : itemCategoryList) {

            category.getItems().add(item.getItemName());
        }



        UOMService uOMService =
                (UOMService) ApplicationMain.springContext.getBean("UOMService");

        for (UOMDo uOMDo : uOMService.getAllUOM()) {
            System.out.println("size:" + uOMService.getAllUOM().size());
            uom.getItems().add(uOMDo.getUomDesc());
        }




        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("barcode"));
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("itemName"));
        itemMRP.setCellValueFactory(
                new PropertyValueFactory<ItemDetailsDo, Double>("mrp"));
        itemWeightCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("weight"));
        itemWeightUnitCol.setCellValueFactory(new PropertyValueFactory<ItemDo, String>("weightUnit"));
        itemActualPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("actualPrice"));
        itemRetailPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("retailBillingPrice"));
        itemWholesalePriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("wholesaleBillingPrice"));
        taxCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("tax"));
        itemHasGiftCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("hasGift"));
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");

        System.out.println("Get all items..");

        itemDetailsList = new ArrayList<ItemDetailsDo>();

        for (Items item : itemService.getAllItems()) {
            // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(item.getIdPk())) {
            System.out.println("itemId:" + item.getIdPk());
            itemDetailsList.add(itemService.getItemDetailsDoByItemId(item.getIdPk()));
        }
        fillDataTable();


    }

    private void clearForm() {
        itemName.clear();
        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();
        actualPrice.clear();
        retailPrice.clear();
        wholesalePrice.clear();
        tax.clear();
        outputLabel.setText("");

    }
    private ApplicationMain application;

    void setApp(ApplicationMain aThis) {
        System.out.println("aThis" + aThis);
        this.application = aThis;
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    /* private void fillDataTable() {
     if (itemService == null) {
     itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
     }
     List<ItemDetailsDo> itemDetailsDoList = new ArrayList<ItemDetailsDo>();
     List<Items> items = itemService.getAllItems();
     for (Items item : itemService.getAllItems()) {
     // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(item.getIdPk())) {
     System.out.println("itemId:"+item.getIdPk());
     itemDetailsDoList.add(itemService.getItemDetailsByItemId(item.getIdPk()));
                
            

     }
     dataTableData.setAll(itemDetailsDoList);
     }*/
    private void fillDataTable() {

        dataTableData.setAll(itemDetailsList);
    }

    @FXML
    private void addItem(ActionEvent event) {

        tabPane.getSelectionModel().select(itemDetails_tab);
        //itemDetails_tab



    }

    @FXML
    private void deleteItem(ActionEvent event) {

        if (itemService.getAllItemDetailsByItemId(dataTableData.get(index.get()).getItemId())) {
            outputLabel.setText("Item already invoiced");
            return;
        }

        dataTableData.remove(index.get());
        dataTable.getSelectionModel().clearSelection();



    }

    @FXML
    public void gotoBarcode(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            itemBarcode.requestFocus();
        }
    }

    @FXML
    public void OpenInvoice(ActionEvent e) {
        application.gotoInvoice();
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
    private void searchAction(ActionEvent event) {

        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");

        if (KCPUtils.isNullString(searchCriteria.getText())) {
            label.setText("Please enter product name");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return;
        }


        itemDetailsList = itemService.getItemsByCriteria(searchCriteria.getText());


        fillDataTable();

    }
}


/*
 class EditingCellActualPrice extends TableCell<ItemDetailsDo, Double> {

 private TextField textField;

 public EditingCellActualPrice() {
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

 //                      InvoiceDetailsDo data = (InvoiceDetailsDo) t.getTableView().getItems().get(event.getTablePosition().getRow());
 commitEdit(Double.parseDouble(textField.getText()));
 //commitEdit(textField.getText());
 } else if (t.getCode() == KeyCode.ESCAPE) {
 cancelEdit();
 }
 }
 });
 }

 private String getString() {
 return getItem() == null ? "" : getItem().toString();
 }
    
    
    
 }*/
class EditingCellDouble extends TableCell<ItemDetailsDo, Double> {

    private TextField textField;

    public EditingCellDouble() {
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

//                      InvoiceDetailsDo data = (InvoiceDetailsDo) t.getTableView().getItems().get(event.getTablePosition().getRow());
                    commitEdit(Double.parseDouble(textField.getText()));
                    //commitEdit(textField.getText());
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
