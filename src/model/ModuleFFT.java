package model;

import java.util.ArrayList;
import java.util.Observable;

public class ModuleFFT extends Observable {
	
		// tableau des a0, a1, aé, .... sortie de TOUTES LES FFT
		private ArrayList<Complex> output;
		// liste des modules des outputs
		private ArrayList<Double> moduleList;
		
		public ModuleFFT(){
			output = new ArrayList<Complex>();
			moduleList = new ArrayList<Double>();
		}
		
		public void addFFT(ArrayList<Complex> output, ArrayList<Double> mod)
		{
			this.output.addAll(output);
			this.moduleList.addAll(mod);
			setChanged();
			notifyObservers(this.moduleList);
		}
		
		public ArrayList<Double> getModuleList() {
			return moduleList;
		}

		public void setModuleList(ArrayList<Double> moduleList) {
			this.moduleList = moduleList;
			setChanged();
			notifyObservers(this.moduleList);
		}
		
		public ArrayList<Complex> getOutput() {
			return output;
		}

		public void setOutput(ArrayList<Complex> output) {
			this.output = output;
		}

}
