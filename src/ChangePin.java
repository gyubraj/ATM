import java.util.*;
import java.sql.*;
public class ChangePin {
	void changePin() throws Exception{
	Scanner sc=new Scanner(System.in);
	AccessAtm aa=new AccessAtm();
	int anumber=aa.number;
	int apin=aa.pin;
	Connection cn=DatabaseConnection.database();
	Statement stat=cn.createStatement();
	int i=1;
	do {
		System.out.println("\n\n\t--------------------------------------------------------------------------");
	System.out.print("\n\tEnter your pin:");
	int pin=sc.nextInt();
	System.out.print("\n\tEnter New pin:");
	int newpin=sc.nextInt();
	System.out.print("\n\tConfirm New pin:");
	int confirmpin=sc.nextInt();
	if(apin==pin&&newpin==confirmpin) {
		String sql="update accountinfo set pin="+newpin+" where account_no="+anumber+"";
		stat.executeUpdate(sql);
		aa.pin=newpin;
		System.out.println("\n\tPin changed Success.");
		break;
		
	}
	else if(pin!=apin) {
		System.out.println("\n\tYour old pin is incorrect.Enter again:\n");
		i++;
	}
	else {
		System.out.println("\n\tYour new pin are not Matching.Enter again:\n");
		i++;
		
	}
	if(i==4)
		System.out.println("\n\tYou have tried 3 times.Back to menu.");
	
	}while(i<4);
	}
	

}
