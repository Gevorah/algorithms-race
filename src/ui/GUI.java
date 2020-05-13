package ui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;
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
    
    private ArrayList al;
	private BinarySearchTree bst;
	private LinkedList ll;
	public GUI(ArrayList al, BinarySearchTree bst, LinkedList ll) {
		this.al = al;
		this.bst = bst;
		this.ll = ll;
	}
	private long[] generatedArray;
	private void returnArray(long[] array) {
		generatedArray = array;
	}
    @FXML
    public void initializeRace(ActionEvent event) {
    	if(check()) {
    		bRun.setDisable(true);
    		updateTime(Constants.START_TIME,true,true,true);
    		new Thread() {
    			public void run() {
    				long[] array = new long[Integer.parseInt(txtN.getText())];
    				for(int i=0;i<array.length;i++) {
    					array[i] = Long.MIN_VALUE+(long)(Math.random()*(Long.MAX_VALUE-Long.MIN_VALUE));
    				}
    				returnArray(array);
    			}
    		}.start();
    		try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		Timekeeper tk = new Timekeeper();
    		Timekeeper tkArrayList = new Timekeeper();
    		Timekeeper tkLinkedList = new Timekeeper();
    		Timekeeper tkBST = new Timekeeper();
    		ArrayListThread alt = new ArrayListThread(al,generatedArray,option());
    		LinkedListThread llt = new LinkedListThread(ll,generatedArray,option());
    		BinarySearchTreeThread bstt = new BinarySearchTreeThread(bst,generatedArray,option());
    		AnimationTimer at = new AnimationTimer() {
    			public boolean t=true;
    			@Override
    			public void handle(long now) {
    				if((int)outerCircle.getRadius()==(int)innerCircle.getRadius()) t=true;
    				if(outerCircle.getRadius()==Constants.MAX_RADIO) t=false;
    				if(t==true) {
    					outerCircle.setRadius(outerCircle.getRadius()+0.1);
    					innerCircle.setRadius(innerCircle.getRadius()-0.1);
    				} else if(t==false) {
    					outerCircle.setRadius(outerCircle.getRadius()-0.1);
    					innerCircle.setRadius(innerCircle.getRadius()+0.1);
    				}
    				if(!(alt.isAlive()&&llt.isAlive()&&bstt.isAlive())) {
    					tk.stopTimekeeper();
    					stop();
    				}
    				updateTime(tk.getTimer(),alt.isAlive(),llt.isAlive(),bstt.isAlive());
    			}
    		};
    		tk.start();
    		at.start();
    		alt.start();
    		llt.start();
    		bstt.start();
    		bRun.setDisable(false);
    	}
    }
    public void updateTime(String time, boolean al, boolean ll, boolean bst) {
    	lbTimekeeper.setText(time);
    	if(al==true) lbArrayList.setText(time);
    	if(ll==true) lbLinkedList.setText(time);
    	if(bst==true) lbBST.setText(time);
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
    /*private long[] generateRandom(int n) {
		long[] array = new long[n];
		for(int i=0;i<n;i++) {
			array[i] = Long.MIN_VALUE+(long)(Math.random()*(Long.MAX_VALUE-Long.MIN_VALUE));
		}
    	return array;
    }*/
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
