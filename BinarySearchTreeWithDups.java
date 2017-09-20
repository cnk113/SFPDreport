//Chang Kim

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperIterative(newEntry);
		return result;
	}

	// ??? IMPLEMENT THIS METHOD
	private T addEntryHelperIterative(T newEntry) {
		// IMPORTANT: METHOD MUST BE ITERATIVE!!
		BinaryNodeInterface<T> currentNode = getRootNode();
		boolean found = false;
		T result = null;
		boolean dup = false;
		while(!found){
			T currentEntry = currentNode.getData();
			int comparison = newEntry.compareTo(currentEntry);
			if(comparison == 0){
				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else{
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}			
			}
			else if(comparison < 0){
				if (currentNode.hasLeftChild())
					currentNode = currentNode.getLeftChild();
				else{
					found = true;
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				}
			}
			else{
				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else{
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
			}
		}
		return result;
	}
	
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> currentNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		while(currentNode != null){
			T currentEntry = currentNode.getData();
			int comparison = searchVal.compareTo(currentEntry);
			if(comparison == 0){
				entryList.add(currentNode.getData());
				currentNode = currentNode.getRightChild();
			}
			else if(comparison < 0)
				currentNode = currentNode.getLeftChild();	
			else
				currentNode = currentNode.getRightChild();
		}		
		return entryList;
	}
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();
		if(!isEmpty()){
			BinaryNodeInterface<T> rootNode = getRootNode();
			getAllEntriesHelper(searchVal, rootNode, entryList);
		}
		return entryList;
	}
	public void getAllEntriesHelper(T val, BinaryNodeInterface<T> currentNode, ArrayList<T> list){
		T currentEntry = currentNode.getData();
		int comparison = val.compareTo(currentEntry);
		if(comparison == 0){
			list.add(currentEntry);
			if(currentNode.hasRightChild())
				getAllEntriesHelper(val,currentNode.getRightChild(),list);
		}
		else if(comparison < 0 && currentNode.hasLeftChild())
			getAllEntriesHelper(val,currentNode.getLeftChild(),list);

		else if(currentNode.hasRightChild())
			getAllEntriesHelper(val,currentNode.getRightChild(),list);
	}
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();
		// Hint: consider using a stack to mimic recursion!
		Stack<BinaryNodeInterface<T>> nodeStack = new
		Stack<BinaryNodeInterface<T>>();
		nodeStack.push(getRootNode());
		while(!nodeStack.isEmpty()){
			BinaryNodeInterface<T> currentNode = nodeStack.pop();
			if(currentNode != null){
				T currentEntry = currentNode.getData();
				int comparison = searchVal.compareTo(currentEntry);
				if(comparison > 0){
					entryList.add(currentEntry);
					nodeStack.push(currentNode.getLeftChild());
					nodeStack.push(currentNode.getRightChild());
				}
				else if(comparison == 0){
					nodeStack.push(currentNode.getLeftChild());
					nodeStack.push(currentNode.getRightChild());		
				}
				else
					nodeStack.push(currentNode.getLeftChild());
			}
		}
		return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();
		if(!isEmpty()){
			BinaryNodeInterface<T> rootNode = getRootNode();
			getAllEntriesLessThanHelper(searchVal, rootNode, entryList);
		}	
		return entryList;
	}
	public void getAllEntriesLessThanHelper(T val, BinaryNodeInterface<T> currentNode, ArrayList<T> list){
		T currentEntry = currentNode.getData();
		int comparison = val.compareTo(currentEntry);
		if(comparison > 0){
			list.add(currentEntry);
			if(currentNode.hasLeftChild())
				getAllEntriesLessThanHelper(val,currentNode.getLeftChild(),list);
			if(currentNode.hasRightChild())
				getAllEntriesLessThanHelper(val,currentNode.getRightChild(),list);
		}
		else if(comparison == 0){
			if(currentNode.hasLeftChild())
				getAllEntriesLessThanHelper(val,currentNode.getLeftChild(),list);
			if(currentNode.hasRightChild())
				getAllEntriesLessThanHelper(val,currentNode.getRightChild(),list);
		}
		else if(currentNode.hasLeftChild())
			getAllEntriesLessThanHelper(val,currentNode.getLeftChild(),list);
	}
	public int calculateLeftHeight(){
		BinaryNodeInterface<T> currentNode = getRootNode().getLeftChild();
		int height = 0;
		if(currentNode != null)
			return height = 1 + calculateHeight(currentNode);
		else if (!isEmpty())
			return 1;
		else
			return 0;
	}
	public int calculateHeight(BinaryNodeInterface<T> currentNode){
		int right = 0;
		int left = 0;
		if(currentNode != null){
			if(currentNode.hasRightChild()){
				right = calculateHeight(currentNode.getRightChild());
			}
			else if(currentNode.hasLeftChild()){
				left = calculateHeight(currentNode.getLeftChild());
			}
			else
				return 0;
			if(right > left)
				return right + 1;
			else
				return left + 1;
		}
		else
			return 0;
	}
	public int calculateRightHeight(){
		BinaryNodeInterface<T> currentNode = getRootNode().getRightChild();
		int height = 0;
		if(currentNode != null)
			return height = 1 + calculateHeight(currentNode);
		else if (!isEmpty())
			return 1;
		else
			return 0;
	}
}