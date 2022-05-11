import java.io.*;

public interface OrderManagement{
	 public abstract void addOrder() throws Exception;
	 public abstract void viewOrder() throws Exception ;
	 public abstract void viewByOrderId() throws Exception;
	 public abstract void sortOrder()throws Exception;
	 public abstract void deletByOrderId() throws Exception;
	 public abstract void markAsDelivery() throws Exception;
	 public abstract void generateReport() throws Exception ;
	 public abstract void exit() throws Exception;
	 
}
