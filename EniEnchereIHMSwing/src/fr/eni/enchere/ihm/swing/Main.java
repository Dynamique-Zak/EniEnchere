package fr.eni.enchere.ihm.swing;

import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.enchere.bll.BLLManager;
import fr.eni.enchere.bo.Utilisateur;

public class Main {

	JTextField jtfPseudo;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f =new JFrame();//creating instance of JFrame  
        
		//JButton b=new JButton("click");//creating instance of JButton  
		//b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
		          
		//f.add(b);//adding button in JFrame  
		          
		// Set layout
		JPanel listPane = new JPanel();
		BoxLayout bxL = new BoxLayout(listPane, BoxLayout.Y_AXIS);
		listPane.setLayout(bxL);
		f.add(listPane);
		
		listPane.add(new JLabel("Inscription"));
		
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		listPane.add(new JTextField("pseudo"));
		
		f.setSize(400,500);//400 width and 500 height  
		//f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
	
	public void onSubmit() {
		// 1: Contrôle de surface
		HashMap<String, String> fieldsMap = new HashMap<String, String>();
		
		//fieldsMap.put(null, null);
		// 2: Hydrader l'utilisateur
		Utilisateur user = new Utilisateur(-1, fieldsMap.get("pseudo"), 
				fieldsMap.get("name"), fieldsMap.get("prenom"), fieldsMap.get("email"),
				fieldsMap.get("phone"), fieldsMap.get("rue"), fieldsMap.get("codePostale"), 
				fieldsMap.get("city"), fieldsMap.get("password"), 0, 0);
		
		// 3 : BLL
		BLLManager.getInstance().getUtilisaterManager().registerUser(user);
	}

}
