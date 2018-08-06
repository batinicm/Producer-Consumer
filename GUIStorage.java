package storage;

import java.awt.*;
import java.awt.event.*;

public class GUIStorage extends Frame {
	
	private Thread[] threads;
	private TextArea strg;
	private List prod,cons;
	
	private void fill() {
		Panel pan=new Panel(new GridLayout(1, 3));
		pan.add(new Label("Producers:"));
		pan.add(new Label("Storage:"));
		pan.add(new Label("Consumer:"));
		add(pan,BorderLayout.NORTH);
		
		pan=new Panel(new GridLayout(1,3));
		add(pan,BorderLayout.CENTER);
		
		strg=new TextArea();
		prod=new List();
		cons=new List();
		
		prod.setMultipleMode(true);
		cons.setMultipleMode(true);
		
		pan.add(prod);
		pan.add(strg);
		pan.add(cons);
		pan.setEnabled(false);		
	}
	
	public GUIStorage(int cap, int numOfProd, int maxPro, int minPro, int numOfCons, int maxCons, int minCons) {
		super("Producer-Consumer");
		setBounds(100,100,400,500);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				for(Thread thr: threads) thr.interrupt();
				dispose();
			}
		});
		fill();
		threads=new Thread[numOfProd+numOfCons];
		Storage str=new Storage(cap, this.strg);
		int t=0;
		for(int i=0; i<numOfProd; i++) {
			prod.add("***");
			(threads[t++]=new Producer(maxPro, minPro, str, prod)).start();
		}
		for(int i=0; i<numOfCons; i++) {
			cons.add("***");
			(threads[t++]=new Consumer(maxPro, minPro, str, cons)).start();
		}
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new GUIStorage(4, 8, 8000, 4000, 7, 7000, 3500);
	}
	
	

}
