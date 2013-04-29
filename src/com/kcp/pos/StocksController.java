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
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
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
    private TextField dateField;
    
    @FXML
    private AnchorPane anchorPane;
    
     @FXML
    private GridPane gridPane;
     
     private DatePicker birthdayDatePicker;

    
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
    
    
    
    private List<StocksDo> stocksList = new ArrayList<StocksDo>();
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

    public List<StocksDo> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<StocksDo> stocksList) {
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
        /*HBox dateBox = new HBox(15);
        dateBox.setAlignment(Pos.CENTER);
        final TextField dateField = new TextField("Select date");
        dateField.setEditable(false);
        dateField.setDisable(true);
        SimpleCalendar simpleCalender = new SimpleCalendar(anchorPane);
        simpleCalender.dateProperty().addListener(new ChangeListener<Date>() {

        @Override
	public void changed(ObservableValue<? extends Date> ov,
            Date oldDate, Date newDate) {
                dateField.setText((new SimpleDateFormat("dd/MM/yyyy")).
                        format(newDate));
                /*
                 *  System.out.println("selected date is:"+date);
                Calendar c15DaysAgo = Calendar.getInstance(); // 15 days ago
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date start_date = format.parse(date + " 00:00:00");
                Date end_date = format.parse(date + " 23:59:59");
                //2012-11-11 23:49:38
                new Date(date+" 00:00:00");
                 */
               /* System.out.println("actual date is:"+newDate);
                System.out.println("simple date is:"+new SimpleDateFormat("dd/MM/yyyy").
                        format(newDate));
                SimpleDateFormat format;
                Date date;
               try
               {
                format = new SimpleDateFormat("dd/MM/yyyy");
                date=format.parse(newDate.toString());
                   System.out.println("formated date is:"+date);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
                stocksService.getStocksListByDate(
                        new Date
                        ((new SimpleDateFormat("dd/MM/yyyy")).
                        format(newDate)));*/
                
	//}
       /* });
        
        dateBox.getChildren().addAll(dateField, simpleCalender);
        VBox vbox = new VBox(20);
        //Label label = new Label("JavaFX 2.0 Simple Calendar Demo");
        vbox.getChildren().addAll( dateBox);
        anchorPane.getChildren().add(vbox);*/
        
       birthdayDatePicker = new DatePicker(Locale.ENGLISH);
  birthdayDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
  birthdayDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
  birthdayDatePicker.getCalendarView().setShowWeeks(false);
   birthdayDatePicker.getStylesheets().add("com/kcp/pos/style/DatePicker.css");

  // Add DatePicker_backup to grid
  gridPane.add(birthdayDatePicker, 1, 5);


        fillDataTable();
    }

   
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        System.out.println("selected date is:"+ birthdayDatePicker.getSelectedDate());
    }
    
    
    
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), label);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    private void fillDataTable() {
        stocksService = (StocksService) ApplicationMain.springContext.getBean("stocksService");        
       
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
    public void openPurchaseDetails(ActionEvent e) {
        application.gotoPurchaseDetails();
    }

    
    @FXML
    public void openStocks(ActionEvent e) {
        application.gotoStocks();
    }
    
    
    
}