package storage;

import java.awt.*;

public class Consumer extends Thread {
	
	private static int gId=0;
	private int id=gId++;
	private int num;
	private double maxT,minT;
	private Storage s;
	private List l;
	
	public Consumer(double maxT, double minT, Storage s, List l) {
		this.maxT = maxT;
		this.minT = minT;
		this.s = s;
		this.l = l;
	}

	public Consumer(double maxT, double minT, Storage s) {
		this(maxT,minT,s,null);
	}

	public Consumer(Storage s) {
		this(2000,1000,s,null);
	}

	@Override
	public void run() {
		try {
		while(!interrupted()) {
			if(l!=null) l.select(id);
			int vr=100000*id + s.takeNum();
			if(l!=null) l.deselect(id);
			if(l!=null) {
				l.replaceItem(Integer.toString(vr), id);
				l.revalidate();
			}
			sleep((long)(minT + Math.random()*(maxT-minT)));
		}
	}
		catch(InterruptedException i) {}
	}
	
	
	
	
	
	

	
	

}
