package medium;

import org.junit.Test;

import java.util.*;

public class Permutations {
    @Test
    public void test1(){
        System.out.println(permute(new int[]{1,2,3,4}));

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        int[] flag=new int[nums.length];
        find(list,nums,flag,new ArrayList<>());
        return list;
    }
    public void find(List<List<Integer>>list,int[] nums,int[] flag,List<Integer>temp){
        if(temp.size()==nums.length){
            list.add(new ArrayList<>(temp));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(flag[i]==0){//还没有被使用
                flag[i]=1;
                temp.add(nums[i]);
                find(list,nums,flag,temp);
                temp.remove(temp.size()-1);
                flag[i]=0;
            }
        }
    }

    @Test
    public void test2(){
        System.out.println(permuteUnique(new int[]{1,1,2}));

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>>list= new ArrayList<>();
        Arrays.sort(nums);
        int[] flag=new int[nums.length];
        find2(list,nums,flag,new ArrayList<>());
        return list;

    }
    public void find2(List<List<Integer>>list,int[] nums,int[] flag,List<Integer>temp){
        if(temp.size()==nums.length){
            list.add(new ArrayList<>(temp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(flag[i]==1||i>0&&nums[i-1]==nums[i]&&flag[i-1]==0)continue;
            flag[i]=1;
            temp.add(nums[i]);
            find2(list,nums,flag,temp);
            temp.remove(temp.size()-1);
            flag[i]=0;
        }

    }


    @Test
    public void test3(){
        int[] nums=new int[]{2,3,5,4,3,2,1};
//        int[] nums=new int[]{4,3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void nextPermutation(int[] nums) {
        int i=nums.length-1;
        while(i>0){
            if(nums[i-1]>=nums[i]){
                i--;
            }
            else break;
        }
        if(i>0){
            int j=nums.length-1;
            while(nums[j]<=nums[i-1]){
                j--;
            }
            int temp=nums[i-1];
            nums[i-1]=nums[j];
            nums[j]=temp;
        }
        res(nums,i);

    }

    public void res(int[] nums,int begin){
        for(int i=0;i<nums.length-1-begin;i++){
            for(int j=begin;j<nums.length-i-1;j++){
                if(nums[j]>nums[j+1]){
                    int temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                }
            }
        }
    }

    @Test
    public void test4(){
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        node1.next=node2;
        node2.next=null;
        swapPairs(node1);
    }
    public ListNode swapPairs(ListNode head) {
        ListNode a=head.next;
        while(head!=null&&head.next!=null){
            ListNode node1=head;
            ListNode node2=head.next;
            head=head.next.next;
            node2.next=node1;
            node1.next=head;
        }
        return a;
    }


    @Test
    public void test5(){
        System.out.println(myPow(2,0));

    }
    public double myPow(double x, int n) {
        if(n>=0){
            return my(x,n);
        }
        else{
            return 1/my(x,n);
        }


    }
    public double my(double x,int n){
        if(n==0)return 1;

        double a=my(x,n/2);
        if(n%2==0)return a*a;
        return a*a*x;
    }

    @Test
    public void test6(){
        MyHashMap myHashMap=new MyHashMap();
        myHashMap.put(1,1);
        myHashMap.put(2,1);
        myHashMap.remove(2);
        System.out.println(myHashMap.get(1));
    }

    @Test
    public void test7(){
        System.out.println(getRow(4));

    }
    public List<Integer>getRow(int rowIndex){
        List<Integer>temp=new ArrayList<>();

        if(rowIndex==0){
            temp.add(1);
            return temp;
        }
        List<Integer>before=getRow(rowIndex-1);
        temp.add(before.get(0));
        for(int i=0;i<before.size()-1;i++){
            temp.add(before.get(i)+before.get(i+1));
        }
        temp.add(before.get(0));
        return temp;
    }

    @Test
    public void test8(){
        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//        rotate(new int[][]{{1,2},{3,4}});

    }
    public void rotate(int[][] matrix) {
        int len=matrix.length;

        for(int i=0;i<Math.ceil(len/2.0);i++){
            for(int j=i;j<len-i-1;j++){
                //接下来更新矩阵中的四个数字a[i][j] a[j][len-i-1] a[len-i-1][len-j-1] a[len-j-1][i]
                int x=i,y=j,temp=matrix[i][j];

                for(int count=0;count<4;count++){
                    //交换temp与对应值
                    int temp1=temp;
                    temp=matrix[y][len-x-1];
                    matrix[y][len-x-1]=temp1;
                    //更新x y
                    int t=x;
                    x=y;
                    y=len-t-1;
                }

            }
        }


    }

    @Test
    public void test9(){
        System.out.println(Arrays.deepToString(generateMatrix(5)));

    }
    public int[][] generateMatrix(int n) {
        if(n==0)return null;
        if(n==1)return new int[][]{{1}};
//        if(n==2)return new int[][]{{1,2},{4,3}};
        int[][] matrix=new int[n][n];
        for(int i=1;i<n*n-(n-2)*(n-2)+1;i++){

            if(i<=n){
                matrix[0][i-1]=i;
            }
            else if(i<=2*n-1){
                matrix[i-n][n-1]=i;
            }
            else if(i<=3*n-2){
                matrix[n-1][n-1-(i-2*n+1)]=i;
            }
            else{
                matrix[n-1-(i-3*n+2)][0]=i;
            }
        }

        int[][] matrix_in=generateMatrix(n-2);
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                matrix[i][j]=matrix_in[i-1][j-1]+n*n-(n-2)*(n-2);
            }
        }

        return matrix;
    }


