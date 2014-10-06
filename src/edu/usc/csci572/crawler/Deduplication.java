package edu.usc.csci572.crawler;

import java.util.ArrayList;
import java.util.Calendar;

import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarityTest;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.tokenisers.TokeniserQGram2;
import uk.ac.shef.wit.simmetrics.tokenisers.TokeniserQGram3;

public class Deduplication {
	
	public static void main(String[] args) {
		System.out.println(args[0].toLowerCase() + "  :  " + args[1].toLowerCase());
		System.out.println(getSimilarity(args[0].toLowerCase(),args[1].toLowerCase()));
	}
	
	public static boolean getSimilarity(String s1, String s2) {
		TokeniserQGram2 tokeniserToUse = new TokeniserQGram2();
		//JaccardSimilarity csObj = new JaccardSimilarity(tokeniserToUse);
		Levenshtein csObj = new Levenshtein();
		float simValue = csObj.getSimilarity(s1, s2);
		//System.out.println(simValue);
		if(simValue > 0.8) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isSimilar(ArrayList<String> fieldList, String field) {
		
		for(String str: fieldList) {			
			if(getSimilarity(str, field)) {
				return true;
			}
		}
		
		return false;		
	}

}