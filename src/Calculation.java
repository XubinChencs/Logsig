import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculation {
	private static Calculation instance;
	private Calculation(){
		
	}
	public static Calculation getInstance() {
		return instance;
	}
	
	public int calOptimalTransfer(String logMessage, List<Set<String>> CList, int src, List<Set<String>> CrList) {
		double max_delta = 0;
		int final_dst = src;
		Set<String> RX = new HashSet<String>();
		String[] patition = logMessage.split(" ");
		for (int i = 0; i < patition.length; i ++) {
			for (int j = i + 1; j < patition.length; i++) {
				RX.add(patition[i]+patition[j]);
			}
		}
		for (int i = 0; i < CList.size(); i ++) {
			double temp = 0;
			for (String pair1 : RX) {
				double dst_cnt = 0;
				double src_cnt = 0;
				for (String pair2 : CrList.get(i)) {
					if (pair1.equals(pair2)) {
						dst_cnt++;
					}
				}
				
				for (String pair2 : CrList.get(src)) {
					if (pair1.equals(pair2)) {
						src_cnt++;
					}
				}
				
				temp = temp +  3 * (Math.pow((dst_cnt / (double)CList.get(i).size()),2) 
							-  Math.pow((src_cnt / (double)CList.get(src).size()),2));
			}
			if (temp > max_delta) {
				max_delta = temp;
				final_dst = i;
			}
		}
		return final_dst;
	}
}
