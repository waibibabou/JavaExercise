package medium;

import org.junit.Test;

import java.util.*;

public class ThreeSum {
    @Test
    public void test1(){
        System.out.println(threeSum(new int[]{0,0,0,0}));
        String s="aa";

    }

    public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            Arrays.sort(nums);
            for(int i=0;i<nums.length;i++){
                if(i>0&&nums[i]==nums[i-1])continue;
                int first=nums[i];
                int second_index=i+1;
                int third_index=nums.length-1;
                while(second_index<third_index){
                    if(first+nums[second_index]+nums[third_index]>0)third_index--;
                    else if(first+nums[second_index]+nums[third_index]<0)second_index++;
                    else if(first+nums[second_index]+nums[third_index]==0){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(first);
                        list.add(nums[second_index]);
                        list.add(nums[third_index]);
                        ans.add(list);
//                        second_index++;
                        while(nums[second_index]==nums[second_index+1]&&second_index<nums.length-2)second_index++;
                        second_index++;

                        while(nums[third_index]==nums[third_index-1]&&third_index>1)third_index--;
                        third_index--;

                    }
                }


            }
            return ans;
        }

    @Test
    public void test2(){
        System.out.println(lengthOfLongestSubstring("21474836460"));
    }
    public int lengthOfLongestSubstring(String s) {
        if(s=="")return 0;
        int n=s.length();
        int[] count=new int[s.length()];
        count[0]=1;
        for(int i=1;i<n;i++){
            char ch=s.charAt(i);
            int j=i-1;
            while(j>=0&&s.charAt(j)!=ch)j--;
            count[i]=Math.min(count[i-1]+1,i-j);
        }
        int max=0;
        for(int i=0;i<count.length;i++){
            if(count[i]>max)max=count[i];
        }
        return max;
    }




    @Test
    public void test3(){
        System.out.println(myAtoi("  +  413"));
    }
    public int myAtoi(String s) {
        int n=s.length();
        int index=0;
        int begin=0;
        int end=0;
        int positive=0;
        int no=0;
        while(index!=n&&begin==end){
            begin=index;
            end=index;
            if(s.charAt(index)==' '&&no==0){
                index++;
                continue;
            }
            no=1;
            char ch=s.charAt(index);
            if((ch>'9'||ch<'0')&&begin==end&&ch!='-'&&ch!='+')return 0;
            if(positive!=0&&(ch=='+'||ch=='-'))return 0;
            if(ch=='-'&&positive==0)positive=-1;
            if(ch=='+'&&positive==0)positive=1;
            if(ch>='0'&&ch<='9'&&positive==0){
                positive=1;

            }
            while(end<n&&s.charAt(end)>='0'&&s.charAt(end)<='9'){
                end++;
            }
            index++;
        }
        if(begin==end)return 0;

        int i;
        for(i=begin;i<end;i++){
            if(s.charAt(i)!='0'){
                begin=i;
                break;
            }
        }
        if(i==end)return 0;


        if(positive==1){
            if(end-begin>10){
                return (int)(Math.pow(2,31)-1);
            }
            long a=Long.parseLong(s.substring(begin,end));
            if(a>(int)(Math.pow(2,31)-1))return (int)(Math.pow(2,31)-1);
            return Integer.parseInt(s.substring(begin,end));
        }
        if(positive==-1){
            if(end-begin>10){
                return (int)-Math.pow(2,31);
            }
            long a=Long.parseLong(s.substring(begin,end));
            if(-a<(int)-Math.pow(2,31))return (int)-Math.pow(2,31);
            if(s.substring(begin, end).equals("2147483648"))return -2147483648;
            return -Integer.parseInt(s.substring(begin,end));

        }
        return Integer.parseInt(s.substring(begin,end));
    }


    @Test
    public void test4(){
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));

    }


    public String longestCommonPrefix(String[] strs) {
        int index=0,min_len=250;
        for(int i=0;i<strs.length;i++){
            min_len=Math.min(min_len,strs[i].length());
        }

        for(int i=0;i<min_len;i++){
            char ch=strs[0].charAt(i);
            int j=0;
            for(j=0;j<strs.length;j++){
                if(strs[j].charAt(i)!=ch)break;
            }
            if(j==strs.length)index++;
            else break;
        }
        return strs[0].substring(0,index);

    }



    @Test
    public void test5(){
        System.out.println(isValid("(})"));

    }
    public boolean isValid(String s) {
        Collection collection1=new PriorityQueue();
        List collection=new ArrayList();
        for(int i=0;i<s.length();i++){
            if(collection.isEmpty()){
                collection.add(s.charAt(i));

            }
            else{
                if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                    collection.add(s.charAt(i));
                }
                else{
                    char ch=(char)collection.get(collection.size()-1);
                    if(ch=='('&&s.charAt(i)==')'||ch=='['&&s.charAt(i)==']'||ch=='{'&&s.charAt(i)=='}'){
                        collection.remove(collection.size()-1);

                    }
                    else return false;
                }

            }
        }
        return collection.isEmpty();

    }


    @Test
    public void test6(){
//        System.out.println(generateParenthesis(3));
        String a="abc";
        System.out.println(a);

    }
    public void change(String a){
        a="aa";
    }

    public List<String> generateParenthesis(int n){
        char[] a=new char[2*n];
        System.out.println(a[0]);
        List list=new ArrayList();
        find1(a,0,list);
        return list;
    }
    public void find1(char[] a,int pos,List list){
        int open=0,close=0;
        for(int i=0;i<a.length;i++){
            if(a[i]=='(')open++;
            else if(a[i]==')')close++;
        }
        if(close>open||open>a.length/2)return;

        if(pos==a.length){
            if(isValid(String.valueOf(a))){
                list.add(String.valueOf(a));
            }
            return;
        }
        a[pos]='(';
        find1(a,pos+1,list);
        a[pos]=0;
        a[pos]=')';
        find1(a,pos+1,list);
        a[pos]=0;

    }


    @Test
    public void test7(){
        System.out.println(letterCombinations("23"));
    }


    public List<String> letterCombinations(String digits) {

        List list=new ArrayList();
        Map map=new HashMap();
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
        find(0,digits,new char[digits.length()],list,map);

        return list;
    }
    public void find(int pos, String digits, char[] temp,List list, Map map){
        if(pos==digits.length()){
            list.add(String.valueOf(temp));
            return;
        }
        int x=digits.charAt(pos)-'0';
        String a=(String) map.get(x);
        for(int i=0;i<a.length();i++){
            temp[pos]=a.charAt(i);
            find(pos+1,digits,temp,list,map);
        }
        temp[pos]=0;


    }

    @Test
    public void test8(){
        System.out.println(combinationSum(new int[]{8},8));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        find(list,candidates,target,0,0,new ArrayList<Integer>());
//        System.out.println(list);
        return list;
    }

    public void find(List<List<Integer>> list,int[] candidates,int target,int currentSum,int pos,List<Integer> path){
        if(currentSum==target){
            list.add(new ArrayList(path));
            return;
        }
        if(pos==candidates.length||currentSum>target)return;

        //不用当前位置
        find(list,candidates,target,currentSum,pos+1,path);
        //用当前位置
        path.add(candidates[pos]);
        find(list,candidates,target,currentSum+candidates[pos],pos,path);
        path.remove(path.size()-1);
    }

    @Test
    public void test9(){
        System.out.println(longestPalindrome("ccc"));
    }

    public String longestPalindrome(String s) {
        boolean[][] a=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            a[i][i]=true;
        }
        for(int i=0;i<s.length()-1;i++){
            a[i][i+1]=(s.charAt(i)==s.charAt(i+1));
        }
        for(int i=2;i<s.length();i++){
            for(int j=0;j<s.length()-i;j++){
                //改变a[j][j+i]
                if(a[j+1][j+i-1]==true&&s.charAt(j)==s.charAt(j+i)){
                    a[j][j+i]=true;
                }
                else a[j][j+i]=false;
            }
        }

        int left=0,right=0;
        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++){
                if(a[i][j] &&j-i>right-left){
                    right=j;
                    left=i;
                }
            }
        }
        return s.substring(left,right+1);

    }


    @Test
    public void test10(){
        System.out.println(jump(new int[]{3,2,1,0,4}));

    }
    public boolean jump(int[] nums) {
        int[] a=new int[nums.length];
        for(int i=nums.length-2;i>=0;i--){
            int min=nums.length;
            for(int j=0;j<nums[i];j++){
                if(i+j+1<nums.length)min=Math.min(min,a[i+j+1]+1);
            }
            a[i]=min;
        }
        return a[0]<nums.length;

    }

    @Test
    public void test11(){
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 7}})));

    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        List<int[]>list=new ArrayList<int[]>();
        for(int i=0;i<intervals.length;i++){
            if(list.isEmpty()){
                list.add(new int[]{intervals[0][0],intervals[0][1]});

            }
            else if(list.get(list.size()-1)[1]<intervals[i][0]){
                list.add(new int[]{intervals[i][0],intervals[i][1]});

            }
            else if(list.get(list.size()-1)[1]>=intervals[i][0]&&list.get(list.size()-1)[1]<intervals[i][1]){
                list.set(list.size()-1,new int[]{list.get(list.size()-1)[0],intervals[i][1]});
            }
            else if(list.get(list.size()-1)[1]>=intervals[i][1]){

            }

        }

        return list.toArray(new int[list.size()][]);

    }



}
