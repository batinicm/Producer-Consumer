package storage;

import java.awt.*;


public class Storage {
	
	private static int gId=0;
	private int id=++gId;
	private int[] arr;
	private int tot;
	private int head,tail;
	private TextArea ta;
	
	public Storage(int k, TextArea t) {
		arr=new int[k];
		ta=t;
	}
	
	public Storage(int k) {
		this(k,null);
	}
	
	public synchronized void addNum(int num) throws InterruptedException {
		while(tot==arr.length) wait();
		arr[tail]=num;
		tail=(tail+1)%arr.length;
		tot++;
		notifyAll();
		if(ta!=null) ta.setText(toString());
	}
	
	public synchronized int takeNum() throws InterruptedException {
		while(tot==0) wait();
		int res=arr[head];
		head=(head+1)%arr.length;
		tot--;
		if(ta!=null) ta.setText(toString());
		notifyAll();
		return res;
	}
	
	public synchronized String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<tot; i++)
			sb.append(Integer.toString(arr[(head+i)%arr.length]) + "\n");
		return sb.toString();
	}
	

}
