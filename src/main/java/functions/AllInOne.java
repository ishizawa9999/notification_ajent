package functions;

public class AllInOne {

	
	/**
	 * 条件 明るさが0．3以下ならfalse
	 */
	private double condition = 0.3;
	
	public static String TwitterName = "@kyabe";
	/**
	 * 条件判定用関数
	 * @param recievedCondition
	 * @return
	 */
	public boolean judge(double recievedCondition){
		return recievedCondition >= condition;
	}
}
