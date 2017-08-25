import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author liqiang
* @DATE 2008-12-28 *
* @desc ����������ȫû�б�Ҫ
*/
public class BigIntegerOperation {
   
    public static void main(String[] args) throws IOException {       
        /**
        * �ӿ���̨��ȡ�������� ��ʽΪ num1�ո�num2 Ϊ����exit
        */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        while(str!=null){
        StringTokenizer st = new StringTokenizer(str," ");
        String num1 = st.nextToken();
        String num2 = st.nextToken();
        int len1 = num1.length();
        int len2 = num2.length();
        /**
        * �����ȳ����������ַ�����ʽ�������� num1 = 123 num2 = 1234
        * ����ʽ���󽫳�Ϊnum1 = 0123 num2 = 1234Ϊ�������
        */
        if(len1 > len2){
            for(int i=0; i< len1-len2; i++){
                num2 = "0"+num2;
            }
        }else if(len2>len1){
            for(int i=0; i< len2-len1; i++){
                num1 = "0"+num1;
            }
        }
       
        int[] arr1 = BigIntegerOperation.str2intArr(num1);
        int[] arr2 = BigIntegerOperation.str2intArr(num2);
       
        arr1 = BigIntegerOperation.reverse(arr1);
        arr2 = BigIntegerOperation.reverse(arr2);
       
       
        int[] result = BigIntegerOperation.add(arr1, arr2);
       
//        System.out.print(num1 +" + " + num2 +" = ");
        for(int i=result.length-1; i>=0; i--){
            if(i == result.length-1 && result[i] == 0) continue;
            System.out.print(result[i]);
        }
        str = in.readLine();
        }
    }
   
    /**
    * ��������Ԫ��
    */
    public static int [] reverse(int[] arr){
        int len = arr.length;
        for(int i = 0,j = len-1; i<j; i++,j--){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
   
    /**
    *     ���ַ���ת������������
    */
    public static int [] str2intArr(String str){
        int len = str.length();
        int [] arr = new int[len];
        for(int i = 0 ; i<len; i++){
            arr[i] = str.charAt(i) - '0' ;
        }
        return arr;
    }
   
    /**
    * ���ķ���   ���������������    
    */
    public static int [] add(int a[], int b[]){
        int maxLen = a.length;
        int[] sum = new int[maxLen+1];
       
        for(int i = 0; i< maxLen ; i++){
            int tempSum = a[i] + b[i];
            sum[i] += tempSum%10;
            int d = tempSum/10; //��λ
            sum[i+1] += d;           
        }
        return sum;
    }

}