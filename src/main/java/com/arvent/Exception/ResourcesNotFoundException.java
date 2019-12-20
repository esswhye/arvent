package com.arvent.Exception;

public class ResourcesNotFoundException extends Throwable {
    private static final long serialVersionUID = -9079454849611061074L;

    public ResourcesNotFoundException() {
        super();
    }

    public ResourcesNotFoundException(final String message) {
        super(message);
    }
}
