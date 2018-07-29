package zipcodeproject.zipcode.exception;

public class ZipCodeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String str1;
	Exception e;
	public ZipCodeException(String str2,Exception eObj) {
		str1=str2;
		e=eObj;
	
	 }
	   public String toString(){
	     return ("Exception Occurred :  "+str1 + " and Exception is:"+e) ;
	  }
	}