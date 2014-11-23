package cs.colostate.cs414.g.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextArea;

import cs.colostate.cs414.g.util.OrderStatus;
import cs.colostate.cs414.g.util.OrderUtil;
import cs.colostate.cs414.g.util.WaitingQueue;


public class Chef implements OrderItemEmp, java.io.Serializable
{
	private static final long serialVersionUID = 1249465871308592055L;
	private WaitingQueue waitingQueue = null;
	private OrderItem currentOrderItem = null;
	
	public void setWaitingQueue(WaitingQueue waitingQueue) {
		this.waitingQueue = waitingQueue;
	}
	public OrderItem getCurrentItem(){
		return this.currentOrderItem;
	}

	public boolean cancel(OrderItem item) {
		if (item == currentOrderItem) {
			currentOrderItem = null;
			return true;
		}
		
		return false;
	}

	public OrderStatus getAssociatedStage() {
		return OrderStatus.PREPARATION;
	}

	public void update() {

		if (currentOrderItem != null) {
				currentOrderItem.endStage(getAssociatedStage());
				currentOrderItem.setWorker(null);
				currentOrderItem = null;
			
		}
		
		if (currentOrderItem == null && waitingQueue.peekNextItem() != null) {
			addOrderItem(waitingQueue.removeNextItem());
		}
	}

	public void addOrderItem(OrderItem item) {

		if (currentOrderItem == null) {
			item.startStage(getAssociatedStage());
			item.setWorker(this);
			currentOrderItem = item;
		}
	}
	
	public String makeCompleted(JTextArea editTextArea,int thisline){
		// TODO Auto-generated method stub
		File file = new File("order.txt");
		String orderList = new String();
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String lineRead ;
			if(thisline == 1)
				orderList+= bufferedReader.readLine().replace("PAID", "COMPLETED")+"\n";
			else
				orderList += bufferedReader.readLine()+"\n";
			
			int lineCount = 1;
			while((lineRead = bufferedReader.readLine()) != null)
			{
			  if (lineCount == thisline-1){
				  orderList += lineRead.replace("PAID", "COMPLETED")+"\n";
				  lineCount++;
			  }
			  else{
				  lineCount++;
				  orderList += lineRead+"\n";
			  }
			}
			
			PrintWriter fileWriter = new PrintWriter(file);
			fileWriter.println(orderList);
			fileWriter.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error!!");
		}
		return orderList;
	}
}
