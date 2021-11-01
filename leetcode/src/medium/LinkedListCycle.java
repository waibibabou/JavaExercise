package medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinkedListCycle {

    @Test
    public void test1(){
        ListNode listNode=new ListNode();
        System.out.println(hasCycle(listNode));
    }
    public boolean hasCycle(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast)return true;
        }
        return false;
    }
    public ListNode detectCycle(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow)break;

        }
        if(fast==null||fast.next==null)return null;
        ListNode node=head;
        while(node!=slow){
            node=node.next;
            slow=slow.next;
        }
        return node;
    }

    @Test
    public void test2(){
        System.out.println(search(new int[]{},0));

    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1=headA,p2=headB;
        while(p1!=p2){
            p1=p1==null?headB:p1.next;
            p2=p2==null?headA:p2.next;

        }
        return p1;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode node=head;
        while(node!=null&&node.next!=null){
            while(node.next!=null&&node.next.val== node.val)node.next=node.next.next;
            node=node.next;
        }
        return head;
    }

    public boolean search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)return true;
            if(nums[left]==nums[mid]&&nums[mid]==nums[right]){
                right--;
                left++;
            }
            if(nums[left]<=nums[mid]){//左侧递增
                if(nums[left]<=target&&nums[mid]>target)right=mid-1;
                else left=mid+1;
            }
            else{//右侧递增
                if(nums[right]>=target&&nums[mid]<target)left=mid+1;
                else right=mid-1;
            }
        }
        return false;
    }

    @Test
    public void test3(){
        ListNode head=new ListNode(1);
        ListNode end=head;
        for(int i=0;i<3;i++){
            ListNode node=new ListNode(i+2);
            end.next=node;
            end=end.next;
        }
        ListNode node=reverseList(head);
    }

    public ListNode reverseList(ListNode head) {
        ListNode left=null,mid=head,right=head.next;
        while(true){
            mid.next=left;
            left=mid;
            mid=right;
            if(mid==null)break;
            right=right.next;
        }
        return left;
    }

    @Test
    public void test4(){
        System.out.println(spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer>list=new ArrayList<>();
        int up=0,down=matrix.length-1;
        int left=0,right=matrix[0].length-1;
        int size=matrix.length*matrix[0].length;
        while(true){
            for(int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
            if(list.size()==size)break;
            up++;
            for(int i=up;i<=down;i++){
                list.add(matrix[i][right]);
            }
            if(list.size()==size)break;
            right--;
            for(int i=right;i>=left;i--){
                list.add(matrix[down][i]);
            }
            if(list.size()==size)break;
            down--;
            for(int i=down;i>=up;i--){
                list.add(matrix[i][left]);
            }
            if(list.size()==size)break;
            left++;
        }
        return list;
    }

    @Test
    public void test5(){
        System.out.println(Arrays.deepToString(insert(new int[][]{{0,2},{3,9}},new int[]{6,8})));
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list=new ArrayList<>();
        int index=0,flag=0;
        if(intervals.length==0)return new int[][]{new int[]{newInterval[0],newInterval[1]}};

        while(index<intervals.length||flag==0){
            if(flag==0){
                if(index==intervals.length){
                    list.add(newInterval);
                    break;
                }
                if(newInterval[0]>intervals[index][1]){
                    list.add(intervals[index]);
                    index++;
                }
                else{
                    flag=1;
                    if(list.isEmpty()){
                        list.add(newInterval);
                        continue;
                    }
                    if(intervals[index][0]>newInterval[1]){
                        list.add(newInterval);
                        continue;
                    }
                    list.add(intervals[index]);
                    index++;
                    list.set(list.size()-1,new int[]{Math.min(list.get(list.size()-1)[0],newInterval[0]),Math.max(list.get(list.size()-1)[1],newInterval[1])});
                }

            }
            else{//已经加入了
                if(intervals[index][0]<=newInterval[1]){
                    int left=Math.min(list.get(list.size()-1)[0],intervals[index][0]);
                    int right=Math.max(list.get(list.size()-1)[1],intervals[index][1]);
                    list.set(list.size()-1,new int[]{left,right});

                }
                else{
                    list.add(intervals[index]);
                }
                index++;
            }

        }
        return list.toArray(new int[list.size()][]);
    }

    @Test
    public void test6(){
        System.out.println(lengthOfLastWord(" hello  world  "));

    }
    public int lengthOfLastWord(String s) {
        int ans=0,flag=0;
        for(int index=0;index<s.length();index++){
            if(s.charAt(index)==' ')flag=1;
            else{
                if(flag==1)ans=0;
                ans++;
                flag=0;
            }
        }
        return ans;
    }

    @Test
    public void test7(){
        System.out.println(Arrays.toString(smallestK(new int[]{1,3,5,7,2,3},3)));
    }
    public int[] smallestK(int[] arr, int k) {
        if(k==0)return null;
        int[] ans=new int[k];
        find(arr,k,0,arr.length-1);
        for(int i=0;i<k;i++){
            ans[i]=arr[i];
        }
        return ans;
    }
    public void find(int[] arr,int num,int left,int right){
        int pos=partition(arr,left,right);
        if(pos==num-1){
            return;
        }
        else if(pos<num-1){//左侧的还不够
            find(arr,num,pos+1,right);
        }
        else{
            find(arr,num,left,pos-1);
        }
    }

    @Test
    public void test8(){
        int[] arr=new int[]{3,5,2,1};
        qSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
    public void qSort(int[] arr,int left,int right){
        if(left<right){
            int mid=partition(arr,left,right);
            qSort(arr,left,mid-1);
            qSort(arr,mid+1,right);
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
    public void test9(){
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));

    }
    public String minRemoveToMakeValid(String s) {
        int right=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==')')right++;
        }
        StringBuffer stringBuffer=new StringBuffer();
        int left=0;//还未闭合的左括号
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch==')'){
                if(left>0){
                    left--;
                    right--;
                    stringBuffer.append(ch);
                }
                else{
                    right--;
                }
            }
            else if(ch=='('){
                if(right>0){
                    if(left+1>right)continue;
                    left++;
                    stringBuffer.append(ch);
                }
            }
            else{
                stringBuffer.append(ch);
            }
        }

        return stringBuffer.toString();
    }

    @Test
    public void test10(){
        System.out.println(findTheWinner(5,2));
    }
    public int findTheWinner(int n, int k) {
        List<Integer>list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(i+1);
        }
        int current=0;
        while(list.size()>1){
            for(int i=0;i<k-1;i++){
                current=(current+1)%list.size();
            }
            list.remove(current);
        }
        return list.get(0);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null)return null;
        List<Integer>list =new ArrayList<>();
        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)return true;
        else if(p==null||q==null)return false;
        else if(p.val!=q.val)return false;
        else{
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
    }


}



class MinStack {
    List<Integer>list;
    List<Integer>minList;
    /** initialize your data structure here. */
    public MinStack() {
        list=new ArrayList<>();
        minList=new ArrayList<>();
    }

    public void push(int val) {
        list.add(val);
        minList.add(Collections.min(list));
    }

    public void pop() {
        list.remove(list.size()-1);
        minList.remove(minList.size()-1);
    }

    public int top() {
        return list.get(list.size()-1);
    }

    public int getMin() {
        return minList.get(minList.size()-1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
