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
import com.kcp.pos.data.BagWeightDo;
import com.kcp.pos.data.ItemCategoryDo;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.UOMDo;
import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.UOM;
import com.kcp.pos.modal.WeighingType;
import com.kcp.pos.service.BagWeightService;
import com.kcp.pos.service.BillingPriceService;
import com.kcp.pos.service.ItemCategoryService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.service.UOMService;
import com.kcp.pos.service.WeighingTypeService;
import com.kcp.pos.utils.KCPUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.GridPane;
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
    private ChoiceBox category = new ChoiceBox();
    @FXML
    private ChoiceBox uom = new ChoiceBox();
    @FXML
    private TextField retailPrice;
    @FXML
    private ComboBox bagWeight;
    @FXML
    private GridPane gridPane;
    /*@FXML
    private TextField wholesalePrice;*/
    
    @FXML
    private TextField startRange1;
    @FXML
    private TextField endRange1;
    @FXML
    private TextField billingPrice1;
    
    @FXML
    private TextField startRange2;
    @FXML
    private TextField endRange2;
    @FXML
    private TextField billingPrice2;
    
    @FXML
    private TextField startRange3;
    @FXML
    private TextField endRange3;
    @FXML
    private TextField billingPrice3;
    
    
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
    private TableColumn<ItemDetailsDo, Double> startRange1Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> endRange1Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> billingPrice1Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> startRange2Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> endRange2Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> billingPrice2Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> startRange3Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> endRange3Col;
    @FXML
    private TableColumn<ItemDetailsDo, Double> billingPrice3Col;
    
    /*@FXML
    private TableColumn<ItemDetailsDo, Double> itemWholesalePriceCol;*/
    @FXML
    private TableColumn<ItemDetailsDo, Double> taxCol;
    @FXML
    private TableColumn<ItemDetailsDo, String> itemHasGiftCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab items_tab;
    
    @FXML
    private Tab itemDetails_tab;
    List<ItemDetailsDo> itemDetailsList = new ArrayList<ItemDetailsDo>();
    @Autowired
    private ItemService itemService;
    @Autowired
    private UOMService uOMService;
    
    private Map<String,Integer> categoryMap=new HashMap<String,Integer>();
    
    @Autowired
    private BagWeightService bagWeightService;
    
    @FXML
    private Button button;
    
    
    private Map<Double, BagWeightDo> bagWeightMap = new HashMap<Double, BagWeightDo>();
    
    @Autowired
    private WeighingTypeService weighingTypeService;
    
    @Autowired
    private BillingPriceService billingPriceService;
    
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

        Double rPrice = null, wPrice = null, aPrice = null;

        if (KCPUtils.isNullString(actualPrice.getText())) {
            label.setText("Please enter actualPrice");
            animateMessage();
            fillDataTable();
            System.out.println("reenter item");
            return true;
        } else {
            aPrice = Double.parseDouble(actualPrice.getText());
        }

//        if (KCPUtils.isNullString(retailPrice.getText())) {
//            label.setText("Please enter retailPrice");
//            animateMessage();
//            fillDataTable();
//            System.out.println("reenter item");
//            return true;
//        } else {
//            rPrice = Double.parseDouble(retailPrice.getText());
//        }

       /* if (KCPUtils.isNullString(wholesalePrice.getText())) {
            label.setText("Please enter retailPrice");
            animateMessage();
            fillDataTable();
            System.out.println("reenter bagWeighDo");
            return true;
        } else {
            wPrice = Double.parseDouble(wholesalePrice.getText());
        }*/

