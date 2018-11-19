package com.codigo.aplios.sdk.core;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// https://dzone.com/articles/letting-garbage-collector-do-c

/**
 * @author andrzej.radziszewski
 *
 * @param <K>
 *        Typ dancyh klucza kolekcji
 * @param <V>
 *        Typ danych elemetó kolkecji
 */
public class GarbageCollectionConcurrentMap<K, V> {

	public static void main(final String[] args) throws InterruptedException {

		String savePoint = new String(
			"Random"); // a strong object

		final ReferenceQueue<String> savepointQ = new ReferenceQueue<>();// the ReferenceQueue
		final WeakReference<String> savePointWRefernce = new WeakReference<>(
			savePoint, savepointQ);

		System.out.println("SavePoint created as a weak ref " + savePointWRefernce);
		Runtime.getRuntime()
				.gc();
		System.out.println("Any weak references in Q ? " + (savepointQ.poll() != null));
		savePoint = null; // the only strong reference has been removed. The heap
							// object is now only weakly reachable

		System.out.println("Now to call gc...");
		Runtime.getRuntime()
				.gc(); // the object will be cleared here - finalize will be called.

		System.out.println("Any weak references in Q ? " + (savepointQ.remove() != null));
		System.out
				.println("Does the weak reference still hold the heap object ? " + (savePointWRefernce.get() != null));
		System.out.println("Is the weak reference added to the ReferenceQ  ? " + (savePointWRefernce.isEnqueued()));

	}

	private final static ReferenceQueue<? super Object> referenceQueue = new ReferenceQueue<>();

	static {
		new CleanupThread().start();
	}

	private final ConcurrentMap<K, GarbageReference<K, V>> map = new ConcurrentHashMap<>();

	public void clear() {

		this.map.clear();
	}

	public V get(final K key) {

		final GarbageReference<K, V> ref = this.map.get(key);
		return ref == null ? null : ref.value;
	}

	public Object getGarbageObject(final K key) {

		final GarbageReference<K, V> ref = this.map.get(key);
		return ref == null ? null : ref.get();
	}

	public Collection<K> keySet() {

		return this.map.keySet();
	}

	public void put(final K key, final V value, final Object garbageObject) {

		if ((key == null) || (value == null) || (garbageObject == null))
			throw new NullPointerException();
		if (key == garbageObject)
			throw new IllegalArgumentException(
				"key can't be equal to garbageObject for gc to work");
		if (value == garbageObject)
			throw new IllegalArgumentException(
				"value can't be equal to garbageObject for gc to work");

		final GarbageReference<K, V> reference = new GarbageReference(
			garbageObject, key, value, this.map);
		this.map.put(key, reference);
	}

	/**
	 * @author dp0470
	 *
	 * @param <K>
	 * @param <V>
	 */
	static class GarbageReference<K, V> extends WeakReference<Object> {
		final K						key;
		final V						value;
		final ConcurrentMap<K, V>	map;

		GarbageReference(final Object referent, final K key, final V value, final ConcurrentMap<K, V> map) {

			super(referent, GarbageCollectionConcurrentMap.referenceQueue);
			this.key = key;
			this.value = value;
			this.map = map;
		}
	}

	/**
	 * @author dp0470
	 *
	 */
	static class CleanupThread extends Thread {
		CleanupThread() {

			setPriority(Thread.MAX_PRIORITY);
			setName("GarbageCollectingConcurrentMap-cleanupthread");
			setDaemon(true);
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			while (true)
				try {
					GarbageReference<?, ?> ref = (GarbageReference<?, ?>) GarbageCollectionConcurrentMap.referenceQueue
							.remove();
					while (true) {
						System.out.println(ref.key);
						ref.map.remove(ref.key);
						ref = (GarbageReference<?, ?>) GarbageCollectionConcurrentMap.referenceQueue.remove();
					}
				}
				catch (final InterruptedException e) {
					// ignore
				}
		}
	}
}