import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;

/*----------------------------------------------------------
name : Kankore HayamiHyo.
Version: 1.4.1α
memo: tabを配列化した。
Copylight: Kurohime Akari
Website: http://shioneko.sakura.ne.jp/
//----------------------------------------------------------*/

public class Plate extends JFrame implements ActionListener{
	JLabel label;
	JTable table[] = new JTable[5];
	JScrollPane scroll[] = new JScrollPane[5];
	JPanel tab[] = new JPanel[3];
	JPanel panel;
	JTabbedPane jtab;
	DefaultTableModel tableModel[] = new DefaultTableModel[3];
	String blist[] = {"リセット","燃料","弾薬","鋼材","ボーキ","バケツ","資材","建造材","家具箱","艦これWiki - 遠征 -を開く","艦これWiki - 建造 -を開く","艦これWiki - 大型建造 -を開く","リセット","理論値のみ表示"};
	int lsc = blist.length;
	JButton ls[] = new JButton[lsc];
	OpenBrouzer bro = new OpenBrouzer();
	
	/* Main */
	public static void main(String[] args){
		Plate pl = new Plate("艦これ早見表");
		pl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pl.setVisible(true);
	}
	
	/* Plate */
	Plate(String title){
		panel = new JPanel();
		label = new JLabel();
		jtab = new JTabbedPane();
		setTitle(title);
		setBounds(1400, 900, 1000, 600);
		
		// 配列処理　addActionListener
		for (int i = 0; i < ls.length; i++) {
			ls[i] = new JButton();
			ls[i].setText(blist[i]);
			ls[i].addActionListener(this);
		}
		
		// 配列処理　JPanel
		for (int i=0; i < tab.length; i++){
			tab[i] = new JPanel();
		}
		
		/*table呼び出し*/
		table();
		
		
		/*タブ1をおした時の中身*/
		tab[0].add(panel.add(ls[0]));
		tab[0].add(panel.add(ls[1]));
		tab[0].add(panel.add(ls[2]));
		tab[0].add(panel.add(ls[3]));
		tab[0].add(panel.add(ls[4]));
		tab[0].add(panel.add(ls[5]));
		tab[0].add(panel.add(ls[6]));
		tab[0].add(panel.add(ls[7]));
		tab[0].add(panel.add(ls[8]));
		tab[0].add(scroll[0]);
		tab[0].add(panel.add(ls[9]));
		jtab.addTab("遠征早見表", tab[0]);
		tab[1].add(scroll[1]);
		tab[1].add(panel.add(ls[10]));
		tab[1].add(panel.add(ls[11]));
		jtab.addTab("建造早見表", tab[1]);
		tab[2].add(panel.add(ls[12]));
		tab[2].add(panel.add(ls[13]));
		tab[2].add(scroll[2]);
		jtab.addTab("開発早見表", tab[2]);
		getContentPane().add("Center",jtab);
	}



/* 中央寄せ void これも自動的にやってくれるので触らない。*/
	private void tableCenter(String[] header,int table_count) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		//table[1].getTableHeader().setDefaultRenderer(renderer);
		for (int i = 0 ; i < header.length; i++){
			table[table_count].getTableHeader().setDefaultRenderer(renderer);
			table[table_count].getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	}
	
// ボタン押した時のアクションイベントはココに書く　処理は変数で自動的にやってくれるので触らない。
	public void actionPerformed(ActionEvent e){	
		JButton botS = (JButton)e.getSource();
		String buttLb = botS.getText();
		
		for (int i = 0; i < ls.length; i++){
			if(buttLb == "リセット"){
				Reset();
			} else if(buttLb == blist[1]){
				find(50,6,0);
			} else if (buttLb == blist[2]){
				find(50,7,0);
			} else if (buttLb == blist[3]){
				find(50,8,0);
			} else if (buttLb == blist[4]){
				find(50,9,0);
			} else if (buttLb == blist[5]){
				find(50,10,0);
			} else if (buttLb == blist[6]){
				find(50,11,0);
			} else if (buttLb == blist[7]){
				find(50,12,0);
			} else if (buttLb == blist[8]){
				find(50,13,0);
			} else if(buttLb == blist[9]){
				bro.open("http://wikiwiki.jp/kancolle/?%B1%F3%C0%AC");
			} else if (buttLb == blist[10]){
				bro.open("http://wikiwiki.jp/kancolle/?%B7%FA%C2%A4%A5%EC%A5%B7%A5%D4");
			} else if(buttLb == blist[11]){
				bro.open("http://wikiwiki.jp/kancolle/?%C2%E7%B7%BF%B4%CF%B7%FA%C2%A4");
			} else if (buttLb == blist[13]){
				find(50,1,2);
			} 
		}
	}
	
