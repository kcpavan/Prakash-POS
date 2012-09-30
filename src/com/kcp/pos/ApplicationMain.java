/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

import com.kcp.pos.dao.UserDao;
import com.kcp.pos.modal.Users;
import com.kcp.pos.security.Authenticator;
import com.sun.javaws.Main;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kcpavan
 */
public class ApplicationMain extends Application {
    
    public  static ApplicationContext springContext = null;

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
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
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
    
       private void gotoMain() {
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
    
    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
}
