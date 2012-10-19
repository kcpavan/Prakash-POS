/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;


/**
 *
 * @author Prakash
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import com.sun.prism.impl.Disposer.Record;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author pbhimanna
 */
public class InvoiceDetailsController implements Initializable {

    @FXML
    private Label label;
   
  
    @FXML
    public TableView<InvoiceDo> dataTable;
    private final ObservableList<InvoiceDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<InvoiceDo, Integer> invoiceNumber;
    
    @FXML
    private TableColumn<InvoiceDo, Integer> totalQuantity;
    @FXML
    private TableColumn<InvoiceDo, Double> totalAmount;
    @FXML
    private TableColumn<InvoiceDo, String> modifiedBy;
   
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ItemService itemService;
    private List<InvoiceDetailsDo> invoiceDetailsDoList = new ArrayList<InvoiceDetailsDo>();
    private List<Items> itemList = new ArrayList<Items>();
    private Map<String, ItemDo> itemMap = new HashMap<String, ItemDo>();
    private Invoice invoice;
    
    private List<InvoiceDo> invoiceDoList;

    
    private IntegerProperty index = new SimpleIntegerProperty();

    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab invoiceTab;
    @FXML
    private Tab invoiceDetails_tab;
    
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

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoiceService = (InvoiceService) ApplicationMain.springContext.getBean("invoiceService");
        
        dataTable.setEditable(true);
        
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

        
        
       
        invoiceDoList=invoiceService.getAllInvoiceDo() ;
            
     
        


        dataTable.setItems(dataTableData);
        invoiceNumber.setCellValueFactory(
                new PropertyValueFactory<InvoiceDo, Integer>("invoiceNumber"));

        
        totalQuantity.setCellValueFactory(
                new PropertyValueFactory<InvoiceDo, Integer>("totalQuantity"));


        totalAmount.setCellValueFactory(
                new PropertyValueFactory<InvoiceDo, Double>("totalAmount"));

        modifiedBy.setCellValueFactory(
                new PropertyValueFactory<InvoiceDo, String>("modifiedBy"));

        

        fillInvoiceDataTable();
    }

    private void clearForm() {
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillInvoiceDataTable() {
        if(invoiceDoList!=null)
            System.out.println("size:"+invoiceDoList.size());
        dataTableData.setAll(invoiceDoList);
    }

   
    public void deleteInvoiceItem() {
        //invoiceDao.deleteInvoiceItem(invoiceId, itemId);
        //invoiceDao.deleteInvoiceItem(1,1);
    }
    private ApplicationMain application;

    void setApp(ApplicationMain aThis) {
        System.out.println("aThis" + aThis);
        this.application = aThis;
    }

    @FXML
    public void openMain(ActionEvent e) {



        application.gotoMain();
        //System.out.println("In barcode");


    }

    @FXML
    public void OpenInvoice(ActionEvent e) {



        application.gotoInvoice();
        //System.out.println("In barcode");


    }
}


