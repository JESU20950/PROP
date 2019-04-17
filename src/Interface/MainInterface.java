package Interface;

import java.io.IOException;

public class MainInterface {
    private FrameProgram miframe;
    public MainInterface() throws IOException, CloneNotSupportedException {
        miframe = new FrameProgram(this);
    }

    public FrameProgram getMiframe() {
        return miframe;
    }

    public void setMiframe(FrameProgram miframe) {
        this.miframe = miframe;
    }

}
