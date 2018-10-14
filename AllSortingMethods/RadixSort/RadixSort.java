import java.io.*;

class node1{
	int data;
	node1 next;
	node1(int n){
		data=n;
		next=null;
	} 
}
class list{
		node1 last[]=new node1[10];
		node1 bucket[]=new node1[10];
		node1 current;
		int i,digit,divisor,d,j,k;
		list()
		{}
		void radix_sort(int x[],int n,int m)
		{
		for(digit=1;digit<=m;digit++)
		{
		for( i=0;i<10;i++)
		last[i]=bucket[i]=null;
		divisor=1;
		for(k=1;k<=digit-1;k++)
		divisor=divisor*10;
		for(i=0;i<n;i++)
		{
		node1 temp=new node1(x[i]);
		d=(x[i]/divisor)%10;
		if(bucket[d]==null)
		bucket[d]=last[d]=temp;
		else
		{
		last[d].next=temp;
		last[d]=temp;
		}
		}
		j=0;
		for(i=0;i<10;i++)
		{
		current=bucket[i];
		while(current!=null)
		{
		x[j]=current.data;
		j++;
		current=current.next;
		}
		}
		}
		}
 
}
public class RadixSort 
{
 public static int c=0;
 public static void main(String args[]) throws IOException
  {
  int a[],n,m;
   BufferedReader b=new BufferedReader(new InputStreamReader(System.in)); 
   list l=new list();
System.out.println("Enter size of array = ");
   n = Integer.parseInt(b.readLine());
System.out.println("Enter no of digits = ");
m = Integer.parseInt(b.readLine()); 
  a = new int[n];
   System.out.println("Enter values = ");
   for(int i=0;i<n;i++)
     a[i] = Integer.parseInt(b.readLine());
   l.radix_sort(a,n,m);
   System.out.println(" Sorted Array is : ");
   for(int i=0;i<n;i++)
   {
    System.out.println(a[i]+" ");
   }
  }
}
 