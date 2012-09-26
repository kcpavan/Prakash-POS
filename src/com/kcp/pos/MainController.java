/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.BillingTypeDao;
import com.kcp.pos.dao.BillingTypeDaoImpl;
import com.kcp.pos.dao.ItemCategoryDao;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.dao.UserDao;
import com.kcp.pos.dao.commonDao;
import com.kcp.pos.dao.commonDaoImpl;
import com.kcp.pos.data.ItemCategoryDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.BillingType;
import com.kcp.pos.modal.ItemCategory;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.service.ItemCategoryService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
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
public class MainController implements Initializable {

    @FXML
    private Label label;
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
    @FXML
    private TextField sellingPrice;
    @FXML
    private CheckBox hasGift = new CheckBox();
    @FXML
    private ChoiceBox weightUnit = new ChoiceBox();
    @FXML
    private ChoiceBox category = new ChoiceBox();
    @FXML
    public TableView<ItemDo> dataTable;
    private final ObservableList<ItemDo> dataTableData = FXCollections.observableArrayList();
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
    private TableColumn<ItemDo, Double> itemSellingPriceCol;
    @FXML
    private TableColumn<ItemDo, Double> itemHasGiftCol;
    @FXML
    private TableColumn<ItemDo, Double> itemTotalAmountsCol;
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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Items item = new Items();
        item.setItemName(itemName.getText());
        item.setBarcode(itemBarcode.getText());

        ItemDetails itemDetails = new ItemDetails();

        itemDetails.setMrp(Double.valueOf(itemMrp.getText()));
        item.setWeight(Double.valueOf(itemWeight.getText()));
        itemDetails.setActualPrice(Double.valueOf(actualPrice.getText()));
        commonDao comDao = new commonDaoImpl();
        /*List list = comDao.getLookUpValues(
                ItemCategory.class, "typeDesc");
        if (!KCPUtils.isNullList(list)) {
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                ItemCategory obj = (ItemCategory) iterator.next();
            }*/
            String selectedItem =(String)category.getSelectionModel().getSelectedItem();
            
             if (KCPUtils.isNullString(selectedItem))
                  {
            label.setText("Please select item");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return;
        }
             itemCategoryService= (ItemCategoryService) ApplicationMain.applicationContext.getBean("itemCategoryService");
            itemCategoryService.getItemCategoryByName((String)selectedItem);
           
            item.setUom((String) weightUnit.getSelectionModel().getSelectedItem());
            item.setModifiedDate(new Date());
           // Object selectedItem = category.getSelectionModel().getSelectedItem();
            System.out.println("selectedItem:" + selectedItem);
            ItemCategoryDao itemCategoryDao = (ItemCategoryDao) ApplicationMain.applicationContext.getBean("itemCategoryDaoImpl");
            item.setItemCategory(itemCategoryDao.findByName(selectedItem.toString()));
            itemService = (ItemService) ApplicationMain.applicationContext.getBean("itemService");
            UserDao userDao = (UserDao) ApplicationMain.applicationContext.getBean("userDaoImpl");
            item.setUsers(userDao.findById(1));

            BillingTypeDao billingTypeDao=new BillingTypeDaoImpl();
            BillingType billingType=billingTypeDao.findByName("retail");
            itemDetails.setBillingType(billingType);
            
            
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
        public void initialize
        (URL url, ResourceBundle rb
        
            ) {
         weightUnit.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
            weightUnit.getItems().addAll("choose", "mg", "cg", "dg", "g", "kg");
            category.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");

            ItemCategoryService itemCategoryService =
                    (ItemCategoryService) ApplicationMain.applicationContext.getBean("itemCategoryService");
            List<ItemCategoryDo> itemCategoryList = itemCategoryService.getAllItems();

            for (ItemCategoryDo item : itemCategoryList) {

                category.getItems().add(item.getItemName());
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
            itemSellingPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDo, Double>("sellingPrice"));
            itemHasGiftCol.setCellValueFactory(new PropertyValueFactory<ItemDo, Double>("hasGift"));


            fillDataTable();
        }   
    
    

    private void clearForm() {
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

    private void fillDataTable() {
        if (itemService == null) {
            itemService = (ItemService) ApplicationMain.applicationContext.getBean("itemService");
        }
        List<ItemDo> items = itemService.getAllItems();

        dataTableData.setAll(items);
    }
}
