import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenerateReport extends Thread{
	
	public GenerateReport(List<Orders> li) throws Exception {
		

		File filer=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filer));
		li=(List<Orders>) ois.readObject();
		ois.close();
			File file=new File("GenerateReports.txt");
			try {
				FileWriter fw=new FileWriter(file);
				if(file.exists()) {
					file.delete();
				}
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss a");
				LocalDateTime deliverydate = LocalDateTime.now();
				dtf.format(deliverydate);
				fw.write("------------------------------------------------------\n");
				fw.write("Order report "+dtf.format(deliverydate)+"\n" );
				fw.write("------------------------------------------------------\n");
				for(Orders ord:li) {
					if(ord.getDeliverdate().equals(deliverydate))
						fw.write("order id :"+ord.getOrderid()+"\n");
						fw.write("Order Desc :"+ord.getOrderdesc()+"\n");
						fw.write("Amount :" +ord.getAmount()+"\n");
						fw.write("Delivery Date :"+ord.getDeliverdate()+"\n");
						fw.close();
					
				}
			}catch(Exception e) {}
		
	
	}
		@Override
		public void run() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss a");
			LocalDateTime deliverydate = LocalDateTime.now();
			dtf.format(deliverydate);
		}
	
	}


