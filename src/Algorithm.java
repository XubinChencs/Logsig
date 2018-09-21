import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithm {
	private static Algorithm instance;
	
	public static Algorithm getInstance() {
		return instance;
	}
	
	public void logSig_localSearch(Set<String> logSet, int k) {
		List<Set<String>> CList = new ArrayList<Set<String>>();
		List<Set<String>> CList_bk = new ArrayList<Set<String>>();
		List<Set<String>> CrList = new ArrayList<Set<String>>();
		Map<String, Integer> G = new HashMap<String, Integer>();
		for (int i = 0; i < k; i++) {
			CList.add(new HashSet<String>());
			CList_bk.add(new HashSet<String>());
		}
		for (String log : logSet) {
			int index = (int)(0+Math.random()*(k-1-0+1));
			CList.get(index).add(log);
			G.put(log, index);
		}
		for (int i = 0; i < CList.size(); i++) {
			for (String log : CList.get(i)) {
				String[] patition = log.split(" ");
				for (int j = 0; j < patition.length; j++) {
					for (int l = j + 1; l < patition.length; l++) {
						CrList.get(i).add(patition[i]+patition[j]);
					}
				}
			}
		}
		while (!isEqual(CList, CList_bk)) {
			patitionAssign(CList, CList_bk);
			for (String log : logSet) {
				int src = G.get(log);
				int j = 0;
				j = Calculation.getInstance().calOptimalTransfer(log, CList, src, CrList);
				if (src!=j) {
					CList.get(src).remove(log);
					CList.get(j).add(log);
					G.put(log, j);
				}
			}
		}
		
	}
	
	private void patitionAssign(List<Set<String>> src, List<Set<String>> dst) {
		for (int i = 0; i < src.size(); i++) {
			dst.add(new HashSet<String>());
			for (String log : src.get(i)) {
				dst.get(i).add(log);
			}
		}
	}
	
	private boolean isEqual(List<Set<String>> p1, List<Set<String>> p2) {
		for (int i = 0; i < p1.size(); i++) {
			if (!p1.get(i).equals(p2.get(i)))
				return false;
		}
		return true;
		
	}
	
	 public static void main( String[] args ) {
		 System.out.println((int)(0+Math.random()*(7-1-0+1)));
		 Integer i = 1;
		 Set<String> s1 = new HashSet<String>();
		 Set<String> s2 = new HashSet<String>();
		 s1.add(new String("123"));
		 s2.add(new String("123"));
		 System.out.println(s1.equals(s2));
	 }
}
