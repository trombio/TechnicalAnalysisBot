package com.crypto.jmes.util;

import java.util.List;

import org.ta4j.core.Bar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Esta utilidad se encarga de administrar los archivos generados
 * y guardados como historial 
 */
public class SerializeUtil {
	
	private String localFileDir = "C:/crypto";
	
	public SerializeUtil(String exchange){
		localFileDir += "/binance";
	}
	
	public boolean existsFile(String symbol, String interval){
		File dir = new File(localFileDir);
		File f = new File(dir, symbol + "_" + interval + ".ser");
		
		return f.exists();
	}
	
	public void saveHistory(List<Bar> bars, String symbol, Interval interval){
		File dir = new File(localFileDir);
		String fileName = symbol + "_" + interval.name() + ".ser";
		File serialize = new File(dir, fileName);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(serialize);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(bars);
			oos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Bar> retrieveHistoryData(String symbol, Interval interval){
		List<Bar> bars = null;
		File dir = new File(localFileDir);
		String fileName = symbol + "_" + interval.name() + ".ser";
		File serialize = new File(dir, fileName);
		
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new FileInputStream(serialize);
			ois = new ObjectInputStream(is);
			bars = (List<Bar>)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//TODO
		return bars;
	}
	
	
}
