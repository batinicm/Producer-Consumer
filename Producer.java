package storage;

import java.awt.*;

public class Producer extends Thread {
	
	private static int gId=0;
	private int id=gId++;
	private int num;
	private double maxT,minT;
	private Storage s;
	private List l;
	
	
	public Producer(double maxT, double minT, Storage s, List l) {
		this.maxT = maxT;
		this.minT = minT;
		this.s=s;
		this.l = l;
	}


	public Producer(double maxT, double minT, Storage s) {
		this(maxT,minT,s,null);
	}
	
	public Producer(Storage s) {
		this(2000,1000,s,null);
	}


	@Override
	public void run() {
		try {
		while(!interrupted()) {
			sleep((long)(minT + Math.random()*(maxT-minT)));
			int vr=1000*id+ ++num;
			if(l!=null) {
			l.replaceItem(Integer.toString(vr), id);
			l.revalidate();
			}
			if(l!=null) l.select(id);
			s.addNum(vr);
			if(l!=null) l.deselect(id);
		}
		}
		catch(InterruptedException i) {}
	}
	
	
	
	
	
	

}
