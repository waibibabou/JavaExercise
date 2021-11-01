package medium;

import org.junit.Test;

import java.util.*;

public class CombinationSum {

    @Test
    public void test1(){
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(candidates);
        find(list,0,candidates,target,0,new ArrayList<>());
        return list;
    }
    public void find(List<List<Integer>> list,int pos,int[] candidates,int target,int currentSum,List<Integer>path){
        if(currentSum==target){//不到最后就判断
            list.add(new ArrayList<>(path));
            return;
        }
        if(pos==candidates.length||currentSum+candidates[pos]>target)return;


        //用当前位置
        path.add(candidates[pos]);
        find(list,pos+1,candidates,target,currentSum+candidates[pos],path);
        path.remove(path.size()-1);
        //不用当前位置
        while(candidates[pos+1]==candidates[pos])pos++;//如果这个数字不用，则后面与该数字相同的都不能用！！
        find(list,pos+1,candidates,target,currentSum,path);

    }


    @Test
    public void test2(){
        System.out.println(combinationSum(new int[]{2,3,5},8));
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list=new ArrayList<>();
        Arrays.sort(candidates);
        find1(list,0,candidates,0,target,new ArrayList<>());
        return list;

    }
    public void find1(List<List<Integer>>list,int begin,int[] candidates,int currentSum,int target,List<Integer>path){
        if(currentSum==target){
            list.add(new ArrayList<>(path));
            return;
        }
        for(int i=begin;i<candidates.length;i++){
            if(currentSum+candidates[i]>target)break;
            path.add(candidates[i]);
            find1(list,i,candidates,currentSum+candidates[i],target,path);
            path.remove(path.size()-1);
        }

    }

//    @Test
//    public void test3(){
//        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5},8));
//
//    }
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        List<List<Integer>>list =new ArrayList<>();
//        Arrays.sort(candidates);
//        find3(list,0,candidates,target,new ArrayList<>(),0);
//        return list;
//
//    }
//    public void find3(List<List<Integer>>list,int begin,int[] candidates,int target,List<Integer>path,int currentSum){
//        if(currentSum==target){
//            list.add(new ArrayList<>(path));
//            return;
//        }
//        for(int i=begin;i<candidates.length;i++){
//            if(currentSum+candidates[i]>target)break;
//            if(i>begin&&candidates[i]==candidates[i-1]){
//                continue;
//            }
//            path.add(candidates[i]);
//            find3(list,i+1,candidates,target,path,currentSum+candidates[i]);
//            path.remove(path.size()-1);
//        }
//    }
//

    @Test
    public void test4(){
        System.out.println(findDuplicate(new int[]{1,3,4,2,1}));
    }
    public int findDuplicate(int[] nums) {
        int left = 0,right = nums.length-1;
        int ans=0;

        while(left<=right){
            int mid=left+(right-left)/2;
            int count=0;
            for(int i=0;i<nums.length;i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if(count<=mid)left=mid+1;
            else{
                right=mid-1;
                ans=mid;
            }

        }

        return ans;
    }

    @Test
    public void test5(){
        System.out.println(Arrays.toString(intersection(new int[]{1, 2, 2, 1,3}, new int[]{2, 2})));
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer>set1=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            set1.add(nums1[i]);
        }
        Set<Integer>set2=new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            set2.add(nums2[i]);
        }
        Iterator<Integer>iterator=set1.iterator();
        while(iterator.hasNext()){
            int temp=iterator.next();
            if(!set2.contains(temp))iterator.remove();//注意这里 在遍历时remove会报异常
                                                      //使用iterator.remove()不会报异常
        }
        int[] ans=new int[set1.size()];
        int index=0;
        for(int i:set1){
            ans[index++]=i;
        }
        return ans;
    }

    @Test
    public void test6(){
        System.out.println(rob(new int[]{20,7,9,3,1,0,10}));
    }
    public boolean isValidBST(TreeNode root) {
        long pre=Long.MIN_VALUE;
        if(root==null)return true;
        if(!isValidBST(root.left))return false;
        if(root.val<=pre)return false;
        pre=root.val;
        return isValidBST(root.right);
    }

    public int rob(int[] nums) {
        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);
        return Math.max(find(nums,0,nums.length-2),find(nums,1,nums.length-1));
    }
    public int find(int[] nums,int left,int right){
        int[] dp=new int[nums.length];
        dp[left]=nums[left];
        dp[left+1]=Math.max(nums[left],nums[left+1]);
        for(int i=left+2;i<=right;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[right];
    }

    @Test
    public void test7(){
        System.out.println(peakIndexInMountainArray(new int[]{3,4,5,1}));
    }
    public int peakIndexInMountainArray(int[] arr) {
        int left=0,right=arr.length-1;
        int ans=0;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]>=arr[mid-1]){
                ans=arr[mid];
                left=mid+1;

            }
            else{
                right=mid-1;
            }

        }
        return ans;
    }

    @Test
    public void test8(){

    }


}

class Solution {
    List<List<TreeNode>>list=new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>ans=new ArrayList<>();
        List<TreeNode>temp=new ArrayList<>();
        temp.add(root);
        list.add(temp);
        int i=0;
        while(i<list.size()){
            temp=list.get(i);
            Iterator<TreeNode>iterator1=temp.iterator();
            List<TreeNode>listAppend=new ArrayList<>();
            List<Integer>ansAppend=new ArrayList<>();
            while(iterator1.hasNext()){
                TreeNode tt=iterator1.next();
                ansAppend.add(tt.val);
                if(tt.left!=null)listAppend.add(tt.left);
                if(tt.right!=null)listAppend.add(tt.right);
            }
            ans.add(ansAppend);
            if(listAppend.size()>0)list.add(listAppend);
            i++;
        }

        return ans;
    }
}
