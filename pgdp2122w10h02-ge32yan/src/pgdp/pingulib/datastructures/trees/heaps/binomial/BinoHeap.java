package pgdp.pingulib.datastructures.trees.heaps.binomial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BinoHeap<T extends Comparable<T>> {

	private ArrayList<BinoNode<T>> roots;
	private BinoNode<T> min;

	public BinoHeap() {
		roots = new ArrayList<>();
		min = null;
	}

	protected ArrayList<BinoNode<T>> getRoots() {
		return roots;
	}

	protected void setRoots(ArrayList<BinoNode<T>> roots) {
		this.roots = roots;
	}

	protected BinoNode<T> getMin() {
		return min;
	}

	protected void setMin(BinoNode<T> min) {
		this.min = min;
	}

	public boolean isEmpty() {
		return roots.isEmpty();
	}

	/**
	 * insert new entry into the heap
	 * 
	 * @param value entry that will be inserted
	 */
	public void insert(T value) {
		BinoNode<T> node = new BinoNode<>(value);

		if (getRoots().isEmpty()){
			roots.add(node);
			setMin(node);
			return;
		}

		getRoots().add(node);
		helpInsert(node);

		setRoots((ArrayList<BinoNode<T>>) getRoots().stream().sorted(Comparator.comparing(BinoNode::order)).collect(Collectors.toList()));

		if (value.compareTo(findMin()) < 0){
			setMin(node);
		}
	}

	private void helpInsert(BinoNode<T> node){

		List<BinoNode<T>> list = getRoots().stream().filter(bino -> bino.order() == node.order() && !bino.equals(node)).collect(Collectors.toList());
		if (!list.isEmpty()) {
			BinoNode<T> same = list.get(0);
			if (same.getValue().compareTo(node.getValue()) < 0) {
				same.addChild(node);
				getRoots().remove(node);
				helpInsert(same);
			} else {
				node.addChild(same);
				getRoots().remove(same);
				helpInsert(node);
			}
		}
	}

	/**
	 * merge two heaps
	 * 
	 * @param heap BinoHeap that is merged onto this heap
	 */
	public void merge(BinoHeap<T> heap) {
		// TODO
	}

	/**
	 * retrieves the current min-value
	 * 
	 * @return current min-value
	 */
	public T findMin() {
		if (getRoots().isEmpty()){
			return null;
		}
		else {
			return getMin().getValue();
		}
	}

	/**
	 * removes current min-value from heap
	 */
	public void delMin() {
		// TODO
	}

	/**
	 * removes and retrieves current min-value
	 * 
	 * @return current min-value
	 */
	public T min() {
		if (findMin() == null){
			return null;
		}
		else {
			delMin();
			return findMin();
		}
	}

	/**
	 * decreases the value of an entry
	 * 
	 * @param key   old value
	 * @param value new value
	 */
	public void decreaseKey(T key, T value) {
		// TODO
	}

	@Override
	public String toString() {
		String nodes = "";

		for (BinoNode<T> node : getRoots()){
			nodes += node + "\n";
		}

		return "min: " + (findMin()==null?"-":findMin()) + "\nrootlist:\n{\n" + nodes + "}";
	}

}