//        if (rPrice < aPrice) {
//            label.setText("Retail price cannot be less than actual price");
//            return true;
//        }
//
//        if (wPrice < aPrice) {
//            label.setText("Wholesale price cannot be less than actual price");
//            return true;
//        }

        return false;

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (!validate()) {
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

            uOMService = (UOMService) ApplicationMain.springContext.getBean("UOMService");

            itemDetails.setUom(uOMService.getUOMByName((String) uom.getSelectionModel().getSelectedItem()));
            itemDetails.setMrp(Double.valueOf(itemMrp.getText()));
            itemDetails.setWeight(Double.valueOf(itemWeight.getText()));
            itemDetails.setActualPrice(Double.valueOf(actualPrice.getText()));
            
            itemDetails.setHasfree(Boolean.valueOf(hasGift.isSelected()));
            /*if (Boolean.valueOf(hasGift.getText())) {
                itemDetails.setHasfree("Yes");
            } else {
                itemDetails.setHasfree("No");
            }
            itemDetails.setHasfree(String.valueOf(hasGift.getText()));*/
            itemDetails.setTax(Double.valueOf(tax.getText()));

            System.out.println("hasfree:" + hasGift.isSelected());

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
                uom.getItems().add(uOMDo.getUomUserName());
            }

           // itemDetails.setRetailBillingPrice(Double.valueOf(retailPrice.getText()));
          //  itemDetails.setWholesaleBillingPrice(Double.valueOf(wholesalePrice.getText()));
           // itemDetails.setMargin(itemDetails.getWholesaleBillingPrice() - itemDetails.getActualPrice());
            
            
            
            
            
            itemDetails.setModifiedDate(new Date());
            itemDetails.setUsers(userDao.findById(1));
            
            
            itemDetails.setEnabled(Boolean.TRUE);
            
            
            itemService.itemSave(item);

            itemDetails.setItem(item);


            itemService.itemDetailsSave(itemDetails);
            
            
            
            List<BillingPrice> billingPriceList=new ArrayList<BillingPrice>();
            BillingPrice billingPriceObj1=new BillingPrice();
            if(startRange1!=null&&!KCPUtils.isNullString(startRange1.getText()))
            {
                billingPriceObj1.setStartRange(
                        Double.parseDouble(startRange1.getText()));
            
            if(startRange1!=null)
                billingPriceObj1.setEndRange(Double.parseDouble(endRange1.getText()));
            if(startRange1!=null)
                billingPriceObj1.setBillingPrice(Double.parseDouble(billingPrice1.getText()));
            
                
            }
            billingPriceObj1.setItemDetails(itemDetails);
                billingPriceList.add(billingPriceObj1);
            
            BillingPrice billingPriceObj2=new BillingPrice();
            if(startRange1!=null&&!KCPUtils.isNullString(startRange1.getText()))
            {
            billingPriceObj2.setStartRange(Double.parseDouble(startRange2.getText()));
            if(startRange1!=null)
            billingPriceObj2.setEndRange(Double.parseDouble(endRange2.getText()));
            if(startRange1!=null)
            billingPriceObj2.setBillingPrice(Double.parseDouble(billingPrice2.getText()));
            
            

            }
                            billingPriceObj2.setItemDetails(itemDetails);
            billingPriceList.add(billingPriceObj2);
            
            BillingPrice billingPriceObj3=new BillingPrice();
            if(startRange1!=null&&!KCPUtils.isNullString(startRange1.getText()))
            {
            billingPriceObj3.setStartRange(Double.parseDouble(startRange3.getText()));
            billingPriceObj3.setEndRange(Double.parseDouble(endRange3.getText()));
            billingPriceObj3.setBillingPrice(Double.parseDouble(billingPrice3.getText()));
            
            }
            billingPriceObj1.setStartRange(1.0);
            billingPriceObj1.setEndRange(2.0);
            billingPriceObj1.setBillingPrice(3.0);
            billingPriceObj1.setModifiedDate(new Date());
            billingPriceObj1.setModifiedUser(userDao.findById(1));
            billingPriceObj1.setItemDetails(itemDetails);
            billingPriceObj2.setStartRange(3.0);
            billingPriceObj2.setEndRange(4.0);
            billingPriceObj2.setBillingPrice(5.0);
            billingPriceObj2.setModifiedDate(new Date());
            billingPriceObj2.setItemDetails(itemDetails);
            billingPriceObj2.setModifiedUser(userDao.findById(1));
            billingPriceObj3.setStartRange(5.0);
            billingPriceObj3.setEndRange(6.0);
            billingPriceObj3.setBillingPrice(6.0);
            billingPriceObj3.setItemDetails(itemDetails);
            billingPriceObj3.setModifiedDate(new Date());
            billingPriceObj3.setModifiedUser(userDao.findById(1));
            billingPriceList.add(billingPriceObj1);
            billingPriceList.add(billingPriceObj2);
            billingPriceList.add(billingPriceObj3);
            
            billingPriceService = (BillingPriceService) ApplicationMain.springContext.getBean("billingPriceService");
           
            billingPriceService.billingPriceSave(billingPriceList);
            
            label.setText("Item Saved");
            animateMessage();
            itemDetailsList = new ArrayList<ItemDetailsDo>();

            for (Items data : itemService.getAllItems()) {

                // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(bagWeighDo.getIdPk())) {
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
        bagWeight.setVisible(false);
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

//        itemRetailPriceCol.setCellFactory(cellFactoryRetail);
//        itemRetailPriceCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<ItemDetailsDo, Double>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<ItemDetailsDo, Double> t) {
//                        ItemDetailsDo record = (ItemDetailsDo) t.getTableView().getItems().get(t.getTablePosition().getRow());
//
//                        ItemDetailsDo data = itemService.getItemDetailsDoByItemId(record.getItemId());
//                        data.setRetailPrice(t.getNewValue());
//
//                        ItemDetails det = itemService.getItemDetailsByItemId(data.getItemId());
//
//
//                        ItemDetails itemDetails = new ItemDetails(det);
//
//                        //itemDetails.setRetailBillingPrice(data.getRetailPrice());
//                        itemDetails.setEnabled(true);
//
//                        itemService.itemDetailsSave(itemDetails);
//                        fillDataTable();
//                    }
//                });


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

       /* itemWholesalePriceCol.setCellFactory(cellFactoryWholeSale);
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
                });*/



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

            categoryMap.put(item.getItemName(),item.getIdPk());
            category.getItems().add(item.getItemName());
        }

        
        weighingTypeService = (WeighingTypeService) ApplicationMain.springContext.getBean("weighingTypeService");

        category.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                System.out.println("new item:" + newItem+":");


              
                if(newItem !=null )
                {
                uOMService=(UOMService) ApplicationMain.springContext.getBean("UOMService");
                
                
               List<UOM> uoms= uOMService.
                       getByCategory(categoryMap.get(
                        newItem.toString()));
                
                  for (UOM uom  : uoms) {
                      System.out.println("uom:"+uom.getUomDesc()); 
                      
           
            MainController.this.uom.getItems().add(uom.getUomDesc());
        }
                
//                List<WeighingType> weighingTypes= weighingTypeService.
//                        getByCategory(categoryMap.get(
//                        newItem.toString()));

                
                
//        for (WeighingType weighingType  : weighingTypes) {
//           
//            uom.getItems().add(weighingType.getTypeDesc());
//        }
                
       
                
            }
            }
        });
        
       

        bagWeight.getItems().removeAll("Item 1", "Item 2", "Item 3", " ");
        uom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                System.out.println("new item:" + newItem+":");
              
                if(newItem !=null)
                {
                    //gridPane.getChildren().remove(bagWeight);
                    //bagWeight=new ComboBox();
                    bagWeight.getItems().removeAll(bagWeight.getItems());
                
                
                if(newItem.toString().equalsIgnoreCase("Bag"))
                {
                    
                    bagWeightService = (BagWeightService) ApplicationMain.springContext.getBean("bagWeightService");
                    List<BagWeightDo> bagWeightList = bagWeightService.getAll();

        for (BagWeightDo bagWeighDo : bagWeightList) {
            bagWeightMap.put(bagWeighDo.getBagWeight(), bagWeighDo);
            bagWeight.getItems().add(bagWeighDo.getBagWeight());
            System.out.println("weight:");
        } 
                }
                else if(newItem.toString().equalsIgnoreCase("kg"))
                {
                    List<Items> items=itemService.getAllItems(categoryMap.get(category.getSelectionModel().getSelectedItem()));
                    
                    for (Items item : items) {
                        bagWeight.getItems().add(item.getItemName());
                        System.out.println("weight:");
        } 
                    //get all items by category
                }
//                List<WeighingType> weighingTypes= weighingTypeService.
//                        getByCategory(categoryMap.get(
//                        newItem.toString()));

//        for (WeighingType weighingType  : weighingTypes) {
//           
//            uom.getItems().add(weighingType.getTypeDesc());
//        }
              
          
       
                
            }
            }
        });


        

