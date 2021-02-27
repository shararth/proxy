import java.net.*;
import javax.swing.*;
import java.util.*;

public class ImageChangerProxy {
	ImagePart imageComponent;
	JFrame frame = new JFrame("Image Extracter");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> albums = new Hashtable<String, String>();

	public static void main (String[] args) throws Exception {
		ImageChangerProxy testDrive = new ImageChangerProxy();
	}

	public ImageChangerProxy() throws Exception {
		albums.put("Green Landscape","https://upload.wikimedia.org/wikipedia/commons/b/b5/800x600_Wallpaper_Blue_Sky.png");
		albums.put("Chevy","https://img.favcars.com/chevrolet/opala/chevrolet_opala_1970_wallpapers_1_800x600.jpg");
		albums.put("Night Light","http://i.stack.imgur.com/wPh0S.jpg");
		albums.put("Stashed Images","http://www.seekgif.com/uploads/polygonal-low-poly-background-textures-17.animatedgif");
		albums.put("League of Legends","https://a-static.besthdwallpaper.com/league-of-legends-galaxy-slayer-zed-wallpaper-25269_L.jpg");
		albums.put("Air Cars","https://a-static.besthdwallpaper.com/cyberpunk-city-wallpaper-24553_L.jpg");

		URL initialURL = new URL((String)albums.get("Air Cars"));
		menuBar = new JMenuBar();
		menu = new JMenu("Selection");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = albums.keys(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem); 
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getAlbumUrl(event.getActionCommand())));
				frame.repaint();
			});
		}

		// set up frame and menus

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImagePart(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setVisible(true);

	}

	URL getAlbumUrl(String name) {
		try {
			return new URL((String)albums.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}