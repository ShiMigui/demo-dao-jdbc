package application;

import java.util.Date;

import entities.Department;
import entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department d = new Department(1, "Computers");
		Seller s = new Seller(1, "Miguel Nascimento", "miguel.nasto@gmail.com", new Date(), 2500.00, d);
		
		System.out.println(s);
	}

}
