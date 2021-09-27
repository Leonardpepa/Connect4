import java.util.*;
public class Player {
	//ιδιοτητες ενος παιχτη το ονομα του και το chip που θα επιλεξει
	private String name;
	private char chip;
	// ο κατασκευαστης ζηταει μονο το ονομα αφου το chip θα το επιλεξει μετα
	public Player(String name) {
		this.name=name;
		this.chip =' ';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getChip() {
		return chip;
	}
	
	/*παραποιση της μεθοδου set εκχωρει στον παιχτει το chipν που θα επιλεξει
	 * κανοντας ελεγχο εγκυροτητας 
	 */
	public void setChip(Scanner scan) {
		this.chip = scan.next().charAt(0);
		while(this.chip!= 'x' && this.chip!= 'o') {
			System.out.println("You can only choose 'x' or 'o' pls try again");
			this.chip = scan.next().charAt(0);
		}
		
		
	}
	
	/*αυτη η μεθοδος δεχεται ως ορισμα τον παιχτει που εχει επιλεξει chip
	 * και εκχωρει το chip που απεμεινε στον 2ο παιχτη
	 */
	public void remained_chip(Player player1) {
		if(player1.getChip()=='x') {
			this.chip='o';
		}
		else {
			this.chip='x';
		}
	}
	
	/*αυτη η μεθοδος δεχεται σαν ορισματα τους παιχτες που παιζουν 
	 * ελεγχει ποιανου παιχτη ειναι η σειρα να παιξει και τον επιστρεφει
	 */
	public Player switch_player(Player player1,Player player2) {
		if( this == player1) {	
			return player2;
		}	
		else
		{
			return player1;
		}
	}
	
	


	
	
	
	
	
	

}
