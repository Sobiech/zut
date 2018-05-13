package pl.zut.zjava.commons.enums;

public enum FrameType {

    F_GET_ALL("F_GET_ALL"),
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

    public String getFrameName(){
        return this.frameName;
    }
}