//        UOMService uOMService =
//                (UOMService) ApplicationMain.springContext.getBean("UOMService");
//
//        for (UOMDo uOMDo : uOMService.getAllUOM()) {
//            System.out.println("size:" + uOMService.getAllUOM().size());
//            uom.getItems().add(uOMDo.getUomUserName());
//        }




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
        //itemRetailPriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("retailBillingPrice"));
        //itemWholesalePriceCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("wholesaleBillingPrice"));
        startRange1Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("startRange1"));
        endRange1Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("endRange1"));
        billingPrice1Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("billingPrice1"));
        startRange2Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("startRange2"));
        endRange2Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("endRange2"));
        billingPrice2Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("billingPrice2"));
        startRange3Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("startRange3"));
        endRange3Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("endRange3"));
        billingPrice3Col.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("billingPrice3"));
        taxCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, Double>("tax"));
        itemHasGiftCol.setCellValueFactory(new PropertyValueFactory<ItemDetailsDo, String>("hasFree"));
        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");

        System.out.println("Get all items..");

        itemDetailsList = new ArrayList<ItemDetailsDo>();

        for (Items item : itemService.getAllItems()) {
            // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(bagWeighDo.getIdPk())) {
            System.out.println("itemId:" + item.getIdPk());
            itemDetailsList.add(itemService.getItemDetailsDoByItemId(item.getIdPk()));
        }
        
        
       
      

        category.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldItem, String newItem) {
                System.out.println("new category:" + newItem+":");


                
                if(newItem !=null)
                {
                    System.out.println("new item found!!");
                    if(newItem.equalsIgnoreCase("Rice"))
                    {
                       bagWeight.setVisible(true); 
                       fillDataTable();
                    }
                BagWeightDo bagWeighDo = bagWeightMap.get(newItem.toString());
            }
            }
        });
        
        fillDataTable();


    }

    private void clearForm() {
        itemName.clear();
        itemBarcode.clear();
        itemMrp.clear();
        itemWeight.clear();
        actualPrice.clear();
        retailPrice.clear();
        //wholesalePrice.clear();
        
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
     for (Items bagWeighDo : itemService.getAllItems()) {
     // for (ItemDetailsDo itemsDetails : itemService.getItemDetailsByItemId(bagWeighDo.getIdPk())) {
     System.out.println("itemId:"+bagWeighDo.getIdPk());
     itemDetailsDoList.add(itemService.getItemDetailsByItemId(bagWeighDo.getIdPk()));
                
            

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

        tabPane.getSelectionModel().select(items_tab);

        fillDataTable();

    }
    
    @FXML
    public void gotoBarcode(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            itemBarcode.requestFocus();
        }
    }
    
    @FXML
    public void gotoMrp(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            itemMrp.requestFocus();
        }
    }
    
    
    @FXML
    public void gotoWeight(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            itemWeight.requestFocus();
        }
    }
    
    
    @FXML
    public void gotoUom(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            uom.requestFocus();
        }
    }
    
    
    @FXML
    public void gotoActualPrice(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            actualPrice.requestFocus();
        }
    }
    
    @FXML
    public void gotoCategory(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            category.requestFocus();
        }
    }
    
    @FXML
    public void gotoHasGift(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            hasGift.requestFocus();
        }
    }
    
    
    @FXML
    public void gotoRetail(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            retailPrice.requestFocus();
        }
    }
    
    /*@FXML
    public void gotoWholesale(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            wholesalePrice.requestFocus();
        }
    }*/
    
    @FXML
    public void gotoStartRange1(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            startRange1.requestFocus();
        }
    }
    
    @FXML
    public void gotoEndRange1(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            endRange1.requestFocus();
        }
    }
    
    @FXML
    public void gotoBillingPrice1(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            billingPrice1.requestFocus();
        }
    }
    
   @FXML
    public void gotoStartRange2(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            startRange2.requestFocus();
        }
    }
    
    @FXML
    public void gotoEndRange2(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            endRange2.requestFocus();
        }
    }
    
    @FXML
    public void gotoBillingPrice2(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            billingPrice1.requestFocus();
        }
    }
    
    @FXML
    public void gotoStartRange3(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            startRange3.requestFocus();
        }
    }
    
    @FXML
    public void gotoEndRange3(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            startRange3.requestFocus();
        }
    }
    
    @FXML
    public void gotoBillingPrice3(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            billingPrice3.requestFocus();
        }
    }
    
    
    @FXML
    public void gotoTax(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            tax.requestFocus();
        }
    }
    
    
    
    @FXML
    public void gotoSave(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            button.requestFocus();
        }
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
 public void updateItem(Double bagWeighDo, boolean empty) {
 super.updateItem(bagWeighDo, empty);

 System.out.println("UpdateItem:" + bagWeighDo);

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
