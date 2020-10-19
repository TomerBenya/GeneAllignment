import java.util.Scanner;

public class Solution {
	public static final int MOD = 1000000009;
	public static int mod(int l) {
		
		if (l>=1000000009) {
			return (l%1000000009);
		}
		else if (l<0) {
			return (((l%1000000009)+1000000009)%1000000009);
		}
		
		return l;
	}
	public static int max(int a, int b, int c) {
		if (a>=b && a>=c) {
			return a;
		}
		else if(b>=a && b>=c) {
			return b;
		}
		else if(c>=a && c>=b) {
			return c;
		}
		else {
			return Math.max(Math.max(a, b), c);
		}
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String a=scan.nextLine();
		String b=scan.nextLine();
		findScore(a,b);
		scan.close();
	}
	public static void findScore(String a, String b) {
		// TODO Auto-generated method stub
		//make dp table
		int[][] dp = new int[a.length()+1][b.length()+1];
		int[][] at = new int[a.length()+1][b.length()+1];

		//calculating max score
		
		for (int i=0;i<dp.length;i++) {
			for (int j=0;j<dp[i].length;j++) {
				if (i==0 && j==0) {
					dp[i][j]=0;
					continue;
				}
				if (i == 0 && j!=0) {
					dp[i][j]=dp[i][j-1] -1;
				}
				else if (i != 0 && j==0) {
					dp[i][j]=dp[i-1][j] -1;
				}
				else {
					int s1,s2,s3;
					
					if (a.charAt(i-1) == b.charAt(j-1)) {
						s1 = dp[i-1][j-1] + 2;
					}
					else {
						s1 = dp[i-1][j-1] - 1;
					}
					s2=dp[i-1][j]-1;
					s3=dp[i][j-1]-1;
					dp[i][j]+=Math.max(Math.max(s2,s3 ), s1);
				}		
				}
			}
			
			
			for (int i=0;i<at.length;i++) {
				for (int j=0;j<at[i].length;j++) {
					if (i==0 || j==0) {
						at[i][j]=1;
					}
					else {
						int s1,s2,s3;
						
						if ((dp[i][j])==(dp[i-1][j]-1)) {at[i][j]=mod(at[i][j]+at[i-1][j]);}
						
						
						if ((dp[i][j])==(dp[i][j-1]-1)) {at[i][j]=mod(at[i][j]+at[i][j-1]);}
						
						
						if ((dp[i][j]==(dp[i-1][j-1]-1) && a.charAt(i-1) != b.charAt(j-1))||((dp[i][j]==dp[i-1][j-1]+2) && a.charAt(i-1) == b.charAt(j-1))) 
						{at[i][j]=mod(at[i][j]+at[i-1][j-1]);}
						
//						System.out.println("" + s1 + s2 + s3);
						
//						at[i][j] += mod((s1+s2+s3));
//						System.out.println(at[i][j]);
					}
				}
			}
			System.out.println(dp[a.length()][b.length()]);
			System.out.println(mod(at[a.length()][b.length()]));
		}
	}