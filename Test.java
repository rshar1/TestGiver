import java.util.ArrayList;
import java.util.Scanner;
class Test{
	int score;
	ArrayList<Question> questions;
	int numquestions;
	private Scanner input;
	private String tracker;
	
	//Constructor
	
	public Test(){
		numquestions=0;
		score=0;
		questions=new ArrayList<Question>();
		input=new Scanner(System.in);
		tracker="";
	}

	
	//Adds the entire object question which contains the choices and the correct answer choice
	void addQuestion(Question q){
		q.setPosition(numquestions);
		questions.add(q);
		numquestions++;
	}

	void shuffleQuestions(){
		ArrayList<Question> shuffledquestions=new ArrayList<Question>();
		int ct=0;
		for(int x=0; x<numquestions; x++){
			ct=(int)(Math.random() * questions.size());
			shuffledquestions.add(questions.get(ct));
			questions.remove(ct);
		
		}
	
	questions=shuffledquestions;
	}

	public void testStudent(){
		for(int x=0;x<questions.size();x++){
			questions.get(x).shuffleChoices();
		
		}
		boolean studentdone=false;
		String ct="";
		Question q=new Question();
		int x=-1;
		int d=1;
		updateTracker();
		int p;
		
		while(!studentdone){
			
			if(d==-1 && findOmit(x,d)==-1){
				System.out.println("YOU HAVE ALREADY ANSWERED ALL OF THE PREVIOUS QUESTIONS. TO REVIEW A QUESTION YOU HAVE ALREADY ANSWERED TYPE THE QUESTION NUMBER");
				
			}
			else
				if(!isNumber(ct))
					x=findOmit(x,d);
				
			
			
				System.out.println(tracker+questions.get(x).ask(x+1));
				ct=input.nextLine();
			
			d=0;
				//while it is a number you will constantly load the numbered question
				if(isNumber(ct)){
					if(Integer.parseInt(ct)<=questions.size() && Integer.parseInt(ct)>0){
						x=Integer.parseInt(ct)-1;
						continue;
					}else
						System.out.println("There is no question for that number");
					continue;
					
						
				}
			
				if(ct.toUpperCase().equals("PREV")){
					d=-1;
					continue;
				}
			
				else if(ct.toUpperCase().equals("DONE")){
					studentdone=true;
					
			
				}
				else if(!keywordUsed(ct)){
					d=1;
					questions.get(x).setUserChoice(ct);
					updateTracker();
					
				}
				
				if(areAllAnswered()){
					if(!ct.toUpperCase().equals("DONE")){
					System.out.println(tracker+"You have answered all the questions!! \nEnter keyword DONE to end the test or enter a question number to review it.");
					ct=input.nextLine();
						if(ct.toUpperCase().equals("DONE")) studentdone=true;
						continue;
						
					}
					
				
				
				}
				else if(findOmit(x,d)==-1 ){
					System.out.println("You have left several questions blank. Going back. If you would like to submit test enter Keyword DONE");
					x=-1;
					d=1;
				}
				if(ct.equals("")){
					d=1;
				}
		}
	}
	
	
	public String showUniqueResults(){
		
		String s="\n\nYour personal shuffled Results:\n\n#  Choice   Key       Original Question #\n";
		int n=1;
		for(Question q : questions){
			
			s+=n+"    "+ q.getUserChoice() +"     "+ q.getNewCorrectChoice()+"                "+(q.getPosition()+1)+"\n";
		
		
		n++;
		
		
		
		}
		
		return s;
	
	
	}
	
	public String showResults(){
		
		String s="\n\nYour results rearranged for Class Review:\n\n#  Choice   Key\n";
		int n=1;
		for(Question q : questions){
			
			if(q.checkCorrect()){
				score++;
			}
			s+=n+"    "+ q.getUserChoice() +"     "+ q.getCorrectChoice()+"\n";
		
		
		n++;
		
		
		
		}
		s+="\n\n"+"Grade: "+score+" / "+numquestions+" =  "+((score/(double)(numquestions))*100)+" % ";
		return s;
	
	
	}
	
	
	public void sortQuestions(){
	
		ArrayList<Question> sortedquestions=new ArrayList<Question>();
		int ct=0;
		for(Question q : questions){
			ct=0;
			while(ct<sortedquestions.size() && q.getPosition()>sortedquestions.get(ct).getPosition()){
				ct++;
			
			}
			
			sortedquestions.add(ct,q);
		}
		questions=sortedquestions;
	
	
	
	
	
	}
	
	//Tracker constantly gets updated to show what the student has put in as there choice as well as what theyve ommited at any given time during the test
	
	public void updateTracker(){
		tracker="\n\n";
		//Makes the numbers on the top of the tracker. ex 1   2    3    4    5...
		for(int x=1;x<=questions.size();x++){
			tracker += x + "    ";
		}
		//Make a new line for the tracker
		tracker += "\n";
		//Check for each answer what the users choice is and display it. if the user has not given an answer to a question yet, place a dash. If the user skipped the question place an omit.
		for(Question q: questions){
			tracker += q.getUserChoice()+"    ";
		}
		
		tracker += "\n\n";
		
		
	}
	
	boolean isNumber(String test){
		
		try{
			Integer.parseInt(test);
		}
		catch(NumberFormatException e){
			return false;		
				
		}
		return true;
	
	}
	
	
	//return the next empty question. If none are empty than it will return -1 or size().
	public int findOmit(int start, int direction){
		int x=start;
		//Moves the starting point if possible, otherwise return -1.
		if(x+direction>=0 && x+direction<questions.size()){
			x+=direction;
		}	
		else return -1;
		
		/*Until you find an empty question:
			
			check that you wont leave the index
		
		*/
		
		while(!questions.get(x).checkOmit()){
		
			if(x+direction<0 || x+direction==questions.size()){
				return -1;
			}
			else x+=direction;
		
		}
		
		return x;
		
		
	}
	
	boolean keywordUsed(String word){
		if(isNumber(word) || word.toUpperCase().equals("PREV") || word.toUpperCase().equals("DONE")){
			return true;
		}
		return false;
	}
	
	
	boolean areAllAnswered(){
		for(int x=0;x<questions.size();x++){
			if(questions.get(x).checkOmit()){
				return false;
			}
		}
			
		return true;
		
	
	}
	
	
}
	
	
	
	











