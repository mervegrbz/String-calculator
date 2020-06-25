import java.util.Scanner;

public class stringCalculation {

	public static void main(String[] args) {
		
		Scanner input=new Scanner(System.in);
		String line=input.nextLine();//user writes the variable name and type;
		String line2=input.nextLine();//user writes the variable name and type;
		String line3=input.nextLine();//user writes the variable name and type;
		String calculation=input.nextLine();//last scanner takes calculation from user.
		calculation=calculation.replaceAll(" ", "");//all spaces is deleted in order to take numbers precisely.
		calculation=replaceValues(line,calculation);//if calculations contains variables it replaces.
		calculation=replaceValues(line2,calculation);//if calculations contains variables it replaces.
		calculation=replaceValues(line3,calculation);//if calculations contains variables it replaces.
	
	System.out.println(findParanthesis( calculation));	
	}
	public static String replaceValues(String line,String problem) {//find given variable name in calculation expression then replace it 
																	//with numerical value
		String type=line.substring(0,line.indexOf(' '));  // define the given variable type.          
		String name=line.substring(line.indexOf(' '),line.indexOf('='));//take variable name.
		name=name.replaceAll(" ", "");//deletes spaces.
		
		String number=line.substring(line.indexOf('=')+1,line.indexOf(';'));//number is taken.
		number=number.replaceAll(" ", "");//deleetes all spaces from number.
			
		if(type.equals("double")) {//if the variable is double but not include "." this if puts ".".
			if(!(number.contains(".")))
				number+=".0";
		}
		if(problem.contains(name))//if calculation contains variable ,replace it with its numerical value.
			problem=problem.replace(name,number);
		
		return problem;
	}
public static String findParanthesis(String operation) {//this method finds the math. operation inside the parenthesis and replace it with result.
		
		while(operation.contains(")")) {//execute this loop until there is not any parenthesis.
			String line=operation.substring(0,operation.indexOf(')'));//it takes first ")".
			
			int operation_start=line.lastIndexOf("(");//it finds the "(" in line .
			
			String inside_paranthesis=line.substring(operation_start+1);// it takes the operation inside parenthesis.
			
			String result=multiplication_division(inside_paranthesis);//finds the value of inside_parenthesis.
			
			operation=operation.replace(operation.substring(operation_start,operation.indexOf(')')+1),result);	//replace mathematical expresssion with its value.
			
		}
		operation=multiplication_division(operation);//the last operation which does not contain parenthesis is solved.
		if(operation.contains(";"))//multiplication_division puts ";" each result to find last number so before printing result it deletes that";".
			operation=operation.substring(0,operation.indexOf(';'));
		
		return operation;	
	}
	
