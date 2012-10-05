/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.UserDao;

import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.InvoiceDo;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;

import com.kcp.pos.service.InvoiceService;
import com.kcp.pos.service.ItemService;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ChoiceBox itemName;
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
    private TextField wholesalePrice;
    @FXML
    private TextField retailPrice;
    
    
    @FXML
    private CheckBox hasGift = new CheckBox();
    @FXML
    private ChoiceBox weightUnit = new ChoiceBox();
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
        
        //InvoiceDetails invoiceDetails = null;

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
            //invoiceNum = new Integer(invoiceDao.getInvoiceId()).toString();

            invoiceNumber.setText(new Integer(invoice.getIdPk()).toString());
           
        }

        //invoiceDetails.setInvoice(Integer.parseInt(invoiceNum));
        

        //invoiceService.getInvoiceDetailsDoById(invoice.getIdPk());

        itemService = (ItemService) ApplicationMain.springContext.getBean("itemService");
        String itemQty = itemQuantity.getText();

        if (KCPUtils.isNullString(itemQty)) {
            label.setText("Please select item quantity");
            animateMessage();
            fillInvoiceDataTable();

            System.out.println("reenter item");
            return;
        }

//        billingService = (BillingService) ApplicationMain.springContext.getBean("billingService");
  //      BillingPrice billingPrice = null;
       
        Items item = itemService.getItemByName(selectedItem.toString());
        ItemDetails itemDetails= itemService.getItemDetailsByItemIdBillingType(item.getIdPk(),1);
        
        String itemName = item.getItemName();

        System.out.println("invoiceIdPk:"+invoice.getIdPk());
        System.out.println("itemIdPk:"+item.getIdPk());
      
       
        InvoiceDetails invoiceDetails=invoiceService.getInvoiceDetailsByInvoiceItemId(invoice.getIdPk(),item.getIdPk());
        
        if(invoiceDetails!=null)
        {
                
                invoiceDetails.setQuantity(invoiceDetails.getQuantity() + Integer.parseInt(itemQty));
                
                //billingPrice = billingService.getBillingPrice(item.getIdPk(), invoiceDetails.getQuantity());
                //Double billingPrice=itemService.getBillingPriceByItemId(item.getIdPk());
                //invoiceDetails.set(billingPrice);
                //Double price = billingPrice.getBillingPrice();
                //double itemTotalPrice = price * invoiceDetails.getQuantity();
                double itemTotalPrice = itemDetails.getRetailBillingPrice()* invoiceDetails.getQuantity();
                invoiceDetails.setTotal(itemTotalPrice);
                invoiceDetails.setItemDetails(itemDetails);
  
        }
        else
        {
            invoiceDetails=new InvoiceDetails();
            
            //invoiceDetails.setItems(item);
            invoiceDetails.setItemDetails(itemDetails);
            invoiceDetails.setQuantity(Integer.parseInt(itemQty));
            //billingPrice = billingService.getBillingPrice(item.getIdPk(), invoiceDetails.getQuantity());
            //invoiceDetails.setBillingPrice(billingPrice);
                //Double price = billingPrice.getBillingPrice();
                double itemTotalPrice = itemDetails.getRetailBillingPrice() * Integer.parseInt(itemQty);
                invoiceDetails.setTotal(itemTotalPrice);

                invoiceDetails.setInvoice(invoice);
        }
        /*List<InvoiceDetails> invoiceDetailslist= invoiceService.getInvoiceDetailsById(invoice.getIdPk());
        if(invoiceDetailslist!=null)
             System.out.println("invoice details size:"+invoiceService.getInvoiceDetailsById(invoice.getIdPk()).size());
        else 
             System.out.println("Invoice detail is null");
        */
        
        
        
        /*if(invoiceService.getInvoiceDetailsById(invoice.getIdPk())==null
                || invoiceService.getInvoiceDetailsById(invoice.getIdPk()).size()==0 )
        {
            System.out.println("Invoice detail is null");
            invoiceDetails=new InvoiceDetails();
            invoiceDetails.setItems(item);
            invoiceDetails.setQuantity(Integer.parseInt(itemQty));
                billingPrice = billingService.getBillingPrice(item.getIdPk(), invoiceDetails.getQuantity());
                invoiceDetails.setBillingPrice(billingPrice);
                Double price = billingPrice.getBillingPrice();
                double itemTotalPrice = price * Integer.parseInt(itemQty);
                invoiceDetails.setTotal(itemTotalPrice);

                invoiceDetails.setInvoice(invoice);
        }
        
        for (InvoiceDetails invoiceDet : invoiceService.getInvoiceDetailsById(invoice.getIdPk())) {
            if (itemName.equalsIgnoreCase(invoiceDet.getItems().getItemName())) {
                System.out.println("Invoice item already exists");
                invoiceDetails=invoiceDet;
                invoiceDetails.setQuantity(invoiceDet.getQuantity() + Integer.parseInt(itemQty));
                
                billingPrice = billingService.getBillingPrice(item.getIdPk(), invoiceDetails.getQuantity());
                invoiceDetails.setBillingPrice(billingPrice);
                Double price = billingPrice.getBillingPrice();
                double itemTotalPrice = price * invoiceDetails.getQuantity();
                invoiceDetails.setTotal(itemTotalPrice);

                
                
            } else {
                System.out.println("1st item");
                invoiceDetails=new InvoiceDetails();
                invoiceDetails.setItems(item);
                invoiceDetails.setQuantity(Integer.parseInt(itemQty));
                billingPrice = billingService.getBillingPrice(item.getIdPk(), invoiceDetails.getQuantity());
                invoiceDetails.setBillingPrice(billingPrice);
                Double price = billingPrice.getBillingPrice();
                double itemTotalPrice = price * Integer.parseInt(itemQty);
                invoiceDetails.setTotal(itemTotalPrice);

                invoiceDetails.setInvoice(invoice);
                
            }
            break;

        }
        */




        
        invoiceService.invoiceDetailsSave(invoiceDetails);




        label.setText("Item Saved");
        animateMessage();
        fillInvoiceDataTable();
        clearForm();
        System.out.println("saved");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                ItemDo item = itemMap.get(newItem);

                ItemDetailsDo itemDetailsDo = itemService.getItemDetailsDoByItemId(item.getIdPk());
                
                itemBarcode.setText(item.getBarcode());
                System.out.println("MRP:" + item.getMrp());
                itemMrp.setText(new Double(item.getMrp()).toString());
                itemWeight.setText(new Double(item.getWeight()).toString());
                //weightUnit.setSelectionModel(item.getWeightUnit());
                billingPrice.setText(new Double(itemDetailsDo.getBillingPrice()).toString());
                


            }
        });


        dataTable.setItems(dataTableData);
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, String>("barcode"));

        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, String>("itemName"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceDetailsDo, Double>("mrp"));


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
        //List<Item> items=itemDao.getItemListByInvoiceId(invoiceDetailsDoList);


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

    public void saveInvoice() {
        //invoiceDao.saveInvoice(invoiceDetailsDoList);
    }

    public void deleteInvoiceItem() {
        //invoiceDao.deleteInvoiceItem(invoiceId, itemId);
        //invoiceDao.deleteInvoiceItem(1,1);
    }
    
    private ApplicationMain application;
     void setApp(ApplicationMain aThis) {
         System.out.println("aThis"+aThis);
        this.application = aThis;
    }
     
            @FXML
     public void openMain(ActionEvent e)
     {
         
         
          
          application.gotoMain();
         //System.out.println("In barcode");
          
        
     }
            
                   @FXML
     public void OpenInvoice(ActionEvent e)
     {
         
         
          
          application.gotoInvoice();
         //System.out.println("In barcode");
          
        
     }
}