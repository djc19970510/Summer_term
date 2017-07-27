package cn.edu.zucc.ding.summerterm.util;

import javax.swing.*;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("数据库错误"+ex.getMessage());
	}
	public void printStackTrace(){
		JOptionPane.showMessageDialog(null,  this.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
		return;
	}
}
