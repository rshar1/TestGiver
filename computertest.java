import java.util.Scanner;
class computertest{
	public static void main(String[] args) throws Exception{
	
		java.io.File file=new java.io.File("testquestions.txt");
		Scanner input=new Scanner(file);
		String ct="";
		String cquestion="";
		int cposition=0;
	
	
		boolean choicesdone=false;
		Test t=new Test();
		boolean questiondone=false;
		
		while(input.hasNext()){
			while(!questiondone){
				ct=input.nextLine();
				
				
				if(ct.equals("//"))
					questiondone=true;
				else
				cquestion+="\n"+ct;
				
				
			}
			
			Question q=new Question();
			q.setQuestion(cquestion);
			cquestion=""; questiondone=false; cposition=65;
			while(!choicesdone){
				ct=input.nextLine();
				
				if(ct.equals("//"))
					choicesdone=true;
				else
				q.addChoice(ct,((char)(cposition)));
				cposition++;
				
			}
			choicesdone=false;
			q.setCorrectChoice(input.nextLine());
			t.addQuestion(q);
			
			
		}
		
		
		t.shuffleQuestions();
		t.testStudent();
		System.out.println(t.showUniqueResults());
		t.sortQuestions();
		
		System.out.println(t.showResults());
		input.close();
		
		
		
		
	}


	public static String input(String msg){
		System.out.println(msg);
		Scanner sc=new Scanner(System.in);
		return sc.nextLine();
	
	}


}