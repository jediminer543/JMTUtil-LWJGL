package com.jediminer543.util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.lwjgl.LWJGLUtil;

/**
 * Replaces functions from LWJGL2's SYS class that no longer exist
 * 
 * @author Jediminer543
 *
 */
public class JSYS {
	public static long getTimerResolution() {
		return 1000;
	}
	
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	public static void alert(String title, String message) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			LWJGLUtil.log("Caught exception while setting LAF: " + e);
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public static String getClipboard() {
		try {
			java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
			java.awt.datatransfer.Transferable transferable = clipboard.getContents(null);
			if (transferable.isDataFlavorSupported(java.awt.datatransfer.DataFlavor.stringFlavor)) {
				return (String)transferable.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
			}
		} catch (Exception e) {
			LWJGLUtil.log("Exception while getting clipboard: " + e);
		}
		return null;
	}

}
