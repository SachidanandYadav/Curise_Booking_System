package Test.Curise_Booking_System;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class All_Method {
	Scanner sc = new Scanner(System.in);

	boolean flag = false;

	public List<Data> read_file(String name1) {
		List<Data> al = new ArrayList<Data>();
		try {
			int count = 0;
			String path = ("src/main/resources/" + name1 + ".csv");
			// String path = ("src/main/resources/Customer_Data.csv");
			CSVReader reader = new CSVReader(new FileReader(path));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if((nextLine[2].endsWith(".com") || nextLine[2].endsWith(".in")) && nextLine[2].contains("@")) {
				Data user = new Data();
				user.setId(nextLine[0]);
				user.setName(nextLine[1]);
				user.setEmail(nextLine[2]);
				al.add(user);
				count++;
				}
			}
			System.out.println("Number of record read : " + count);
		} catch (Exception e) {
			System.out.println(name1 + " Not Found . Enter Valid name.");
		}
		return al;
	}

	public void display(List<Data> al) {

		System.out.println("All Customer Data.");
		System.out.println("Cust_Id\tCust_Name\tCust_Email");
		for (Data user : al)
			System.out.println(user.getId() + "\t" + user.getName()+ "\t" + user.getEmail());
	}

	public void searchByname(String cust_name, List<Data> al) {
		int count = 0;
		System.out.println("Cust_Id\tCust_Name");
		for (Data user : al) {
			if (user.getName().equals(cust_name)) {
				System.out.println(user.getId() + "\t" + user.getName());
				count++;
			}
		}
		if (count == 0)
			System.out.println(cust_name + " Not found.Enter Valid name.");
	}

	public void searchByid(String cust_id, List<Data> al) {
		int count = 0;
		System.out.println("Cust_Id\tCust_Name");
		for (Data user : al) {
			if (user.getId().equals(cust_id)) {
				System.out.println(user.getId() + "\t" + user.getName());
				count++;
			}
		}
		if (count == 0)
			System.out.println(cust_id + " Not found.Enter Valid name.");
	}

	public void booking(String name, List<Data> al) {
		All_Method obj = new All_Method();
		int count = 0;
		for (Data user : al) {
			if (user.getName().equals(name)) {
				String date = obj.date_valid();
				System.out.println("Date is " + date);
				user.setDate(date);

				System.out.println("Booking of " + name + " Confirmed. " + user.getDate());
				count++;
			}
		}
		if (count == 0)
			System.out.println(name + " Not found.");
	}

	public void booking_date(String date, List<Data> al) {
		int count1 = 0;
		System.out.println("Cust_Id\tCust_Name");
		int count = 0;
		for (Data user : al) {
			try {
				if (user.getDate().equals(date)) {
					System.out.println(user.getId() + "\t" + user.getName());
					count++;
					count1++;
				}
			} catch (Exception e) { }
		}
		if (count == 0) 
			System.out.println(date + " Not found.");
			
		System.out.println("Number of attendees : " + count);
	}

	public void save_data(int day, int month, int year, List<Data> al) throws IOException {

		String date2 = (day + "/" + month + "/" + year);
		List<Data> al3 = new ArrayList<Data>();
		for (Data user : al) {
			try {
				if (user.getDate().equals(date2))
					al3.add(user);
			} catch (Exception e) { }
		}
		try {
			String path = ("src/main/resources/" + day + "-" + month + "-" + year + ".csv");
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
		} catch (Exception e) {
			System.out.println("Something Wrong.");
		}
		System.out.println("Data Stored in Excle File.");
	}
	
	
	public String date_valid() {
		System.out.println("Enter Year in YYYY Formate");
		int year = sc.nextInt();
		while (year < 1000 || year > 2022) {
			System.out.println("Enter Valid year");
			year = sc.nextInt();
		}
		System.out.println("Enter Month in MM Formate");
		int month = sc.nextInt();
		while (month > 12 || month <= 0) {
			System.out.println("Enter Valid month");
			month = sc.nextInt();
		}
		System.out.println("Enter Day in DD Formate");
		int day = sc.nextInt();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			while (day <= 0 || day > 31) {
				System.out.println("Enter Valid Day");
				day = sc.nextInt();
			}
		} else if (month == 2) {
			if (year % 4 == 0) {
				while (day <= 0 || day > 29) {
					System.out.println("Enter Valid Day");
					day = sc.nextInt();
				}
			} else {
				while (day <= 0 || day > 28) {
					System.out.println("Enter Valid Day");
					day = sc.nextInt();
				}
			}

		} else {
			while (day <= 0 || day > 30) {
				System.out.println("Enter Valid Day");
				day = sc.nextInt();
			}
		}

		String date = (day + "/" + month + "/" + year);
		return date;
	}
}
