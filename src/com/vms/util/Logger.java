package com.vms.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Logger
{
	public static void log(String item)
	{
		try{

			FileWriter fstream = new FileWriter("out.txt",true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(item);
			System.out.println(item);
			out.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}

	}
}