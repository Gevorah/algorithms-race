package ui;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.*;
import thread.*;

public class GUI {
	@FXML
    private RadioButton rbAdd;

    @FXML
    private RadioButton rbSearch;

    @FXML
    private RadioButton rbDelete;

    @FXML
    private RadioButton rbIterative;

    @FXML
    private RadioButton rbRecursive;

    @FXML
    private Button bRun;

    @FXML
    private TextField txtN;

    @FXML
    private Circle outerCircle;

    @FXML
    private Circle innerCircle;

    @FXML
    private Label lbTimekeeper;

    @FXML
    private Label lbArrayList;

    @FXML
    private Label lbLinkedList;

    @FXML
    private Label lbBST;
    
    @FXML
    private DialogPane popup;

    @FXML
    private ProgressBar pbAL;

    @FXML
    private ProgressBar pbLL;

    @FXML
    private ProgressBar pbBST;

    @FXML
    private ProgressIndicator piWaiting;

    @FXML
    private Label lbWaiting;
    
    private ArrayList al;
	private BinarySearchTree bst;
	private LinkedList ll;
	public GUI(ArrayList al, BinarySearchTree bst, LinkedList ll) {
		this.al = al;
		this.bst = bst;
		this.ll = ll;
	}
	private long[] generatedArray;
	private long[] generatedArraySD;
	private void returnArrays(long[] array, long[] arraySD) {
		generatedArray = array;
		generatedArraySD = arraySD;
	}
	@FXML
	public void initializePopup() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Pop-up.fxml"));
		loader.setController(this);
		DialogPane root = loader.load();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setDialogPane(root);
		dialog.setTitle("Race");
		dialog.show();
	}
    @FXML
    public void initializeRace(ActionEvent event) throws Exception {
    	try {
    		if(check()) {
        		initializePopup();
        		bRun.setDisable(true);
        		String option = option();
        		update(Constants.START_TIME,0,0,0,true,true,true);
        		new Thread() {
        			public void run() {
        				long[] array = new long[Integer.parseInt(txtN.getText())];
        				long[] arraySD = new long[Integer.parseInt(txtN.getText())];
        				for(int i=0;i<array.length;i++) {
        					array[i] = Long.MIN_VALUE+(long)(Math.random()*(Long.MAX_VALUE-Long.MIN_VALUE));
        					if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
        							option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
        						arraySD[i] = Long.MIN_VALUE+(long)(Math.random()*(Long.MAX_VALUE-Long.MIN_VALUE));
        					}
        				}
        				returnArrays(array,arraySD);
        			}
        		}.start();
        		Thread.sleep(900);
        		Timekeeper tk = new Timekeeper();
        		ArrayListThread alt = new ArrayListThread(al,generatedArray,generatedArraySD,option);
        		LinkedListThread llt = new LinkedListThread(ll,generatedArray,generatedArraySD,option);
        		BinarySearchTreeThread bstt = new BinarySearchTreeThread(bst,generatedArray,generatedArraySD,option);
        		AnimationTimer at = new AnimationTimer() {
        			public boolean circle=true;
        			public boolean race=false;
        			@Override
        			public void handle(long now) {
        				//60FPS
        				if((int)outerCircle.getRadius()==(int)innerCircle.getRadius()) circle=true;
        				if(outerCircle.getRadius()==Constants.MAX_RADIO) circle=false;
        				//Radius+1 is fast, but Radius+0.1 is slow and allows us to better appreciate animation 
        				if(circle==true) {
        					outerCircle.setRadius(outerCircle.getRadius()+0.1);
        					innerCircle.setRadius(innerCircle.getRadius()-0.1);
        				} else if(circle==false) {
        					outerCircle.setRadius(outerCircle.getRadius()-0.1);
        					innerCircle.setRadius(innerCircle.getRadius()+0.1);
        				}
        				if(!alt.isAlive()&&!llt.isAlive()&&!bstt.isAlive()) {
        					tk.stopTimekeeper();
        					//this stop the animation timer
        					stop();
        					bRun.setDisable(false);
        				}
        				if((race==false)&&(alt.isReady()==false)&&(llt.isReady()==false)&&(bstt.isReady()==false)) {
        					alt.setReady(true);
        					llt.setReady(true);
        					bstt.setReady(true);
        					piWaiting.setVisible(false);
        					lbWaiting.setVisible(false);
        					race = true;
        				}
        				if(!alt.isAlive()) pbAL.setProgress(1);
        				if(!llt.isAlive()) pbLL.setProgress(1);
        				if(!bstt.isAlive()) pbBST.setProgress(1);
        				update(tk.getTimer(),alt.getProgress(),llt.getProgress(),bstt.getProgress(),alt.isAlive(),llt.isAlive(),bstt.isAlive());
        			}
        		};
        		tk.start();
        		at.start();
        		alt.start();
        		llt.start();
        		bstt.start();
        	}
    	} catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    public void update(String time, double altProgress, double lltProgress, double bsttProgress, boolean alt, boolean llt, boolean bstt) {
    	lbTimekeeper.setText(time);
    	if(alt==true) {
    		lbArrayList.setText(time);
    		pbAL.setProgress(altProgress);
    	}
    	if(llt==true) {
    		lbLinkedList.setText(time);
    		pbLL.setProgress(lltProgress);
    	}
    	if(bstt==true) {
    		lbBST.setText(time);
    		pbBST.setProgress(bsttProgress);
    	}
    }
    private String option() {
    	String option = null;
    	if(rbIterative.isSelected()) {
    		if(rbAdd.isSelected()) {
    			option = Constants.OPTION_1;
    		} else if(rbSearch.isSelected()) {
    			option = Constants.OPTION_2;
    		} else if(rbDelete.isSelected()) {
    			option = Constants.OPTION_3;
    		} 
    	} else if(rbRecursive.isSelected()) {
    		if(rbAdd.isSelected()) {
    			option = Constants.OPTION_4;
    		} else if(rbSearch.isSelected()) {
    			option = Constants.OPTION_5;
    		} else if(rbDelete.isSelected()) {
    			option = Constants.OPTION_6;
    		}
    	}
    	return option;
    }
    private boolean check() {
    	if((!txtN.getText().trim().isEmpty()) && 
    		(rbAdd.isSelected()||rbSearch.isSelected()||rbDelete.isSelected()) && 
    		(rbIterative.isSelected()||rbRecursive.isSelected())) {
    		return true;
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning filling the survey");
    		alert.setContentText("Some fields are empty. \n"+"Please fill the fields or select the options.");
    		alert.showAndWait();
    		return false;
    	}
    	
    	
    }
    @FXML
    void handleAddButton() {
    	if(rbAdd.isSelected()) {
    		rbSearch.setSelected(false);
    		rbDelete.setSelected(false);
    	}
    }
    @FXML
    void handleSearchButton() {
    	if(rbSearch.isSelected()) {
    		rbAdd.setSelected(false);
    		rbDelete.setSelected(false);
    	}
    }
    @FXML
    void handleDeleteButton() {
    	if(rbDelete.isSelected()) {
    		rbAdd.setSelected(false);
    		rbSearch.setSelected(false);
    	}
    }
    @FXML
    void handleIterativeButton() {
    	if(rbIterative.isSelected()) {
    		rbRecursive.setSelected(false);
    	}
    }
    @FXML
    void handleRecursiveButton() {
    	if(rbRecursive.isSelected()) {
    		rbIterative.setSelected(false);
    	}
    }
    
}