	// 検索機能　find (length,Columnamber) で空白文字を除去するもの
	private void find(int array,int findColum,int plnum){
		// 初期化
		Reset();
		// ここから除去する
		for (int i = 0; i < array; i++) {
			//i++;
			try{
				if(table[plnum].getValueAt(i,findColum) == ""){
					tableModel[plnum].removeRow(i);
					i--;
				} else if(table[plnum].getValueAt(i,findColum) != "【 理論値レシピ 】" && plnum == 2){
					tableModel[plnum].removeRow(i);
					i--;
				} 
			} catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void Reset(){
		for (int i = 0; i < 30; i++) {
			try{
				tableModel[0].removeRow(i);
				i--;
			} catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}
		/* ここの部分が複数描かなきゃいけない事実*/
		String array[][] = new String[39][14];
		String header[] = new String[14];
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
		/* ここまで */
		
		// 3番目のリセット用
		String kaiAry[][] = new String[32][8];
		String kaiHead[] = new String[8];
		Kaihatsu kai = new Kaihatsu();
		Object[] kaiObjects = new Object[header.length];
		//objects[] = array[];
		for(int i = 0; i < array.length; i++) {
		    tableModel[2].addRow(kaiObjects);
		}
		kai.henkou(kaiAry,kaiHead);
		for (int i = 0; i < kaiAry.length ; i++) {
			for (int a = 0; a < kaiHead.length; a++){
				String reprint = kaiAry[i][a];
				tableModel[2].setValueAt(reprint , i, a);
			}
		}
	}
	
	private void table(){
		/* table 遠征*/
			
			String array[][] = new String[39][14];
			String header[] = new String[14];
			Enseinin en = new Enseinin();
			en.henkou(array,header);
			tableModel[0] = new DefaultTableModel(array,header);
			
		/* table 建造*/
			
			Kenzou kenz = new Kenzou();
			String kenzAry[][] = new String[24][9];
			String kenzHead[] = new String[9];
			kenz.henkou(kenzAry,kenzHead);
			tableModel[1] = new DefaultTableModel(kenzAry,kenzHead);

			
		/* table 開発*/
		
			Kaihatsu kai = new Kaihatsu();
			String kaiAry[][] = new String[32][8];
			String kaiHead[] = new String[8];
			kai.henkou(kaiAry,kaiHead);
		 	tableModel[2] = new DefaultTableModel(kaiAry,kaiHead);
		
		/* table の配列処理*/
			for (int i = 0; i < tableModel.length; i++) {
				table[i] = new JTable(tableModel[i]);
				table[i].setRowHeight(30);
				table[i].setGridColor(Color.LIGHT_GRAY);
				table[i].setEnabled(false);
				table[i].setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				scroll[i] = new JScrollPane(table[i]);
				scroll[i].setPreferredSize(new Dimension(950,450));
			}
			
			int table_count = 0;
			tableCenter(header,table_count);
			/*中央寄せするやつ*/
			table[0].getColumnModel().getColumn(0).setPreferredWidth(30);
			table[0].getColumnModel().getColumn(1).setPreferredWidth(200);
			table[0].getColumnModel().getColumn(2).setPreferredWidth(120);
			table[0].getColumnModel().getColumn(3).setPreferredWidth(140);
			table[0].getColumnModel().getColumn(4).setPreferredWidth(210);
			
			
			table_count = 1;
			/*中央寄せするやつ*/
			tableCenter(kenzHead,table_count);
			table[1].getColumnModel().getColumn(0).setPreferredWidth(100);
			table[1].getColumnModel().getColumn(1).setPreferredWidth(250);
			table[1].getColumnModel().getColumn(2).setPreferredWidth(120);
			table[1].getColumnModel().getColumn(8).setPreferredWidth(400);
			
			
			table_count = 2;
			/*中央寄せするやつ*/
			tableCenter(kaiHead,table_count);
			table[2].getColumnModel().getColumn(0).setPreferredWidth(90);
			table[2].getColumnModel().getColumn(1).setPreferredWidth(120);
			table[2].getColumnModel().getColumn(2).setPreferredWidth(120);
			table[2].getColumnModel().getColumn(7).setPreferredWidth(400);
	}
}
