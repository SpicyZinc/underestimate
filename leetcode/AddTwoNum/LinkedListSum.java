import java.util.ArrayList;
import java.util.List;

public class LinkedListSum {
    static class LLNode {
        int value;
        LLNode next;
        public LLNode(int value){
            this.value = value;
        }
        public int length(){
            LLNode node = this;
            int count = 0;
            do {
                count++;
            } while((node = node.next) != null);
            return count;
        }
        public List<Integer> toList(){
            List<Integer> res = new ArrayList<Integer>();
            LLNode node = this;
            while(node != null){
                res.add(node.value);
                node = node.next;
            }
            return res;
        }
    }

    public static void main(String[] argc){
        LLNode list_a = fromArray(new int[]{4,7,4,7});
        LLNode list_b = fromArray(new int[]{5,3,7,4,7,4});
        System.out.println("Sum: " + sum(list_a, list_b).toList());
    }
//this method fromArray from position 0 to array.length-1, 
//corresponding to linked list from beginning to endding. 
//The only way to do that is insert from the tail.
    private static LLNode fromArray(int[] arr){
        LLNode res = new LLNode(0);
        LLNode current = res;
        for(int i = 0; i < arr.length; i++){
            LLNode node = new LLNode(arr[i]);
            current.next = node;
            current = node;
        }
        return res.next;
    }

    private static LLNode sum(LLNode list_1, LLNode list_2){
        LLNode longer;
        LLNode shorter;
        if(list_1.length() >= list_2.length()){
            longer = list_1;
            shorter = list_2;
        } else {
            longer = list_2;
            shorter = list_1;
        }

        // Pad short to same length as long
        int diff = longer.length() - shorter.length();
        for(int i = 0; i < diff; i++){
            LLNode temp = new LLNode(0);
            temp.next = shorter;
            shorter = temp;
        }

        System.out.println("Longer: " + longer.toList());
        System.out.println("Shorter: " + shorter.toList());

        return sum_same_length(new LLNode(0), null, longer, shorter);
    }

    private static LLNode sum_same_length(LLNode current, LLNode previous, LLNode longerList, LLNode shorterList){
        LLNode result = current;
        if(longerList == null){
            previous.next = null;
            return result;
        }

        int sum = longerList.value + shorterList.value;
        int first_value = sum % 10;
        int first_carry = sum / 10;
        current.value = first_value;

        // Propagate the carry backwards - increase next multiple of 10 if necessary
        LLNode root = propagateCarry(current, previous, first_carry);

        current.next = new LLNode(0);
        sum_same_length(current.next, current, longerList.next, shorterList.next);

        // Propagate the carry backwards - increase next multiple of 10 if necessary:
        // The current value could have been increased during the recursive call
        int second_value = current.value % 10;
        int second_carry = current.value / 10;
        current.value = second_value;

        root = propagateCarry(current,previous,second_carry);
        if(root != null) result = root;

        return result;
    }

    // Returns the new root of the linked list if one had to be added (due to carry)
    private static LLNode propagateCarry(LLNode current, LLNode previous, int carry){
        LLNode result = null;
        if(carry != 0){
            if(previous != null){
                previous.value += carry;
            } else {
                LLNode first = new LLNode(carry);
                first.next = current;
                result = first;
            }
        }
        return result;
    }
}