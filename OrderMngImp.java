import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class OrderMngImp implements OrderManagement{
	Scanner sc= new Scanner(System.in);
	Scanner sc1= new Scanner(System.in);
	List<Orders> li=new ArrayList<Orders>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss a");
	LocalDateTime orderdate = LocalDateTime.now();
	LocalDateTime deliverydate = LocalDateTime.now();
	
	  

	@Override
	public void addOrder() throws Exception {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		li=(List<Orders>) ois.readObject();
		ois.close();
		
		String ch1;int orderid;
	do{
	
		System.out.println(" Add Order : \n");
		System.out.println("Add the order by entering following values");
		boolean found;
		do{
		 found = false;
		System.out.print("Enter the order id : ");
		orderid=sc.nextInt();
		for(Orders ord:li)
		{if(ord.getOrderid()==orderid)
			{System.out.println("Duplicate Order Id. Please enter unique order id");
			found=true;
			}
		}}while(found==true);
		
		System.out.print("Order Description : ");
		String orderdesc = sc1.nextLine();
		System.out.print("Delivery Address : ");
		String orderDelAdd = sc1.nextLine();
		System.out.print("Enter amount : ");
		double amount= sc.nextDouble();
		System.out.println("Order time : "+dtf.format(orderdate));
		li.add(new Orders(orderid, orderdesc, orderDelAdd,amount,orderdate,deliverydate));
		System.out.println("Do you want to enter more order details(Y/N)");
		ch1=sc1.nextLine();
	}while(ch1.equals("Y")||ch1.equals("y"));
	
		File filew=new File("Orders.txt");
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
		oos.writeObject(li);
		oos.close();
		System.out.println("Order Added Successfully");
		
		}

	@Override
	public void viewOrder() throws Exception {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		li=(List<Orders>) ois.readObject();
		ois.close();
		
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("OrderId | OrderDesc | Delivery Address |OrderDate                     |Amount        |DeliveryDate");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		Iterator<Orders> i=li.iterator();
		while(i.hasNext()) {
			Orders o=i.next();
		System.out.println(o.getOrderid()+"  \t " +o.getOrderdesc()+" \t "+o.getOrderDelAdd()+" \t "+dtf.format(orderdate)+" \t " +o.getAmount()+" \t "+dtf.format(orderdate));
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------");
	
	}
	

	@Override
	public void viewByOrderId()throws Exception  {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		li=(List<Orders>) ois.readObject();
		ois.close();
		boolean found=false;
		System.out.print("Enter the order id  to search : ");
		int soid=sc.nextInt();
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Order Detail :");
		System.out.println("------------------------------------------------------------------------------------");
		Iterator<Orders> i=li.iterator();
		while(i.hasNext()) {
			Orders o=i.next();
		if(o.getOrderid()==soid)
		{
		System.out.println("Order Id : "+ o.getOrderid());
		System.out.println("Order Desc : "+ o.getOrderdesc());
		System.out.println("Delivery Address : "+ o.getOrderDelAdd());
		System.out.println("Order Date : "+ dtf.format(orderdate));
		System.out.println("Amount : "+o.getAmount());
		System.out.println("Delivery Date : "+dtf.format(orderdate));
		found=true;
		}
		}
		if(!found)
		System.out.println("Record not found");	
		System.out.println("---------------------------------------------------------------------------");
	}
	
		
	@Override
	public void sortOrder()throws Exception {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		List<Orders> li=new ArrayList<Orders>();
		li=(List<Orders>)ois.readObject();
		ois.close();
		System.out.println("******** Choose Sort Order Property********* ");
		System.out.println("1. OrderId ");
		System.out.println("2. Order Desc ");
		System.out.println("3. Delivery Address ");
		System.out.println("4. Order Date ");
		System.out.println("5. Amount ");
		System.out.println("6. Delivery Datetime ");
		int so=sc.nextInt();
		switch (so) {
		case 1:
			Collections.sort(li, new Comparator<Orders>() {
				@Override
				public int compare(Orders o1, Orders o2) {
					return o1.getOrderid()- o2.getOrderid();
				}
			});
			System.out.println("Successfully Sorted by Order Id");
		break;
		case 2: Collections.sort(li, new Comparator<Orders>() {

			@Override
			public int compare(Orders o1, Orders o2) {
				return o1.getOrderdesc().compareTo(o2.getOrderdesc());
			}
		});
			System.out.println("Successfully Sorted by Order Desc");
		break;
		case 3: Collections.sort(li, new Comparator<Orders>() {
			@Override
			public int compare(Orders o1, Orders o2) {
				return o1.getOrderDelAdd().compareTo(o2.getOrderDelAdd());
			}
		});
			System.out.println("Successfully Sorted by Order Delivery Address");
		break;
		case 4: Collections.sort(li, new Comparator<Orders>() {
			@Override
			public int compare(Orders o1, Orders o2) {
				return o1.getOrderdate().compareTo(o2.getOrderdate());
			}
		});
			System.out.println("Successfully Sorted by Order Date");
		break;
		case 5: Collections.sort(li, new Comparator<Orders>() {
			@Override
			public int compare(Orders o1, Orders o2) {
				return o1.getAmount()>o2.getAmount() ? 1 : (o1.getAmount()<o2.getAmount()? -1:0);
			}
		});
			System.out.println("Successfully Sorted by Amount");
		break;
		case 6: Collections.sort(li, new Comparator<Orders>() {
			@Override
			public int compare(Orders o1, Orders o2) {
				return o1.getDeliverdate().compareTo(o2.getDeliverdate());
			}
		});
			System.out.println("Successfully Sorted by Amount");
		break;
	
		default:System.out.println("Enter correct number");
		}
		File filew=new File("Orders.txt");
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
		oos.writeObject(li);
		oos.close();
	}

	@Override
	public void deletByOrderId() throws Exception {
		
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		List<Orders> li=new ArrayList<Orders>();
		li=(List<Orders>)ois.readObject();
		ois.close();
		String chd;
		do{ 
		
			boolean found=false;
			System.out.print("Enter the Order Id  to delete : ");
			int doid=sc.nextInt();
			System.out.println("---------------------------------------------------------------------------");
			Iterator<Orders> i=li.iterator();
			while(i.hasNext()) {
				Orders o=i.next();
			if(o.getOrderid()==doid)
			{ i.remove();
			found=true;
			}
			}
			if(!found)
				System.out.println("Order Id is not available");	
			else
				System.out.println("Order Deleted successfully");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Do you want to delete another order(Y/N)");
			chd=sc1.nextLine();
		}while(chd.equals("Y")||chd.equals("y"));
		File filew=new File("Orders.txt");
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
		oos.writeObject(li);
		oos.close();
		
		}
		
	

	@Override
	public void markAsDelivery() throws Exception {
		
		String chmd;
		do{
			System.out.println("Enter the Order Id for delivery");
			int md=sc.nextInt();
			boolean found=false;
			System.out.println("---------------------------------------------------------------------------");
			Iterator<Orders> i=li.iterator();
			while(i.hasNext()) {
				Orders o=i.next();
			if(o.getOrderid()==md)
			{
				dtf.format(deliverydate);
				 o.setDeliverdate(deliverydate);
				found=true;
			}
			}
			if(!found)
				System.out.println("Duplicate Order Id. Please enter unique order id");
			else
				System.out.println("Successfully delivered");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Do you want to mark another Order as Delivered(Y/N) ");
			chmd=sc1.nextLine();
		}while(chmd.equals("Y")||chmd.equals("y"));
		File filew=new File("Orders.txt");
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
		oos.writeObject(li);
		oos.close();
		
	}

	@Override
	public void generateReport() throws Exception {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		li=(List<Orders>) ois.readObject();
		ois.close();
		GenerateReport gr = null;
		try {
			gr = new GenerateReport(li);
		} catch (Exception e) {}
		
			gr.start();
			System.out.println("Report Generated Successfully\n");
			File filew=new File("Orders.txt");
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
			oos.writeObject(li);
			oos.close();
			
	}

	@Override
	public void exit() throws Exception {
		File file=new File("Orders.txt");
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		li=(List<Orders>) ois.readObject();
		ois.close();
		File filew=new File("Orders.txt");
		
		
		
		File file1=new File("final.txt");
		try {
			FileWriter fw=new FileWriter(file1);
		
			fw.write("------------------------------------------------------------------------------------------------------------\n");
			fw.write("OrderId | OrderDesc | Delivery Address |OrderDate                     |Amount        |DeliveryDate\n");
			fw.write("------------------------------------------------------------------------------------------------------------\n");
			Iterator<Orders> i=li.iterator();
			for(Orders o:li) {
	
			fw.write(o.getOrderid()+"  \t " +o.getOrderdesc()+" \t "+o.getOrderDelAdd()+" \t "+dtf.format(orderdate)+" \t " +o.getAmount()+" \t "+dtf.format(orderdate)+"\n");
			}
			fw.write("-------------------------------------------------------------------------------------------------------------\n");
			fw.close();
		
				
			
		}catch(Exception e) {System.out.println("error");}
	
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filew));
		oos.writeObject(li);
		oos.close();
				System.out.println("Exited");	
	}}



