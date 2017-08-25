import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author liqiang
* @DATE 2008-12-28 *
* @desc 倒置数组完全没有必要
*/
public class BigIntegerOperation {
   
    public static void main(String[] args) throws IOException {       
        /**
        * 从控制台读取输入数据 格式为 num1空格num2 为空则exit
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
        * 将不等长的两输入字符串格式化，比如 num1 = 123 num2 = 1234
        * 经格式化后将成为num1 = 0123 num2 = 1234为方便计算
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
    * 倒置数组元素
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
    *     将字符串转换成整型数组
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
    * 核心方法   两个整型数组相加    
    */
    public static int [] add(int a[], int b[]){
        int maxLen = a.length;
        int[] sum = new int[maxLen+1];
       
        for(int i = 0; i< maxLen ; i++){
            int tempSum = a[i] + b[i];
            sum[i] += tempSum%10;
            int d = tempSum/10; //进位
            sum[i+1] += d;           
        }
        return sum;
    }

}