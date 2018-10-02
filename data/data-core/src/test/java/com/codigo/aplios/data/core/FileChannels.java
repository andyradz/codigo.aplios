package com.codigo.aplios.data.core;

public class FileChannels {

	public void randomAccess() {

		// random acces file
		/**
		 * long position; long size; position(long); truncate
		 *
		 *
		 */
		// final SeekableByteChannel file1Channel = Paths.get("file")
		// .newByteChannel();// default read
		// final SeekableByteChannel file2Channel = Paths.get("file")
		// .newByteChannel(StandardOperation.READ);
		//
		// try (SeekableByteChannel fileChannel = FileChannel.open(Paths.get(""), null)) {
		// final ByteBuffer readBuffer = ByteBuffer.allocate(1_024);
		//
		// System.out.println(String.format("File size: %d", fileChannel.size()));
		//
		// while (fileChannel.read(readBuffer) > 0) {
		// readBuffer.rewind();
		// System.out.print(new String(readBuffer.array(),
		// 0,
		// readBuffer.remaining()));
		// readBuffer.flip();
		// System.out.println("Curren position : " + fileChannel.position());
		//
		// }
		// } finally {
		//
		// }
		//
		// // ThreadFactory myThreadFactory = Executors.defaultThreadFactory();
		// // AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(25,
		// myThreadFactory);
		// // FileChannel.open(Paths.get(""));
		// try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(""), null)) {
		// final ByteBuffer readBuffer = ByteBuffer.allocate(1_024);
		// final Future<Integer> result = channel.read(readBuffer, 0);
		// final boolean isDone = result.isDone();
		//
		// // result.get(); czekamy na wynik i blokujemy dalsze przetwarzanie
		// final Future<Integer> result1 = channel.read(readBuffer, 100, null,
		// new CompletionHandler<Integer, ByteBuffer>() {
		//
		// @Override
		// public void completed(final Integer result, final ByteBuffer attachment) {
		//
		// // TODO Auto-generated method stub
		// }
		//
		// @Override
		// public void failed(final Throwable exc, final ByteBuffer attachment) {
		//
		// // TODO Auto-generated method stub
		// }
		//
		// });
		// }
	}

}
