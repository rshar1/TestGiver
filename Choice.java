class Choice{
	String msg;                               //contains the message in the choice or what the choice says
	char letter;							//Contains the letter that represents the choice at any given time
	char originalletter;					//Contains the letter that was representing this choice at the start of the program
	
	//Constructor
	
	Choice(String message,char original){
		msg=message;
		originalletter=original;
	
	}
	
	//Mutators
	void setLetter(char letter){ this.letter=letter; }
	
	void setmsg(String message){ msg=message; }
	
	
	
	
	
	
	
	
	//Accessors
	char getLetter(){ return letter; }
	
	String getmsg() { return    msg; }
	
	char getOriginalLetter() { return originalletter; }
	
	
	//This will find the amount that the letter changed when the choices were shuffled and will return the difference. If difference is subtracted  from user choice you will now have the ordered choices.
	int findLetterTranslation(){
		int x;
		int y;
		x=(int)(letter);
		y=(int)(originalletter);
		return x-y;
	
	}
	
	
	
	
	
	
}