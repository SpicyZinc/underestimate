import java.util.*;

class All {
	public static void main(String[] args) {
		All eg = new All();
		char[] a = {'s', 'c', 'e', 'r', 'o', 'y', 'r'};
		List<String> result = eg.permute(a);	

		for (String s : result) {
			if (s.length() == 7) {
				System.out.println(s);
			} 
		}
	}

	public List<String> permute(char[] nums) {
        List<String> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), nums);

        return result;
    }
}