/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.UserDao;
import com.kcp.pos.modal.Users;
import com.kcp.pos.security.Authenticator;
import com.sun.javaws.Main;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kcpavan
 */
public class ApplicationMain extends Application {

    public static ApplicationContext springContext = null;

    /* public ApplicationContext getSpringContext() {
     return springContext;
     }

     public void setSpringContext(ApplicationContext springContext) {
     this.springContext = springContext;
     }
     */
    private Stage stage;
    private Users loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        stage.setTitle("Krishna Stores");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotoLogin();

        // gotoMain();
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));



        Parent root = (Parent) loader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //stage.sizeToScene();
        return (Initializable) loader.getController();
    }

     
    
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoMain() {
        try {
            MainController main = (MainController) replaceSceneContent("Main.fxml");
            main.setApp(this);
            /*InvoiceController invoice=(InvoiceController)replaceSceneContent("Invoice.fxml");
             invoice.setApp(this);*/
            /*PurchaseController purchase=(PurchaseController)replaceSceneContent("Purchase.fxml");
             purchase.setApp(this);*/
            /* StocksController stocks=(StocksController)replaceSceneContent("Stocks.fxml");
             stocks.setApp(this);*/
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoInvoice() {
        try {

            InvoiceController invoice = (InvoiceController) replaceSceneContent("Invoice.fxml");
            invoice.setApp(this);
            /*PurchaseController purchase=(PurchaseController)replaceSceneContent("Purchase.fxml");
             purchase.setApp(this);*/
            /* StocksController stocks=(StocksController)replaceSceneContent("Stocks.fxml");
             stocks.setApp(this);*/
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoInvoiceDetails() {
        try {

            InvoiceDetailsController invoice = (InvoiceDetailsController) replaceSceneContent("InvoiceDetails.fxml");
            invoice.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void gotoInvoiceDetailsMisc() {
        try {

            InvoiceDetailsMiscController invoice = (InvoiceDetailsMiscController) replaceSceneContent("InvoiceDetailsMisc.fxml");
            invoice.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoPurchase() {
        try {

            PurchaseController purchase = (PurchaseController) replaceSceneContent("Purchase.fxml");
            purchase.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void gotoPurchaseDetails() {
        try {
            PurchaseDetailsController purchase = (PurchaseDetailsController) replaceSceneContent("PurchaseDetails.fxml");
            purchase.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void gotoStocks() {
        try {

            StocksController purchase = (StocksController) replaceSceneContent("Stocks.fxml");
            purchase.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void gotoItem() {
        try {

            InvoiceController invoice = (InvoiceController) replaceSceneContent("Invoice.fxml");
            invoice.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceHomeSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));


        /*    MenuBar menuBar = new MenuBar();
 
         Menu menu1 = new Menu("Menu");
     
         MenuItem menuItemA = new MenuItem("Item A");
         menuItemA.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
         menuItemA.setOnAction(new EventHandler<ActionEvent>() {
         @Override public void handle(ActionEvent e) {
         System.out.println("Item A Clicked");
         }
         });
     
         MenuItem menuItemB = new MenuItem("Item B");
         menuItemB.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
         menuItemB.setOnAction(new EventHandler<ActionEvent>() {
         @Override public void handle(ActionEvent e) {
         System.out.println("Item B Clicked");
         }
         });
     
         MenuItem menuItemC = new MenuItem("Item C");
         menuItemC.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
         menuItemC.setOnAction(new EventHandler<ActionEvent>() {
         @Override public void handle(ActionEvent e) {
         System.out.println("Item C Clicked");
         }
         });
 
         menu1.getItems().add(menuItemA);
         menu1.getItems().add(menuItemB);
         menu1.getItems().add(menuItemC);
         menuBar.getMenus().add(menu1);
 
         menuBar.prefWidthProperty().bind(stage.widthProperty());
      
         final Group rootGroup = new Group();
         final Scene scene = new Scene(rootGroup, 800, 400, Color.WHEAT);
      
         rootGroup.getChildren().add(menuBar);
         stage.setScene(scene);
         stage.show();
        
       
        
         root.get.add(menuBar);
         primaryStage.setScene(scene);
         primaryStage.show();
         */

        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public Users getLoggedUser() {
        return loggedUser;
    }

    boolean userLogging(String userId, String password) {
        if (Authenticator.validate(userId, password)) {
            // UserDao userDao = (UserDao) springContext.getBean("userDaoImpl");
            // loggedUser = userDao.findById(1);
            gotoMain();
            return true;
        } else {
            return false;
        }
    }

    void userLogout() {
        loggedUser = null;
        gotoLogin();
    }
}
