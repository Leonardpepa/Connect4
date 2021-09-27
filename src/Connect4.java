//ics 20033
import java.util.*;

public class Connect4 {

	public static void main(String[] args) {
		System.out.println("This is score4");
		
		//δημιουργια ενος  αντικειμενου scanner για τις εισαγωσες απο το πληκτρολογιο
		Scanner scan = new Scanner(System.in);
		
		// προτροπή των παιχτων να δωσουν το ονομα τους και διαβασμα των ονοματων  
		System.out.print("Please enter the name of the 1st player:");
		Player player1 = new Player(scan.nextLine());
		System.out.print("Please enter the name of the 2nd player:");
		Player player2 = new Player(scan.nextLine());
		System.out.println("The chip can be 'x' or 'o' ");
		
		/*προτροπη του πρωτου παιχτη να επιλεξει το chip του και επιλογη του chip 
		 *που απεμεινε στον δευτερο παιχτη*/
		System.out.print(player1.getName()+", please select your chip:");		
		player1.setChip(scan);
		player2.remained_chip(player1);
		System.out.println(player2.getName() + ", Your chip is:" + player2.getChip());
		// δημιουργια του ταμπλο του παιχνιδιου
		Board board = new Board(scan);
		
		/* τυχαια επιλογη του παιχτη που θα παιξει πρωτος χρησιμοποιωντας 
		 * την random.nextInt(2)  που παραγει τυχαια τους αριθμους 0 ή 1 
		 */
		Player player_to_move;
		Random random = new Random();
		int first_to_start=random.nextInt(2);
		
		if(first_to_start == 0) {
			player_to_move = player1;
		}
		else {
			player_to_move = player2;
		}
		
		
		/* δηλωση μιας boolean μεταβλητης ωστε να ειναι η τιμη φρουρος
		 *  στην επαναληψη του παιχνιδιου
		 */
		boolean play=true;
		while(play) {
			//ξεκιναει το παιχνιδι και εμφανιζει τον πινακα στην οθονη
			board.display();
			/*προτροπη στον παιχτη που παιζει να επιλεξη την στηλη
			 *στην οποια θα τοποθετηση το chip του και τοποθετηση του chip στην στηλη 
			 *με την βοηθεια της μεθοδου putcoint()
			 */
			System.out.print(player_to_move.getName() + ", your turn. Select column: " );
			board.putcoin(player_to_move, scan);
			System.out.println();
			/*ελεγχος για το αν υπαρχει νικητης με την μεθοδο is_winner
			 * ή αν εχει γεμισει ο πινακας και στις δυο περιπτωσεις 
			 * πρεπει να τερματιστει το παιχνιδι
			 * 
			 */
			if(board.is_winner(player_to_move) || board.fullboard()) {
				play = false;
				board.display();
			}
			//εναλλαγη παιχτων δηλαδη παιζει ο επομενος παιχτης
			player_to_move=player_to_move.switch_player(player1, player2);

		}
		
		/* αφου τερματιστει το παιχνιδι γινεται ο ελεγχος για το ποιος ειναι 
		 * ο νικητης και τον εμφανιζει αν δεν υπαρχει νικητης τοτε υπαρχει ισοπαλια 
		 */
		if(board.is_winner(player1)) {
			System.out.println(" GAME OVER. THE WINNER IS " + player1.getName());
		}
		else if(board.is_winner(player2)) {
			System.out.println(" GAME OVER. THE WINNER IS " + player2.getName());
		}
		else{
			System.out.println("GAME OVER. WE HAVE A DRAW");
		}
		scan.close();
		
	
	}
}
