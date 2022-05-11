import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable {
	private int orderid;
	private String orderdesc;
	private String orderDelAdd;
	private double amount;
	private LocalDateTime orderdate;
	private LocalDateTime deliverydate;

	
	
	public Orders(int orderid, String orderdesc, String orderDelAdd, double amount, LocalDateTime orderdate,
			LocalDateTime deliverydate) {
		super();
		this.orderid = orderid;
		this.orderdesc = orderdesc;
		this.orderDelAdd = orderDelAdd;
		this.amount = amount;
		this.orderdate = orderdate;
		this.deliverydate = deliverydate;
	}
	
	public LocalDateTime getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(LocalDateTime orderdate) {
		this.orderdate = orderdate;
	}
	public LocalDateTime getDeliverdate() {
		return deliverydate;
	}
	public void setDeliverdate(LocalDateTime deliverdate) {
		this.deliverydate = deliverdate;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getOrderdesc() {
		return orderdesc;
	}
	public void setOrderdesc(String orderdesc) {
		this.orderdesc = orderdesc;
	}
	public String getOrderDelAdd() {
		return orderDelAdd;
	}
	public void setOrderDelAdd(String orderDelAdd) {
		this.orderDelAdd = orderDelAdd;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return  orderid+"   "+ orderdesc+"   "+ orderDelAdd+"   " 
				+ amount +"   "+ orderdate+"   "  +"   "+deliverydate ;
	}
	

	
	
}
