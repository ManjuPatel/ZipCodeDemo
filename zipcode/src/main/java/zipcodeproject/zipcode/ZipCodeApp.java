package zipcodeproject.zipcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import zipcodeproject.zipcode.exception.ZipCodeException;
import zipcodeproject.zipcode.range.ZipCodeRange;
import zipcodeproject.zipcode.util.ZipCodeUtils;

public class ZipCodeApp 
{
    public static void main(String[] args) throws ZipCodeException{
    	String file = "C:/Users/manju-laptop/workspace/zipcode/src/main/resources/excludeZipCode.txt";
        List<ZipCodeRange> inputRanges = new ArrayList<>();
        /*
            - ranges would typically be read from database
            - for this demo, we allow ranges to be ready from a text file
        */

      try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            for (String line; (line = br.readLine()) != null;) {
                inputRanges.add(new ZipCodeRange(line));
            }
            br.close();
        }
        catch (IOException e) {
        	throw new ZipCodeException("This is My Custom Error Message",e);
        }
        
        List<ZipCodeRange> excludes = ZipCodeUtils.consolidate(inputRanges);
        System.out.println("\nConsolidated exclusion ranges:\n" + excludes);

        // if any command-line arguments, assume they are ZIP codes to test for exclusion, comparing against the
        // ranges read in from excludeZipCode.txt
        if (args.length > 0) {
            System.out.println();
            for (String arg : args) {
                if(ZipCodeUtils.isExcluded(arg, excludes)){
                	System.out.println("Can't deleiver to ZIP code " + arg);
                }
                else{
                	System.out.println("Can deliver to ZIP code " + arg);
                }
            }
        }
    }

}

