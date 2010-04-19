import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

abstract class TreeNode implements Iterable<TreeNode> {

  private Set<TreeNode> children;

  public TreeNode() {
    children = new HashSet<TreeNode>();
  }

  public boolean addChild(TreeNode n) {
    return children.add(n);
  }

  public boolean removeChild(TreeNode n) {
    return children.remove(n);
  }

  public Iterator<TreeNode> iterator() {
    return children.iterator();
  }
  
  public int getSize()
  {
	  return children.size();
  }
}

