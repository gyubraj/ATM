import java.util.*;
import java.sql.*;
public class CreateAccount{
	void createAccount() throws Exception{
		int anumber=0,number,time;
		Scanner sc=new Scanner(System.in);
		Connection cn=DatabaseConnection.database();
		Statement stat=cn.createStatement();
		Random rd=new Random();
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\tFirst name:");
		String fname=sc.nextLine();
		System.out.print("\n\tLast name:");
		String lname=sc.nextLine();
		System.out.print("\n\tAddress:");
		String address =sc.nextLine();
		System.out.print("\n\tGender:");
		char gender=sc.next().charAt(0);
        sc.nextLine();
        int i=1;
        do {
		System.out.print("\n\tContact:");
		String contact =sc.nextLine();
		Boolean check=true;
		try {
			Double checkContact = Double.parseDouble(contact);
		}
		catch(NumberFormatException e) {
			check=false;	
		}
		if(check)
		{
			i=4;
		System.out.print("\n\tAccount Number:");
		String query="Select account_no from accountinfo";
		ResultSet rs=stat.executeQuery(query);
		label:
			do {
				number=rd.nextInt(10000000)+1000000;
				while(rs.next()) {
				anumber=rs.getInt("account_no");
				if(anumber==number)
					continue label;
			}
				
			}while(number==anumber);
		System.out.println(number);
		time=1;
		do {
			
		System.out.print("\n\tEnter Bank pin");
		int bankPin=sc.nextInt();
		System.out.print("\n\tConfirm Bank pin:");
		int confirmPin=sc.nextInt();
		if(bankPin==confirmPin) {
			System.out.println("\t\t\tAccount Created Successfully:");
			System.out.println("\n\tFill these Question for your Account recover Process.");
			sc.nextLine();
			System.out.print("\n\tYour Pet name:");
			String petname=sc.nextLine();
			System.out.print("\n\tYour Favourite Food:");
			String foodname=sc.nextLine();
		String sql="insert into accountinfo(firstname,lastname,account_no,bankpin,gender,address,contact,petname,food) values ('"+fname+"','"+lname+"',"+number+","+bankPin+",'"+gender+"','"+address+"','"+contact+"','"+petname+"','"+foodname+"')";
		stat.executeUpdate(sql);
		time=4;
		
		}
		else {
			System.out.println("\n\tSorry your pin doesn't match.Try again.\n");
			System.out.println("\tYou can try "+(3-time)+" times.");
			time++;
		}
		}while(time!=4);
		}
		else {
			i++;
			System.out.println("\n\tSorry your Contact is not valid.Please Enter again.\n");
			if(i==4)
				   System.out.println("\n\tYou entered Contact 3 times Wrong.Back to Menu.");
			
		}
        }while(i<4);
     
		
	}

}
