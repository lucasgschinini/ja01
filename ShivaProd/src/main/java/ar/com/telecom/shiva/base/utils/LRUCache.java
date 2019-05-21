package ar.com.telecom.shiva.base.utils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache<K, V> {
	
	/**
	 * Capacidad máxima de la cache 
	 */
	private final int capacidad;
	
	/**
	 * Queue (pila) para guardar las claves utilizadas recientemente.
	 */
	private final ConcurrentLinkedQueue<K> queue;
	
	/**
	 * Mapa clave-valor 
	 */
	private final ConcurrentHashMap<K, V> map;

	public LRUCache(final int capacidad) {
		this.capacidad = capacidad;
		this.queue = new ConcurrentLinkedQueue<K>();
		this.map = new ConcurrentHashMap<K, V>(capacidad);
	}
	
	public Boolean containsKey(K key) {
		return map.containsKey(key);
	}
	
	public synchronized void put(final K key, final V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Argumentos no válidos");
		}
		if (map.containsKey(key)) {
			queue.remove(key);
		}
		while (queue.size() >= capacidad) {
			K expiredKey = queue.poll();
			if (expiredKey != null) {
				map.remove(expiredKey);
			}
		}
		queue.add(key);
		map.put(key, value);
	} 
	
	public V get(final K key) {
		if (map.containsKey(key)) {
			queue.remove(key);
			queue.add(key);
			return map.get(key);
		}
		return null;
	}
	
	public synchronized void remove(final K key) {
		if (queue.contains(key)) queue.remove(key);
		if (map.containsKey(key)) map.remove(key);
	}
	
	public synchronized void clear() {
		queue.clear();
		map.clear();
	}
	
	public Set<K> keySet() {
		return map.keySet();
	}
	
}