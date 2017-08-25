/**
10����Χ��һȦ�����α�ţ�ÿ��2���뿪һ������1��ʼ�������뿪3��6��9��2��7������
�����һ��ʣ�µ����Ǽ��ţ�
*/

import java.util.ArrayList;
/**
 * ͨ������������total_person�����ڵ���1���������ͼ������out_index�����ڵ���1��������������̨���ɴ�ӡ�����ʣ�µ���
 */
public class TheLastOne {

	int total_person 	= 111;	//�����������ڵ���1
	int out_index 		= 2;	//ÿ���޳����ǵڼ����ˣ����ڵ���1
	
	int out_number		= 0;	//�Ѿ��޳�������
	ArrayList<Integer> 	person 			= new ArrayList<Integer>();	//�����˱�����飬����1��2��3��4��5������
	ArrayList<Boolean> 	is_person_out	= new ArrayList<Boolean>();	//�Ƿ��Ѿ��޳������޳�����ӦindexΪfalse
	
	public TheLastOne(){
		//��ʼ��person��is_person_out
		for(int i=0; i<this.total_person; i++){
			this.person.add(i+1);
			this.is_person_out.add(true);
		}
	}
	
	/**
	 * ���ݵ�ǰ�ˣ�������һ����
	 * @param current_index ��ǰ�˱��
	 * @return ��һ���˵ı��
	 */
	public int next(int current_index){
		if(current_index == this.total_person){	//�����ǰ�˵ı�ŵ������������򷵻ص�һ���˵ı��
			return 1;
		}else{
			return current_index+1;	//���򷵻���һ���˵ı��
		}
	}
	/**
	 * ���ݵ�ǰ�ˣ�������һû�б��޳����˵ı��
	 * @param current_index ��ǰ�˱��
	 * @return ��һû�б��޳����˵ı��
	 */
	public int next_not_out(int current_index){
		current_index = this.next(current_index);	//��һ��
		while(!this.is_person_out.get(current_index-1)){
			current_index = this.next(current_index);
		}
		return current_index;	
	}
	/**
	 * �޳���һ���ˣ����ֻʣ��һ���ˣ��򷵻ص�ǰ�˱��
	 * @param current_index ��ǰ�˱��
	 * @return ���޳��˵ı��
	 */
	public int out_next_one(int current_index){
		if(this.total_person - this.out_number == 1){	//���ֻʣ��һ���ˣ��򷵻ص�ǰ�˱��
			this.out_number++;
			return current_index;
		}else{
			int index = 0;
			while(index < this.out_index){	//�������������С��ÿ���޳�������ˣ������±���
				current_index = this.next(current_index);	//�õ���һ���˵ı��
				if(this.is_person_out.get(current_index-1)){	//�����һ����û�б��޳�
					index++;
				}
			}
			this.is_person_out.set(current_index-1, false);	//�޳������
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
		
		int current_index = 0;	//�ӵ�һ���˿�ʼ�޳�
		while(obj.total_person > obj.out_number){	//��������������Ѿ��޳����ˣ����޳�һ����
			current_index = obj.out_next_one(current_index);	//�޳�
		}
		
		System.out.println("���ʣ����Ǹ����ǣ�" + obj.next_not_out(current_index) + "��");
	}

}