package easy;

import java.util.Scanner;

public class ProblemE {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[][] weight=new int[n][n];
        int[][] dp=new int[n][n];
        for(int i=0;i<n;i++){
            weight[i][i]=scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                //weight[i][j]
                weight[i][j]+=weight[i][j-1]+weight[j][j];

            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<n-i;j++){

                if(dp[j][j+i]==0)dp[j][j+i]=-1;

                for(int k=j;k<j+i;k++){
                    int min=Math.min(weight[j][k],weight[k+1][j+i]);
                    int max=Math.max(weight[j][k],weight[k+1][j+i]);
                    if(min>=10&&max%min==0){//不能合并
                        continue;
                    }
                    if(dp[j][j+i]==-1)dp[j][j+i]=min+max+dp[j][k]+dp[k+1][j+i];
                    else dp[j][j+i]=Math.min(dp[j][j+i],min+max+dp[j][k]+dp[k+1][j+i]);

                }
            }
        }
        System.out.println(dp[0][n-1]);
    }
}
