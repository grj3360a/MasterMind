package chessquito;

public enum EtatPartie {
	NON_COMMENCEE, 
	EN_COURS, 
	NOIR_A_GAGNE,
	BLANC_A_GAGNE, 
	NULLE;

	public String getMessage() {
		switch(this) {
		case BLANC_A_GAGNE:
			return "Blanc a gagné !";
		case NOIR_A_GAGNE:
			return "Noir a gagné !";
		case NULLE:
			return "Partie nulle !";
		default:
			return null;
		
		}
	}
}
