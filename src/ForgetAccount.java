import java.util.*;
import java.sql.*;
public class ForgetAccount {
        void forgetAccount() throws Exception {
        	Connection cn=DatabaseConnection.database();
        	Statement stat=cn.createStatement();
        	Scanner sc=new Scanner(System.in);
        	System.out.println("\n\n\t--------------------------------------------------------------------------");
        	System.out.print("\n\tInput your pet name:");
        	String petName=sc.nextLine();
        	System.out.print("\n\tYour Contact Number:");
        	String contact=sc.nextLine();
        	System.out.print("\n\tYour favourite food:");
        	String food=sc.nextLine();
        	String query="select * from accountinfo";
        	ResultSet rs=stat.executeQuery(query);
        	Boolean hasNext=rs.next();
        	while(hasNext)
        	{
        		String apetname=rs.getString("petname");
        		String acontact=rs.getString("contact");
        		String afood=rs.getString("food");
        		if(apetname.equals(petName) && acontact.equals(contact) && afood.equals(food)) {
        	query="select * from accountinfo where contact='"+contact+"' and petname='"+petName+"' and food='"+food+"'";
        	rs=stat.executeQuery(query);
        	rs.next();
        	int anumber=rs.getInt("account_no");
        	int pin=rs.getInt("bankpin");
        	System.out.println("\n\tYour Account Information is :");
        	System.out.println("\n\tAccount Number:"+anumber);
        	System.out.println("\n\tAccount Pin:"+pin);
        	break;
        		}
        		else {
        			hasNext=rs.next();
        			if(!hasNext)
        			System.out.println("\n\tWrong Input.Can't recover Account Details.");
        			
        		}
        	}
        	
        }
}
