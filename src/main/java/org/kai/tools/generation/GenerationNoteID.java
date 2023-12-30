package org.kai.tools.generation;

import java.security.SecureRandom;

public class GenerationNoteID {
    private static final SecureRandom random = new SecureRandom();
    public int createNoteID() {
        int noteID = random.nextInt(900000);
        return noteID;
    }
}
