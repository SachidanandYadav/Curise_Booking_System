package Test.Curise_Booking_System;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;


public class App {
	public static void main(String[] args) throws IOException, CsvValidationException {
		Scanner sc = new Scanner(System.in);
		List<Data> al = new ArrayList<Data>();
		All_Method obj = new All_Method();
		int choice;
		boolean flag = false;
		do {
			System.out.println("============ MAIN MENU =============");
			System.out.println("\n* Select any one Option *");
			System.out.println("[1]Read customer data into list form file.\n[2]Display all customer id and name frome list."
					+ "\n[3]Search for customer in list.\n[4]Allow customer to book cruise."
					+ "\n[5]View customer who booked crusie.\n[6]Save customer to file."
					+ "\n[0]Exit Enter your option:");
			System.out.print("Enter your Choice : ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:		
				System.out.println("Enter the name of the data file :");
				String name1 = sc.next();
					List<Data> data = obj.read_file(name1);
					al.addAll(data);
					flag = true;
				break;
			case 2:
				if(flag == true)
				obj.display(al);
				else
					System.out.println("First Read the customer data .");
				break;
			case 3:
				if(flag == true) {
					System.out.println("[1].Search by ID\n[2].Search by Name.");
					int ch = sc.nextInt();
					if(ch == 1) {
						System.out.println("Enter the customer id");
						String cust_id = sc.next();
						obj.searchByid(cust_id,al);
					}else if(ch == 2){
						System.out.println("Enter the customer name");
						String cust_name = sc.next();
						obj.searchByname(cust_name,al);
					}else {
						System.out.println("Enter valid option.");
					}
				
				}else {
					System.out.println("First Read the customer data .");
				}
				break;
			case 4:
				if(flag == true) {
				System.out.println("Enter the name :");
				String name = sc.next();
				obj.booking(name,al);
				}else {
					System.out.println("First Read the customer data .");
				}
				break;
			case 5:
				if(flag == true) {
				String date = obj.date_valid();	
				obj.booking_date(date,al);
				}else {
					System.out.println("First Read the customer data .");
				}
				break;
			case 6:
				if(flag == true) {
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
				obj.save_data(day,month,year,al);
				}else {
					System.out.println("First Read the customer data .");
				}
			break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("Enter valid number.");
				break;
			}
		} while (choice != 0);

	}
}
