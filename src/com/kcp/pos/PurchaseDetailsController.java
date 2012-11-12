/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.DistributorDao;
import com.kcp.pos.dao.UserDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.InvoiceDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.data.PurchaseDetailsDo;
import com.kcp.pos.data.PurchaseDo;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.FadeTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

/**
 *
 * @author Prakash
 */
public class PurchaseDetailsController implements Initializable {

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
    public TableView<PurchaseDo> dataTable;
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
    
    private final ObservableList<PurchaseDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    public TableView<PurchaseDetailsDo> detailsDataTable;
    private final ObservableList<PurchaseDetailsDo> detailsDataTableData = FXCollections.observableArrayList();
    
    
    @FXML
    private TableColumn<PurchaseDo, String> purchaseIdCol;
    @FXML
    private TableColumn<PurchaseDo, String> purchasedByCol;
    @FXML
    private TableColumn<PurchaseDo, Double> purchaseDateCol;
    @FXML
    private TableColumn<PurchaseDo, Double> purchaseNetAmountCol;
    @FXML
    private TableColumn<PurchaseDo, Double> cdCol;
    @FXML
    private TableColumn<PurchaseDo, Double> cdAmountCol;
    @FXML
    private TableColumn<PurchaseDo, Double> totalAmountCol;
    
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

    private IntegerProperty index = new SimpleIntegerProperty();

    
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab purchase_tab;
    @FXML
    private Tab purchaseDetails_tab;
    
    public final void setIndex(Integer value) {
        index.set(value);
    }

    public IntegerProperty indexProperty() {
        return index;
    }
   
    private List<PurchaseDetailsDo> purchaseDetailsDoList = new ArrayList<PurchaseDetailsDo>();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("PurchaseDetailsController()");
        purchaseService = (PurchaseService) ApplicationMain.springContext.getBean("purchaseService");
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
                PurchaseDo purchaseDo = (PurchaseDo) newValue;
                setIndex(dataTableData.indexOf(newValue));
                tabPane.getSelectionModel().select(purchaseDetails_tab);
                System.out.println("select purhcase id:"+purchaseDo.getIdPk());
                purchaseDetailsDoList = purchaseService.
                                getPurchaseDetails(purchaseDo.getIdPk());
                detailsDataTable.setItems(detailsDataTableData);
                fillDetailsDataTable();
                System.out.println("OK");
            }
        });
       
        
        dataTable.setItems(dataTableData);
        purchaseIdCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, String>("idPk"));
        purchasedByCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, String>("users"));
        purchaseDateCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, Double>("modifiedDate"));
       purchaseNetAmountCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, Double>("netAmount"));
       cdCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, Double>("cd"));
       cdAmountCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, Double>("cdAmount"));
       totalAmountCol.setCellValueFactory(
                new PropertyValueFactory<PurchaseDo, Double>("totalAmount"));
       
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


        
        fillDataTable();
    }

    

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
        List<PurchaseDo> purchaseList = null;
            purchaseList = purchaseService.getPurchaseList();
            System.out.println("purchase list size:"+purchaseList.size());
            for(PurchaseDo p:purchaseList)
            {
                System.out.println("net Amount:"+p.getNetAmount());
            }
            dataTableData.setAll(purchaseList);
    }
    
    private void fillDetailsDataTable() {
      
        detailsDataTableData.setAll(purchaseDetailsDoList);
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
    public void openPurchase(ActionEvent e) {
        application.gotoPurchase();
    }

     @FXML
    public void openPurchaseDetails(ActionEvent e) {
        application.gotoPurchaseDetails();
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
    public void openStocks(ActionEvent e) {
        application.gotoStocks();
    }
    
     @FXML
    public void openInvoiceDetails(ActionEvent e) {
        application.gotoInvoiceDetails();
    }
    
}
