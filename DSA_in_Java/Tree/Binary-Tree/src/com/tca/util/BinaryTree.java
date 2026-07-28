package com.tca.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree <E extends Comparable<E>> {

	private class TreeNode{
		E data;
		TreeNode left;
		TreeNode right;
		
		TreeNode(E data){
			this.data = data;
		}
		
		TreeNode(E data, TreeNode left, TreeNode right){
			this.data = data;
			this.right = right;
			this.left = left;
		}
	}
	
	private TreeNode root;
	private int size;
	private List<TreeNode> path;
	
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(E data) {
		if(root == null) {
			root = new TreeNode(data);
		}
		else {
			Queue<TreeNode> q = new LinkedList<>();
			q.offer(root);
			
			while(! q.isEmpty()) {
				TreeNode node = q.poll();
				
				if(node.left == null) {
					if(node.data != null) {
						node.left = new TreeNode(data);
						break;						
					}
				}
				else {
					q.offer(node.left);
				}
				
				if(node.right == null) {
					if(node.data != null) {
						node.right = new TreeNode(data);
						break;						
					}
				}
				else {
					q.offer(node.right);
				}
			}
		}
		size++;
	}
	
	public boolean isSame(TreeNode anotherRoot) {
		boolean result = true;
		
		return result;
	}
	
	
	public ArrayList<ArrayList<E>> levelOrder(){
  		ArrayList<ArrayList<E>> levels = new ArrayList<>();
  		Queue<TreeNode> q = new LinkedList<>();
  		ArrayList<E> cur = new ArrayList<>();
  		
  		if(root == null)
  			return levels;
  
  		/*
  		q.offer(root);
  		q.offer(null);
  		
  		while(! q.isEmpty()) {
  			TreeNode node = q.poll();
  			
  			if(node != null) {
  				cur.add(node.data);

  				if(node.left != null)
  					q.offer(node.left);
  				if(node.right != null)
  					q.offer(node.right);
  			}
  			else {
  				ArrayList<E> cur_copy = new ArrayList<E>(cur);
  				levels.add(cur_copy);
  				cur.clear();
  				
  				if(! q.isEmpty())
  					q.offer(null);
  			}
  		}
  		*/
  		
  		q.offer(root);
  		while(! q.isEmpty()) {
  			ArrayList<E> level = new ArrayList<>();
  			int size = q.size();
  			
  			for(int i = 0; i < size; ++i) {
  				TreeNode node = q.poll();
  				if(node != null) {
  					level.add(node.data);
  					
  					if(node.left != null)
  						q.offer(node.left);
  					if(node.right != null)
  						q.offer(node.right);
  				}
  				
  			}
  			
  			levels.add(level);
  			
  		}
  		
  		return levels;
  	}
	
	public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        q.offer(root);
        while( ! q.isEmpty() ){
            int size = q.size();

            for(int i = 0; i < size; ++i){
                TreeNode node = q.poll();

               
                list.add((Integer)node.data);

                if(node.left != null ){
                    q.offer(node.left);
                }
                else{
                    list.add(Integer.MIN_VALUE);
                }


                if(node.right != null ){
                    q.offer(node.right);
                }
                else{
                    list.add(Integer.MIN_VALUE);
                }

            }

            if( ! isPalindrome(list) ) {
            	System.out.println("False");
            	return false;
            }
            else {
            	System.out.println("True");
            }

            list.clear(); 
        }

        return true;
    }

    private boolean isPalindrome(ArrayList<Integer> list){
        int i = 0, j = list.size() - 1;

        while(i < j){
            if(list.get(i++).compareTo( list.get(j--)) != 0)
                return false;
        }

        return true;
    }
    
    public void delete(E data) {
    	if(root == null)
    		return ;
    	
    	TreeNode nodeToDelete = null;
    	TreeNode deepestNode = null;
    	TreeNode deepestNodeParent = null;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
 
    	while(! q.isEmpty() ) {
    		deepestNode = q.poll();
    		
    		if(deepestNode.data == null && data == null)
    			nodeToDelete = deepestNode;
    		else if(deepestNode.data != null && deepestNode.data.compareTo(data) == 0) {
    			nodeToDelete = deepestNode;
    		}
    		
    		if(deepestNode.left != null) {
    			deepestNodeParent = deepestNode;
    			q.offer(deepestNode.left);
    		}
    		
    		if(deepestNode.right != null) {
    			deepestNodeParent = deepestNode;
    			q.offer(deepestNode.right);
    		}
    	}
    	
    	nodeToDelete.data = deepestNode.data;
    	
    	if(deepestNodeParent == null)
    		root = null;
    	else {
    		if(deepestNodeParent.right == deepestNode)
    			deepestNodeParent.right = null;
    		else if(deepestNodeParent.left == deepestNode)
    			deepestNodeParent.left = null;
    	}
    }
    
    public boolean areStructurallySimilar(BinaryTree<E> tree) {
    	return areStructurallySimilar(root, tree.root);
    } 

    private boolean areStructurallySimilar(TreeNode root1, TreeNode root2) {
    	if(root1 == null && root2 == null)
    		return true;
    	if(root1 == null || root2 == null)
    		return false;
    	
    	return areStructurallySimilar(root1.left, root2.left) && 
    			areStructurallySimilar(root1.right, root2.right);
    
    }
    
    public int width(int depth) {
    	return width(root, depth);
    }
    
    private int width(TreeNode root, int depth) {
    	if(root == null)
    		return 0;
    	else
    		if(depth == 0)
    			return 1;
    		else
    			return width(root.left, depth - 1 ) + width(root.right, depth - 1);
    }
    
    public void printPathToLeafNodes() {
    	path = new ArrayList<>();
    	printPathToLeafNodes(root);
    }
    
    private void printPathToLeafNodes(TreeNode node) {
    	if(node == null)
    		return;
    	
    	path.add(node);
    	
    	if(node.left == null && node.right == null)
    			printPath();
    	printPathToLeafNodes(node.left);
    	printPathToLeafNodes(node.right);
    	path.remove(path.size() - 1);
    }
    
    private void printPath() {
    	for(TreeNode node : path ) {
    		System.out.print(node.data + " | ");
    	}
    	System.out.println();
    }
    
}
