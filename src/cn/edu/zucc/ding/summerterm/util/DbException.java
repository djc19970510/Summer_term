package cn.edu.zucc.ding.summerterm.util;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("数据库错误"+ex.getMessage());
	}
}
