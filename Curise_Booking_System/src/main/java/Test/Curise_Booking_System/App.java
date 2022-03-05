package Test.Curise_Booking_System;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, CsvValidationException {
		Scanner sc = new Scanner(System.in);
		List<Data> al = new ArrayList<Data>();
		All_Method obj = new All_Method();
		int choice, search_ac;
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
//				System.out.println("Enter the name of the data file :");
//				String name = sc.next();
					List<Data> data = obj.read_file();
					al.addAll(data);
				break;
			case 2:
				obj.display(al);
				break;
			case 3:
				System.out.println("Enter the customer name");
				String cust_name = sc.next();
				obj.search(cust_name,al);
				break;
			case 4:
				System.out.println("Enter the name :");
				String name = sc.next();
				obj.booking(name,al);
				break;
			case 5:
				System.out.println("Enter the date :");
				String b_date = sc.next();
				obj.booking_date(b_date,al);
				break;
			case 6:
				System.out.println("Enter the day :");
				int day = sc.nextInt();
				System.out.println("Enter the month :");
				int month = sc.nextInt();
				System.out.println("Enter the data :");
				int year = sc.nextInt();
				obj.save_data(day,month,year,al);
			break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("Enter valid number.");
			}
		} while (choice < 7);

	}
}
