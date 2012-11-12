/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;


import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.InvoiceDaoImpl;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.dao.ItemsDaoImpl;
import com.kcp.pos.dao.StocksDao;
import com.kcp.pos.dao.StocksDaoImpl;
import com.kcp.pos.data.StocksDo;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Stocks;
import com.kcp.pos.service.InvoiceService;
import com.kcp.pos.service.StocksService;
import com.kcp.pos.utils.KCPUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * @author Prakash
 */
public class StocksController implements Initializable{
    
    @FXML
    private Label label;
    
   
    
    @FXML
    private TextField barcode;
    @FXML
    private TextField mrp;
    @FXML
    
    
    private TextField caseQuantity;
    @FXML
    private TextField unitsQuantity;
    @FXML
    private TextField freeUnits;
    
    @FXML
    public TableView<StocksDo> dataTable;
    
    private final ObservableList<StocksDo> dataTableData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<StocksDo, String> itemNameCol;
    @FXML
    private TableColumn<StocksDo, String> itemBarcodeCol;
    @FXML
    private TableColumn<StocksDo, Double> itemMRPCol;
    @FXML
    private TableColumn<StocksDo, Double> caseQuantityCol;
    @FXML
    private TableColumn<StocksDo, Double> quantityPerCase;
    @FXML
    private TableColumn<StocksDo, Integer> unitsQuantityCol;
    @FXML
    private TableColumn<StocksDo, Double> freeUnitsCol;
    
    private List<Stocks> stocksList = new ArrayList<Stocks>();
    private List<Items> itemList = new ArrayList<Items>();
    private Map<String, Items> itemMap = new HashMap<String, Items>();
    ItemDao itemDao = new ItemsDaoImpl();
     @Autowired
    private StocksService stocksService;

    public List<Items> getItemList() {
        List<Items> list = itemDao.findByAll();
        setItemList(list);
        return list;
    }

    public void setItemList(List<Items> itemList) {
        this.itemList = itemList;
    }
    InvoiceDao invoiceDao = new InvoiceDaoImpl();

    public List<Stocks> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<Stocks> stocksList) {
        this.stocksList = stocksList;
    }

     StocksDao stocksDao = new StocksDaoImpl();
     
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataTable.setItems(dataTableData);
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, String>("itemName"));
        itemBarcodeCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, String>("barcode"));
        itemMRPCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, Double>("mrp"));
        caseQuantityCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, Double>("caseQuantity"));
        unitsQuantityCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, Integer>("unitQuantity"));
        quantityPerCase.setCellValueFactory(
                new PropertyValueFactory<StocksDo, Double>("unitsPerCase"));
        freeUnitsCol.setCellValueFactory(
                new PropertyValueFactory<StocksDo, Double>("itemFreeUnits"));
        fillDataTable();
    }

   
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
        stocksService = (StocksService) ApplicationMain.springContext.getBean("stocksService");        
        List<StocksDo> stocksList = new ArrayList<StocksDo>();
                for(Stocks stocks:stocksService.getAllStocks())
                {
                    System.out.println("size:"+stocksService.getAllStocks().size());
                    stocksList.add(new StocksDo(stocks));
                }
                for (Iterator<StocksDo> it = stocksList.iterator(); it.hasNext();) {
            StocksDo stocksDo = it.next();
                    System.out.println("name:"+stocksDo.getItemName());
        }
        dataTableData.setAll(stocksList);
    }

    private ApplicationMain application;
     void setApp(ApplicationMain aThis) {
         System.out.println("aThis"+aThis);
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
    public void openMain(ActionEvent e) {
        application.gotoMain();
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