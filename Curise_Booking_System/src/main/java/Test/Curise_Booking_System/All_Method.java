package Test.Curise_Booking_System;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;



public class All_Method {
	Scanner sc = new Scanner(System.in);
	List<Data> al3 = new ArrayList<Data>();
	boolean flag = false;
	public List<Data> read_file() throws IOException, CsvValidationException {
		List<Data> al = new ArrayList<Data>();
		int count = 0;
		//String path = ("src/main/resources/"+name+".csv");
		String path = ("src/main/resources/Customer_Data.csv");
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Data user = new Data();
				
				user.setId(nextLine[0]);
				user.setName(nextLine[1]);
				user.setEmail(nextLine[2]);
				al.add(user);
				count++;
				
			}
			System.out.println("Number of record read : "+count);
		return al;
	}
	
	public void display(List<Data> al) {
		System.out.println("All Customer Data.");
		System.out.println("Cust_Id\tCust_Name");
		for (Data user : al) 
				System.out.println(user.getId()+"\t"+user.getName());
		}	

	public void search(String cust_name, List<Data> al) {
		System.out.println("Cust_Id\tCust_Name");
		for (Data user : al) {
			if (user.getName().equals(cust_name))
				System.out.println(user.getId()+"\t"+user.getName());
			flag = true;
		}	
		if(flag = false)
			System.out.println(cust_name+" Not found.");

	}

	public void booking(String name, List<Data> al) {
		for (Data user : al) {
			if (user.getName().equals(name)) {
				System.out.println("Enter the day :");
				int day = sc.nextInt();
				System.out.println("Enter the month :");
				int month = sc.nextInt();
				System.out.println("Enter the year :");
				int year = sc.nextInt();
				String date = (day+"/"+month+"/"+year);
				System.out.println("Date is "+date);
				user.setDate(date);
				
				System.out.println("Booking of "+name+" Confirmed. "+user.getDate());
				flag = true;
			}
		}
		if(flag = false)
			System.out.println(name+" Not found.");
	}

	public void booking_date(String b_date, List<Data> al) {
		System.out.println("Cust_Id\tCust_Name");
		int count = 0;
		for (Data user : al) {
			try {
				if (user.getDate().equals(b_date))
					System.out.println(user.getId() + "\t" + user.getName());
				count++;
			} catch (Exception e) {
				
			}
			flag =true;
		}
	System.out.println("Number of attendees : "+ count);
	if(flag = false)
		System.out.println(b_date+" Not found.");
	}
	

	public void save_data(int day,int month,int year, List<Data> al) throws IOException {
		
		String date2 = (day+"/"+month+"/"+year);
		for (Data user : al) {
			try {
				if (user.getDate().equals(date2))
					al3.add(user);
				System.out.println("Data Add Succesfully");
			} catch (Exception e) {
				
			}
		}
		
		try {
			String path = ("src/main/resources/"+day+"-"+month+"-"+year+".csv");
					File file = new File(path);
					file.createNewFile();
					FileWriter writer = new FileWriter(path);
					writer.append("Cust_ID ,Cust_Name,Booking Data,\n");
					for (Data list : al3) {
						writer.append(list.getId());
						writer.append(",");
						writer.append(list.getName());
						writer.append(",");
						writer.append(list.getDate() + "\n");
					}
					writer.close();
					}catch (Exception e) {
						// TODO: handle exception
					}
	}

}
