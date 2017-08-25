    
	public class LLFromArray{
		private static class LLNode{
			int value;
			LLNode next;
			
			public LLNode(int value){
				this.value = value;
				next = null;
			}
		}	
	
		public static void main(String[] argc){
			LLNode list_a = fromArray(new int[]{4,7,4,7});
			LLNode list_b = fromArray(new int[]{5,3,7,4,7,4});
			
			System.out.println(toString(list_a));
			System.out.println(toString(list_b));
		}
		
		//this method fromArray from position 0 to array.length-1, 
		//corresponding to linked list from beginning to endding. 
		//The only way to do that is insertion from the tail.
		private static LLNode fromArray(int[] arr){
			LLNode res = new LLNode(0);
			LLNode current = res;
			for(int i = 0; i < arr.length; i++){
				LLNode node = new LLNode(arr[i]);
				current.next = node;
				current = node;
				//"current" this pointer gradually moves to the right, towards the direction of increasing the index number.
			}
			return res.next;
		}
		
		private static String toString(LLNode aLLNode){
		    LLNode current = aLLNode;
			String out = "";
			while(current != null){
				out += Integer.toString(current.value);
				current = current.next;
			}
			return out;
		}
		
	}