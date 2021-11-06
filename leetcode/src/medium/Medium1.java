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


    public boolean canPartition(int[] nums) {
        int target=0;
        for(int i:nums){
            target+=i;
        }
        if(target%2==1)return false;
        target/=2;
        //dp[i][j]表示前i个能否组成j
        boolean[][] dp=new boolean[nums.length+1][target];
        dp[0][0]=true;
        for(int i=1;i<=nums.length;i++){
            for(int j=0;j<target;j++){
                if(j<nums[i])dp[i][j]=dp[i-1][j];
                //要当前位置
                dp[i][j]=dp[i-1][j-nums[i]];
                //不要当前位置
                if(!dp[i][j])
                dp[i][j]=dp[i-1][j];
            }
        }
        return dp[nums.length][target];

    }
    int temp;
    @Test
    public void test8(){
        System.out.println(temp);
        System.out.println(decodeString("33[a]2[bc]"));
    }

    public String decodeString(String s) {
        StringBuffer stringBuffer=new StringBuffer();
        if(s.indexOf('[')==-1)return s;

        for(int i=0;i<s.length();i++){
            int indexBegin=s.indexOf('[',i);
            int indexEnd=0;
            int count=0;
            for(int m=indexBegin+1;m<s.length();m++){
                char ch=s.charAt(m);
                if(ch=='[')count++;
                if(ch==']'){
                    if(count>0)count--;
                    else{
                        indexEnd=m;
                        break;
                    }
                }
            }
            int index=indexBegin-1;
            while (index>=0&&Character.isDigit(s.charAt(index)))index--;

            if(i<=index||i>indexEnd){
                stringBuffer.append(s.charAt(i));
            }
            else{
                index=indexBegin-1;
                while (index>=0&&s.charAt(index)>='0'&&s.charAt(index)<='9')index--;

                int repeatNumber=Integer.parseInt(s.substring(index+1,indexBegin));
                String ss=decodeString(s.substring(indexBegin+1,indexEnd));
                for(int j=0;j<repeatNumber;j++)stringBuffer.append(ss);
                i=indexEnd;
            }

        }

        return new String(stringBuffer);
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] flag=new boolean[image.length][image[0].length];
        find(image,sr,sc,flag);
        for(int i=0;i<image.length;i++){
            for(int j=0;j<image[0].length;j++){
                if(flag[i][j])image[i][j]=newColor;
            }
        }
        return image;
    }
    public void find(int[][] image,int x,int y,boolean[][] flag){
        flag[x][y]=true;
        if(x>0&&flag[x-1][y]==false&&image[x-1][y]==image[x][y])find(image,x-1,y,flag);
        if(x<image.length-1&&flag[x+1][y]==false&&image[x+1][y]==image[x][y])find(image,x+1,y,flag);
        if(y>0&&flag[x][y-1]==false&&image[x][y-1]==image[x][y])find(image,x,y-1,flag);
        if(y<image[0].length-1&&flag[x][y+1]==false&&image[x][y+1]==image[x][y])find(image,x,y+1,flag);
    }

    @Test
    public void test9(){
        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}})));
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] distance=new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0)distance[i][j]=0;
                else distance[i][j]=bfs(mat,i,j);
            }
        }
        return distance;
    }

    int bfs(int[][] mat,int x,int y){
        HashSet<List<Integer>>hashSet=new HashSet<>();
        Queue<List<Integer>>queue=new LinkedList<>();
        int step=-1;
        List<Integer>temp=new ArrayList<>();
        temp.add(x);
        temp.add(y);
        queue.add(temp);
        hashSet.add(temp);

        while(!queue.isEmpty()){
            step++;
            int size=queue.size();
            for(int i=0;i<size;i++){
                List<Integer>t=queue.poll();
                int xx=t.get(0);
                int yy=t.get(1);
                if(mat[xx][yy]==0)return step;
                if(xx>0){
                    List<Integer>tt=new ArrayList<>();
                    tt.add(xx-1);
                    tt.add(yy);
                    if(!hashSet.contains(tt))queue.add(tt);
                }
                if(xx<mat.length-1){
                    List<Integer>tt=new ArrayList<>();
                    tt.add(xx+1);
                    tt.add(yy);
                    if(!hashSet.contains(tt))queue.add(tt);
                }
                if(yy>0){
                    List<Integer>tt=new ArrayList<>();
                    tt.add(xx);
                    tt.add(yy-1);
                    if(!hashSet.contains(tt))queue.add(tt);
                }
                if(yy<mat[0].length-1){
                    List<Integer>tt=new ArrayList<>();
                    tt.add(xx);
                    tt.add(yy+1);
                    if(!hashSet.contains(tt))queue.add(tt);
                }
            }

        }

        return step;
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] flag=new boolean[rooms.size()];
        dfs1(rooms,0,flag);

        for(int i=0;i<flag.length;i++){
            if(!flag[i])return false;
        }
        return true;
    }

    void dfs1(List<List<Integer>> rooms,int pos,boolean[] flag){
        flag[pos]=true;
        List<Integer>list=rooms.get(pos);
        for(int i:list){
            if(!flag[i])dfs1(rooms,i,flag);
        }
    }

    public int missingNumber(int[] nums) {
        HashSet<Integer>hashSet=new HashSet<>();
        for(int i=0;i<=nums.length;i++){
            hashSet.add(i);
        }
        for(int i=0;i<nums.length;i++){
            hashSet.remove(nums[i]);
        }
        return (int) hashSet.toArray()[0];
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

class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new LinkedList<Integer>();
        outStack = new LinkedList<Integer>();
    }

    public void push(int x) {
        inStack.add(x);

    }

    public int pop() {
        if(outStack.isEmpty()){
            while(!inStack.isEmpty())outStack.add(inStack.pop());
        }
        return outStack.pop();
    }

    public int peek() {
        if(outStack.isEmpty()){
            while(!inStack.isEmpty())outStack.add(inStack.pop());
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty()&&outStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

class MyStack {

    Queue<Integer>queueOut;
    Queue<Integer>queueIn;

    public MyStack() {
        queueOut=new LinkedList<>();
        queueIn=new LinkedList<>();
    }

    public void push(int x) {
        queueIn.add(x);
        while (!queueOut.isEmpty())queueIn.add(queueOut.poll());
        while(!queueIn.isEmpty())queueOut.add(queueIn.poll());
    }

    public int pop() {
        return queueOut.poll();
    }

    public int top() {
        return queueOut.peek();
    }

    public boolean empty() {
        return queueOut.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */