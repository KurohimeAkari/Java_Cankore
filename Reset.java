class Reset {
	public static void main(String[] args) {
		
	}
	void Reset(){
		for (int i = 0; i < 30; i++) {
			try{
				tableModel[0].removeRow(i);
				i--;
			} catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}
		
		String array[][] = new String[39][15];
		String header[] = new String[15];
		Enseinin en = new Enseinin();
		Object[] objects = new Object[header.length];
		//objects[] = array[];
		for(int i = 0; i < array.length; i++) {
		    tableModel[0].addRow(objects);
		}
		en.henkou(array,header);
		for (int i = 0; i < array.length ; i++) {
			for (int a = 0; a < header.length; a++){
				String reprint = array[i][a];
				tableModel[0].setValueAt(reprint , i, a);
			}
		}
	}

}