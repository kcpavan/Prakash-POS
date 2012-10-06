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
import com.kcp.pos.data.ItemCategoryDo;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.UOMDo;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pavankumar
 */
@Component
public class MainController implements Initializable  
{

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
    private TableColumn<ItemDo, Double> itemMRP;
    @FXML
    private TableColumn<ItemDo, Double> itemWeightCol;
    @FXML
    private TableColumn<ItemDo, String> itemWeightUnitCol;
    @FXML
    private TableColumn<ItemDo, Double> itemActualPriceCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemRetailBillingPriceCol;
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemWholesaleBillingPriceCol;
    
    @FXML
    private TableColumn<ItemDetailsDo, Double> taxCol;
    
    @FXML
    private TableColumn<ItemDetailsDo, Double> itemHasGiftCol;
 
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab itemDetails_tab;
    
    
    
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
    
    
    
   
    
        
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");

        if (KCPUtils.isNullString(itemName.getText())) {
            label.setText("Please enter item name");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return;
        }
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

        itemDetails.setMrp(Double.valueOf(itemMrp.getText()));
        item.setWeight(Double.valueOf(itemWeight.getText()));
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

        
        UOMService uOMService=(UOMService) ApplicationMain.springContext.getBean("UOMService");
        
       
        item.setModifiedDate(new Date());
        // Object selectedItem = category.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem:" + selectedItem);
        ItemCategoryDao itemCategoryDao = (ItemCategoryDao) ApplicationMain.springContext.getBean("itemCategoryDaoImpl");
        item.setItemCategory(itemCategoryDao.findByName(selectedItem.toString()));

        
        UserDao userDao = (UserDao) ApplicationMain.springContext.getBean("userDaoImpl");
        item.setUsers(userDao.findById(1));

        BillingTypeDao billingTypeDao = (BillingTypeDao) ApplicationMain.springContext.getBean("billingTypeDaoImpl");

        itemDetails.setBillingType(billingTypeDao.findByName("retail"));
        
      
        System.out.println("UOM:"+uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()).getUomDesc());
       //itemDetails.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
       item.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
        for (UOMDo uOMDo : uOMService.getAllUOM()) {
            System.out.println("size:"+uOMService.getAllUOM().size());
            uom.getItems().add(uOMDo.getUomDesc());
        }
        
        itemDetails.setRetailBillingPrice(Double.valueOf(retailPrice.getText()));
        itemDetails.setWholesaleBillingPrice(Double.valueOf(wholesalePrice.getText()));
        itemDetails.setModifiedDate(new Date());
        itemDetails.setUsers(userDao.findById(1));
        itemDetails.setEnabled(Boolean.TRUE);
        itemService.itemSave(item);

        itemDetails.setItem(item);


        itemService.itemDetailsSave(itemDetails);
        label.setText("Item Saved");
        animateMessage();
        fillDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    
        
        
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
            System.out.println("size:"+uOMService.getAllUOM().size());
            uom.getItems().add(uOMDo.getUomDesc());
        }
        
        
        
        
        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("barcode"));
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<ItemDo, String>("itemName"));
        itemMRP.setCellValueFactory(
                new PropertyValueFactory<ItemDo, Double>("mrp"));
        itemWeightCol.setCellValueFactory(new PropertyValueFactory<ItemDo, Double>("weight"));
        itemWeightUnitCol.setCellValueFactory(new PropertyValueFactory<ItemDo, String>("weightUnit"));
        itemActualPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDo, Double>("actualPrice"));
        itemRetailBillingPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("retailBillingPrice"));
        itemWholesaleBillingPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("wholesaleBillingPrice"));
        taxCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("tax"));
        itemHasGiftCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("hasGift"));
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

    private void fillDataTable() {
        if (itemService == null) {
            itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        }
        List<ItemDetailsDo> itemDetailsDoList = new ArrayList<ItemDetailsDo>();
        List<Items> items = itemService.getAllItems();
        for (Items item : itemService.getAllItems()) {
            for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(item.getIdPk())) {
                itemDetailsDoList.add(itemsDetails);
            }

        }
        dataTableData.setAll(itemDetailsDoList);
    }
    
    
     @FXML
    private void addItem(ActionEvent event) {
  
            tabPane.getSelectionModel().select(itemDetails_tab);
         //itemDetails_tab
       
            
            
        }
     
     
    @FXML
    private void deleteItem(ActionEvent event) {
  
           if(itemService.getAllItemDetailsByItemId(dataTableData.get(index.get()).getItemId()))
           {
               outputLabel.setText("Item already invoiced");
               return;
           }
           
           dataTableData.remove(index.get());
           dataTable.getSelectionModel().clearSelection();
       
 
            
        }
     
     
     @FXML
     public void gotoBarcode(KeyEvent e)
     {
         
          
          
         //System.out.println("In barcode");
          
         if(e.getCode()==KeyCode.ENTER)
         {
             //System.out.println("Enter key pressed");
            itemBarcode.requestFocus();
         }
     }
     
     
             @FXML
     public void OpenInvoice(ActionEvent e)
     {
         
         
          
          application.gotoInvoice();
         //System.out.println("In barcode");
          
        
     }
     
     
             @FXML
     public void openMain(ActionEvent e)
     {
         
         
          
          application.gotoInvoice();
         //System.out.println("In barcode");
          
        
     }
}


 