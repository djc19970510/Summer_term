package cn.edu.zucc.ding.summerterm.util;

import javax.swing.*;

public class BaseException  extends Exception {
	public BaseException(String msg){
		super(msg);
	}

	public void printStackTrace(){
		JOptionPane.showMessageDialog(null,  this.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
		return;
	}
}
