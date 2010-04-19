import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

abstract class TreeNode<E> implements Iterable<TreeNode<E>> {

	private Set<TreeNode<E>> children;
	private E myObject;

	public TreeNode() {
		children = new HashSet<TreeNode<E>>();
	}

	public TreeNode(E n) {
		myObject = n;
		children = new HashSet<TreeNode<E>>();
	}

	public boolean addChild(TreeNode<E> n) {
		return children.add(n);
	}

	public boolean removeChild(TreeNode<E> n) {
		return children.remove(n);
	}

	public Iterator<TreeNode<E>> iterator() {
		return children.iterator();
	}

	public int getSize() {
		return children.size();
	}

	public E getObject() {
		return myObject;
	}
	
	public void setObject(E newobj) {
		myObject = newobj;
	}
}
