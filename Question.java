import java.util.ArrayList;
class Question{
	private String q;
	private ArrayList<Choice> choices;
	private char correctchoice;
	private char userchoice;
	private int originalposition;
	private char newcchoice;
	
	Question()
	{
		q="";
		choices=new ArrayList<Choice>();
		correctchoice='-';
		userchoice='-';
		originalposition=0;
	
	}
	
	void setQuestion(String question){
		q=question;
	}
	
	char getNewCorrectChoice(){return newcchoice;}
	
	
	
	void addChoice(String choice,char original){
		choices.add(new Choice(choice,original));
		
	}
	
	void setPosition(int position){
		originalposition=position;
	}
	
	int getPosition(){
		return originalposition;
	}
	
	
	void setCorrectChoice(String answer){
		correctchoice=answer.toUpperCase().charAt(0);
	}

	void setUserChoice(String uc){
		if(!uc.equals(""))
		userchoice=uc.toUpperCase().charAt(0);
	}
	
	boolean checkOmit(){
		if(userchoice=='-'){return true; }return false;
	}
	
	
	boolean checkCorrect(){
		convertUserChoice();
		if(correctchoice==userchoice)
			return true;
		return false;
	}

	String getQuestion(){
		return q;
	
	
	}
	String getChoice(int index){
		return choices.get(index).getmsg();
	
	}
	
	int getNumChoices(){
		return choices.size();
	
	}

	String ask(int num){
		String s="Question  "+num+":\n"+ q +"\n-------------------------------------------------------\n";
		for(Choice c : choices){
			s+="\n"+c.getLetter() +")   "+c.getmsg();
		
		}
		return s;
	}

	char getCorrectChoice(){return correctchoice;}
	
	char getUserChoice(){return userchoice;}



	
	
	void shuffleChoices(){
		//test variable
		newcchoice=correctchoice;
		boolean tmp=false;
		ArrayList<Choice> shuffledchoices=new ArrayList<Choice>();
		int ct=0;
		int numchoices=choices.size();
		for(int x=0; x<numchoices; x++){
			ct=(int)(Math.random() * choices.size());
			
			
			if(!tmp){
			if(65<((int)(newcchoice))-ct){
				newcchoice--;
			}
			else if(65==((int)(newcchoice))-ct){
				newcchoice=(char)(shuffledchoices.size()+65);
				tmp=true;
			}
			}
			
			
			
			choices.get(ct).setLetter((char)(   65 + shuffledchoices.size()   ));
			shuffledchoices.add(choices.get(ct));
			choices.remove(ct);
		
		}
		
		choices=shuffledchoices;
	}
	
	
	
	public void convertUserChoice(){
		if(userchoice != '-'){
		int uc=(int)(userchoice);
		uc-=65;
		userchoice=(char)(((int)(userchoice)) - choices.get(uc).findLetterTranslation());
		}
		
		
	}
	
















}