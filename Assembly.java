// NURUL ANIS AFIQAH BINTI MOHD ARIFF
// 148279
// ASSIGNMENT 1_CPT212

//  A java program of assembly line scheduling
//	to find best minimal time using dynamic programming

package assign1_148279;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.io.File; 		// import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Assembly {
	
	// recursion method!
	static int min(int a, int b) 
	{ 
		return a < b ? a : b; // comparison final time T1 or T2
	}
	
	
	static int assembly(int n, int a[][], int t[][], int e[], int x[], int counter)
	{
		
		// definition of dynamic programming tables
		int T1[]= new int [n]; // for assembly line 1, station n
		int T2[] =new int[n] ; // for assembly line 2, station n
		int i; // line
		
		counter = counter + 10;
		
		// adding base case times
		// entry to first station
		T1[0] = e[0] + a[0][0]; // time taken to leave first station on line 1 
		T2[0] = e[1] + a[1][0]; // time taken to leave first station on line 2 
		
		counter = counter + ((3*n)-1);
		
		// Filling the dynamic programming tables T1[] and T2[] using recursive relations
		for (i = 1; i < n; i++)
		{
			counter = counter + 12;
			T1[i] = min(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i]); // on station i of assembly line 1
			
			counter = counter + 12;
			T2[i] = min(T2[i - 1] + a[1][i], T1[i - 1] + t[0][i] + a[1][i]); // on station i of assembly line 2
		}
		
		counter = counter + (10*(n-1));
		
		System.out.println("Number of primitive operations: " + counter); 
	
		// finding final times and returning the minimum value 
		return min(T1[n-1] + x[0], 
					T2[n-1] + x[1]);
		
	}
	
	
	// Driver code
	public static void main (String[] args) throws IOException
	{
	
	   // Number of assembly line = 2
	   int n; 							// number of station
	   int e[] = new int[5];			// entry time
	   int x[] = new int[5];			// exit time
	   int a[][] = new int[20][20];		// processing time
	   int t[][] = new int[20][20];		// transfer time
	   
	   // initialize counter
	   // to calculate number of primitive operations performed
	   int counter = 0;
	   
	   File myFile = new File("outputFile.txt");	 // file object = myFile
	   FileWriter fw = new FileWriter(myFile, true); // filewriter object = fw
	   PrintWriter pw = new PrintWriter(fw);		 // printwriter object = pw
				
		// input number of stations
		Scanner num = new Scanner(System.in);
		System.out.println("Enter the number of stations : ");
		n = num.nextInt();
			    
		// append to file
		pw.println("Number of stations: " + n);
			    
			    
		// random generate input for entry time
		Random entry = new Random(); // random object
		for(int k=0; k<2; k++) {	 // 2 = number of assembly line
			e[k] = entry.nextInt(5); // storing random integers in an array
			System.out.println("Line: " + (k+1));
			System.out.println("Entry time: " + e[k] + "\n");
				    	
			// append to file
			pw.println("Line: " + (k+1));
			pw.println("Entry time: " + e[k] + "\n");
		}
			    
			    
		// random generate processing time at all station on assembly line 1 and 2
		Random processing = new Random(); 
			    
		for(int i=0; i<2; i++) { 	// 2 = number of assembly line
			for(int j=0; j<n; j++) {
				a[i][j] = processing.nextInt(20); 
					    	
				System.out.println("Line: " + (i+1));
			    System.out.println("Processing time at station: " + (j+1) + " = " + a[i][j] + "\n");
					    	
				// append to file
			    pw.println("Line: " + (i+1));
			    pw.println("Processing time at station: " + (j+1) + " = " + a[i][j] + "\n");
			}
		}
			  
			    
		// random generate transfer time at all station on assembly line 1 and 2
	    Random transfer = new Random(); 
			    
		for(int i=0; i<2; i++) { 	// 2 = number of assembly line
			for(int j=0; j<n; j++) {
				t[i][j] = transfer.nextInt(20); 
				System.out.println("Line: " + (i+1));
				System.out.println("Extra time at station: " + (j+1) + " = " + t[i][j] + "\n");
				    	
				// write to file
				pw.println("Line: " + (i+1));
				pw.println("Extra time at station: " + (j+1) + " = " + t[i][j] + "\n");
				    	
			}
		}
			    
		// random generate input for exit time
		Random exit = new Random(); 
		for(int l=0; l<2; l++) { 	// 2 = number of assembly line
			x[l] = exit.nextInt(5); 
			System.out.println("Line: " + (l+1));
			System.out.println("Exit time: " + x[l] + "\n");
			    	
			// append to file
			pw.println("Line: " + (l+1));
			pw.println("Exit time: " + x[l] + "\n");
		}
			    
			    
		System.out.println("The minimum time taken is: " + assembly(n, a, t, e, x, counter)); 
		    
		// append to file
		pw.println("The minimum time taken is: " + assembly(n, a, t, e, x, counter));
			    
		// close the file
		pw.close();
	}
}