    @Test
    public void test10(){
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2},{1,3}}));

    }
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int currentEnd=intervals[0][1],count=0;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<currentEnd)count++;
            else currentEnd=intervals[i][1];
        }
        return count;
    }

    @Test
    public void test11(){
        System.out.println(increasingTriplet(new int[]{2,1,0,5,4,5}));
    }
    public boolean increasingTriplet(int[] nums) {
        int small=Integer.MAX_VALUE,mid=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<small)small=nums[i];
            else if(nums[i]<mid)mid=nums[i];
            else return true;
        }
        return false;
    }

    @Test
    public void test12(){
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));

    }
    public int[] productExceptSelf(int[] nums) {
        int[] answer=new int[nums.length];
        answer[0]=1;
        for(int i=1;i<nums.length;i++){
            //用answer数组充当L数组
            answer[i]=answer[i-1]*nums[i-1];
        }
        int R=1;
        for(int i=nums.length-1;i>=0;i--){
            answer[i]=answer[i]*R;
            R=R*nums[i];
        }
        return answer;

    }

    @Test
    public void test13(){
        System.out.println(subarraySum(new int[]{1,1},2));

    }
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int pre=0,count=0;
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            pre+=nums[i];
            if(map.containsKey(pre-k)){
                count+=map.get(pre-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);

        }
        return count;


    }

    @Test
    public void test14(){
        StringBuffer a=new StringBuffer();//StringBuffer可变，String不可变
        System.out.println(addStrings("456","77"));

    }
    public String addStrings(String num1, String num2) {
        int flag=0,index=0,len1=num1.length(),len2=num2.length();
        char[] ans=new char[Math.max(len1,len2)+1];
        int index_ans=ans.length-1;
        while(index<len1||index<len2||flag==1){
            int a=0,b=0;
            if(index<len1){
                a=num1.charAt(len1-1-index)-'0';
            }
            if(index<len2){
                b=num2.charAt(len2-1-index)-'0';
            }
            if(a+b+flag>9){
                ans[index_ans--]= (char) (a+b+flag-10+'0');
                flag=1;
            }
            else{

                ans[index_ans--]= (char) (a+b+flag+'0');
                flag=0;
            }
            index++;
        }

        if(ans[0]==0){
            char[] a=new char[ans.length-1];
            System.arraycopy(ans, 1, a, 0, a.length);
            return String.valueOf(a);
        }
        return String.valueOf(ans);
    }


    @Test
    public void test15(){
        System.out.println(longestPalindrome("abcccccdd"));

    }
    public int longestPalindrome(String s) {
        int ans=0,flag=0;
        HashMap<Character,Integer>hashMap=new HashMap<>();
        for(int i=0;i<s.length();i++){
            hashMap.put(s.charAt(i),hashMap.getOrDefault(s.charAt(i),0)+1);
        }
        Iterator<Character> iterator=hashMap.keySet().iterator();
        while (iterator.hasNext()){
            char ch=iterator.next();
            int a=hashMap.get(ch);
            if(a%2==0){
                ans+=a;
            }
            else{
                if(flag==0){
                    ans+=a;
                    flag=1;
                }
                else{
                    ans+=a-1;
                }
            }
        }
        return ans;
    }

    @Test
    public void test16(){
        Collection collection=new ArrayList();
        System.out.println(wordPattern("abba","dog dog dog dog"));

    }
    public boolean wordPattern(String pattern, String s) {

        HashMap<Character,String>hashMap=new HashMap<>();
        String[] a=s.split(" ");
        for(int i=0;i<pattern.length();i++){
            if(!hashMap.containsKey(pattern.charAt(i))){
                hashMap.put(pattern.charAt(i),a[i]);
            }
            else{
                if(!hashMap.get(pattern.charAt(i)).equals(a[i]))return false;
            }
        }
        List collection= (List) hashMap.values();
        Set set=new HashSet(collection);
        return set.size() >= collection.size();
    }

    @Test
    public void test17(){
        System.out.println(partitionLabels("caedbdedda"));

    }
    public List<Integer> partitionLabels(String s) {
//        List<Integer> list=new ArrayList<>();
//        Map<Character,List<Integer>> map=new HashMap<>();
//        for(int i=0;i<s.length();i++){
//            char ch=s.charAt(i);
//            if(map.containsKey(ch)){//不是第一次出现
//                map.get(ch).set(1,i);
//            }
//            else{
//                List<Integer> temp=new ArrayList<>();
//                temp.add(i);
//                temp.add(i);
//                map.put(ch,temp);
//            }
//        }
//        List<List<Integer>> a= new ArrayList<>(map.values());
//
//        Collections.sort(a, new Comparator<List<Integer>>() {
//            @Override
//            public int compare(List<Integer> o1, List<Integer> o2) {
//                return o1.get(0)-o2.get(0);
//            }
//        });
//
//        int left=0,right=0;
//        for(int i=0;i<a.size();i++){
//            if(i==0){
//                right=a.get(0).get(1);
//                continue;
//            }
//            if(a.get(i).get(0)>right){
//                list.add(right-left+1);
//                left=a.get(i).get(0);
//                right=a.get(i).get(1);
//            }
//            else if(a.get(i).get(1)>right){
//                right=a.get(i).get(1);
//
//            }
//
//        }
//        list.add(right-left+1);
//        return list;
        List<Integer>list =new ArrayList<>();
        int[] endIndex=new int[26];
        for(int i=0;i<s.length();i++){
            endIndex[s.charAt(i)-'a']=i;
        }
        int start=0,end=0;
        for(int i=0;i<s.length();i++){
            end=Math.max(end,endIndex[s.charAt(i)-'a']);
            if(i==end){
                list.add(end-start+1);
                start=end+1;
            }
        }
        return list;

    }

    @Test
    public void test18(){
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>>hashMap=new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] arr=strs[i].toCharArray();
            Arrays.sort(arr);
            String afterSort=String.valueOf(arr);
            List<String>list=hashMap.getOrDefault(afterSort,new ArrayList<>());
            list.add(strs[i]);
            hashMap.put(afterSort,list);

        }
        return new ArrayList<>(hashMap.values());

    }

    @Test
    public void test19(){
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));

    }
    public int[] searchRange(int[] nums, int target) {
        int left=-1,right=-1;
        int low=0,high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]>=target)high=mid-1;
            else if(nums[mid]<target)low=mid+1;
        }
        if(low<nums.length&&nums[low]==target){
            left=low;
        }

        low=0;
        high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]>target)high=mid-1;
            else if(nums[mid]<=target)low=mid+1;
        }
        if(high>=0&&nums[high]==target){
            right=high;
        }


        return new int[]{left,right};
    }

    @Test
    public void test20(){
        System.out.println(search(new int[]{4,5,6,7,0,1,2},1));

    }
    public int search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)return mid;
            //mid两侧必有一个是递增序列
            if(nums[left]<=nums[mid]){
                if(nums[mid]>target&&nums[left]<=target)right=mid-1;
                else left=mid+1;
            }
            else{
                if(nums[mid]<target&&nums[right]>=target)left=mid+1;
                else right=mid-1;

            }

        }

        return -1;
    }

    @Test
    public void test21(){
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{9,11,13,15},{17,19,21,23}},6));

    }
    public boolean searchMatrix(int[][] matrix, int target) {
        //从左下角开始搜索
        int x=matrix.length-1,y=0;
        while(x>=0){
            if(matrix[x][y]>target)x--;
            else{
                for(int i=0;i<matrix[0].length;i++){
                    if(matrix[x][i]==target)return true;
                }
                return false;
            }

        }
        return false;
    }

    @Test
    public void test22(){
        System.out.println(findMin(new int[]{3,4,5,6}));

    }
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(mid!=0&&nums[mid-1]>nums[mid])return nums[mid];
            if(nums[mid]<=nums[right]){//如果mid右侧是递增的
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        return nums[0];

    }

    @Test
    public void test23(){
        System.out.println(findPeakElement(new int[]{2,1}));
    }
    public int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(right==0||left==nums.length-1||mid>0&&mid<nums.length&&nums[mid]>nums[mid+1]&&nums[mid]>nums[mid-1]){
                return mid;
            }
            else if(nums[mid+1]>nums[mid]){
                left=mid+1;
            }
            else right=mid-1;
        }
        return left;

    }

    @Test
    public void test24(){
        ListNode head=new ListNode(1,null);
//        ListNode end=head;
//        for(int i=0;i<1;i++){
//            ListNode node=new ListNode(i+1);
//            end.next=node;
//            end=node;
//        }

        System.out.println(deleteDuplicates(head));

    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead=new ListNode(0,head);
        ListNode node=newHead;
        while(node.next!=null){

            if(node.next.next == null || node.next.val != node.next.next.val){
                node=node.next;
            }
            else{
                ListNode temp=node.next;
                while(temp.next!=null&&temp.next.val==temp.val){
                    temp=temp.next;
                }
                node.next=temp.next;
            }

        }
        return newHead.next;
    }

    @Test
    public void test25(){
        System.out.println(Arrays.toString(twoSum(new int[]{3,2,1,4},4)));

    }
    public int[] twoSum(int[] nums, int target) {
        //排序+双指针
        int[][] a=new int[nums.length][2];
        for(int i=0;i<nums.length;i++){
            a[i][0]=i;
            a[i][1]=nums[i];
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int left=0,right=nums.length-1;
        while(left<right){
            if(a[left][1]+a[right][1]==target)return new int[]{a[left][0],a[right][0]};
            else if(a[left][1]+a[right][1]>target)right--;
            else left++;
        }
        return new int[]{-1,-1};

    }

    @Test
    public void test26(){
        System.out.println(backspaceCompare1("a##c","#a#c"));

    }
    public boolean backspaceCompare(String s, String t) {
        StringBuffer stringBuffer1=new StringBuffer();
        StringBuffer stringBuffer2=new StringBuffer();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch!='#')stringBuffer1.append(ch);
            else if(stringBuffer1.length()!=0)stringBuffer1.delete(stringBuffer1.length()-1,stringBuffer1.length());
        }
        for(int i=0;i<t.length();i++){
            char ch=t.charAt(i);
            if(ch!='#')stringBuffer2.append(ch);
            else if(stringBuffer2.length()!=0)stringBuffer2.delete(stringBuffer2.length()-1,stringBuffer2.length());
        }
        return stringBuffer1.toString().equals(stringBuffer2.toString());

    }
    public boolean backspaceCompare1(String s,String t){
        int skipS=0,skipT=0;
        int indexS=s.length()-1,indexT=t.length()-1;
        while(indexS>=0||indexT>=0){
            while(indexS>=0){
                if(s.charAt(indexS)=='#'){
                    skipS++;
                    indexS--;
                }
                else if(skipS>0){
                    skipS--;
                    indexS--;
                }
                else break;
            }
            while (indexT>=0){
                if(t.charAt(indexT)=='#'){
                    skipT++;
                    indexT--;
                }
                else if(skipT>0){
                    skipT--;
                    indexT--;
                }
                else break;
            }
            if(indexS>=0&&indexT>=0){
                if(s.charAt(indexS)!=t.charAt(indexT)){
                    return false;
                }
            }
            else{
                if(indexS>=0||indexT>=0){
                    return false;
                }
            }
            indexS--;
            indexT--;
        }
        return true;
    }

    @Test
    public void test27(){
        System.out.println(Arrays.deepToString(intervalIntersection(new int[][]{{3,10}},new int[][]{{5,10}})));
//        System.out.println(Arrays.deepToString(intervalIntersection(new int[][]{{0,2},{5,10},{13,23},{24,25}},new int[][]{{1,5},{8,12},{15,24},{25,26}})));
    }
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]>ans =new ArrayList<>();
        int i=0,j=0;
        while(i<firstList.length&&j<secondList.length){
            int low=Math.max(firstList[i][0],secondList[j][0]);
            int high=Math.min(firstList[i][1],secondList[j][1]);
            if(low<=high){
                ans.add(new int[]{low,high});
            }
            if(firstList[i][1]<secondList[j][1])i++;
            else j++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    @Test
    public void test28(){
        System.out.println(findAnagrams("cbaebabacd","abc"));

    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans=new ArrayList<>();
        int[] numS=new int[26];
        int[] numP=new int[26];
        for(int i=0;i<p.length();i++){
            numP[p.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            numS[s.charAt(i)-'a']++;
            if(i>=p.length()){
                numS[s.charAt(i-p.length())-'a']--;
            }
            if(Arrays.equals(numP,numS))ans.add(i-p.length()+1);
        }
        return ans;
    }

    @Test
    public void test29(){
        System.out.println(numSubarrayProductLessThanK(new int[]{1,2,3},0));

    }
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1)return 0;
        int left=0,right=0;//滑动窗口的左右值
        int ans=0,windowTotal=1;
        for(right=0;right<nums.length;right++){
            windowTotal*=nums[right];
            while(windowTotal>=k){
                windowTotal/=nums[left];
                left++;
            }
            ans+=right-left+1;
        }
        return ans;
    }

    @Test
    public void test30(){
        System.out.println(minSubArrayLen(11,new int[]{1,2,3,4,5}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int ans=Integer.MAX_VALUE;
        int left=0,right=0;
        int currentSum=0;//包括Left不包括right
        while(right!=nums.length+1){
            if(currentSum>=target){
                ans=Math.min(ans,right-left);
                currentSum-=nums[left++];
            }
            else if(currentSum<target){
                if(right>=nums.length)break;
                currentSum+=nums[right++];
            }
        }
        return ans<Integer.MAX_VALUE?ans:0;
    }

    @Test
    public void test31(){
        System.out.println(multiply("123","456"));
    }
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0"))return "0";
        int len1=num1.length(),len2=num2.length();
        int[] ans=new int[len1+len2];
        for(int i=len1-1;i>=0;i--){
            int x=num1.charAt(i)-'0';
            for(int j=len2-1;j>=0;j--){
                int y=num2.charAt(j)-'0';
                ans[i+j+1]+=x*y;
            }
        }
        for(int i=ans.length-1;i>0;i--){
            ans[i-1]+=ans[i]/10;
            ans[i]=ans[i]%10;
        }
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<ans.length;i++){
            if(i==0&&ans[i]==0)continue;
            stringBuffer.append(ans[i]);
        }
        return stringBuffer.toString();

    }

    @Test
    public void test32(){
        System.out.println(numIslands(new char[][]{{'1','0'},{'0','1'}}));
    }
    public int numIslands(char[][] grid) {
        int[][] flag=new int[grid.length][grid[0].length];
        int nowX=0,nowY=0,ans=0;
        for(int i=0;i<flag.length;i++){
            for(int j=0;j<flag[0].length;j++){
                if(grid[i][j]=='1'&&flag[i][j]==0){//这个位置还没有探索过
                    ans++;
                    dfs(grid,flag,i,j);
                }
            }
        }
        return ans;
    }
    public void dfs(char[][] grid,int[][] flag,int nowX,int nowY){
        flag[nowX][nowY]=1;
        if(nowX>0&&grid[nowX-1][nowY]=='1'&&flag[nowX-1][nowY]==0){//向上
            dfs(grid,flag,nowX-1,nowY);
        }
        if(nowX<grid.length-1&&grid[nowX+1][nowY]=='1'&&flag[nowX+1][nowY]==0){//向下
            dfs(grid,flag,nowX+1,nowY);
        }
        if(nowY<grid[0].length-1&&grid[nowX][nowY+1]=='1'&&flag[nowX][nowY+1]==0){//向右
            dfs(grid,flag,nowX,nowY+1);
        }
        if(nowY>0&&grid[nowX][nowY-1]=='1'&&flag[nowX][nowY-1]==0){//向左
            dfs(grid,flag,nowX,nowY-1);
        }
    }

    @Test
    public void test33(){
        System.out.println(findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
    }
    public int findCircleNum(int[][] isConnected) {
        int[] flag=new int[isConnected.length];
        int ans=0;
        for(int i=0;i<flag.length;i++){
            if(flag[i]==0){
                ans++;
                dfs(isConnected,flag,i);
            }
        }
        return ans;
    }
    public void dfs(int[][] isConnected,int[] flag,int pos){
        flag[pos]=1;
        for(int j=0;j<isConnected[0].length;j++){
            if(isConnected[pos][j]==1&&flag[j]==0){
                dfs(isConnected,flag,j);
            }
        }
    }

    @Test
    public void test34(){
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list=new ArrayList<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        for(int end=10;end<=s.length();end++){
            String subString=s.substring(end-10,end);
            int num=hashMap.getOrDefault(subString,0);
            hashMap.put(subString,num+1);
        }
        Set<String>set=hashMap.keySet();
        Iterator<String> iterator=set.iterator();
        while(iterator.hasNext()){
            String ss=iterator.next();
            if(hashMap.get(ss)>1)list.add(ss);
        }
        return list;
    }

    @Test
    public void test35(){
        ListNode head=new ListNode(1);
        ListNode end=head;
        for(int i=0;i<5;i++){
            ListNode node=new ListNode(i+2);
            end.next=node;
            end=node;
        }
        rotateRight(head,2);
        System.out.println(Math.pow(2,3));


    }
    public ListNode rotateRight(ListNode head, int k) {
        ListNode fast=head,slow=head;
        int len=0;
        while(fast!=null){
            fast=fast.next;
            len++;
        }
        k=k%len;
        if(k==0)return head;
        fast=head;
        for(int i=0;i<k;i++){
            fast=fast.next;
        }
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        ListNode newHead=slow.next;
        slow.next=null;
        fast.next=head;

        return newHead;
    }

    @Test
    public void test36(){
        System.out.println(grayCode(3));

    }
    public List<Integer> grayCode(int n) {
        List<Integer>ans=new ArrayList<>();
        ans.add(0);
        int head=1;
        for(int i=0;i<n;i++){
            for(int j=ans.size()-1;j>=0;j--){
                ans.add(head+ans.get(j));
            }
            head<<=1;
        }

        return ans;
    }

    @Test
    public void test37(){
        ListNode head=new ListNode(1);
        ListNode end=head;
        for(int i=0;i<5;i++){
            ListNode node=new ListNode(i);
            end.next=node;
            end=node;
        }
        partition(head,1);

    }
    public ListNode partition(ListNode head, int x) {
        ListNode newHead=new ListNode();
        ListNode end=newHead;
        ListNode traverse=head;
        while(traverse!=null){
           if(traverse.val<x){
               ListNode node=new ListNode(traverse.val);
               end.next=node;
               end=node;
           }
           traverse=traverse.next;
        }
        traverse=head;
        while(traverse!=null){
            if(traverse.val>=x){
                ListNode node=new ListNode(traverse.val);
                end.next=node;
                end=node;
            }
            traverse=traverse.next;
        }
        return newHead;
    }

    @Test
    public void test38(){
        System.out.println(threeSumClosest(new int[]{-3,-2,-5,3,-4},-1));
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        long ans=Long.MAX_VALUE-Math.abs(target-1);
        int left,right;
        for(int i=0;i<nums.length;i++){
            left=i+1;
            right=nums.length-1;
            while(left<right){
                if(nums[i]+nums[left]+nums[right]>target){
                    if(nums[i]+nums[left]+nums[right]-target<Math.abs(ans-target)){
                        ans=nums[i]+nums[left]+nums[right];
                    }
                    right--;
                }
                else if(nums[i]+nums[left]+nums[right]<target){
                    long tempLeft=target-nums[i]-nums[left]-nums[right];
                    long tempRight=Math.abs(ans-target);
                    if(tempLeft<tempRight){
                        ans=nums[i]+nums[left]+nums[right];
                    }
                    left++;
                }
                else return target;
            }
        }

        return (int)ans;
    }

    @Test
    public void test39(){
        System.out.println(strStr("",""));
    }
    public int strStr(String haystack, String needle) {
        if(needle.equals(""))return 0;
        for(int i=0;i<haystack.length();i++){
            if(haystack.charAt(i)==needle.charAt(0)){
                for(int j=i;j<i+needle.length();j++){
                    if(j>=haystack.length())return -1;
                    if(j==i+needle.length()-1&&haystack.charAt(j)==needle.charAt(j-i))return i;
                    if(haystack.charAt(j)!=needle.charAt(j-i)) {
                        break;
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test40(){
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
    public int trap(int[] height) {
        int left=0,right=height.length-1;
        int leftMax=0,rightMax=0;
        int ans=0;
        while(left<right){
            leftMax=Math.max(leftMax,height[left]);
            rightMax=Math.max(rightMax,height[right]);
            if(leftMax<rightMax){//此时left处可以存多少水已经可以确定了
                ans+=leftMax-height[left];
                left++;
            }
            else{
                ans+=rightMax-height[right];
                right--;
            }
        }
        return ans;
    }

    @Test
    public void test41(){
        int[] arr=new int[]{5,2,1,7,3,2};
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
    public void test42(){
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{0,2,1,1,5,3},3)));

    }
    public int[] getLeastNumbers(int[] arr, int k) {
        getLeastNumbers_(arr,k,0,arr.length-1);
        int[] ans=new int[k];
        if (k >= 0) System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
    public void getLeastNumbers_(int[] arr,int k,int left,int right){
        int current=partition(arr,left,right);
        if(current==k)return;
        else if(current>k){
            getLeastNumbers_(arr,k,left,current-1);
        }
        else{
            getLeastNumbers_(arr,k,current+1,right);
        }
    }

    @Test
    public void test43(){
        System.out.println(uniquePaths(3,7));
    }
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i!=0&&j!=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
                else if(i==0){
                    dp[i][j]=1;
                }
                else{
                    dp[i][j]=1;
                }
            }
        }
        return dp[m-1][n-1];

    }

    @Test
    public void test44(){
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,1,0},{0,1,0},{0,0,0}}));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp=new int[obstacleGrid.length][obstacleGrid[0].length];
        int flag=0;
        for(int i=0;i<obstacleGrid[0].length;i++){
            if(obstacleGrid[0][i]==1)flag=1;
            if(flag==0)dp[0][i]=1;
        }
        flag=0;
        for(int i=0;i<obstacleGrid.length;i++){
            if(obstacleGrid[i][0]==1)flag=1;
            if(flag==0)dp[i][0]=1;
        }

        for(int i=0;i<obstacleGrid.length;i++){
            for(int j=0;j<obstacleGrid[0].length;j++){
                if(obstacleGrid[i][j]==1)dp[i][j]=0;
                else if(i!=0&&j!=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    @Test
    public void test45(){
//        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
        int[] a=new int[]{0,0,1,0,2,0,3};
        moveZeroes(a);
        System.out.println(Arrays.toString(a));

    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length())return false;
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        dp[0][0]=true;
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i>0){
                    dp[i][j]=dp[i][j]||dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1);
                }
                if(j>0){
                    dp[i][j]=dp[i][j]||dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public void moveZeroes(int[] nums) {
        //slow指针指向第一个0,双指针在数组上同样可以同方向移动
        int slow=0;
        int fast=0;
        for(fast=0;fast<nums.length;fast++){
            if(nums[fast]!=0){
                if(fast!=slow){
                    int temp=nums[fast];
                    nums[fast]=nums[slow];
                    nums[slow]=temp;
                }
                slow++;
            }
        }

    }
    @Test
    public void test46(){
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,2,3,4}));
    }
    public int removeDuplicates(int[] nums) {
        int index=1;
        for(int i=1;i<nums.length;i++){
            if(nums[index-1]!=nums[i]){
                nums[index++]=nums[i];
            }
        }
        return index;
    }

    @Test
    public void test47(){
        System.out.println(reverseVowels("leetcode"));

    }
    public String reverseVowels(String s) {
        StringBuffer stringBuffer=new StringBuffer(s);
        Set<Character> set=new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int left=0,right=s.length()-1;
        while(left<right){
            if(!set.contains(s.charAt(left)))left++;
            if(!set.contains(s.charAt(right)))right--;
            if(set.contains(s.charAt(left))&&set.contains(s.charAt(right))){
                char temp=s.charAt(left);
                stringBuffer.setCharAt(left,s.charAt(right));
                stringBuffer.setCharAt(right,temp);
                left++;
                right--;
            }

        }
        return stringBuffer.toString();
    }

    @Test
    public void test48(){
        System.out.println(minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
    }

    @Test
    public void test49(){
        System.out.println(mySqrt(50));
    }
    public int mySqrt(int x) {
        int left=0,right=x;
        int ans=0;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(mid*mid==x)return mid;
            else if(mid*mid<x){
                ans=mid;
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }
        return ans;
    }

    @Test
    public void test50(){
        System.out.println(minDistance("horse","ros"));
    }
    public int minDistance(String word1, String word2) {
        //记录word1前i个到word2前j个的最少改变次数
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]);
                }
                else dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+1);
            }
        }
        return dp[dp.length-1][dp[0].length-1];

    }

    @Test
    public void test51(){

    }
    public int minDepth(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null)return minDepth(root.right)+1;
        if(root.right==null)return minDepth(root.left)+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;

    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null)return false;
        if(root.left==null&&root.right==null){//此时是叶子节点
            return root.val == targetSum;
        }
        //不是叶子节点
        if(hasPathSum(root.left,targetSum-root.val)||hasPathSum(root.right,targetSum-root.val))return true;
        else return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list=new ArrayList<>();
        if(root==null)return list;
        if(root.left==null&&root.right==null){//这是叶子节点
            if(root.val==targetSum){
                List<Integer>list1=new ArrayList<>();
                list1.add(root.val);
                list.add(list1);
            }
            return list;
        }
        List<List<Integer>>leftList=pathSum(root.left,targetSum-root.val);
        List<List<Integer>>rightList=pathSum(root.right,targetSum-root.val);
        list.addAll(leftList);
        list.addAll(rightList);
        Iterator<List<Integer>> iterator=list.iterator();
        while(iterator.hasNext()){
            iterator.next().add(0,root.val);
        }
        return list;

    }

    public boolean isSymmetric(TreeNode root) {
        if(root==null)return true;
        return isSymmetric_(root.left,root.right);

    }

    public boolean isSymmetric_(TreeNode leftNode,TreeNode rightNode){
        if(leftNode==null&&rightNode==null)return true;
        if(leftNode==null||rightNode==null||leftNode.val!=rightNode.val)return false;
        return isSymmetric_(leftNode.right,rightNode.left)&&isSymmetric_(leftNode.left,rightNode.right);
    }

    @Test
    public void test52(){

    }
    public boolean isBalanced(TreeNode root) {
        if(root==null||root.left==null&&root.right==null)return true;
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        if(Math.abs(leftDepth-rightDepth)>1)return false;
        return isBalanced(root.left)&&isBalanced(root.right);


    }
    public int maxDepth(TreeNode root){
        if(root==null)return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    public void flatten(TreeNode root) {
        if(root==null||root.left==null&&root.right==null)return;
        flatten(root.left);
        flatten(root.right);
        TreeNode node=root.left;
        if(node!=null){
            while(node.right!=null){
                node=node.right;
            }
            node.right=root.right;
            root.right=root.left;
            root.left=null;
        }

    }



}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class MyHashMap{
    private class Pair{
        int key;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int value;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    private final int BASE=769;
    private LinkedList[] data;
    public MyHashMap(){
        data=new LinkedList[BASE];
        for(int i=0;i<BASE;i++){
            data[i]=new LinkedList<Pair>();
        }

    }
    public void put(int key,int value){
        int h=hash(key);
        Iterator<Pair> iterator=data[h].iterator();
        while(iterator.hasNext()){
            Pair a=iterator.next();
            if(a.key==key){
                a.value=value;
                return;
            }
        }
        data[h].add(new Pair(key,value));

    }
    public int get(int key){
        int h=hash(key);
        Iterator<Pair>iterator=data[h].iterator();
        while(iterator.hasNext()){
            Pair a=iterator.next();

            if(a.key==key){
                return a.value;
            }
        }
        return -1;
    }
    public void remove(int key){
        int h=hash(key);
        Iterator<Pair> iterator=data[h].iterator();
        while(iterator.hasNext()){
            Pair a=iterator.next();
            if(a.key==key){
                data[h].remove(a);
                return;
            }
        }
    }

    public int hash(int key){
        return key%BASE;
    }

}

class MyCircularQueue {
    int[] myCircularQueue;
    int front,rear;
    int len;

    public MyCircularQueue(int k) {
        myCircularQueue=new int[k+1];
        front=0;
        rear=0;
        len=k+1;
    }

    public boolean enQueue(int value) {
        if((rear+1)%len==front)return false;
        myCircularQueue[rear++]=value;
        rear%=len;
        return true;
    }

    public boolean deQueue() {
        if(rear==front)return false;
        front=(front+1)%len;
        return true;
    }

    public int Front() {
        if(rear==front)return -1;
        return myCircularQueue[front];

    }

    public int Rear() {
        if(rear==front)return -1;
        return myCircularQueue[(rear-1+len)%len];

    }

    public boolean isEmpty() {
        return rear==front;
    }

    public boolean isFull() {
        return (rear+1)%len==front;
    }
}
