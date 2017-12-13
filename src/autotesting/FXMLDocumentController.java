/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autotesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author juanpoveda
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML 
    private TextField repo;
    
    @FXML 
    private TextField branch;
    
    @FXML 
    private TextField pack;
    
    @FXML 
    private TextField samples;
    
    @FXML 
    private TextField monkeys;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            System.out.println("You clicked me!");
            //label.setText("Hello World!");
            ProcessBuilder pb = new ProcessBuilder("/Users/juanpoveda/NetBeansProjects/AutoTesting/auto.sh", repo.getText(), branch.getText(), pack.getText(), samples.getText(), monkeys.getText());
            Map<String, String> env = pb.environment();
            env.put("ANDROID_HOME", "/Users/juanpoveda/Library/Android/sdk");
            pb.redirectOutput(new File("out.txt"));
            pb.redirectError(new File("error.txt"));
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
            System.out.println("finished!");
        } catch (IOException ex) {
            System.out.println("Exception " + ex);
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				                        System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
