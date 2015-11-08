package com.luyue.acm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.PriorityBlockingQueue;

public class Solution {
	public String longestPalindrome(String s) {
		int max = 0;
		int pos = 0;
		boolean isOdd = true;
		for(int i=0;i<s.length();i++){
			int cnt = 0;
			while(i-1-cnt>=0 && i+cnt<s.length() && s.charAt(i-1-cnt)==s.charAt(i+cnt)){
				cnt++;
			}
			if(2*cnt>max){
				pos = i;
				isOdd = false;
				max = 2*cnt;
			}
			cnt = 0;
			while(i-cnt>=0 && i+cnt<s.length() && s.charAt(i-cnt)==s.charAt(i+cnt)){
				cnt++;
			}
			if((2*cnt-1)>max){
				pos = i;
				max = 2*cnt-1;
				isOdd = true;
			}
		}
		if(isOdd){
			return s.substring(pos-max/2, pos+max/2+1);
		}else{
			return s.substring(pos-max/2,pos+max/2);
		}
    }
	public String convert(String s, int numRows) {
		if(numRows==1){
			return s;
		}
        StringBuilder[] sbs = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
        	sbs[i] = new StringBuilder();
        }
        int t = 0;
        boolean isDown = true;
        for(int i=0;i<s.length();i++){
        	sbs[t].append(s.charAt(i));
        	if(isDown){
        		if(t<numRows-1){
        			t++;
        		}else{
        			t--;
        			isDown = false;
        		}
        	}else{
        		if(t>0){
        			t--;
        		}else{
        			t++;
        			isDown = true;
        		}
        	}
        }
        String res = "";
        for(int i=0;i<numRows;i++){
        	res = res + sbs[i].toString();
        }
        return res;
    }
	public int reverse(int x) {
		if(x==Integer.MIN_VALUE){
			return 0;
		}
        int abs = Math.abs(x);
        int r = 0;
        while(abs/10>0){
        	r = r*10 + abs%10;
        	abs = abs/10;
        	System.out.println(r+" "+abs);
        }
        if(r!=0 && (Integer.MAX_VALUE-abs)/r<10){
        	return 0;
        }
        r = r*10 + abs;
        if(x<0){
        	return -r;
        }else{
        	return r;
        }
    }
	public int myAtoi(String str) {
		str = str.trim();
		if("".equals(str))
			return 0;
		char c = str.charAt(0);
		if(c!='+'&&c!='-'&&(c<'0'||c>'9'))
			return 0;
		boolean isPositive = true;
		if(c=='-'){
			isPositive = false;
		}
		int r = 0;
		if(c>='0'&&c<='9'){
			r = c-'0';
		}
		for(int i=1;i<str.length();i++){
			c = str.charAt(i);
			if(c>='0'&&c<='9'){
				if(r==0||(Integer.MAX_VALUE-(c-'0'))/r>=10){
					r = r*10 + (c-'0');
				}else{
					if(isPositive){
						return Integer.MAX_VALUE;
					}else{
						return Integer.MIN_VALUE;
					}
				}
			}else{
				break;
			}
		}
		if(isPositive){
			return r;
		}else{
			return -r;
		}
    }
	public boolean isPalindrome(int x) {
		if(x==Integer.MIN_VALUE){
			return false;
		}
		String str;
		if(x<0){
			str = String.valueOf(-x);
		}else{
			str = String.valueOf(x);
		}
		for(int i=0;i<str.length()/2;i++){
			if(str.charAt(i)!=str.charAt(str.length()-i-1)){
				return false;
			}
		}
		return true;
    }
	
	// TODO unfinished
	public boolean isMatch(String s, String p) {
		if ("".equals(p))
			return "".equals(s); 
		if(p.length()>1 && p.charAt(1)=='*'){
			if(p.charAt(0)=='.'){
				if(!isMatch(s.substring(1), p.substring(2))){
					return s.length()>2?isMatch(s.substring(2), p):false;
				}
			}
			int i=0;
			while(i< s.length() && p.charAt(0)==s.charAt(i)){
				i++;
			}
			return isMatch(s.substring(i), p.substring(2));
		}else{
			if((p.length()>0 && s.length()>0 && p.charAt(0)==s.charAt(0))||(p.length()>0&&'.'==p.charAt(0)&&!"".equals(s))){
				return isMatch(s.substring(1), p.substring(1));
			}
		}
		return false;
    }
	
    public int maxArea(int[] height) {
    	int max = 0;
        int left = 0;
        int right = height.length-1;
        while(left<right){
        	int minHeight = Math.min(height[left], height[right]);
        	int area = minHeight*(right-left);
        	max = area>max?area:max;
        	if(height[left]<height[right]){
        		left++;
        	}else{
        		right--;
        	}
        }
        return max;
    }
    
    public String longestCommonPrefix(String[] strs) {
    	if(strs.length==0){
    		return "";
    	}
    	int j = 0;
    	while(true){
    		if(j>=strs[0].length())
    			return strs[0].substring(0, j);
    		char c = strs[0].charAt(j);
    		for(int i=0;i<strs.length;i++){
    			if(strs[i].length()<=j||strs[i].charAt(j)!=c){
    				if(j!=0){
    					return strs[0].substring(0, j);
    				}else{
    					return "";
    				}
    				
    			}
    		}
    		j++;
    	}
    }
    public void insertSort(int[] nums){
    	for(int i=0;i<nums.length;i++){
    		int temp = nums[i];
    		int j = i;
    		for(;j>0&&temp<nums[j-1];j--){
    			nums[j] = nums[j-1];
    		}
    		nums[j] = temp;
    	}
    }
    // TODO Time Limit Exceeded
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	if(nums.length<3){
    		return result;
    	}
    	insertSort(nums);
    	
    	for(int i=0;i<nums.length;i++){
    		if(i!=0&&nums[i]==nums[i-1]){
    			continue;
    		}
    		int p = i+1;
    		int q = nums.length-1;
    		int sum = 0;
    		while(p<q){
    			sum = nums[i]+nums[p]+nums[q];
    			if(sum==0){
    				List<Integer> triplets = new ArrayList<>();
    				triplets.add(nums[i]);
    				triplets.add(nums[p]);
    				triplets.add(nums[q]);
    				result.add(triplets);
    				while(++p<q&&nums[p-1]==nums[p]){
    					
    				}
    				while(--q>p&&nums[q+1]==nums[q]){
    					
    				}
    			}
    			else if(sum<0){
    				p++;
    			}else{
    				q--;
    			}
    		}
    	}
		return result;
    }
    // AC
    public int threeSumClosest(int[] nums, int target) {
    	if(nums.length<3){
    		return -1;
    	}
        int min = Integer.MAX_VALUE;
        insertSort(nums);
        int diff = 0;
        for(int i=0;i<nums.length;i++){
    		int p = i+1;
    		int q = nums.length-1;
    		while(p<q){
    			diff = nums[i]+nums[p]+nums[q]-target;
    			if(Math.abs(min)>Math.abs(diff)){
    				min = diff;
    			}
    			if(diff==0){
    				return target;
    			}
    			else if(diff<0){
    				p++;
    			}else if(diff>0){
    				q--;
    			}
    		}
    	}
        return target+min;
    }
    // AC
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        int n = digits.length();
        if(n<=0){
        	return list;
        }
        int[] numbs = new int[n];
        int[] max = new int[n];
        for(int i=0;i<n;i++){
        	if(digits.charAt(i)=='7' || digits.charAt(i)=='9'){
        		max[i] = 3;
        	}else{
        		max[i]=2;
        	}
        }
        StringBuilder sb = new StringBuilder(n);
        sb.setLength(n);
        while(numbs[0]<=max[0]){
        	for(int i=0;i<n;i++){
        			sb.setCharAt(i, (char) (digits.charAt(i)>'7'?('a'+(digits.charAt(i)-'2')*3+numbs[i]+1):('a'+(digits.charAt(i)-'2')*3+numbs[i])));
        	}
        	list.add(sb.toString());
        	for(int i=n-1;i>=0;i--){
        		if(numbs[i]<max[i]){
        			numbs[i]++;
        			break;
        		}else{
        			if(i!=0){
        				numbs[i]=0;
        			}else{
        				numbs[i]++;
        			}
        		}
        	}
        }
        return list;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        while(p!=null&&n>0){
            p = p.next;
            n--;
        }
        if(p==null){
            return head.next;
        }
        ListNode q = head;
        while(p.next!=null){
            p = p.next;
            q = q.next;
        }
        if(q!=null){
            q.next = q.next.next;
        }
        return head;
    }
    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i)=='{'||s.charAt(i)=='('||s.charAt(i)=='['){
        		stack.push(""+s.charAt(i));
        	}
        	if(s.charAt(i)=='}'||s.charAt(i)==')'||s.charAt(i)==']'){
        		if(stack.isEmpty()){
        			return false;
        		}
        		char token = stack.pop().charAt(0);
        		if((s.charAt(i)=='}'&&token=='{')||(s.charAt(i)==']'&&token=='[')||(s.charAt(i)==')'&&token=='(')){
        		
        		}else{
        			return false;
        		}
        	}
        }
        if(!stack.isEmpty()){
        	return false;
        }
        return true;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode p = null;
        ListNode header = null;
        if(l1.val>l2.val){
            p = l2;
            l2 = l2.next;
        }else{
            p = l1;
            l1 = l1.next;
        }
        header = p;
        while(l1!=null&&l2!=null){
            if(l1.val>l2.val){
                p.next = l2;
                l2 = l2.next;
            }else{
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        if(l1!=null){
            p.next = l1;
        }else{
            p.next = l2;
        }
        return header;
    }
    
    /*
    left and right represents the remaining number of ( and ) that need to be added. 
    When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops. 
    */
    public void dfs(List<String> result, String s, int left, int right){
        if(left > right)
            return;
     
        if(left==0&&right==0){
            result.add(s);
            return;
        }
     
        if(left>0){
            dfs(result, s+"(", left-1, right);
        }
     
        if(right>0){
            dfs(result, s+")", left, right-1);
        }
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
		PriorityBlockingQueue<ListNode> heap = new PriorityBlockingQueue<ListNode>(10, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode n1, ListNode n2) {
				return n1.val - n2.val;
			}
		});

		for (int i = 0; i < lists.length; i++) {
			ListNode node = lists[i];
			if (node != null) {
				heap.offer(node);
			}
		}
		ListNode head = null;
		ListNode pre = head;
		while (heap.size() > 0) {
			ListNode cur = heap.poll();
			if (head == null) {
				head = cur;
				pre = head;
			} else {
				pre.next = cur;
			}
			pre = cur;
			if (cur.next != null)
				heap.offer(cur.next);
		}
		return head;
		
	}
    
    public ListNode swapPairs(ListNode head) {
    	if(head==null){
    		return null;
    	}
        ListNode pre = null;
        ListNode mid = head;
        ListNode next = null;
        head = head.next;
        while(mid!=null){
        	next = mid.next;
        	if(pre!=null){
        		pre.next = mid;
        	}
        	if(next==null){
        		break;
        	}
        	pre = mid;
        	mid.next = next.next;
        	next.next = mid;
        	mid = pre.next;
        }
        return head;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null){
            return null;
        }
        int len = 1;
        ListNode temp = head;
        while(temp.next!=null){
            len++;
            temp=temp.next;
        }
        if(len<k){
            return head;
        }
        ListNode newHead = head;
        int n = k;
        while(--n>0){
            newHead = newHead.next;
        }
        ListNode tail = null;
        ListNode next = null;
        ListNode preTail = null;
        for(int i=0;i<len/k;i++){
            int j=k;
            tail = head;
            next = head.next;
            while(--j>0&&next!=null){
                tail.next = next.next;
                next.next = head;
                head = next;
                next = tail.next;
            }
            if(preTail!=null){
                preTail.next = head;
            }
            preTail = tail;
            head = tail.next;
        }
        
        return newHead;
    }
    public int removeDuplicates(int[] nums) {
        if(nums.length==0){
            return 0;
        }
         int newLen = 0;
         for(int i=0;i<nums.length;i++){
             if(nums[i]>nums[newLen]){
                 nums[++newLen] = nums[i];
             }
         }
         return ++newLen;
    }
    public int removeElement(int[] nums, int val) {
        int newLen = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[newLen++] = nums[i];
            }
        }
        return newLen;
    }
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
    public int divide(int dividend, int divisor) {
        if(divisor == 0){
            return Integer.MAX_VALUE;
        }
        
        int res = 0;
        if(dividend == Integer.MIN_VALUE){
            if(divisor == -1){
                return Integer.MAX_VALUE;
            }
            dividend = dividend + Math.abs(divisor);
            res++;
        }
        
        if(divisor == Integer.MIN_VALUE){
            return res;
        }
        
        boolean isNeg = (dividend^divisor)>>>31==1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int cnt = 0;
        while(divisor<=(dividend>>1)){
            cnt++;
            divisor <<= 1;
        }
        while(cnt>=0){
            if(dividend>=divisor){  
                res += 1<<cnt;  
                dividend -= divisor;  
            }  
            divisor >>= 1;
            cnt--;
        }
        
        return isNeg?-res:res;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        int wordCnt = words.length;
        int wordLen = words[0].length();
        int targetLen = wordCnt*wordLen;
        int offset = 0;
        
        while(offset+targetLen<s.length()){
        	String source = s.substring(offset);
        	int i = 0;
        	for(;i<wordCnt;i++){
        		int firstIndex = source.indexOf(words[i]);
        		if(firstIndex<0){
        			offset = offset+targetLen;
        			break;
        		}else{
        			while(firstIndex!=-1&&(firstIndex+1)%wordLen!=0){
        				firstIndex = source.substring(firstIndex-firstIndex%wordLen+wordLen, offset+targetLen).indexOf(words[i]);
        			}
        			if(firstIndex<0){
        				offset = offset+targetLen;
        				break;
        			}
        		}
        	}
        	if(i==wordCnt){
        		
        	}
        	result.add(new Integer(offset));
        	offset++;
        }
        
        return result;
    }
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            int[] row = new int[9];
            int[] col = new int[9];
            int[] subBoard = new int[9];
            for(int j=0;j<9;j++){
                if(board[i][j] == '.'){
                    
                }else if(row[board[i][j]-'1']>0){
                    return false;
                }else{
                    row[board[i][j]-'1'] = 1;
                }
                if(board[j][i] == '.'){
                    
                }else if(col[board[j][i]-'1']>0){
                    return false;
                }else{
                    col[board[j][i]-'1'] = 1;
                }
                if(board[i-i%3+j/3][i%3*3+j%3] == '.'){
                    
                }else if(subBoard[board[i-i%3+j/3][i%3*3+j%3]-'1']>0){
                    return false;
                }else{
                    subBoard[board[i-i%3+j/3][i%3*3+j%3]-'1'] = 1;
                }
            }
        }
        return true;
    }
    // TODO: amazing!!!
//    The idea is very simple. Begin scan from beginning and end of array. Compare value of left and right pointer, hold the greater one and move the other to inner array. Compute passed area when pointer gets inner.
    public int trap(int[] height) {
        int secHight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                secHight = Math.max(height[left], secHight);
                area += secHight - height[left];
                left++;
            } else {
                secHight = Math.max(height[right], secHight);
                area += secHight - height[right];
                right--;
            }
        }
        return area;
    }
}
