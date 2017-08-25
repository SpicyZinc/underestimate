public class MaximumGap {
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2) {
          return 0;
        }
      int min=Integer.MAX_VALUE;
      int max=0;  
      for(int i=0;i<num.length;i++){  
        min=Math.min(min,num[i]);  
        max=Math.max(max,num[i]);  
      }  
      int res=(max-min-1)/(num.length-1)+1;  
      int gap=res;  
      int[] minBucket=new int[num.length];  
      for(int i=0;i<num.length;i++) minBucket[i]=max;  
      int[] maxBucket=new int[num.length];  
      for(int i=0;i<num.length;i++){  
        int ind=(num[i]-min)/gap;  
        minBucket[ind]=Math.min(minBucket[ind],num[i]);  
        maxBucket[ind]=Math.max(maxBucket[ind],num[i]);  
      }  
      int pre=0;  
      for(int i=1;i<num.length;i++){  
        if(maxBucket[i]!=0){  
        res=Math.max(res,minBucket[i]-maxBucket[pre]);  
        pre=i;  
        }  
      }  
      return res;  
  }

}