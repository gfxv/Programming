package shared.serializables;

import java.io.Serializable;

public class CommandInfoObject implements Serializable {
    private static final long serialVersionUID = 3;
    private String command;
    private boolean primitiveArg;
    private boolean complexArg;

    public CommandInfoObject(String command, boolean primitiveArg, boolean complexArg) {
        this.command = command;
        this.primitiveArg = primitiveArg;
        this.complexArg = complexArg;
    }

    public String getCommand() {
        return command;
    }

    public boolean hasPrimitiveArg() {
        return primitiveArg;
    }

    public boolean hasComplexArg() {
        return complexArg;
    }
}
