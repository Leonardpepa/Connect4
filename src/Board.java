import java.util.*;

public class Board {
	/*ιδιοτητες του πλεγματος του παιχνιδιου μας
	 * η γραμμες , η στηλες και ενας  δισδιαστατος πινακας  με μεγεθος γραμμες επι στηλες
	 */
	private int rows;
	private int colums;
	private char grid[][];
	

	/*ο κατασκευαστης του πλεγματος εχει σαν ορισμα ενα αντικειμενο Scanner 
	 * επειδη καλει την μεθοδο που διαβαζει και αρχικοποιει τις γραμμες και τις στηλες
	 * επισης καλει και την μεθοδο που αρχικοποιει τον πινακα μας
	 */ 
	public Board(Scanner scan) {
		read_rows_colums(scan);
		this.grid = new char[rows][colums];
		makeboard();
		
	}
	
	/*εχει σαν ορισμα ενα αντεικειμενο Scanner 
	 * διαβαζει και ελεγχει αν αν οι γραμμες και οι στηλες ειναι 
	 * μεγαλυτερες ή ισες του 4 και μικροτερες η ισες του 15
	 */
	public void read_rows_colums(Scanner scan) {
		System.out.print("Please enter the number of rows:");
		while (!scan.hasNextInt()) {
			scan.next();
		System.out.println("wrong input enter an integer");	
		}
		this.rows = scan.nextInt();
		while(rows<4 || rows>15) {
			System.out.println("The numbers of rows can be >= to 4 and <= 15");
			while (!scan.hasNextInt()) {
				scan.next();
			System.out.println("wrong input enter an integer");	
			}
			this.rows = scan.nextInt();
		}
		System.out.print("Please enter the number of colums:");
		while (!scan.hasNextInt()) {
			scan.next();
		System.out.println("wrong input enter an integer");	
		}
		this.colums = scan.nextInt();
		
		while(colums<4 || colums>15) {
			System.out.println("The numbers of colums can be >= to 4 and <= 15");
			while (!scan.hasNextInt()) {
				scan.next();
			System.out.println("wrong input enter an integer");	
			}
			this.colums = scan.nextInt();
		}
		
		
	}
	
	
	//αρχικοποιει τον πινακα με παυλες που στο παιχνιδι μας σημαινει οτι ειναι κενος
	public void makeboard() {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<colums; j++) {
				grid[i][j]='-';
			}
			
		}
	}
	//εμφανιζει στην οθονη τον πινακα 
	public void display() {
		//εμφανιση πινακα
		for(int i=0; i<rows; i++ ) 
		{
			System.out.print("|");
			for(int j=0; j<colums; j++) 
			{
				if(colums > 10) {
					System.out.print(" " + grid[i][j]+"  ");
				}
				else {
				System.out.print(grid[i][j]+" ");
				}
			}
			System.out.println("|");
			
		}
		//εμφανιση παλας κατω απο τον πινακα 
		    if(colums > 10) {
			for(int i=0; i<(colums*2+2)*2;  i++) 
			{
				System.out.print("-");
			}
		   }
		    else {
		    	for(int i=0; i<(colums*2+2);  i++) 
				{
					System.out.print("-");
				}
		    }
			System.out.println();
			//εμφανιση αριθμου στηλης
			for(int i=1; i<=colums;  i++) 
			{
				if(i > 10 && i!=1) {
					System.out.print(" " + i + " " );
				}
				else if(colums>10) {
					System.out.print("  " + i + " ");
				}
				else System.out.print(" " + i);
				
			}
			System.out.println();
	}
	
	/*ελεγχοι αν η στηλη που επιλεχθηκε ειναι μεσα στα ορια 
	 * και αν δεν ειναι γεματη τοποθετη το chip του παιχτη
	 */
	public void putcoin(Player player,Scanner scan) {
		int column = scan.nextInt();
		
		while(column>this.colums || column-1<0  )
		{
			System.out.println("Out of boards bound Try again");
			column=scan.nextInt();
		}
			for(int i=rows-1; i>=0; i--)
			{
				if(grid[i][column-1]=='-')
				{
					grid[i][column-1]=player.getChip();
					break;
				}
				//αν φτασαμε στη  κορυφη της στηλης και δεν εχει γεμισει ο πινακας
				//προτρεπουμε τον παιχτη να επιλεξει αλλη στηλη αφου αυτη ειναι γεματη
				if(i==0 && !fullboard())
				{
					System.out.println("The colum is full please try another colum");
					//καλουμε ξανα την συναρτηση με τα ιδια ορισματα για να ξανα επιλεξει στηλη
					putcoin(player,scan);
				}
			}
			
	}
	
	
	//ελεχγος για το αν γεμισε ο πινακας
	public boolean fullboard() {
		int count=0;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<colums; j++) {
				if(grid[i][j]!='-') {
					count++;
				}
			}
		}
		if(count==rows*colums) {
			return true;
		}
		else return false;
	}
	
	//ελεγχος για το αν υπαρχουν 4 ιδια συμβολα στην ιδια οριζοντια σειρα
	public boolean horizontally_connected(Player player) {
		int count=0;
		for(int i=0; i<rows; i++) {
			count=0;
			for(int j=0; j<colums; j++) {
				if(grid[i][j]==player.getChip()) {
					count++;
				}
				else
				{
					count=0;
				}
				if(count==4) {
					return true;
				}
				
			}
		
		}
		
		return false;	
	}
	
	//ελεγχος αν υπαρχουν 4 ιδια συμβολα στην ιδια καθετη σειρα
	public boolean vertically_connected(Player player) {
		int count=0;
		for(int j=0; j<colums; j++) {
			count=0;
			for(int i=0; i<rows; i++) {
				if(grid[i][j]==player.getChip()) {
					count++;
				}
				else {
					count=0;
				}
				if(count==4) {
					return true;
					
				}
			}
			
		}
		return false;
		
	}
	// ελεγχος αν υπαρχουν 4 ιδια συμβολα διαγωνια 
	public boolean  diagonal_connected(Player player) {
		/*ελεγχοι αν υπαρχουν 4 ιδια συμβολα διαγωννια ξεκινοντας απο τα αριστερα κατω
		 *  προς τα δεξια πανω
		 */
		for(int i=3; i<rows; i++){
			for(int j=0;  j<colums-3; j++){
				if (grid[i][j] == player.getChip()   && 
					grid[i-1][j+1] == player.getChip() &&
					grid[i-2][j+2] == player.getChip() &&
					grid[i-3][j+3] == player.getChip()){
					return true;
				}
			}
		}
		/*ελεγχοι αν υπαρχνουν 4 ιδια συμβολα διαγωνια ξεκινοντας απο τα αριστερα πανω
		 * προς τα δεξια κατω
		 */
		for(int i=0; i<rows-3; i++){
			for(int j=0; j<colums-3; j++){
				if (grid[i][j] == player.getChip()   && 
					grid[i+1][j+1] == player.getChip() &&
					grid[i+2][j+2] == player.getChip() &&
					grid[i+3][j+3] == player.getChip()){
					return true;
				}
			}
		}
		return false;
		
		
	}
	//ελεγχει αν υπαρχει νικητης οριζοντια ,καθετα ή διαγωνια και επιστρεφει αληθης αν ισχυει
	public boolean is_winner(Player player) {
		return  (horizontally_connected(player) || vertically_connected(player) || diagonal_connected(player) );
	}
	
	
	public int getrows() {
		return rows;
	}
	
	public int getcolums() {
		return colums;
	}
	
}
