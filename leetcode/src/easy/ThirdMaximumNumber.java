package easy;

import org.junit.Test;

import java.util.*;

public class ThirdMaximumNumber {
    @Test
    public void test1(){
        System.out.println(thirdMax(new int[]{3,2,1}));

    }
    public int thirdMax(int[] nums) {
        int low,mid,high,flag_low=0,flag_mid=0;
        int index=0;
        high=nums[0];
        mid=high;
        low=high;
        index++;
        while(index<nums.length){
           int temp=nums[index];
           if(temp==high||temp==mid||temp==low){
               index++;
               continue;
           }
           if(temp>high){
               low=mid;
               mid=high;
               high=temp;
           }
           else if(temp<high&&temp>mid||flag_mid==0&&flag_low==0&&temp<high){
               low=mid;
               mid=temp;
               if(low>mid){
                   int temp1=low;
                   low=mid;
                   mid=temp1;
                   flag_low=1;
                   index++;
                   continue;
               }
               flag_mid=1;
           }
           else if(temp<mid&&temp>low||flag_low==0&&temp<mid){
               low=temp;
               flag_low=1;
           }
            index++;
        }

        if(low==mid||flag_low==1&&flag_mid==0&&mid==high)return high;
        return low;

    }

    @Test
    public void test2(){
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }
    public int minPathSum(int[][] grid) {
        int[][] temp=new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0){
                    if(j>0){
                        temp[i][j]=temp[i][j-1]+grid[i][j];
                    }
                    else temp[i][j]=grid[i][j];

                }
                else if(j==0){
                    temp[i][j]=temp[i-1][j]+grid[i][j];

                }
                else{
                    temp[i][j]=Math.min(temp[i-1][j],temp[i][j-1])+grid[i][j];
                }
            }
        }
        return temp[temp.length-1][temp[0].length-1];

    }

    @Test
    public void test3(){
        System.out.println(isPalindrome("Sore was I ere I saw Eros."));

    }
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>>temp=new ArrayList<>();
        int row=triangle.size();
        int now=0;
        while(now<row){
            List<Integer>t=triangle.get(now);
            if(now!=0){
                List<Integer>up=temp.get(now-1);
                for(int i=0;i<t.size();i++){
                    if(i==0){
                        t.set(0,up.get(0)+t.get(0));
                    }
                    else if(i==t.size()-1){
                        t.set(i,up.get(i-1)+t.get(i));
                    }
                    else{
                        t.set(i,Math.min(up.get(i-1),up.get(i))+t.get(i));
                    }
                }
            }

            temp.add(t);
            now++;
            if(now==row)return Collections.min(t);
        }
        return 0;
    }

    public boolean isPalindrome(String s) {
        int index_left=0,index_right=s.length()-1;
        while(index_left<index_right){
            char left=s.charAt(index_left),right=s.charAt(index_right);
            if(!(left>='a'&&left<='z'||left>='A'&&left<='Z'||left>='0'&&left<='9')){
                index_left++;
            }
            else if(!(right>='a'&&right<='z'||right>='A'&&right<='Z'||right>='0'&&right<='9')){
                index_right--;
            }
            else{
                if(left>='A'&&left<='Z')left= (char) (left+32);
                else if(right>='A'&&right<='Z')right= (char) (right+32);
                if(left!=right)return false;
                index_left++;
                index_right--;
            }
        }
        return true;

    }

    @Test
    public void test4(){
        System.out.println(longestConsecutive(new int[]{100,4,200,1,4,3,2,2}));
    }
    public int longestConsecutive(int[] nums) {
        Set<Integer>set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int ans=0;
        int current,currentLength;
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i]-1))continue;
            current=nums[i];
            currentLength=1;
            while(set.contains(current+1)){
                currentLength++;
                current++;
            }
            if(currentLength>ans)ans=currentLength;
        }
        return ans;
    }

    @Test
    public void test5(){
        System.out.println(countSegments("Hello, , my name is John"));

    }
    public int countSegments(String s) {
        int ans=0;
        int flag=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(flag==0&&ch!=' '){
                ans++;
                flag=1;
            }
            if(ch==' ')flag=0;
        }
        return ans;
    }

    @Test
    public void test6(){
        System.out.println(licenseKeyFormatting("2-5g-3-J",2));
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuffer stringBuffer=new StringBuffer();
        int currentLength=0;
        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if(ch=='-')continue;
            if(currentLength>=k){
                currentLength=0;
                stringBuffer.append('-');
            }
            if(ch>='a'&&ch<='z')ch= (char) (ch-32);
            stringBuffer.append(ch);
            currentLength++;
        }
        return String.valueOf(stringBuffer.reverse());

    }


}
