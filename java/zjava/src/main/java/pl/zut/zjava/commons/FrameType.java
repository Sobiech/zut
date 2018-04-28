package pl.zut.zjava.commons;

public enum FrameType {

    F_GET_WORKER_LIST("F_WORKER_LIST"),
    F_GET_HANDLER_LIST("F_HANDLER_LIST"),
    F_GET_DIRECTOR_LIST("F_DIRECTOR_LIST"),
    F_HELP("F_HELP");

    private final String frameName;

    FrameType(String frameName){
        this.frameName = frameName;
    }

    public static FrameType GetFrameByName(String frameName) {
        for ( FrameType frameType : values() ) {
            if ( frameType.frameName.equalsIgnoreCase(frameName)) {
                return frameType;
            }
        }
        throw new IllegalArgumentException("Processor dla danej bramki nie istnieje!");
    }

}
