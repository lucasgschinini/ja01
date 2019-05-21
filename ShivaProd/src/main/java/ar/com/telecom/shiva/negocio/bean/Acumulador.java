package ar.com.telecom.shiva.negocio.bean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Acumulador <T> {
	private Map<T, _KeyAcumulador<T>> keyAcumular = new HashMap<T, _KeyAcumulador<T>>();
	private TreeSet<_KeyAcumulador<T>> tree = new TreeSet<_KeyAcumulador<T>>();
	
	public Acumulador() {
		
	}
	public void aAcumular(T key, BigDecimal aAcumular) {
		if (!this.keyAcumular.containsKey(key)) {
			this.keyAcumular.put(key, new _KeyAcumulador<T>(key, aAcumular));
		} else {
			this.keyAcumular.get(key).acumular(aAcumular);
		}
	}

	public void setKey(T key) {
		this.aAcumular(key, BigDecimal.ZERO);
	}
	public BigDecimal getAcumulado(T key) {
		if (keyAcumular.containsKey(key)) {
			return keyAcumular.get(key).getAccumulado();	
		}
		return null;
	}
	
	public void ordernar() {
		tree.addAll(this.keyAcumular.values());
	}
	public T getPrimeroKey() {
		return tree.first().getKey();
	}

	public List<T> getKeys() {
		List<T> keys = new ArrayList<T>();
		for (_KeyAcumulador<T> key : tree) {
			keys.add(key.getKey());
		}
		return keys;
	}
	/**
	 * @return the keyAcumular
	 */
	public Map<T, _KeyAcumulador<T>> getKeyAcumular() {
		return keyAcumular;
	}
	/**
	 * @param keyAcumular the keyAcumular to set
	 */
	public void setKeyAcumular(Map<T, _KeyAcumulador<T>> keyAcumular) {
		this.keyAcumular = keyAcumular;
	}
	/**
	 * @return the tree
	 */
	public TreeSet<_KeyAcumulador<T>> getTree() {
		return tree;
	}
	/**
	 * @param tree the tree to set
	 */
	public void setTree(TreeSet<_KeyAcumulador<T>> tree) {
		this.tree = tree;
	}
	// Metodos
	
	
	public class _KeyAcumulador <K> implements Comparable<_KeyAcumulador<K>>{
		private K key;
		private BigDecimal accumulado = BigDecimal.ZERO;

		public _KeyAcumulador(K key, BigDecimal aAcumular) {
			this.key = key;
			this.accumulado = this.accumulado.add(aAcumular);
		}
		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}
		/**
		 * @param key the key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}
		/**
		 * @return the accumulado
		 */
		public BigDecimal getAccumulado() {
			return accumulado;
		}
		/**
		 * @param accumulado the accumulado to set
		 */
		public void setAccumulado(BigDecimal accumulado) {
			this.accumulado = accumulado;
		}
		public void acumular(BigDecimal aAcumular) {
			this.accumulado = this.accumulado.add(aAcumular);
		}
	
		@Override
		public int compareTo(_KeyAcumulador<K> o) {
			return o.accumulado.compareTo(this.getAccumulado());
		}
	}
}
