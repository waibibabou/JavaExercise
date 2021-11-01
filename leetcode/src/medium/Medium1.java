package medium;

import org.junit.Test;

import java.util.*;

public class Medium1 {
    @Test
    public void test1(){
        System.out.println(binarySearch(new int[]{1,3,5,6,8,9},8));
    }
    public int binarySearch(int[] arr,int target){
        int left=0,right=arr.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]==target)return mid;
            if(arr[mid]<target)left=mid+1;
            else right=mid-1;
        }
        return -1;
    }

    @Test
    public void test2(){
        int[] arr=new int[]{1,6,3,2,1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public void quickSort(int[] arr,int left,int right){
        if(left<right){
            int partition=partition(arr,left,right);
            quickSort(arr,left,partition-1);
            quickSort(arr,partition+1,right);
        }

    }
    public int partition(int[] arr,int left,int right){
        int temp=arr[left];
        while(left<right){
            while(left<right&&arr[right]>=temp)right--;
            arr[left]=arr[right];
            while(left<right&&arr[left]<=temp)left++;
            arr[right]=arr[left];

        }
        arr[left]=temp;
        return left;
    }


    @Test
    public void test3(){
        System.out.println(openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"},"8888"));
    }
    public int openLock(String[] deadends, String target) {
        //用于记录已经到过的图中的点 类似flag数组
        HashSet<String> hashSet=new HashSet<>();
        Queue<String> queue=new ArrayDeque<>();
        HashSet<String> deadSet=new HashSet<>();
        deadSet.addAll(Arrays.asList(deadends));
        if(deadSet.contains("0000"))return -1;
        hashSet.add("0000");
        queue.add("0000");
        int step=0;
        while(queue.size()>0){
            step++;
            int size=queue.size();
            for(int i=0;i<size;i++){
                String temp=queue.poll();
                //生成8个String
                for(int j=0;j<4;j++){
                    char[] chars=temp.toCharArray();
                    if(chars[j]=='9')chars[j]='0';
                    else chars[j]= (char) (chars[j]+1);
                    String up=String.valueOf(chars);
                    if(up.equals(target))return step;
                    if(!deadSet.contains(up)&&!hashSet.contains(up)){
                        queue.add(up);
                        hashSet.add(up);
                    }

                    chars=temp.toCharArray();
                    if(chars[j]=='0')chars[j]='9';
                    else chars[j]= (char) (chars[j]-1);
                    String down=String.valueOf(chars);
                    if(down.equals(target))return step;
                    if(!deadSet.contains(down)&&!hashSet.contains(down)){
                        queue.add(down);
                        hashSet.add(down);
                    }
                }
            }


        }


        return -1;
    }

    @Test
    public void test4(){
        System.out.println(numSquares(440));

    }
    public int numSquares(int n) {
        int step=0;
        HashSet<Integer> hashSet=new HashSet<>();
        Queue<Integer>queue=new ArrayDeque<>();
        HashSet<Integer> hashSet1=new HashSet<>();
        queue.add(0);
        for(int i=0;i<200;i++){
            int temp=(int)Math.pow(i,2);
            if(temp<=10000)hashSet.add(temp);
        }
        while(queue.size()>0){
            step++;
            int size=queue.size();
            for(int i=0;i<size;i++){
                int temp=queue.poll();
                for(int j :hashSet){
                    if(temp+j==n)return step;
                    if(!hashSet1.contains(temp+j)&&temp+j<n){
                        hashSet1.add(temp+j);
                        queue.add(temp+j);
                    }

                }
            }
        }

        return -1;

    }

    @Test
    public void test5(){
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans=new int[temperatures.length];
        List<Integer> list=new ArrayList<>();
        list.add(0);
        for(int i=1;i<temperatures.length;i++){
            while(list.size()>0&&temperatures[list.get(list.size()-1)]<temperatures[i]){
                ans[list.get(list.size()-1)]=i-list.get(list.size()-1);
                list.remove(list.size()-1);
            }
            list.add(i);
        }

        return ans;
    }

    @Test
    public void test6(){
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
    public int evalRPN(String[] tokens) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<tokens.length;i++){
            String temp=tokens[i];
            if(!temp.equals("+") && !temp.equals("-") && !temp.equals("*") && !temp.equals("/")){
                list.add(Integer.parseInt(temp));
            }
            else{
                int left=list.get(list.size()-2);
                int right=list.get(list.size()-1);
                list.remove(list.size()-2);
                list.remove(list.size()-1);
                int t;
                if(temp.equals("+"))t=left+right;
                else if(temp.equals("-"))t=left-right;
                else if(temp.equals("*"))t=left*right;
                else t=left/right;
                list.add(t);
            }
        }
        return list.get(0);
    }

    @Test
    public void test7(){
        System.out.println(findTargetSumWays(new int[]{1,1,1,1,1},3));

    }

    public int findTargetSumWays(int[] nums, int target) {
        return find(nums,target,0,0,0);

    }
    public int find(int[] nums,int target,int pos,int currentsum,int count){
        if(pos==nums.length&&currentsum==target)return count+1;
        else if(pos==nums.length)return count;
        //+
        count=find(nums,target,pos+1,currentsum+nums[pos],count);

        //-
        count=find(nums,target,pos+1,currentsum-nums[pos],count);

        return count;
    }



}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}