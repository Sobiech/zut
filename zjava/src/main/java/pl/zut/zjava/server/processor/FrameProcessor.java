package pl.zut.zjava.server.processor;

public interface FrameProcessor<E, T> {

    T processFrame(E inputFrame);

}