	public static String multiplication_division(String calculation) {
		
		for(int i=0; i<calculation.length(); i++) {//this loop executes to find mathematical operation.
		
			if(calculation.charAt(i)=='*') { // if there is a multiplication expression it continues from there.
				String number1 = calculation.substring(0,i);// it takes the all string before "*".
				String before_number1="";
				if(number1.contains("+")&&number1.contains("-")) {//if string number1 contains "-" and "+". it takes the last one`s index to find number1.
					if (number1.lastIndexOf('+')> number1.lastIndexOf('-')) {
						before_number1=number1.substring(0,number1.lastIndexOf('+'))+"+";
						number1=number1.substring(number1.lastIndexOf('+'));
					}
					else {
						before_number1=number1.substring(0,number1.lastIndexOf('-'));
						number1=number1.substring(number1.lastIndexOf('-'));
					}
				}
				else if(number1.contains("+")) {//if it only contains "+" it takes last index of "+" to find number1.
					before_number1=number1.substring(0,number1.lastIndexOf('+'))+"+";//it store the expression before number1.
					number1=number1.substring(number1.lastIndexOf('+'));	
				}
				else if(number1.contains("-")) {//if it only contains "-" it takes last index of "-" to find number1.
					before_number1=number1.substring(0,number1.lastIndexOf('-'));
					number1=number1.substring(number1.lastIndexOf('-'));
					
				}
					double num1number=Double.parseDouble(number1);//it finds the double value of number1 
				String result_string="";
				String number2=calculation.substring(i+1,1+i+findnumber(i,calculation));//it finds the number2.
				double num2number=Double.parseDouble(number2);//it finds the double value of number2.
					if(number1.contains(".")||number2.contains(".")) {//if any of number1 or number2 contains "." result should be double type.
						double result_of_calc=num2number*num1number;
						result_string=result_of_calc+"";//it converted double result to string type.
					}
					else {
						int result_of_calc=(int)num2number*(int)num1number;//if any of them is not double, it finds result int type.
						 result_string=result_of_calc+"";
					}
					
				calculation=calculation.replace(calculation.substring(0,i+1+findnumber(i,calculation)),result_string);// it replace math expressions with its result.
				calculation=before_number1+calculation;//it adds the string before result to calculation.
				
				i=0;//after this math calculation is done. it turns to first place and again search for  other calculations.	
			}
			if(calculation.charAt(i)=='/') { // if there is a division expression it continues from there.
				 
				String number1=calculation.substring(0,i);// it takes the all string before "/".
				String before_number1="";
				if(number1.contains("+")&&number1.contains("-")) {//if string number1 contains "-" and "+". it takes the last one`s index to find number1.
					if (number1.lastIndexOf('+')>number1.lastIndexOf('-')) {
						number1=number1.substring(number1.lastIndexOf('+'));
						
					}
					else {
						before_number1=number1.substring(0,number1.lastIndexOf('-'));
						number1=number1.substring(number1.lastIndexOf('-'));
					}	
				}
				else if(number1.contains("+")) {//if it only contains "+" it takes last index of "+" to find number1.
					before_number1=number1.substring(0,number1.lastIndexOf('+'))+"+";
					number1=number1.substring(number1.lastIndexOf('+'));
					
				}
				else if(number1.contains("-")) {//if it only contains "-" it takes last index of "-" to find number1.
					before_number1=number1.substring(0,number1.lastIndexOf('-'));
					number1=number1.substring(number1.lastIndexOf('-'));
					
				}
				double num1number=Double.parseDouble(number1);//it finds the double value of number1 
					
				String result_string="";//it converted double result to string type.
				String number2=calculation.substring(i+1,1+i+findnumber(i,calculation));//it finds the number2.
	
					double num2number=Double.parseDouble(number2);//it finds the double value of number2.
					if(number1.contains(".")||number2.contains(".")) {//if any of number1 or number2 contains "." result should be double type.
						double result_of_calc=num1number/num2number;
						
						result_string=result_of_calc+"";
					}
					else {//if any of them is not double, it finds result int type.
						int result_of_calc=(int)num1number/(int)num2number;
						 result_string=result_of_calc+"";
					}
				
				calculation=calculation.replace(calculation.substring(0,i+1+findnumber(i,calculation)),result_string);// it replace math expressions with its result.
				
				calculation=before_number1+calculation;//it adds the string before result to calculation.
				i=0;//after this math calculation is done. it turns to first place and again search for  other calculations.
					
			}	
		}
		calculation=substraction_addition(calculation);//after calculating all multiplication and division operation it sends calculation to subtraction_division method. 
		return calculation;
	}
	public static String substraction_addition(String operation) {
	
		for(int i=0; i<operation.length(); i++) {
			
			if(operation.charAt(i) == '+') {//it calculates addition operation just like multiplication.
				String number1 = operation.substring(0, i);	
				double num1number=Double.parseDouble(number1);
				String number2=operation.substring(i+1,1+i+findnumber(i,operation));
				String result_string="";
				double num2number=Double.parseDouble(number2);
				if(number1.contains(".")||number2.contains(".")) {
					double result_calc = num2number + num1number;
					result_string=result_calc+"";
				}
				else {
					int sonuc= (int)num2number + (int)num1number;
					 result_string=sonuc+"";
				}
			
				operation=operation.replace(operation.substring(0,i+1+findnumber(i,operation)),result_string);
			
				i=0;
			}
			if(operation.charAt(i) == '-') {//it calculates subtraction operation just like multiplication 
				String number1=operation.substring(0,i);
				double num1number=Double.parseDouble(number1);	
				String number2=operation.substring(i+1,1+i+findnumber(i,operation));	
				double num2number=Double.parseDouble(number2);
				String result_string="";
				if(number1.contains(".")||number2.contains(".")) {
					double result_calc=num1number-num2number;
					result_string=result_calc+"";
				}
				else {
					int result_calc=-(int)num2number+(int)num1number;
					 result_string=result_calc+"";
				}
			
				operation=operation.replace(operation.substring(0,i+1+findnumber(i,operation)),result_string);
				i=0;
			}
			}
		return operation;//in that operation string all calculations are done and only string value of result returns.	
		}
		
	public static int findnumber(int w, String operation) {// this method finds the index  of mathematical operation symbols and return that number.
		operation=operation.substring(w+1)+";";//it takes the string from the math symbol index to end.
		int count=1;
		for(int i=0;i<operation.length();i++) {//it search for any letter other than numbers.
			if((operation.charAt(i)=='*'||operation.charAt(i)=='+'||operation.charAt(i)=='-'||operation.charAt(i)=='/'||operation.charAt(i)==')'||operation.charAt(i)=='(')||operation.charAt(i)==';') {
				 count=i;//when it finds them its value is returned so that number2 last index can be found.
			break;//after finding first symbol it breaks.
			}	
		}
		return count;
	}

	}

