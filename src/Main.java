import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		int choice;
		Scanner sc=new Scanner(System.in);
		do
		{
		System.out.println("\n\n\t--------------------------------------------------------------------------");
		System.out.print("\n\tEnter 1.Bank\n\t2.Atm\n\t3.Exit\n");
		System.out.print("\n\tEnter your choice:");
		choice =sc.nextInt();
		switch(choice) {
		case 1:
			do
			{
			System.out.println("\t--------------------------------------------------------------------------");
			System.out.print("\n\tEnter 1.Create Account\n\t2.Deposite\n\t3.Transfer\n\t4.Make Atm Card\n\t5.Exit\n" );
			System.out.print("\n\tEnter your choice:");
			choice =sc.nextInt();
			switch(choice){
			case 1:
				CreateAccount ca=new CreateAccount();
				ca.createAccount();
				break;
			case 2:
				Deposite de=new Deposite();
				de.depositeAmount();
				break;
			case 3:
				Transfer tr=new Transfer();
				tr.transferMoney();
				break;
			case 4:
				GetAtm ga=new GetAtm();
				ga.makeAtm();
				break;
			case 5:
				break;
			default:
				System.out.println("\n\tInput Correct Choice.");
			}
			}while(choice!=5);
			break;
		case 2:
			AccessAtm aa=new AccessAtm();
			int a=aa.accessAtm();
			if(a==1) {
				do
				{
				System.out.println("\t--------------------------------------------------------------------------");
				System.out.println("\n\tEnter 1.Account Information\n\t2.WithDraw Money\n\t3.MiniStatement\n\t4.Change Pin\n\t5.Exit");
				System.out.print("\n\tEnter your choice:");
				choice =sc.nextInt();
				switch(choice) {
				case 1:
					AccountInformation ai=new AccountInformation();
					ai.accountInformation();
					break;
				case 2:
					WithDraw wd=new WithDraw();
					wd.withDrawMoney();
					break;
				case 3:
					MiniStatement ms=new MiniStatement();
					ms.miniStatement();
					break;
				case 4:
					ChangePin cp=new ChangePin();
					cp.changePin();
					break;
				case 5 :
					break;
				default:
					System.out.println("\n\tInput right Choice.");
				}
				
			}while(choice!=5);
			}
			break;
		case 3:
			System.out.println("Thank you for using Service");
			System.exit(0);
		default:
			System.out.print("\n\tWrong Input.\n");
			
		}
		}while(choice!=3);
	
	}
}
