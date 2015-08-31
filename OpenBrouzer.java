import java.awt.*;
import java.net.*;
import java.io.IOException;

class OpenBrouzer {
	public static void main(String[] args) {
		open("http://google.com/");
	}
	static void open(String openUrl){
		Desktop desktop = Desktop.getDesktop();
		String url = openUrl;
		try{
			URI uri = new URI(url);
			desktop.browse(uri);
		}
		catch(URISyntaxException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}