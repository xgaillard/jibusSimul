package com.fareco.simul;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fareco.model.recueil.Nature;
import com.fareco.model.recueil.Sequence;
import com.fareco.model.recueil.lynx.DataLynx;
import com.fareco.model.recueil.lynx.MesureLynx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import communication.BlagueurSocketMultiServeur;

public class SimulDetectorSocketServer implements Runnable {
	private BlagueurSocketMultiServeur bss;
	private Thread maTache;
	public SimulDetectorSocketServer()
	{
		bss=new BlagueurSocketMultiServeur(33335);
		maTache=new Thread(this,"SimulDetectorSockerServer");
		maTache.start();
	}
	public void run()
	{
		int indice=0;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			MesureLynx mesure=new MesureLynx();
			List<DataLynx> datas=new ArrayList<>();
			DataLynx data=new DataLynx();
			data.setNature(Nature.QT.getInfo());
			data.setPeriode(Sequence.B.getInfo());
			data.setValeur(10);
			datas.add(data);

			mesure.setDate(new Date().getTime());
			mesure.setPointMesure(12345L);
			mesure.setMesures(datas);
			ObjectMapper mapper=new ObjectMapper();
			try {
				bss.write((mapper.writeValueAsString(mesure)+System.getProperty("line.separator")).getBytes());
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		SimulDetectorSocketServer sdss=new SimulDetectorSocketServer();
		
		
	}
}
