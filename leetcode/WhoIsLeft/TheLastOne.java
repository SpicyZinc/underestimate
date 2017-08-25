/**
10个人围成一圈，依次编号，每隔2个离开一个，从1开始，依次离开3，6，9，2，7。。。
问最后一个剩下的人是几号？
*/

import java.util.ArrayList;
/**
 * 通过设置总人数total_person（大于等于1的整数）和间隔人数out_index（大于等于1的整数），控制台即可打印出最后剩下的人
 */
public class TheLastOne {

	int total_person 	= 111;	//总人数，大于等于1
	int out_index 		= 2;	//每次剔除的是第几个人，大于等于1
	
	int out_number		= 0;	//已经剔除的人数
	ArrayList<Integer> 	person 			= new ArrayList<Integer>();	//所有人编号数组，例如1，2，3，4，5。。。
	ArrayList<Boolean> 	is_person_out	= new ArrayList<Boolean>();	//是否已经剔除，若剔除，对应index为false
	
	public TheLastOne(){
		//初始化person和is_person_out
		for(int i=0; i<this.total_person; i++){
			this.person.add(i+1);
			this.is_person_out.add(true);
		}
	}
	
	/**
	 * 根据当前人，返回下一个人
	 * @param current_index 当前人编号
	 * @return 下一个人的编号
	 */
	public int next(int current_index){
		if(current_index == this.total_person){	//如果当前人的编号等于总人数，则返回第一个人的编号
			return 1;
		}else{
			return current_index+1;	//否则返回下一个人的编号
		}
	}
	/**
	 * 根据当前人，返回下一没有被剔除的人的编号
	 * @param current_index 当前人编号
	 * @return 下一没有被剔除的人的编号
	 */
	public int next_not_out(int current_index){
		current_index = this.next(current_index);	//下一个
		while(!this.is_person_out.get(current_index-1)){
			current_index = this.next(current_index);
		}
		return current_index;	
	}
	/**
	 * 剔除下一个人，如果只剩下一个人，则返回当前人编号
	 * @param current_index 当前人编号
	 * @return 被剔除人的编号
	 */
	public int out_next_one(int current_index){
		if(this.total_person - this.out_number == 1){	//如果只剩下一个人，则返回当前人编号
			this.out_number++;
			return current_index;
		}else{
			int index = 0;
			while(index < this.out_index){	//如果遍历的人数小于每次剔除间隔的人，则往下遍历
				current_index = this.next(current_index);	//得到下一个人的编号
				if(this.is_person_out.get(current_index-1)){	//如果下一个人没有被剔除
					index++;
				}
			}
			this.is_person_out.set(current_index-1, false);	//剔除这个人
			this.out_number++;
			return current_index;
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheLastOne obj = new TheLastOne();
		
		int current_index = 0;	//从第一个人开始剔除
		while(obj.total_person > obj.out_number){	//如果总人数大于已经剔除的人，则剔除一个人
			current_index = obj.out_next_one(current_index);	//剔除
		}
		
		System.out.println("最后剩余的那个人是：" + obj.next_not_out(current_index) + "号");
	}

}